package com.example.democloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class DemoCloudApplication

fun main(args: Array<String>) {
    runApplication<DemoCloudApplication>(*args)
}
