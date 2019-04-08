/*
 * Copyright 2019 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.example

import java.nio.charset.StandardCharsets
import java.util.Collections

import com.google.bigtable.v2.Mutation
import com.google.cloud.bigtable.config.{BigtableOptions, BulkOptions}
import com.google.cloud.example.protobuf.{HostInfo, Metrics}
import com.google.protobuf.ByteString
import org.apache.beam.sdk.Pipeline
import org.apache.beam.sdk.io.gcp.bigtable.BigtableIO
import org.apache.beam.sdk.io.gcp.pubsub.{PubsubIO, PubsubMessage}
import org.apache.beam.sdk.options.PipelineOptionsFactory
import org.apache.beam.sdk.transforms.DoFn.ProcessElement
import org.apache.beam.sdk.transforms.{DoFn, ParDo, SerializableFunction}
import org.apache.beam.sdk.values.KV
import org.joda.time.Duration
import org.slf4j.LoggerFactory

object CloudPipeline {
  private val logger = LoggerFactory.getLogger(this.getClass)

  def buildRowKey(h: HostInfo, ts: Long): ByteString = {
    val k = s"${h.getDc}#${h.getCloudRegion}#${h.getHost}#$ts"
    ByteString.copyFrom(k, StandardCharsets.UTF_8)
  }

  def main(args: Array[String]): Unit = {
    val options = PipelineOptionsFactory.fromArgs(args: _*).as(classOf[CloudPipelineOptions])

    val bigtableConfigurator = new SerializableFunction[BigtableOptions.Builder, BigtableOptions.Builder] {
      override def apply(input: BigtableOptions.Builder): BigtableOptions.Builder = {
        input.setUserAgent("CloudPipeline")
          .setBulkOptions(BulkOptions.builder().enableBulkMutationThrottling().build())
      }
    }

    val cq = ByteString.copyFrom(options.getColumn, StandardCharsets.UTF_8)
    logger.info("Creating pipeline")
    val p = Pipeline.create(options)

    val subscription = s"projects/${options.getProject}/subscription/${options.getSubscription}"

    p.begin()
      .apply(PubsubIO.readMessagesWithAttributes()
        .fromSubscription(subscription))
      .apply(ParDo.of(new DoFn[PubsubMessage,Metrics] {
        @ProcessElement
        private[examples] def process(c: ProcessContext): Unit = {
          val metrics = Metrics.parseFrom(c.element().getPayload)
          c.output(metrics)
        }
      }))
      .apply(ParDo.of(new DoFn[Metrics,KV[ByteString,java.lang.Iterable[Mutation]]] {
        @ProcessElement
        private[examples] def process(c: ProcessContext): Unit = {
          val metrics: Metrics = c.element()
          val rowKey = buildRowKey(metrics.getHostInfo, metrics.getTimestamp/1000L)
          val m = Mutation.newBuilder().setSetCell(
            Mutation.SetCell.newBuilder()
              .setValue(metrics.toByteString)
              .setFamilyName(options.getColumnFamily)
              .setColumnQualifier(cq)
              .setTimestampMicros(-1))
          c.output(KV.of(rowKey, Collections.singleton(m.build())))
        }
      }))
      .apply("Write", BigtableIO.write()
        .withProjectId(options.getProject)
        .withInstanceId(options.getInstanceId)
        .withBigtableOptionsConfigurator(bigtableConfigurator)
        .withTableId(options.getTableId))

    logger.info("Running pipeline")
    val results = p.run()
    results.waitUntilFinish(Duration.standardSeconds(120))
    logger.info("Pipeline finished")
  }
}
