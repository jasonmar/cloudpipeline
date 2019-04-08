package com.google.cloud.example

import com.google.cloud.monitoring.v3.{MetricServiceClient, MetricServiceSettings}
import com.google.monitoring.v3.{ListTimeSeriesRequest, TimeInterval}
import com.google.protobuf.Timestamp
import scala.collection.JavaConverters.iterableAsScalaIterableConverter

object QueueDepth {
  case class Config(project: String = "myproject",
                    subscription: String = "mysubscription")

  private val Parser: scopt.OptionParser[Config] =
    new scopt.OptionParser[Config]("QueueDepth") {
      head("QueueDepth", "3.x")

      opt[String]('p', "project")
        .required()
        .action{(x, c) => c.copy(project = x)}
        .text("projectId is a string property")

      opt[String]('s', "subscription")
        .required()
        .action{(x, c) => c.copy(subscription = x)}
        .text("subscription is a string property")

      note("Queries Pubsub subscription queue depth from StackDriver Monitoring")

      help("help")
        .text("prints this usage text")
    }

  def main(args: Array[String]): Unit = {
    Parser.parse(args, Config()) match {
      case Some(config) =>
        val metrics = MetricServiceClient.create(MetricServiceSettings.newBuilder().build())
        val t1 = System.currentTimeMillis()
        val t0 = t1 - 300

        val request = ListTimeSeriesRequest
          .newBuilder()
          .setName(s"projects/${config.project}")
          .setFilter(s"""metric.type = "pubsub.googleapis.com/subscription/num_undelivered_messages" AND resource.label.subscription_id = "${config.subscription}"""")
          .setInterval(TimeInterval.newBuilder()
            .setStartTime(Timestamp.newBuilder().setSeconds(t1))
            .setEndTime(Timestamp.newBuilder().setSeconds(t0)))
          .setView(ListTimeSeriesRequest.TimeSeriesView.FULL)

        val response = metrics.listTimeSeries(request.build).iterateAll
        for (series <- response.asScala) {
          val seriesName = series.getResource.getLabelsMap.getOrDefault("subscription_id", "")
          series.getPointsList.asScala.headOption.foreach{p =>
            val count = p.getValue.getInt64Value
            System.out.println(s"$seriesName num_undelivered_messages = $count")
          }
        }
      case _ =>
        System.err.println(s"Unable to parse args '${args.mkString(" ")}'")
    }
  }
}
