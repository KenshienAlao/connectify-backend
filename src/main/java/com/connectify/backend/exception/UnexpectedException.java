package com.connectify.backend.exception;

public class UnexpectedException extends RuntimeException {
    private String code;
    public UnexpectedException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
