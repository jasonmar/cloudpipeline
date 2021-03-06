// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/instancemetric.proto

package com.google.cloud.example.protobuf;

/**
 * <pre>
 * mem_vm,
 * </pre>
 *
 * Protobuf type {@code google.protobuf.example.VMMemMetrics}
 */
public  final class VMMemMetrics extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:google.protobuf.example.VMMemMetrics)
    VMMemMetricsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use VMMemMetrics.newBuilder() to construct.
  private VMMemMetrics(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private VMMemMetrics() {
    totalMemory_ = 0L;
    freeMemory_ = 0L;
    memoryUtil_ = 0F;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private VMMemMetrics(
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
          case 8: {

            totalMemory_ = input.readInt64();
            break;
          }
          case 16: {

            freeMemory_ = input.readInt64();
            break;
          }
          case 29: {

            memoryUtil_ = input.readFloat();
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
    return com.google.cloud.example.protobuf.MetricsProto.internal_static_google_protobuf_example_VMMemMetrics_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.cloud.example.protobuf.MetricsProto.internal_static_google_protobuf_example_VMMemMetrics_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.cloud.example.protobuf.VMMemMetrics.class, com.google.cloud.example.protobuf.VMMemMetrics.Builder.class);
  }

  public static final int TOTAL_MEMORY_FIELD_NUMBER = 1;
  private long totalMemory_;
  /**
   * <code>int64 total_memory = 1;</code>
   */
  public long getTotalMemory() {
    return totalMemory_;
  }

  public static final int FREE_MEMORY_FIELD_NUMBER = 2;
  private long freeMemory_;
  /**
   * <code>int64 free_memory = 2;</code>
   */
  public long getFreeMemory() {
    return freeMemory_;
  }

  public static final int MEMORY_UTIL_FIELD_NUMBER = 3;
  private float memoryUtil_;
  /**
   * <code>float memory_util = 3;</code>
   */
  public float getMemoryUtil() {
    return memoryUtil_;
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
    if (totalMemory_ != 0L) {
      output.writeInt64(1, totalMemory_);
    }
    if (freeMemory_ != 0L) {
      output.writeInt64(2, freeMemory_);
    }
    if (memoryUtil_ != 0F) {
      output.writeFloat(3, memoryUtil_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (totalMemory_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, totalMemory_);
    }
    if (freeMemory_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, freeMemory_);
    }
    if (memoryUtil_ != 0F) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(3, memoryUtil_);
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
    if (!(obj instanceof com.google.cloud.example.protobuf.VMMemMetrics)) {
      return super.equals(obj);
    }
    com.google.cloud.example.protobuf.VMMemMetrics other = (com.google.cloud.example.protobuf.VMMemMetrics) obj;

    boolean result = true;
    result = result && (getTotalMemory()
        == other.getTotalMemory());
    result = result && (getFreeMemory()
        == other.getFreeMemory());
    result = result && (
        java.lang.Float.floatToIntBits(getMemoryUtil())
        == java.lang.Float.floatToIntBits(
            other.getMemoryUtil()));
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
    hash = (37 * hash) + TOTAL_MEMORY_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTotalMemory());
    hash = (37 * hash) + FREE_MEMORY_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFreeMemory());
    hash = (37 * hash) + MEMORY_UTIL_FIELD_NUMBER;
    hash = (53 * hash) + java.lang.Float.floatToIntBits(
        getMemoryUtil());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.cloud.example.protobuf.VMMemMetrics parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.cloud.example.protobuf.VMMemMetrics parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.cloud.example.protobuf.VMMemMetrics parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.cloud.example.protobuf.VMMemMetrics parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.cloud.example.protobuf.VMMemMetrics parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.cloud.example.protobuf.VMMemMetrics parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.cloud.example.protobuf.VMMemMetrics parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.cloud.example.protobuf.VMMemMetrics parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.cloud.example.protobuf.VMMemMetrics parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.google.cloud.example.protobuf.VMMemMetrics parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.cloud.example.protobuf.VMMemMetrics parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.cloud.example.protobuf.VMMemMetrics parseFrom(
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
  public static Builder newBuilder(com.google.cloud.example.protobuf.VMMemMetrics prototype) {
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
   * mem_vm,
   * </pre>
   *
   * Protobuf type {@code google.protobuf.example.VMMemMetrics}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.protobuf.example.VMMemMetrics)
      com.google.cloud.example.protobuf.VMMemMetricsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.cloud.example.protobuf.MetricsProto.internal_static_google_protobuf_example_VMMemMetrics_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.cloud.example.protobuf.MetricsProto.internal_static_google_protobuf_example_VMMemMetrics_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.cloud.example.protobuf.VMMemMetrics.class, com.google.cloud.example.protobuf.VMMemMetrics.Builder.class);
    }

    // Construct using com.google.cloud.example.protobuf.VMMemMetrics.newBuilder()
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
      totalMemory_ = 0L;

      freeMemory_ = 0L;

      memoryUtil_ = 0F;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.cloud.example.protobuf.MetricsProto.internal_static_google_protobuf_example_VMMemMetrics_descriptor;
    }

    @java.lang.Override
    public com.google.cloud.example.protobuf.VMMemMetrics getDefaultInstanceForType() {
      return com.google.cloud.example.protobuf.VMMemMetrics.getDefaultInstance();
    }

    @java.lang.Override
    public com.google.cloud.example.protobuf.VMMemMetrics build() {
      com.google.cloud.example.protobuf.VMMemMetrics result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.google.cloud.example.protobuf.VMMemMetrics buildPartial() {
      com.google.cloud.example.protobuf.VMMemMetrics result = new com.google.cloud.example.protobuf.VMMemMetrics(this);
      result.totalMemory_ = totalMemory_;
      result.freeMemory_ = freeMemory_;
      result.memoryUtil_ = memoryUtil_;
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
      if (other instanceof com.google.cloud.example.protobuf.VMMemMetrics) {
        return mergeFrom((com.google.cloud.example.protobuf.VMMemMetrics)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.cloud.example.protobuf.VMMemMetrics other) {
      if (other == com.google.cloud.example.protobuf.VMMemMetrics.getDefaultInstance()) return this;
      if (other.getTotalMemory() != 0L) {
        setTotalMemory(other.getTotalMemory());
      }
      if (other.getFreeMemory() != 0L) {
        setFreeMemory(other.getFreeMemory());
      }
      if (other.getMemoryUtil() != 0F) {
        setMemoryUtil(other.getMemoryUtil());
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
      com.google.cloud.example.protobuf.VMMemMetrics parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.cloud.example.protobuf.VMMemMetrics) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long totalMemory_ ;
    /**
     * <code>int64 total_memory = 1;</code>
     */
    public long getTotalMemory() {
      return totalMemory_;
    }
    /**
     * <code>int64 total_memory = 1;</code>
     */
    public Builder setTotalMemory(long value) {
      
      totalMemory_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 total_memory = 1;</code>
     */
    public Builder clearTotalMemory() {
      
      totalMemory_ = 0L;
      onChanged();
      return this;
    }

    private long freeMemory_ ;
    /**
     * <code>int64 free_memory = 2;</code>
     */
    public long getFreeMemory() {
      return freeMemory_;
    }
    /**
     * <code>int64 free_memory = 2;</code>
     */
    public Builder setFreeMemory(long value) {
      
      freeMemory_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 free_memory = 2;</code>
     */
    public Builder clearFreeMemory() {
      
      freeMemory_ = 0L;
      onChanged();
      return this;
    }

    private float memoryUtil_ ;
    /**
     * <code>float memory_util = 3;</code>
     */
    public float getMemoryUtil() {
      return memoryUtil_;
    }
    /**
     * <code>float memory_util = 3;</code>
     */
    public Builder setMemoryUtil(float value) {
      
      memoryUtil_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>float memory_util = 3;</code>
     */
    public Builder clearMemoryUtil() {
      
      memoryUtil_ = 0F;
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


    // @@protoc_insertion_point(builder_scope:google.protobuf.example.VMMemMetrics)
  }

  // @@protoc_insertion_point(class_scope:google.protobuf.example.VMMemMetrics)
  private static final com.google.cloud.example.protobuf.VMMemMetrics DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.cloud.example.protobuf.VMMemMetrics();
  }

  public static com.google.cloud.example.protobuf.VMMemMetrics getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<VMMemMetrics>
      PARSER = new com.google.protobuf.AbstractParser<VMMemMetrics>() {
    @java.lang.Override
    public VMMemMetrics parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new VMMemMetrics(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<VMMemMetrics> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<VMMemMetrics> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.cloud.example.protobuf.VMMemMetrics getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

