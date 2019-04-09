package com.google.cloud.example

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.google.cloud.bigtable.grpc.{BigtableDataClient, BigtableSession}
import com.google.cloud.bigtable.config.BigtableOptions
import com.google.cloud.example.CloudQuery.{Config, Response}
import com.google.cloud.example.ServletManager.{App, start}
import com.google.cloud.example.protobuf.Metrics
import com.google.protobuf.util.JsonFormat
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import scala.util.Random

object CloudServlet extends Logging {
  val Parser: scopt.OptionParser[Config] =
    new scopt.OptionParser[Config]("CloudServlet") {
      head("CloudServlet", "0.1")

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

      opt[Int]('n',"topN")
        .action{(x, c) => c.copy(topN = x)}
        .text("topN is an Int property")

      opt[Double]('m',"minCpu")
        .action{(x, c) => c.copy(minCpu = x.toFloat)}
        .text("minCpu is a Float property")

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
        val qh: QueryHandler = new QueryHandler(config)

        val apps = Seq(
          App("/top", new MetricsServlet(qh)),
          App("/metrics", new MetricsServlet2(qh))
        )
        start(8080, apps)
      case _ =>
        logger.error(s"invalid args ${args.mkString(" ")}")
    }
  }

  class QueryHandler(config: Config) {
    private val cfg: Config = config
    private val session: BigtableSession =
      new BigtableSession(BigtableOptions.builder()
        .setProjectId(config.project)
        .setInstanceId(config.instance)
        .setUserAgent("CloudServlet")
        .build())
    private val bigtable: BigtableDataClient = session.getDataClient
    private val tableName = s"projects/${config.project}/instances/${config.instance}/tables/${config.table}"

    def query(host: String, dc: String, region: String): Seq[Response] = {
      val t = System.currentTimeMillis()/1000
      CloudQuery.run1(t, cfg.window, cfg.limit, dc, region, host, cfg.topN, cfg.minCpu, tableName, bigtable)
    }

    def query2(host: String, dc: String, region: String, limit: Long): Seq[Metrics] = {
      val t = System.currentTimeMillis()/1000
      val rows = CloudQuery.query(t0 = t-cfg.window, t1 = t, limit, dc, region, host, tableName, bigtable)
      CloudQuery.readMetrics(rows)
    }

    def warmup(): Unit = {
      val rand = new Random()
      for (_ <- 0 until 40) {
        query(host = s"h${rand.nextInt(256)}", dc = s"dc${rand.nextInt(3)}", region = s"r${rand.nextInt(4)}")
      }
    }
  }

  @WebServlet(name = "MetricsServlet2", value = Array("/metrics"))
  class MetricsServlet2(private val qh: QueryHandler) extends HttpServlet {
    private val printer = JsonFormat.printer()
      .includingDefaultValueFields()
      .omittingInsignificantWhitespace()

    override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
      response.setContentType("application/json")
      val maybeResponses = for {
        host <- Option(request.getParameter("host"))
        dc <- Option(request.getParameter("dc"))
        region <- Option(request.getParameter("region"))
        limit <- Option(request.getParameter("limit")).orElse(Option("60"))
      } yield {
        qh.query2(host, dc, region, limit.toLong)
      }
      maybeResponses match {
        case Some(responses) if responses.nonEmpty =>
            response.getWriter.print(responses.map(printer.print).mkString("[",",","]"))
        case _ =>
          response.getWriter.print("[]")
      }
    }
  }

  @WebServlet(name = "MetricsServlet", value = Array("/top"))
  class MetricsServlet(private val qh: QueryHandler) extends HttpServlet {
    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
      response.setContentType("application/json")
      val maybeResponses = for {
        host <- Option(request.getParameter("host"))
        dc <- Option(request.getParameter("dc"))
        region <- Option(request.getParameter("region"))
      } yield {
        qh.query(host, dc, region)
      }
      maybeResponses match {
        case Some(responses) if responses.nonEmpty =>
          response.getWriter.print(mapper.writeValueAsString(responses))
        case _ =>
          response.getWriter.print("[]")
      }
    }
  }
}
