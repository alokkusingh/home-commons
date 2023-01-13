package com.alok.home.commons.exception;

public class NotABearerTokenException extends RuntimeException {

    public NotABearerTokenException(String message) {
        super(message);
    }
}
