package com.rainbow.base

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class RainbowBaseServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(RainbowBaseServerApplication::class.java, *args)
}
