package com.air.user.exception;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException() {
        super("A user with this email already exists. Please use a different email.");
    }
}
