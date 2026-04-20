package com.connectify.backend.dto.common;

public record ApiResponse<T> (
    boolean success,
    String message,
    String code,
    T data

) {
    public static <T> ApiResponse<T> ok(String message, String code, T data) {
        return new ApiResponse<>(true, message, code, data);
    }
    public static <T> ApiResponse<T> error(String message, String code, T data) {
        return new ApiResponse<>(false, message, code, data);
    }
}