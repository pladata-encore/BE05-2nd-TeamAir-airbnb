package com.air.room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundException(NotFoundException e) {
        return e.getMessage() + " IS NOT FOUND";
    }

    @ExceptionHandler(DisabledArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String disabledArgumentException(DisabledArgumentException e) {
        return e.getMessage() + " WAS DISABLED";
    }
}
