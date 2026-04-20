package com.connectify.backend.dto.auth.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    NameRequest name,
    BirthdayRequest birthday,
    @Size(max = 255, message = "Email must be less than 255 characters") String email,
    @Size(max = 15, message = "Contact number must be less than 15 characters") String contactNumber,
    @NotBlank(message = "Password is required") @Size(max = 255, message = "Password must be less than 255 characters") String password,
    @NotBlank(message = "Gender is required") String gender

) {
    public record NameRequest(
       @NotBlank(message = "First name is required") @Size(max = 50, message = "First name must be less than 50 characters") String firstName,
       @NotBlank(message = "Last name is required") @Size(max = 50, message = "Last name must be less than 50 characters") String lastName
    ) {}

    public record BirthdayRequest(
        @NotBlank(message = "Month is required") @Size(max = 10, message = "Month must be less than 10 characters") String month,
        @NotBlank(message = "Day is required") @Size(max = 2, message = "Day must be less than 2 characters") String day,
        @NotBlank(message = "Year is required") @Size(max = 4, message = "Year must be less than 4 characters") String year
    ) {}
}
