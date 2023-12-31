package com.estaciones.modules.user.service;

import com.estaciones.modules.user.entity.User;
import com.estaciones.modules.user.repository.UserRepository;
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

    public boolean verifyEmailIsUnique(@NotBlank String email) {
        if (userRepository.existsByEmail(email)) {
            return true;
        }
        return false;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    // Otros métodos de gestión de usuarios
}
