package com.alok.home.commons.exception;

public class UserNotAuthorizedException extends RuntimeException {

    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
