package com.google.cloud.example

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.google.cloud.bigtable.grpc.{BigtableDataClient, BigtableSession}
import com.google.cloud.bigtable.config.BigtableOptions
import com.google.cloud.example.CloudQuery.{Config, Response, VMCpu, run1}
import com.google.cloud.example.ServletManager.{App, start}
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

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
        val apps = Seq(
          App("/top", new MetricsServlet(config))
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
      run1(t, cfg.window, cfg.limit, dc, region, host, cfg.topN, cfg.minCpu, tableName, bigtable)
    }
  }

  @WebServlet(name = "MetricsServlet", value = Array("/top"))
  class MetricsServlet(config: Config) extends HttpServlet {
    private val qh: QueryHandler = new QueryHandler(config)
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
