package com.sugaya.apischeduler.handler

import com.sugaya.apischeduler.exception.PathNotFoundException
import com.sugaya.apischeduler.exception.ScheduleNotFoundException
import org.springframework.http.HttpStatus
import reactor.core.publisher.Mono

internal class ThrowableTranslator private constructor(throwable: Throwable?) {
    val httpStatus: HttpStatus
    val message: String?

    private fun getStatus(error: Throwable): HttpStatus {
        return if (error is PathNotFoundException) {
            HttpStatus.BAD_REQUEST
        } else if (error is ScheduleNotFoundException) {
            HttpStatus.NOT_FOUND
        } else {
            HttpStatus.INTERNAL_SERVER_ERROR
        }
    }

    companion object {
        fun <T : Throwable?> translate(throwable: Mono<T>): Mono<ThrowableTranslator> {
            return throwable.flatMap { error: T ->
                Mono.just(
                    ThrowableTranslator(error)
                )
            }
        }
    }

    init {
        httpStatus = getStatus(throwable!!)
        message = throwable.message
    }
}