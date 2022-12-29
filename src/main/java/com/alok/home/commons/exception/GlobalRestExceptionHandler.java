package com.alok.home.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ProblemDetail handleResourceNotFoundException(ResourceNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setType(URI.create("home-api/errors/not-found"));
        problemDetail.setProperty("errorCategory", "NotFound");
        problemDetail.setProperty("timestamp", ZonedDateTime.now());
        return problemDetail;
    }
}