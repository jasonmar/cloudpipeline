package com.google.cloud.example

import org.slf4j.{Logger, LoggerFactory}

trait Logging {
  @transient protected val logger: Logger = LoggerFactory.getLogger(this.getClass.getSimpleName.stripSuffix("$"))
}
