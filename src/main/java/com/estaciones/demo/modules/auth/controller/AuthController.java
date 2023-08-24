package com.estaciones.demo.modules.auth.controller;

import com.estaciones.demo.core.handler.CustomExceptionHandler;
import com.estaciones.demo.modules.auth.response.AuthResponse;
import com.estaciones.demo.modules.auth.service.AuthService;
import com.estaciones.demo.modules.auth.request.LoginRequest;
import com.estaciones.demo.modules.auth.request.RegisterRequest;
import com.estaciones.demo.modules.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
