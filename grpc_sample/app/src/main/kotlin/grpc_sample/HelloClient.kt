package grpc_sample

import com.likexx.grpc.sample.HelloRequest
import com.likexx.grpc.sample.HelloServiceGrpcKt
import io.grpc.ManagedChannel
import java.io.Closeable
import java.util.concurrent.TimeUnit

class HelloClient (
    private val channel: ManagedChannel
) : Closeable {
    private val stub: HelloServiceGrpcKt.HelloServiceCoroutineStub = HelloServiceGrpcKt.HelloServiceCoroutineStub(channel)

    suspend fun sendHello(name: String) {
        val request = HelloRequest.newBuilder().setName(name).build()
        val response = stub.hello(request)
        println("received response $response")
    }

    suspend fun sendHelloTest(name: String) {
        val request = HelloRequest.newBuilder().setName("for test: $name").build()
        val response = stub.helloTest1(request)
        println("received from helloTest1: $response")
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}