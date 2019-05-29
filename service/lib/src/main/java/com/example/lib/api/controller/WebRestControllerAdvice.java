package com.example.lib.api.controller;

import com.example.lib.exception.ResourceNotFoundException;
import com.example.lib.exception.ResourceViolationException;
import com.example.lib.exception.TokenViolationException;
import com.example.lib.api.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class WebRestControllerAdvice {

    @ExceptionHandler(ResourceViolationException.class)
    public ResponseEntity handleResourceViolationException(ResourceViolationException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFoundException(ResourceNotFoundException e) {
        return  new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(TokenViolationException.class)
    public ResponseEntity handleTokenViolationException(TokenViolationException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler
    public ErrorResponse internalServerError(Exception _e)
    {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error");
    }

}
