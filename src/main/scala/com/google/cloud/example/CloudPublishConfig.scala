package com.google.cloud.example

object CloudPublishConfig{
  case class Config(project: String = "myproject",
                    topic: String = "mytopic")

  val Parser: scopt.OptionParser[Config] =
    new scopt.OptionParser[Config]("CloudPublish") {
      head("CloudPublish", "3.x")

      opt[String]('p', "project")
        .action{(x, c) => c.copy(project = x)}
        .text("projectId is a string property")

      opt[String]('t', "topic")
        .action{(x, c) => c.copy(topic = x)}
        .text("topic is a string property")

      note("Publishes Metrics to Pubsub")

      help("help")
        .text("prints this usage text")
    }
}
