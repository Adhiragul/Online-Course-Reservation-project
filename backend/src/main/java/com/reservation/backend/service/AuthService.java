package com.reservation.backend.service;

import com.reservation.backend.dto.AuthResponse;
import com.reservation.backend.dto.LoginRequest;
import com.reservation.backend.dto.RegisterRequest;
import com.reservation.backend.model.Role;
import com.reservation.backend.model.User;
import com.reservation.backend.repository.UserRepository;
import com.reservation.backend.security.JwtUtils;
import com.reservation.backend.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    EmailService emailService;

    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
            .findFirst()
            .map(authority -> authority.getAuthority())
            .orElse("ROLE_USER");

        userRepository.findByEmail(userDetails.getEmail())
                .ifPresent(emailService::sendLoginEmail);

        return new AuthResponse(jwt, userDetails.getName(), userDetails.getEmail(), role);
    }

    public void registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        User user = new User(registerRequest.getName(),
                registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()),
                Role.ROLE_USER);

        userRepository.save(user);
    }
}
