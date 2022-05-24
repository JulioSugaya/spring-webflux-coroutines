package com.sugaya.apischeduler.handler

import com.sugaya.apischeduler.entity.ErrorResponse
import com.sugaya.apischeduler.exception.PathNotFoundException
import com.sugaya.apischeduler.exception.ScheduleNotFoundException
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait
import reactor.core.publisher.Mono

class ErrorHandler {
    suspend fun notFound(request: ServerRequest?): ServerResponse {
        return ServerResponse.notFound().buildAndAwait()
    }

    fun badRequest(request: ServerRequest?): Mono<ServerResponse?> {
        return Mono.just(PathNotFoundException(BAD_REQUEST)).transform(this::getResponse)
    }

    fun throwableError(error: Throwable): Mono<ServerResponse?> {
        return Mono.just(error).transform(this::getResponse)
    }

    private fun <T : Throwable?> getResponse(monoError: Mono<T>): Mono<ServerResponse?> {
        return monoError.transform(ThrowableTranslator::translate)
            .flatMap { translation: ThrowableTranslator ->
                ServerResponse
                    .status(translation.httpStatus)
                    .body(Mono.just(ErrorResponse(translation.message!!)), ErrorResponse::class.java)
            }
    }

    companion object {
        private const val BAD_REQUEST = "Bad Request"
    }
}