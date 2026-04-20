package com.connectify.backend.dto.auth.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @NotBlank(message = "Email is required") @Size(max = 255, message = "Email must be less than 255 characters") String email,
    @NotBlank(message = "Password is required") @Size(max = 255, message = "Password must be less than 255 characters") String password
) {
}
