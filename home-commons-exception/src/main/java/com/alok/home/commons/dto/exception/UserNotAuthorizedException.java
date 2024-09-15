package com.alok.home.commons.dto.exception;

public class UserNotAuthorizedException extends RuntimeException {

    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
