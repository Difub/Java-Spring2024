package com.example.Spring4.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseStatusCode;
import org.springframework.web.bind.annotation.ResponseStatusException;

public class ResourceNotFoundException extends RuntimeException implements ResponseStatusException {
    private final String message;

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @ResponseStatusException
    @ResponseStatusCode(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        return new ErrorResponseBuilder().setError(new ErrorResponse(this.message));
    }
}
