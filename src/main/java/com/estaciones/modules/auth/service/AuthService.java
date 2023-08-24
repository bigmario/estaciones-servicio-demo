package com.estaciones.modules.auth.service;

import com.estaciones.core.Jwt.JwtService;
import com.estaciones.modules.auth.request.LoginRequest;
import com.estaciones.modules.auth.request.RegisterRequest;
import com.estaciones.modules.auth.response.AuthResponse;
import com.estaciones.modules.user.entity.Role;
import com.estaciones.modules.user.entity.User;
import com.estaciones.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
                    .build();
    }
    public void register(RegisterRequest request) {


        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
            .role(Role.USER)
            .build();
        userRepository.save(user);
//        return AuthResponse.builder()
//            .token(jwtService.getToken(user))
//            .build();
    }

}
