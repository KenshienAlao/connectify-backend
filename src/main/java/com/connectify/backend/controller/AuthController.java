package com.connectify.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connectify.backend.dto.auth.register.RegisterRequest;
import com.connectify.backend.dto.auth.register.RegisterResponse;
import com.connectify.backend.dto.common.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;



import com.connectify.backend.services.auth.RegisterServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private RegisterServices registerServices;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/register")
    public ResponseEntity <ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest entity) {
        RegisterResponse data = registerServices.registerUser(entity);
        return ResponseEntity.ok(ApiResponse.ok("User registered successfully", "REGISTER_SUCCESS", data));
    }
    
    
}
