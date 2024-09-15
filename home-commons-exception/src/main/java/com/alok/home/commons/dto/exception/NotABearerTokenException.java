package com.alok.home.commons.dto.exception;

public class NotABearerTokenException extends RuntimeException {

    public NotABearerTokenException(String message) {
        super(message);
    }
}
