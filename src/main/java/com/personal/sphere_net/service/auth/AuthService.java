package com.personal.sphere_net.service.auth;

import com.personal.sphere_net.dto.user.LoginRequest;
import com.personal.sphere_net.dto.user.RegisterRequest;
import com.personal.sphere_net.model.User;
import com.personal.sphere_net.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;


    public String registerUser(@Valid RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        userRepository.save(user);
        return "User registered successfully";
    }

    public String authenticateUser(@Valid LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new EntityNotFoundException("User not found with email:" + request.getEmail())
        );
        if (Objects.equals(user.getPassword(), request.getPassword())) {
            return "success";
        } else {
            return "failed";
        }
    }
}
