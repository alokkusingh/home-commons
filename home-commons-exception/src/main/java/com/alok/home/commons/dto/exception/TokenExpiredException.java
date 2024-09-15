package com.alok.home.commons.dto.exception;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String message) {
        super(message);
    }
}
