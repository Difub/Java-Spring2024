package com.example.Spring4.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatusException;

public class BadRequestException extends RuntimeException implements ResponseStatusException {
    private final String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

    @ResponseStatusException
    @ResponseStatusCode(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        return new ErrorResponseBuilder().setError(new ErrorResponse(this.message));
    }
}
