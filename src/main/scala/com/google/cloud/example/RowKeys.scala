package com.google.cloud.example

import com.google.cloud.example.protobuf.HostInfo
import com.google.protobuf.ByteString

object RowKeys {
  def byHost(host: String, dc: String, region: String, t: Long): ByteString = {
    ByteString.copyFromUtf8(s"$host#$dc#$region#$t")
  }

  def byHostInfo(hostInfo: HostInfo, t: Long): ByteString = {
    byHost(hostInfo.getHost, hostInfo.getDc, hostInfo.getCloudRegion, t)
  }
}
