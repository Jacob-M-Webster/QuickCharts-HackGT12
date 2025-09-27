package com.backend.controller;

import com.backend.model.User;
import com.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;

    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepo.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        Optional<User> user = userRepo.findByUsername(loginRequest.getUsername());

        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            return "Login successful!";
        } else {
            return "Invalid credentials.";
        }
    }
}
