package com.glovendorproject.glovendor_backend_dev.controller;

import com.glovendorproject.glovendor_backend_dev.entity.Subvendor;
import com.glovendorproject.glovendor_backend_dev.service.SubvendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.glovendorproject.glovendor_backend_dev.service.SubvendorService;
import com.glovendorproject.glovendor_backend_dev.entity.Subvendor;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final SubvendorService subvendorService;

    public AuthController(SubvendorService subvendorService) {
        this.subvendorService = subvendorService;
    }

    // Signup endpoint for vendors
    @PostMapping("/signup")
    public ResponseEntity<Subvendor> signup(@RequestBody Subvendor subvendor) {
        Subvendor created = subvendorService.createSubvendor(subvendor);
        return ResponseEntity.ok(created);
    }

    // Simple login that accepts email and password in JSON
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest req) {
        return subvendorService.findByEmail(req.getEmail()).map(subvendor -> {
            org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
            if (subvendor.getPasswordHash() != null && encoder.matches(req.getPassword(), subvendor.getPasswordHash())) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        }).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
