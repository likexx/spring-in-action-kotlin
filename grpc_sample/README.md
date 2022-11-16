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
   ```
   gradle init
   ```
   After the project is generated, create a folder under app/src and initialize a new .proto file:  
   ```
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
   ```
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
   ```
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
   ```
   val grpcJavaVersion by extra { "1.50.2" }
   val grpcKotlinVersion by extra { "1.3.0" }
   val protobufVersion by extra { "3.21.9" }
   val protocVersion = protobufVersion
   ```
     
   Now the dependencies:  
   ```
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


4. Create server/client code
5. Modify `build.gradle.kts` to add two tasks
6. Test