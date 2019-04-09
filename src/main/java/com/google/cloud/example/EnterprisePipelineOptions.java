package com.google.cloud.example;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Validation;

interface EnterprisePipelineOptions extends DataflowPipelineOptions {
    @Description("Service Account")
    @Validation.Required
    @Override
    String getServiceAccount();
    void setServiceAccount(String serviceAccount);

    @Description("Use Public Ips")
    @Default.Boolean(false)
    @Override
    Boolean getUsePublicIps();
    void setUsePublicIps(Boolean usePublicIps);

    @Description("Subnetwork")
    @Validation.Required
    @Override
    String getSubnetwork();
    void setSubnetwork(String subnetwork);
}
