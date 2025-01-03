package com.joansebastian.apiauthservice.service;

import com.joansebastian.apiauthservice.model.User;
import com.joansebastian.apiauthservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "El usuario ya existe.";
        }
        userRepository.save(user);
        return "Usuario registrado con éxito.";
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return "Autenticación satisfactoria.";
        }
        return "Error en la autenticación.";
    }
}

