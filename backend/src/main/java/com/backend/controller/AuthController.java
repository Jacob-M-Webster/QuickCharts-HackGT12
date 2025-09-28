package com.backend.controller;

import com.backend.model.User;
import com.backend.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

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

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user, HttpSession session) {
        Optional<User> dbUser = userRepository.findByUsername(user.getUsername());

        if(dbUser.isPresent() && dbUser.get().getPassword().equals(user.getPassword())) {
            session.setAttribute("user", dbUser.get());
            return ResponseEntity.ok(dbUser.get()); // return the User object
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }



    @GetMapping("/users/{id}")   // or /users/me if you implement auth
    public User getUser(@PathVariable Long id) {
    return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }   
}
