package grpc_sample.client

import grpc_sample.HelloClient
import io.grpc.ManagedChannelBuilder

suspend fun main() {
    val port = 8181
    val channel = ManagedChannelBuilder
        .forAddress("localhost", port)
        .usePlaintext()
        .build()

    val client = HelloClient(channel)
    client.sendHello("testuser")
    client.sendHelloTest("testuser #2")
}
