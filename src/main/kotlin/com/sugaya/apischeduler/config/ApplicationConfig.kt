package com.sugaya.apischeduler.config

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import com.sugaya.apischeduler.handler.ErrorHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration

import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.web.reactive.config.EnableWebFlux

@Configuration
@EnableWebFlux
@EnableReactiveMongoRepositories("com.sugaya.apischeduler.repository")
class ApplicationConfig : AbstractReactiveMongoConfiguration() {

    @Value("\${spring.data.mongodb.host}")
    private val host: String? = null

    @Value("\${spring.data.mongodb.database}")
    private val database: String? = null

    override fun reactiveMongoClient(): MongoClient {
        return MongoClients.create("mongodb://$host")
    }

    override fun getDatabaseName(): String {
        return database!!
    }

    @Bean
    fun errorHandler(): ErrorHandler {
        return ErrorHandler()
    }

//    @Bean
//    fun mainRouterFunction(apiHandler: ApiHandler, errorHandler: ErrorHandler): RouterFunction<*>? {
//        return MainRouter.doRoute(apiHandler, errorHandler)
//    }
}