//Generated by the protocol buffer compiler. DO NOT EDIT!
// source: api.proto

package com.likexx.grpc.sample;

@kotlin.jvm.JvmName("-initializehelloRequest")
inline fun helloRequest(block: com.likexx.grpc.sample.HelloRequestKt.Dsl.() -> kotlin.Unit): com.likexx.grpc.sample.HelloRequest =
  com.likexx.grpc.sample.HelloRequestKt.Dsl._create(com.likexx.grpc.sample.HelloRequest.newBuilder()).apply { block() }._build()
object HelloRequestKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  class Dsl private constructor(
    private val _builder: com.likexx.grpc.sample.HelloRequest.Builder
  ) {
    companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: com.likexx.grpc.sample.HelloRequest.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): com.likexx.grpc.sample.HelloRequest = _builder.build()

    /**
     * <code>string name = 1;</code>
     */
    var name: kotlin.String
      @JvmName("getName")
      get() = _builder.getName()
      @JvmName("setName")
      set(value) {
        _builder.setName(value)
      }
    /**
     * <code>string name = 1;</code>
     */
    fun clearName() {
      _builder.clearName()
    }
  }
}
@kotlin.jvm.JvmSynthetic
inline fun com.likexx.grpc.sample.HelloRequest.copy(block: com.likexx.grpc.sample.HelloRequestKt.Dsl.() -> kotlin.Unit): com.likexx.grpc.sample.HelloRequest =
  com.likexx.grpc.sample.HelloRequestKt.Dsl._create(this.toBuilder()).apply { block() }._build()
