package grpc_sample

import com.likexx.grpc.sample.HelloReply
import com.likexx.grpc.sample.HelloRequest
import com.likexx.grpc.sample.HelloServiceGrpcKt

class HelloService : HelloServiceGrpcKt.HelloServiceCoroutineImplBase() {
    override suspend fun hello(request: HelloRequest): HelloReply {
        println("$request")
        return HelloReply.newBuilder().setMessage("${request.name}: Message from hello api").build()
    }

    override suspend fun helloTest1(request: HelloRequest): HelloReply {
        println("$request")
        return HelloReply.newBuilder().setMessage("${request.name}: Message from helloTest1").build()
    }
}