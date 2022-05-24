package com.sugaya.apischeduler.entity;


class ErrorResponse(message: String) {

    private var error: String = message;

    fun getError(): String {
        return error;
    }
}