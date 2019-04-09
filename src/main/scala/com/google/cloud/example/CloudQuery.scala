package com.google.cloud.example

import com.google.bigtable.v2.{ReadRowsRequest, Row, RowRange, RowSet}
import com.google.cloud.bigtable.config.BigtableOptions
import com.google.cloud.bigtable.grpc.{BigtableDataClient, BigtableSession}
import com.google.cloud.bigtable.grpc.scanner.ResultScanner
import com.google.cloud.example.protobuf.{Metrics, VMMetrics}
import com.google.protobuf.ByteString

import scala.collection.mutable.ArrayBuffer

object CloudQuery extends Logging {
  case class Config(project: String = "myproject",
                    instance: String = "myinstance",
                    table: String = "mytable",
                    dc: String = "dc",
                    region: String = "region",
                    host: String = "host",
                    limit: Long = 1,
                    window: Long = 60 * 60,
                    topN: Int = 3)

  val Parser: scopt.OptionParser[Config] =
    new scopt.OptionParser[Config]("CloudQuery") {
      head("CloudQuery", "0.1")

      opt[String]('p', "project")
        .required()
        .action{(x, c) => c.copy(project = x)}
        .text("projectId is a string property")

      opt[String]('i', "instance")
        .required()
        .action{(x, c) => c.copy(instance = x)}
        .text("instance is a string property")

      opt[String]('t', "table")
        .required()
        .action{(x, c) => c.copy(table = x)}
        .text("table is a string property")

      opt[String]('d',"dc")
        .required()
        .action{(x, c) => c.copy(dc = x)}
        .text("dc is a string property")

      opt[String]('r',"region")
        .required()
        .action{(x, c) => c.copy(region = x)}
        .text("region is a string property")

      opt[String]('h',"host")
        .required()
        .action{(x, c) => c.copy(host = x)}
        .text("host is a string property")

      opt[Int]('n',"topN")
        .action{(x, c) => c.copy(topN = x)}
        .text("topN is an Int property")

      opt[Long]('w',"window")
        .action{(x, c) => c.copy(window = x)}
        .text("lookback window in seconds")

      note("Queries Metrics from Bigtable")

      help("help")
        .text("prints this usage text")
    }

  def main(args: Array[String]): Unit = {
    Parser.parse(args, Config()) match {
      case Some(config) =>
        val opts = BigtableOptions.builder()
          .setProjectId(config.project)
          .setInstanceId(config.instance)
          .setUserAgent("CloudQuery")
          .build()
        val session = new BigtableSession(opts)
        val bigtable = session.getDataClient

        val tableName = s"projects/${config.project}/instances/${config.instance}/tables/${config.table}"
        val t1 = System.currentTimeMillis()/1000
        val t0 = t1 - config.window

        val rows = query(t0, t1, config.limit, config.dc, config.region, config.host, tableName, bigtable)
        val metrics = readMetrics(rows)
        processMetrics(metrics, config.topN) match {
          case Some((ts,vms)) =>
            val top = vms.map{vm =>
              val (vmId, cpu) = vm
              s"$vmId\t$cpu"
            }.mkString("\n")
            logger.info(s"top ${config.topN} for ${config.host} at t = $ts:\n"+top)
          case _ =>
            logger.info(s"no data for ${config.host}")
        }

      case _ =>
    }
  }

  def createRowKey(metric: String): ByteString = {
    val dc = metric.substring(0,6)
    val region = metric.substring(7,10)
    val host = metric.substring(11,20)
    ByteString.copyFromUtf8(s"$dc#$region#$host")
  }

  def processMetrics(metrics: Seq[Metrics], topN: Int): Option[(Long,Seq[(String, Float)])] = {
    metrics.headOption.map(findTopNVm(_, topN))
  }

  /** Example 1 - find top N VMs by CPU utilization
    *
    */
  def findTopNVm(metric: Metrics, n: Int): (Long, Seq[(String, Float)]) = {
    import scala.collection.JavaConverters.iterableAsScalaIterableConverter
    val vms = metric.getVmList.asScala.toArray
    util.Sorting.quickSort(vms)(VMUtilizationOrdering.reverse)
    val top = vms.take(n)
      .map{x => (x.getVmid, x.getCpu.getCpuDataCputimePercent)}
    (metric.getTimestamp, top)
  }

  object VMUtilizationOrdering extends Ordering[VMMetrics] {
    override def compare(x: VMMetrics, y: VMMetrics): Int = {
      if (x.getCpu.getCpuDataCputimePercent < y.getCpu.getCpuDataCputimePercent) -1
      else if (x.getCpu.getCpuDataCputimePercent > y.getCpu.getCpuDataCputimePercent) 1
      else 0
    }
  }

  def query(t0: Long, t1: Long, limit: Long, dc: String, region: String, host: String, tableName: String, client: BigtableDataClient): ResultScanner[Row] = {
    val request = ReadRowsRequest.newBuilder()
      .setTableName(tableName)
      .setRows(RowSet.newBuilder()
        .addRowRanges(RowRange.newBuilder()
          .setStartKeyOpen(RowKeys.byHost(host, dc, region, t0))
          .setEndKeyOpen(RowKeys.byHost(host, dc, region, t1))
        ))
      .setRowsLimit(limit)
      .build()
    client.readRows(request)
  }

  /** Parsing a proto message from a Bigtable Row */
  def readMetricsRow(row: Row): Metrics = {
    val cell = row
      .getFamilies(0)
      .getColumns(0)
      .getCells(0)
    Metrics.parseFrom(cell.getValue)
  }

  def readMetrics(rows: ResultScanner[Row]): IndexedSeq[Metrics] = {
    val buf = ArrayBuffer.empty[Metrics]
    var next: Option[Row] = Option(rows.next())
    while (next.isDefined) {
      buf.append(readMetricsRow(next.get))
      next = Option(rows.next())
    }
    buf.result().toIndexedSeq
  }
}
