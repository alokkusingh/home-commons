package com.alok.home.commons.dto.exception;

//import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.security.InvalidParameterException;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ProblemDetail handleException(ResourceNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setType(URI.create("home-api/errors/not-found"));
        problemDetail.setProperty("errorCategory", "NotFound");
        problemDetail.setProperty("timestamp", ZonedDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(AuthenticationException.class)
    ProblemDetail handleException(AuthenticationException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
        problemDetail.setTitle("User not authorized");
        problemDetail.setType(URI.create("home-api/errors/forbidden"));
        problemDetail.setProperty("errorCategory", "Unauthenticated");
        problemDetail.setProperty("timestamp", ZonedDateTime.now());
        e.printStackTrace();
        return problemDetail;
    }

    @ExceptionHandler(GeneralSecurityException.class)
    ProblemDetail handleException(GeneralSecurityException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
        problemDetail.setTitle("Invalid Token");
        problemDetail.setType(URI.create("home-api/errors/forbidden"));
        problemDetail.setProperty("errorCategory", "Unauthenticated");
        problemDetail.setProperty("timestamp", ZonedDateTime.now());
        e.printStackTrace();
        return problemDetail;
    }

    @ExceptionHandler(NotABearerTokenException.class)
    ProblemDetail handleException(NotABearerTokenException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Not A Valid Bearer Token");
        problemDetail.setType(URI.create("home-api/errors/badrequest"));
        problemDetail.setProperty("errorCategory", "Unauthorized");
        problemDetail.setProperty("timestamp", ZonedDateTime.now());
        e.printStackTrace();
        return problemDetail;
    }
    @ExceptionHandler(InvalidTokenException.class)
    ProblemDetail handleException(InvalidTokenException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
        problemDetail.setTitle("Invalid Token");
        problemDetail.setType(URI.create("home-api/errors/invalid-token"));
        problemDetail.setProperty("errorCategory", "Unauthorized");
        problemDetail.setProperty("timestamp", ZonedDateTime.now());
        e.printStackTrace();
        return problemDetail;
    }

    @ExceptionHandler(UserNotAuthenticatedException.class)
    ProblemDetail handleException(UserNotAuthenticatedException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
        problemDetail.setTitle("User Not Authenticated");
        problemDetail.setType(URI.create("home-api/errors/invalid-token"));
        problemDetail.setProperty("errorCategory", "Unauthenticated");
        problemDetail.setProperty("timestamp", ZonedDateTime.now());
        e.printStackTrace();
        return problemDetail;
    }
    @ExceptionHandler(UserNotAuthorizedException.class)
    ProblemDetail handleException(UserNotAuthorizedException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
        problemDetail.setTitle("User Not Authorized");
        problemDetail.setType(URI.create("home-api/errors/forbidden"));
        problemDetail.setProperty("errorCategory", "Unauthorized");
        problemDetail.setProperty("timestamp", ZonedDateTime.now());
        e.printStackTrace();
        return problemDetail;
    }

    @ExceptionHandler(InvalidParameterException.class)
    ProblemDetail handleException(InvalidParameterException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Bad Request");
        problemDetail.setType(URI.create("home-api/errors/bad-request"));
        problemDetail.setProperty("errorCategory", "BadRequest");
        problemDetail.setProperty("timestamp", ZonedDateTime.now());
        e.printStackTrace();
        return problemDetail;
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    ProblemDetail handleException(ConstraintViolationException e) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
//        problemDetail.setTitle("Bad Request");
//        problemDetail.setType(URI.create("home-api/errors/bad-request"));
//        problemDetail.setProperty("errorCategory", "BadRequest");
//        problemDetail.setProperty("timestamp", ZonedDateTime.now());
//        e.printStackTrace();
//        return problemDetail;
//    }
}