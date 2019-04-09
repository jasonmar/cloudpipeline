package com.google.cloud.example

import com.google.bigtable.v2.{ReadRowsRequest, Row, RowRange, RowSet}
import com.google.cloud.bigtable.config.BigtableOptions
import com.google.cloud.bigtable.grpc.{BigtableDataClient, BigtableSession}
import com.google.cloud.bigtable.grpc.scanner.ResultScanner
import com.google.cloud.example.CloudQueryConfig.Config
import com.google.cloud.example.protobuf.{Metrics, VMMetrics}
import com.google.protobuf.ByteString

import scala.collection.mutable.ArrayBuffer

object CloudQuery extends Logging {
  def query(t: Long, window: Long, limit: Long, dc: String, region: String, host: String, tableName: String, client: BigtableDataClient): ResultScanner[Row] = {
    val request = ReadRowsRequest.newBuilder()
      .setTableName(tableName)
      .setRows(RowSet.newBuilder()
        .addRowRanges(RowRange.newBuilder()
          .setStartKeyOpen(RowKeys.byHost(host, dc, region, t))
          .setEndKeyOpen(RowKeys.byHost(host, dc, region, t + window))
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
    while (rows.available() > 0) {
      buf.append(readMetricsRow(rows.next()))
    }
    buf.result().toIndexedSeq
  }

  def main(args: Array[String]): Unit = {
    CloudQueryConfig.Parser.parse(args, Config()) match {
      case Some(config) =>
        val opts = BigtableOptions.builder()
          .setProjectId(config.project)
          .setInstanceId(config.instance)
          .setUserAgent("CloudQuery")
          .build()
        val session = new BigtableSession(opts)
        val bigtable = session.getDataClient

        val tableName = s"projects/${config.project}/instances/${config.instance}/tables/${config.table}"
        val t = System.currentTimeMillis()

        val rows = query(t, config.window, config.limit, config.dc, config.region, config.host, tableName, bigtable)
        val metrics = readMetrics(rows)
        val topVms = processMetrics(metrics, config.topN)
        topVms.foreach{x =>
          x.foreach{vm =>
            val (vmid, cpu) = vm
            System.out.println(s"$vmid\t$cpu")
          }
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

  def processMetrics(metrics: Seq[Metrics], topN: Int): Option[Seq[(String, Float)]] = {
    metrics.headOption.map(findTopNVm(_, topN))
  }

  /** Example 1 - find top N VMs by CPU utilization
    *
    */
  def findTopNVm(metric: Metrics, n: Int): Seq[(String, Float)] = {
    import scala.collection.JavaConverters.iterableAsScalaIterableConverter
    val vms = metric.getVmList.asScala.toArray
    util.Sorting.quickSort(vms)(VMUtilizationOrdering)
    vms.take(n)
      .map{x => (x.getVmid, x.getCpu.getCpuDataCputimePercent)}
  }

  object VMUtilizationOrdering extends Ordering[VMMetrics] {
    override def compare(x: VMMetrics, y: VMMetrics): Int = {
      if (x.getCpu.getCpuDataCputimePercent < y.getCpu.getCpuDataCputimePercent) -1
      else if (x.getCpu.getCpuDataCputimePercent > y.getCpu.getCpuDataCputimePercent) 1
      else 0
    }
  }
}
