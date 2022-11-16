package com.likexx.grpc.sample

import com.likexx.grpc.sample.HelloServiceGrpc.getServiceDescriptor
import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

/**
 * Holder for Kotlin coroutine-based client and server APIs for grpc_sample.HelloService.
 */
public object HelloServiceGrpcKt {
  public const val SERVICE_NAME: String = HelloServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = HelloServiceGrpc.getServiceDescriptor()

  public val helloMethod: MethodDescriptor<HelloRequest, HelloReply>
    @JvmStatic
    get() = HelloServiceGrpc.getHelloMethod()

  public val helloTest1Method: MethodDescriptor<HelloRequest, HelloReply>
    @JvmStatic
    get() = HelloServiceGrpc.getHelloTest1Method()

  /**
   * A stub for issuing RPCs to a(n) grpc_sample.HelloService service as suspending coroutines.
   */
  @StubFor(HelloServiceGrpc::class)
  public class HelloServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<HelloServiceCoroutineStub>(channel, callOptions) {
    public override fun build(channel: Channel, callOptions: CallOptions): HelloServiceCoroutineStub
        = HelloServiceCoroutineStub(channel, callOptions)

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun hello(request: HelloRequest, headers: Metadata = Metadata()): HelloReply =
        unaryRpc(
      channel,
      HelloServiceGrpc.getHelloMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun helloTest1(request: HelloRequest, headers: Metadata = Metadata()): HelloReply
        = unaryRpc(
      channel,
      HelloServiceGrpc.getHelloTest1Method(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the grpc_sample.HelloService service based on Kotlin coroutines.
   */
  public abstract class HelloServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for grpc_sample.HelloService.Hello.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun hello(request: HelloRequest): HelloReply = throw
        StatusException(UNIMPLEMENTED.withDescription("Method grpc_sample.HelloService.Hello is unimplemented"))

    /**
     * Returns the response to an RPC for grpc_sample.HelloService.HelloTest1.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun helloTest1(request: HelloRequest): HelloReply = throw
        StatusException(UNIMPLEMENTED.withDescription("Method grpc_sample.HelloService.HelloTest1 is unimplemented"))

    public final override fun bindService(): ServerServiceDefinition =
        builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = HelloServiceGrpc.getHelloMethod(),
      implementation = ::hello
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = HelloServiceGrpc.getHelloTest1Method(),
      implementation = ::helloTest1
    )).build()
  }
}
