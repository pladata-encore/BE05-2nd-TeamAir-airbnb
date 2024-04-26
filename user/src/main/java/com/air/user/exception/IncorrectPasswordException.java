package com.air.user.exception;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("Incorrect password. Please provide the correct current password.");
    }
}
