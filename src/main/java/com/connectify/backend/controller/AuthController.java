package com.connectify.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connectify.backend.dto.auth.login.LoginRequest;
import com.connectify.backend.dto.auth.login.LoginResponse;
import com.connectify.backend.dto.auth.register.RegisterRequest;
import com.connectify.backend.dto.auth.register.RegisterResponse;
import com.connectify.backend.dto.common.ApiResponse;
import com.connectify.backend.services.auth.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;



import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/register")
    public ResponseEntity <ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest entity) {
        RegisterResponse data = authService.registerUser(entity);
        return ResponseEntity.ok(ApiResponse.ok("User registered successfully", "REGISTER_SUCCESS", data));
    }

    @PostMapping("/login")
    public ResponseEntity <ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest entity) {
        LoginResponse data = authService.loginUser(entity);
        return ResponseEntity.ok(ApiResponse.ok("User logged in successfully", "LOGIN_SUCCESS", data));
    }
    
    
    
}
