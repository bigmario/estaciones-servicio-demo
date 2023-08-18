package com.estaciones.demo.modules.auth.controller;

import com.estaciones.demo.modules.auth.response.AuthResponse;
import com.estaciones.demo.modules.auth.service.AuthService;
import com.estaciones.demo.modules.auth.request.LoginRequest;
import com.estaciones.demo.modules.auth.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}
