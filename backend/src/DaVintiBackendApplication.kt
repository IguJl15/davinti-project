package com.davintiproject.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class DaVintiBackendApplication

fun main(args: Array<String>) {
    runApplication<DaVintiBackendApplication>(*args)
}
