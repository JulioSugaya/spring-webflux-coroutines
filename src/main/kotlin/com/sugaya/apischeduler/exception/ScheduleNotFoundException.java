package com.sugaya.apischeduler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException() {
        super("Schedule not found");
    }
}