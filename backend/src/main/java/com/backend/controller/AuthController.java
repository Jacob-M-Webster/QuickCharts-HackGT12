package com.backend.controller;

import com.backend.model.User;
import com.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // Store password as-is for now; teammate can encrypt before sending
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginData) {
        Optional<User> userOpt = userRepository.findByUsername(loginData.getUsername());
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginData.getPassword())) {
            return ResponseEntity.ok("Login successful!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
