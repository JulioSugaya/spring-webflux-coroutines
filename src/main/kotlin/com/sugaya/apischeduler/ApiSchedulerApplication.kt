package com.sugaya.apischeduler

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration
class ApiScheduleApplication

fun main(args: Array<String>) {
	runApplication<ApiScheduleApplication>(*args)
}
