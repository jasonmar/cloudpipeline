// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/instancemetric.proto

package com.google.cloud.example.protobuf;

public interface VMDiskMetricsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.protobuf.example.VMDiskMetrics)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string disk_name = 1;</code>
   */
  java.lang.String getDiskName();
  /**
   * <code>string disk_name = 1;</code>
   */
  com.google.protobuf.ByteString
      getDiskNameBytes();

  /**
   * <code>int64 write_req = 2;</code>
   */
  long getWriteReq();

  /**
   * <code>int64 read_bytes = 3;</code>
   */
  long getReadBytes();

  /**
   * <code>int64 errors = 4;</code>
   */
  long getErrors();

  /**
   * <code>int64 read_req = 5;</code>
   */
  long getReadReq();

  /**
   * <code>int64 write_bytes = 6;</code>
   */
  long getWriteBytes();
}
