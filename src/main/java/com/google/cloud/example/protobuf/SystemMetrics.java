// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/instancemetric.proto

package com.google.cloud.example.protobuf;

/**
 * <pre>
 * system
 * </pre>
 *
 * Protobuf type {@code google.protobuf.example.SystemMetrics}
 */
public  final class SystemMetrics extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:google.protobuf.example.SystemMetrics)
    SystemMetricsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SystemMetrics.newBuilder() to construct.
  private SystemMetrics(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SystemMetrics() {
    load1_ = 0F;
    load5_ = 0F;
    load15_ = 0F;
    nUsers_ = 0;
    nCpus_ = 0;
    uptimeFormat_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SystemMetrics(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 13: {

            load1_ = input.readFloat();
            break;
          }
          case 21: {

            load5_ = input.readFloat();
            break;
          }
          case 29: {

            load15_ = input.readFloat();
            break;
          }
          case 32: {

            nUsers_ = input.readInt32();
            break;
          }
          case 40: {

            nCpus_ = input.readInt32();
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();

            uptimeFormat_ = s;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.cloud.example.protobuf.MetricsProto.internal_static_google_protobuf_example_SystemMetrics_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.cloud.example.protobuf.MetricsProto.internal_static_google_protobuf_example_SystemMetrics_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.cloud.example.protobuf.SystemMetrics.class, com.google.cloud.example.protobuf.SystemMetrics.Builder.class);
  }

  public static final int LOAD1_FIELD_NUMBER = 1;
  private float load1_;
  /**
   * <code>float load1 = 1;</code>
   */
  public float getLoad1() {
    return load1_;
  }

  public static final int LOAD5_FIELD_NUMBER = 2;
  private float load5_;
  /**
   * <code>float load5 = 2;</code>
   */
  public float getLoad5() {
    return load5_;
  }

  public static final int LOAD15_FIELD_NUMBER = 3;
  private float load15_;
  /**
   * <code>float load15 = 3;</code>
   */
  public float getLoad15() {
    return load15_;
  }

  public static final int N_USERS_FIELD_NUMBER = 4;
  private int nUsers_;
  /**
   * <code>int32 n_users = 4;</code>
   */
  public int getNUsers() {
    return nUsers_;
  }

  public static final int N_CPUS_FIELD_NUMBER = 5;
  private int nCpus_;
  /**
   * <code>int32 n_cpus = 5;</code>
   */
  public int getNCpus() {
    return nCpus_;
  }

  public static final int UPTIME_FORMAT_FIELD_NUMBER = 6;
  private volatile java.lang.Object uptimeFormat_;
  /**
   * <code>string uptime_format = 6;</code>
   */
  public java.lang.String getUptimeFormat() {
    java.lang.Object ref = uptimeFormat_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      uptimeFormat_ = s;
      return s;
    }
  }
  /**
   * <code>string uptime_format = 6;</code>
   */
  public com.google.protobuf.ByteString
      getUptimeFormatBytes() {
    java.lang.Object ref = uptimeFormat_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      uptimeFormat_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (load1_ != 0F) {
      output.writeFloat(1, load1_);
    }
    if (load5_ != 0F) {
      output.writeFloat(2, load5_);
    }
    if (load15_ != 0F) {
      output.writeFloat(3, load15_);
    }
    if (nUsers_ != 0) {
      output.writeInt32(4, nUsers_);
    }
    if (nCpus_ != 0) {
      output.writeInt32(5, nCpus_);
    }
    if (!getUptimeFormatBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, uptimeFormat_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (load1_ != 0F) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(1, load1_);
    }
    if (load5_ != 0F) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(2, load5_);
    }
    if (load15_ != 0F) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(3, load15_);
    }
    if (nUsers_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, nUsers_);
    }
    if (nCpus_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, nCpus_);
    }
    if (!getUptimeFormatBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, uptimeFormat_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.google.cloud.example.protobuf.SystemMetrics)) {
      return super.equals(obj);
    }
    com.google.cloud.example.protobuf.SystemMetrics other = (com.google.cloud.example.protobuf.SystemMetrics) obj;

    boolean result = true;
    result = result && (
        java.lang.Float.floatToIntBits(getLoad1())
        == java.lang.Float.floatToIntBits(
            other.getLoad1()));
    result = result && (
        java.lang.Float.floatToIntBits(getLoad5())
        == java.lang.Float.floatToIntBits(
            other.getLoad5()));
    result = result && (
        java.lang.Float.floatToIntBits(getLoad15())
        == java.lang.Float.floatToIntBits(
            other.getLoad15()));
    result = result && (getNUsers()
        == other.getNUsers());
    result = result && (getNCpus()
        == other.getNCpus());
    result = result && getUptimeFormat()
        .equals(other.getUptimeFormat());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + LOAD1_FIELD_NUMBER;
    hash = (53 * hash) + java.lang.Float.floatToIntBits(
        getLoad1());
    hash = (37 * hash) + LOAD5_FIELD_NUMBER;
    hash = (53 * hash) + java.lang.Float.floatToIntBits(
        getLoad5());
    hash = (37 * hash) + LOAD15_FIELD_NUMBER;
    hash = (53 * hash) + java.lang.Float.floatToIntBits(
        getLoad15());
    hash = (37 * hash) + N_USERS_FIELD_NUMBER;
    hash = (53 * hash) + getNUsers();
    hash = (37 * hash) + N_CPUS_FIELD_NUMBER;
    hash = (53 * hash) + getNCpus();
    hash = (37 * hash) + UPTIME_FORMAT_FIELD_NUMBER;
    hash = (53 * hash) + getUptimeFormat().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.cloud.example.protobuf.SystemMetrics parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.cloud.example.protobuf.SystemMetrics parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.cloud.example.protobuf.SystemMetrics parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.cloud.example.protobuf.SystemMetrics parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.cloud.example.protobuf.SystemMetrics parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.cloud.example.protobuf.SystemMetrics parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.cloud.example.protobuf.SystemMetrics parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.cloud.example.protobuf.SystemMetrics parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.cloud.example.protobuf.SystemMetrics parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.google.cloud.example.protobuf.SystemMetrics parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.cloud.example.protobuf.SystemMetrics parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.cloud.example.protobuf.SystemMetrics parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.google.cloud.example.protobuf.SystemMetrics prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * system
   * </pre>
   *
   * Protobuf type {@code google.protobuf.example.SystemMetrics}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.protobuf.example.SystemMetrics)
      com.google.cloud.example.protobuf.SystemMetricsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.cloud.example.protobuf.MetricsProto.internal_static_google_protobuf_example_SystemMetrics_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.cloud.example.protobuf.MetricsProto.internal_static_google_protobuf_example_SystemMetrics_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.cloud.example.protobuf.SystemMetrics.class, com.google.cloud.example.protobuf.SystemMetrics.Builder.class);
    }

    // Construct using com.google.cloud.example.protobuf.SystemMetrics.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      load1_ = 0F;

      load5_ = 0F;

      load15_ = 0F;

      nUsers_ = 0;

      nCpus_ = 0;

      uptimeFormat_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.cloud.example.protobuf.MetricsProto.internal_static_google_protobuf_example_SystemMetrics_descriptor;
    }

    @java.lang.Override
    public com.google.cloud.example.protobuf.SystemMetrics getDefaultInstanceForType() {
      return com.google.cloud.example.protobuf.SystemMetrics.getDefaultInstance();
    }

    @java.lang.Override
    public com.google.cloud.example.protobuf.SystemMetrics build() {
      com.google.cloud.example.protobuf.SystemMetrics result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.google.cloud.example.protobuf.SystemMetrics buildPartial() {
      com.google.cloud.example.protobuf.SystemMetrics result = new com.google.cloud.example.protobuf.SystemMetrics(this);
      result.load1_ = load1_;
      result.load5_ = load5_;
      result.load15_ = load15_;
      result.nUsers_ = nUsers_;
      result.nCpus_ = nCpus_;
      result.uptimeFormat_ = uptimeFormat_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.cloud.example.protobuf.SystemMetrics) {
        return mergeFrom((com.google.cloud.example.protobuf.SystemMetrics)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.cloud.example.protobuf.SystemMetrics other) {
      if (other == com.google.cloud.example.protobuf.SystemMetrics.getDefaultInstance()) return this;
      if (other.getLoad1() != 0F) {
        setLoad1(other.getLoad1());
      }
      if (other.getLoad5() != 0F) {
        setLoad5(other.getLoad5());
      }
      if (other.getLoad15() != 0F) {
        setLoad15(other.getLoad15());
      }
      if (other.getNUsers() != 0) {
        setNUsers(other.getNUsers());
      }
      if (other.getNCpus() != 0) {
        setNCpus(other.getNCpus());
      }
      if (!other.getUptimeFormat().isEmpty()) {
        uptimeFormat_ = other.uptimeFormat_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.google.cloud.example.protobuf.SystemMetrics parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.cloud.example.protobuf.SystemMetrics) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private float load1_ ;
    /**
     * <code>float load1 = 1;</code>
     */
    public float getLoad1() {
      return load1_;
    }
    /**
     * <code>float load1 = 1;</code>
     */
    public Builder setLoad1(float value) {
      
      load1_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>float load1 = 1;</code>
     */
    public Builder clearLoad1() {
      
      load1_ = 0F;
      onChanged();
      return this;
    }

    private float load5_ ;
    /**
     * <code>float load5 = 2;</code>
     */
    public float getLoad5() {
      return load5_;
    }
    /**
     * <code>float load5 = 2;</code>
     */
    public Builder setLoad5(float value) {
      
      load5_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>float load5 = 2;</code>
     */
    public Builder clearLoad5() {
      
      load5_ = 0F;
      onChanged();
      return this;
    }

    private float load15_ ;
    /**
     * <code>float load15 = 3;</code>
     */
    public float getLoad15() {
      return load15_;
    }
    /**
     * <code>float load15 = 3;</code>
     */
    public Builder setLoad15(float value) {
      
      load15_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>float load15 = 3;</code>
     */
    public Builder clearLoad15() {
      
      load15_ = 0F;
      onChanged();
      return this;
    }

    private int nUsers_ ;
    /**
     * <code>int32 n_users = 4;</code>
     */
    public int getNUsers() {
      return nUsers_;
    }
    /**
     * <code>int32 n_users = 4;</code>
     */
    public Builder setNUsers(int value) {
      
      nUsers_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 n_users = 4;</code>
     */
    public Builder clearNUsers() {
      
      nUsers_ = 0;
      onChanged();
      return this;
    }

    private int nCpus_ ;
    /**
     * <code>int32 n_cpus = 5;</code>
     */
    public int getNCpus() {
      return nCpus_;
    }
    /**
     * <code>int32 n_cpus = 5;</code>
     */
    public Builder setNCpus(int value) {
      
      nCpus_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 n_cpus = 5;</code>
     */
    public Builder clearNCpus() {
      
      nCpus_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object uptimeFormat_ = "";
    /**
     * <code>string uptime_format = 6;</code>
     */
    public java.lang.String getUptimeFormat() {
      java.lang.Object ref = uptimeFormat_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        uptimeFormat_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string uptime_format = 6;</code>
     */
    public com.google.protobuf.ByteString
        getUptimeFormatBytes() {
      java.lang.Object ref = uptimeFormat_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uptimeFormat_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string uptime_format = 6;</code>
     */
    public Builder setUptimeFormat(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      uptimeFormat_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string uptime_format = 6;</code>
     */
    public Builder clearUptimeFormat() {
      
      uptimeFormat_ = getDefaultInstance().getUptimeFormat();
      onChanged();
      return this;
    }
    /**
     * <code>string uptime_format = 6;</code>
     */
    public Builder setUptimeFormatBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      uptimeFormat_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:google.protobuf.example.SystemMetrics)
  }

  // @@protoc_insertion_point(class_scope:google.protobuf.example.SystemMetrics)
  private static final com.google.cloud.example.protobuf.SystemMetrics DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.cloud.example.protobuf.SystemMetrics();
  }

  public static com.google.cloud.example.protobuf.SystemMetrics getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SystemMetrics>
      PARSER = new com.google.protobuf.AbstractParser<SystemMetrics>() {
    @java.lang.Override
    public SystemMetrics parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SystemMetrics(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SystemMetrics> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SystemMetrics> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.cloud.example.protobuf.SystemMetrics getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

