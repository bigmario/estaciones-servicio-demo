package com.estaciones.demo.modules.auth.controller;

import com.estaciones.demo.modules.auth.response.AuthResponse;
import com.estaciones.demo.modules.auth.service.AuthService;
import com.estaciones.demo.modules.auth.request.LoginRequest;
import com.estaciones.demo.modules.auth.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            if (!response.getToken().isBlank()) {
                return ResponseEntity.ok(response);
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return null;
    }


    @PostMapping(value = "register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request)
    {
        authService.register(request);
        return ResponseEntity.ok("Register Success");
    }
}
