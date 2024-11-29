package com.personal.sphere_net.service.auth;

import com.personal.sphere_net.dto.user.LoginRequest;
import com.personal.sphere_net.dto.user.RegisterRequest;
import com.personal.sphere_net.model.User;
import com.personal.sphere_net.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationProvider authenticationProvider;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContextRepository securityContextRepository;

    public String registerUser(@Valid RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        log.info("*****************inside register*********************");
        return "User registered successfully";
    }

    public String loginUser(@Valid LoginRequest request, HttpServletRequest req, HttpServletResponse res) {
        log.info("*****************inside login*********************");
        Authentication authReqToken = UsernamePasswordAuthenticationToken
                .unauthenticated(request.getEmail(), request.getPassword());


        try {
            log.info("*******inside try**********");
            Authentication authRes = authenticationProvider.authenticate(authReqToken);
            System.out.println(authRes.isAuthenticated());
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authRes);
            SecurityContextHolder.setContext(context);
            securityContextRepository.saveContext(context, req, res);
            //logging

            if (authRes != null) {
                // Log Authentication object details
                log.info("Authentication Details:");
                log.info("Principal: {}", authRes.getPrincipal());
                log.info("Authorities: {}", authRes.getAuthorities());
                log.info("Credentials: {}", authRes.getCredentials());
                log.info("Name: {}", authRes.getName());

                // Log the details of the authenticated user (if available)
                if (authRes.getPrincipal() instanceof User) {
                    User user = (User) authRes.getPrincipal();
                    log.info("User Details:");
                    log.info("Username: {}", user.getUsername());
                    log.info("Enabled: {}", user.isEnabled());
                    log.info("Account Non Expired: {}", user.isAccountNonExpired());
                    log.info("Credentials Non Expired: {}", user.isCredentialsNonExpired());
                    log.info("Account Non Locked: {}", user.isAccountNonLocked());
                }

                // Log the WebAuthenticationDetails (for details like remote IP, session ID)
                if (authRes.getDetails() instanceof WebAuthenticationDetails) {
                    WebAuthenticationDetails details = (WebAuthenticationDetails) authRes.getDetails();
                    log.info("Remote Address: {}", details.getRemoteAddress());
                    log.info("Session ID: {}", details.getSessionId());
                }
            }

            // Log Session ID from the HttpServletRequest
            String sessionId = req.getSession(false) != null ? req.getSession(false).getId() : "No session";
            log.info("Session ID: {}", sessionId);

            // Log the SecurityContext details
            SecurityContext ctx = SecurityContextHolder.getContext();
            log.info("SecurityContext: {}", ctx);
            log.info("Authentication in SecurityContext: {}", ctx.getAuthentication());


            //logging end


            if (authRes.isAuthenticated()) {
                return "success";
            } else {
                return "false";
            }
        } catch (AuthenticationException e) {
            log.info("*******inside exception**********");
            log.error("e: ", e);
            throw new RuntimeException(e);
        }
    }

    //TODO : JWT Authentication
    //TODO : Redis Implementation
    //TODO : Account Settings Implementation
}
