package com.sugaya.apischeduler.exception;

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class PathNotFoundException(message: String?) : Exception(message) {

//    constructor(message: String): super(message)
}
