package com.connectify.backend.dto.user;

public record UserResponse(
    Integer id,
    String email,
    String firstName,
    String lastName,
    String avatar
) {
}
