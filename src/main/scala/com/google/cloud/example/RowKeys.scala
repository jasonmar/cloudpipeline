package com.google.cloud.example

import com.google.protobuf.ByteString

object RowKeys {
  def byHost(host: String, dc: String, region: String, t: Long): ByteString = {
    ByteString.copyFromUtf8(s"$host#$dc#$region#$t")
  }
}
