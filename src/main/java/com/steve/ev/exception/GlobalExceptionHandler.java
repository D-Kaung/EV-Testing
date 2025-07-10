package com.steve.ev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidBootNotification(InvalidInputException invalidInputException) {
        return new ResponseEntity<>(
                "{\"status\": \"Error\", \"message\": \"" + invalidInputException.getMessage() + "\"}",
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler
    public ResponseEntity<String> handleGenericException(RuntimeException exception) {
        return new ResponseEntity<>(
                "{\"status\": \"Error\", \"message\": \"An unexpected error occur: " + exception.getMessage()
                        + "\"}",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
