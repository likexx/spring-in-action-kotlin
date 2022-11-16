package grpc_sample

import com.likexx.grpc.sample.HelloServiceGrpcKt
import com.likexx.grpc.sample.helloRequest
import org.junit.Rule
import io.grpc.testing.GrpcServerRule
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class HelloServiceTest {
    @get:Rule
    val grpcServerRule: GrpcServerRule = GrpcServerRule().directExecutor()

    @Test
    fun test1() = runBlocking {
        val service = HelloService()
        grpcServerRule.serviceRegistry.addService(service)

        val stub = HelloServiceGrpcKt.HelloServiceCoroutineStub(grpcServerRule.channel)
        assertEquals("abc: Message from hello api", stub.hello(helloRequest { name="abc" }).message)
        assertEquals("123: Message from helloTest1", stub.helloTest1(helloRequest { name="123" }).message)
    }
}