package com.google.cloud.example

object CloudQueryConfig{
  case class Config(project: String = "myproject",
                    instance: String = "myinstance",
                    table: String = "mytable",
                    dc: String = "dc",
                    region: String = "region",
                    host: String = "host",
                    limit: Long = 1,
                    window: Long = 1000 * 60 * 60,
                    topN: Int = 3)

  val Parser: scopt.OptionParser[Config] =
    new scopt.OptionParser[Config]("CloudPublish") {
      head("CloudPublish", "3.x")

      opt[String]('p', "project")
        .action{(x, c) => c.copy(project = x)}
        .text("projectId is a string property")

      opt[String]('i', "instance")
        .action{(x, c) => c.copy(instance = x)}
        .text("instance is a string property")

      opt[String]('t', "table")
        .action{(x, c) => c.copy(table = x)}
        .text("table is a string property")

      opt[String]("dc")
        .action{(x, c) => c.copy(dc = x)}
        .text("dc is a string property")

      opt[String]("region")
        .action{(x, c) => c.copy(region = x)}
        .text("region is a string property")

      opt[String]("host")
        .action{(x, c) => c.copy(host = x)}
        .text("host is a string property")

      opt[Int]("topN")
        .action{(x, c) => c.copy(topN = x)}
        .text("topN is an Int property")

      note("Queries Metrics from Bigtable")

      help("help")
        .text("prints this usage text")
    }
}
