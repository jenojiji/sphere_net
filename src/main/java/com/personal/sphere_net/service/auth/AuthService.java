package com.personal.sphere_net.service.auth;

import com.personal.sphere_net.dto.user.LoginRequest;
import com.personal.sphere_net.dto.user.RegisterRequest;
import com.personal.sphere_net.model.User;
import com.personal.sphere_net.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationProvider authenticationProvider;


    public String registerUser(@Valid RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        userRepository.save(user);
        log.info("*****************inside register*********************");
        return "User registered successfully";
    }

    public String loginUser(@Valid LoginRequest request) {
        log.info("*****************inside login*********************");
        Authentication authReqToken = UsernamePasswordAuthenticationToken
                .unauthenticated(request.getEmail(), request.getPassword());


        Authentication authRes = authenticationProvider.authenticate(authReqToken);
        if (authRes.isAuthenticated()) {
            return "success";
        } else {
            return "false";
        }
//        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
//                () -> new EntityNotFoundException("User not found with email:" + request.getEmail())
//        );
//        if (Objects.equals(user.getPassword(), request.getPassword())) {
//            return "success";
//        } else {
//            return "failed";
//        }
    }

    //TODO : JWT Authentication
    //TODO : Redis Implementation
    //TODO : Account Settings Implementation
}
