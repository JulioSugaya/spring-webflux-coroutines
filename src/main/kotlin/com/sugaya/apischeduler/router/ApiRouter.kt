package com.sugaya.apischeduler.router

import com.sugaya.apischeduler.handler.ApiHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.util.StringUtils
import org.springframework.web.reactive.function.server.RequestPredicate
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ApiRouterConfiguration(
    private val apiHandler: ApiHandler
) {

    companion object {
        private const val PATH = "/scheduler"
        private const val ID = "/{id}"
        private const val YEAR_MONTH = "/yearmonth/{yearmonth}"
        private const val DATE_RANGE = "/date/{startdate}/{enddate}"
    }

    @Bean
    fun apiRouter() = coRouter {
        PATH.nest {
            accept(MediaType.APPLICATION_JSON)
                .nest {
                GET("", apiHandler::findAll)
                GET(YEAR_MONTH, apiHandler::findByYearMonth)
                GET(DATE_RANGE, apiHandler::findByDateRange)
                ID.nest {
                    GET("", apiHandler::findById)
                    DELETE("", apiHandler::delete)
                }
                contentType(MediaType.APPLICATION_JSON).nest {
                    POST("", apiHandler::save)
                }
            }
        }
    }
}
