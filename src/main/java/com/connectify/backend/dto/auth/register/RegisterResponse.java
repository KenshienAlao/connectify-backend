package com.connectify.backend.dto.auth.register;

public record RegisterResponse(
    NameResponse name,
    String email
) {
    public record NameResponse(
        String firstName,
        String lastName
    ) {
    }
}
