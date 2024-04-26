package com.air.user.exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("Login failed. Please check your username and password again.");
    }
}
