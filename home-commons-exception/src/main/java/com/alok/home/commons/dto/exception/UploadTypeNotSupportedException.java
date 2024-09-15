package com.alok.home.commons.dto.exception;

public class UploadTypeNotSupportedException extends RuntimeException {
    public UploadTypeNotSupportedException(String message) {
        super(message);
    }

    public UploadTypeNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
