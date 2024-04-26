package com.air.user.config;

import com.air.user.exception.DuplicateUserException;
import com.air.user.exception.IncorrectPasswordException;
import com.air.user.exception.LoginFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String duplicateUserExceptionHandler(DuplicateUserException e) {
        return e.getMessage();
    }
    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String loginFailedExceptionHandler(LoginFailedException e) {
        return e.getMessage();
    }
    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String incorrectPasswordExceptionHandler(IncorrectPasswordException e) {
        return e.getMessage();
    }
}
