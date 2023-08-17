package com.estaciones.demo.modules.user.service;

import com.estaciones.demo.modules.user.entity.User;
import com.estaciones.demo.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    private void verifyEmailIsUnique(@NotBlank String email) {
        if (userRepository.existsByEmail(email)) {
            log.error("User with email {} already exists", email);
            throw new RuntimeException("User with email " + email + " already exists");
        }
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    // Otros métodos de gestión de usuarios
}
