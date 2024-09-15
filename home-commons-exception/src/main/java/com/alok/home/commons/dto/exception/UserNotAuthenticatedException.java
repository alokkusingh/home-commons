package com.alok.home.commons.dto.exception;

public class UserNotAuthenticatedException extends RuntimeException {

    public UserNotAuthenticatedException(String message) {
        super(message);
    }
}
