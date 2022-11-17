# gRPC service with Kotlin from scratch - A working Example

Let's build a gRPC service with kotlin/gradle from scratch. The [official grpc-kotlin sample](https://github.com/grpc/grpc-kotlin/tree/master/examples)
is too much for a start project, and actually missing some important information (what should be included in gradle file). And most online documents 
about grpc in gradle are for Java, not for Kotlin (which has a different DSL than gradle's original Groovy lanuage, and it requires some additional dependencies)

In this example, we are going to accomplish the following goals:  
- create a kotlin project with protobuf .proto file
- modify `build.gradle.kts` file and MAKE it work for **generating grpc stub code** (this is the most critical part)  
- Create grpc server/client class and related caller functions
- modify `build.gradle.kts` again to create 2 task: `runServer` and `runClient`
- Test it out!

(I'm assuming you already know protocol buffers and grpc. You just want to know how to make them work in Kotlin/Gradle)  

1. Create a kotlin project with .proto file
   ```bash
   gradle init
   ```
   After the project is generated, create a folder under app/src and initialize a new .proto file:  
   ```bash
   mkdir -p ./app/src/proto
   touch ./app/src/proto/app.proto
   ```
   We'll just use some popular sample protobuf definitions here.

2. Modify `build.gradle.kts` to enable support for Protocol Buffers and gRPC
   Now it's the most challenging part: generate stub code. You should be able to generate grpc stub code after running `./gradlew build`. How to make this happen?  
   In short, you should add a "protobuf" section in gradle build file, and define the actions.  
   Different than most online documents for Java gRPC project, you need additional kotlin dependencies to generate correct stub code. And you need to make sure the
   dependency versions match, otherwise the stub code could be messed up.  

   First, import the protobuf plugin for gradle (for the latest version, checkout 
   [https://plugins.gradle.org/plugin/com.google.protobuf](https://plugins.gradle.org/plugin/com.google.protobuf)):  
   ```kotlin
    import com.google.protobuf.gradle.*;
    
    buildscript {
        dependencies {
           classpath("com.google.protobuf:protobuf-gradle-plugin:0.9.1")
        }
    }
    
    plugins {
        kotlin("jvm") version "1.6.21"
        id("com.google.protobuf") version "0.9.1"
        id("java")
        // Apply the application plugin to add support for building a CLI application in Java.
        application
    }
    
    apply(plugin = "com.google.protobuf")
   ```
   
   Then we add some compilation configurations for java and kotlin:  
   ```kotlin
   java {
       toolchain.languageVersion.set(JavaLanguageVersion.of(11))
   }
   
   tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
       kotlinOptions {
           freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
       }
   }
   ```
   The option for Java is specifying the java version, otherwise the build will probably fail.
   The option for KotlinCompile is to stop the warning when creating annotations in some stub code.  

   We also define the shared version for protobuf and grpc:
   ```kotlin
   val grpcJavaVersion by extra { "1.50.2" }
   val grpcKotlinVersion by extra { "1.3.0" }
   val protobufVersion by extra { "3.21.9" }
   val protocVersion = protobufVersion
   ```
     
   Now the dependencies:  
   ```kotlin
    implementation("io.grpc:grpc-stub:${grpcJavaVersion}")
    implementation("io.grpc:grpc-kotlin-stub:${grpcKotlinVersion}")
    implementation("io.grpc:grpc-protobuf:${grpcJavaVersion}")

    implementation("com.google.protobuf:protobuf-java:${protobufVersion}")
    implementation("com.google.protobuf:protobuf-java-util:${protobufVersion}")
    implementation("com.google.protobuf:protobuf-kotlin:${protobufVersion}")

    testImplementation(kotlin("test-junit"))
    testImplementation("io.grpc:grpc-testing:$grpcJavaVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
   ```
     
   Gradle will look for .proto files by default in resource folder, but you can define your proto file in a specific path like below:  
   ```
   sourceSets {
    main {
        proto {
            srcDirs("src/proto")
        }
    }
   }
   ```
     
   Finally we can define the `protobuf` section in gradle:  
   ```kotlin
   protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${protocVersion}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcJavaVersion}"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${grpcKotlinVersion}:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
    generatedFilesBaseDir = "$projectDir/src/generated"
   }
   ```
   In above configuration for `protobuf`, you need to define `protoc`, `grpc` plugin (actually the `protoc-gen-grpc-java`), 
   and `grpckt`(`protoc-gen-grpc-kotlin`). 
   For `protobuf` and `grpc` plugin, they are the same configuration as in many online documents for grpc-java setup.  
   For Kotlin specific, `protoc-gen-grpc-kotlin` is a jar file, not executable, so we need to add `@jar` at the end. Also remembering the `protoc-gen-grpc-kotlin` 
   plugin is depending on the `protoc-gen-grpc-java` plugin, so we must include both of them.  
   
   in `generateProtoTasks`, we iterate .proto file and execute the tools defined in `grpc` `grpckt`, and we also execute a built-in plugin `id("kotlin")`. Honestly 
   speaking, I'm not sure what the `id("kotlin")` does here, but if we don't add it, we'll have `Unresolved reference` problem when creating stub code 
   (it took my couple hours to figure this out).

   `generatedFilesBaseDir` is optional. The generated stub code will go to the `build` folder by default, but I prefer to put them in a specific folder to see what is  
   exactly going on.

   Now if you run `./gradlew build`, you should see the stub code created under `app/src/generated`, and there should be 4 sub folders:
   - grpc (convert protobuf service definition to java)
   - grpckt (convert service java code to kotlin --- actually wrapping with kotlin)
   - java (convert protobuf message definition to java code)
   - kotlin (convert the message java code to kotlin)

4. Create server/client code
   There is nothing special about the actual code. just check out 
   - `app/src/main/kotlin/grpc_sample/HelloService`
   - `app/src/main/kotlin/grpc_sample/HelloServiceServer`
   - `app/src/main/kotlin/grpc_sample/HelloClient`  
     

4. Modify `build.gradle.kts` to add two tasks
   We want using gradle to run two different task for local test:  
   `./gradlew runServer`  (Start grpc server)
   `./gradlew runClient`  (run a simple client)

   This requires we have two different classes with different `main` function. Remember we are using Kotlin, not Java. So we need to create 2 different packages,
   and each with a `main` function:
     
   ```kotlin
   package grpc_sample.server

   import grpc_sample.HelloServiceServer
   
   fun main() {
       val server = HelloServiceServer(8181)
       server.start()
       server.blockUntilShutdown()
   }
   ```
      
   ```kotlin
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
   ```
   
   And you also need to define the tasks in gradle file:  
   ```kotlin
   application {
    // Define the main class for the application.
    mainClass.set("grpc_sample.server.AppKt")
   }
   
   task("runServer", JavaExec::class) {
      group = "Execution"
      description = "Run the main class for Server"
      classpath = sourceSets["main"].runtimeClasspath
      mainClass.set("grpc_sample.server.AppKt")
   }
   
   task("runClient", JavaExec::class) {
      group = "Execution"
      description = "Run the main class for Server"
      classpath = sourceSets["main"].runtimeClasspath
      mainClass.set("grpc_sample.client.AppKt")
   }
   ```
   the `classpath` must be defined, otherwise there will be error complaining not being able to find classes.

5. Test
   Now you can use `./gradlew runServer` and `./gradlew runClient` to test.  
   for unit test, you can refer to `app/src/test/kotlin/grpc_sample/HelloServiceTest`. Since grpc service api is a `suspend` function, 
   you need to run the test function in a `runBlocking` block. In order to do this, we need to add `testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")` in the gradle file.

