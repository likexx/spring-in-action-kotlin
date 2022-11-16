package grpc_sample

import io.grpc.Server
import io.grpc.ServerBuilder

class HelloServiceServer(
    private val port: Int
    ) {
    val server: Server = ServerBuilder
        .forPort(port)
        .addService(HelloService())
        .build()

    fun start() {
        server.start()
        println("server started at $port")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("*** shutdown grpc server")
                this.stop()
                println("server is shut down")
            }
        )
    }

    private fun stop() {
        println("*** stop server")
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }
}