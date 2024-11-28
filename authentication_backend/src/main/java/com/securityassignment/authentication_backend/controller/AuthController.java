package com.securityassignment.authentication_backend.controller;

import com.securityassignment.authentication_backend.dto.LoginRequest;
import com.securityassignment.authentication_backend.dto.RegisterRequest;
import com.securityassignment.authentication_backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")  // Ensure this matches your security config
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        String token = authService.registerUser(
            registerRequest.getUsername(), 
            registerRequest.getEmail(), 
            registerRequest.getPassword()
        );
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        String token = authService.authenticateUser(
            loginRequest.getUsername(), 
            loginRequest.getPassword()
        );
        return ResponseEntity.ok(token);
    }
}