package com.spring.lcwd.user.service.exception;

public class UserException extends RuntimeException {

    public UserException() {
        super("Resource not found on server");
    }

    public UserException(String message) {
        super(message);
    }
}