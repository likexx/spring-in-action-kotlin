package grpc_sample.server

import grpc_sample.HelloServiceServer

fun main() {
    val server = HelloServiceServer(8181)
    server.start()
    server.blockUntilShutdown()
}