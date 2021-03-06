// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/instancemetric.proto

package com.google.cloud.example.protobuf;

public interface DiskIOMetricsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.protobuf.example.DiskIOMetrics)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>int64 reads = 2;</code>
   */
  long getReads();

  /**
   * <code>int64 writes = 3;</code>
   */
  long getWrites();

  /**
   * <code>int64 write_bytes = 4;</code>
   */
  long getWriteBytes();

  /**
   * <code>int64 read_time = 5;</code>
   */
  long getReadTime();

  /**
   * <code>int64 weighted_io_time = 6;</code>
   */
  long getWeightedIoTime();

  /**
   * <code>int64 read_bytes = 7;</code>
   */
  long getReadBytes();

  /**
   * <code>int64 write_time = 8;</code>
   */
  long getWriteTime();

  /**
   * <code>int64 io_time = 9;</code>
   */
  long getIoTime();

  /**
   * <code>int64 iops_in_progress = 10;</code>
   */
  long getIopsInProgress();
}
