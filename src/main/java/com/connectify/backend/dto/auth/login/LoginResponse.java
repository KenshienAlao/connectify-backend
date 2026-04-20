package com.connectify.backend.dto.auth.login;

import com.connectify.backend.dto.user.UserResponse;

public record LoginResponse(
    Integer id,
    String accessToken,
    String refreshToken,
    UserResponse user
) {}
