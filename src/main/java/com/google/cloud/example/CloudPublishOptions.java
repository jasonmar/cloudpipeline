package com.google.cloud.example;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Validation;

interface CloudPublishOptions extends DataflowPipelineOptions {
    @Description("Topic name")
    @Validation.Required
    String getTopic();
    void setTopic(String topic);
}
