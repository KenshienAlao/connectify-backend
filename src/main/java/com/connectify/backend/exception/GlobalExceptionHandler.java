package com.connectify.backend.exception;

import com.connectify.backend.dto.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExistException.class)
    public ResponseEntity<ApiResponse<Object>> existException(ExistException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage(), ex.getCode(), null));
    }

    @ExceptionHandler(UnexpectedException.class)
    public ResponseEntity<ApiResponse<Object>> unexpectedException(UnexpectedException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage(), ex.getCode(), null));
    }
}
