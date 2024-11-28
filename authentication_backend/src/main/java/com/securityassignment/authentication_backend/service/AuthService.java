package com.securityassignment.authentication_backend.service;

import com.securityassignment.authentication_backend.model.Role;
import com.securityassignment.authentication_backend.model.User;
import com.securityassignment.authentication_backend.repository.RoleRepository;
import com.securityassignment.authentication_backend.repository.UserRepository;
import com.securityassignment.authentication_backend.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticateUser(String username, String password) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            // Find the user entity
            User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

            // Print username and roles to the console
            System.out.println("Authenticated User: " + user.getUsername());
            System.out.println("Assigned Roles: " + user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.joining(", ")));

            // Generate and return JWT token
            return jwtTokenProvider.generateToken(user);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }
    }

    public String registerUser(String username, String email, String password) {
        // Check if username already exists
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username is already taken!");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email is already registered!");
        }

        // Create new user
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setCreatedAt(LocalDateTime.now());
        user.setEnabled(true);

        // Get default role (ROLE_USER)
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            throw new RuntimeException("Default role not found.");
        }

        // Set user role
        user.setRoles(Collections.singleton(userRole));

        // Save user
        userRepository.save(user);

        // Generate and return JWT token
        return jwtTokenProvider.generateToken(user);
    }
}