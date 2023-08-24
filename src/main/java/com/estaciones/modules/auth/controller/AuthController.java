package com.estaciones.modules.auth.controller;

import com.estaciones.core.handler.CustomExceptionHandler;
import com.estaciones.modules.auth.response.AuthResponse;
import com.estaciones.modules.auth.service.AuthService;
import com.estaciones.modules.auth.request.LoginRequest;
import com.estaciones.modules.auth.request.RegisterRequest;
import com.estaciones.modules.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    @Autowired
    private CustomExceptionHandler customExceptionHandler;
    @Autowired
    private UserService userService;
    @PostMapping(value = "login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
                return ResponseEntity.ok(response);
    }

    @PostMapping(value = "register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        boolean verifyEmail = userService.verifyEmailIsUnique(request.getEmail());
        if (verifyEmail){
            return ResponseEntity.badRequest().body("User with email " + request.getEmail() + "already exists");
        }
        authService.register(request);
        return ResponseEntity.ok("Register Success");
    }
}
