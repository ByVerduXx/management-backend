package com.ofc.management.service;

import com.ofc.management.model.User;
import com.ofc.management.repository.UserRepository;
import com.ofc.management.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user = userRepository.findFirstByUsername(request.getUsername())
                .orElseThrow();
        var jwt = jwtService.generateToken(new UserDetailsImpl(user));
        return AuthenticationResponse.builder()
                .jwt(jwt)
                .build();
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setLastName(request.getLastName());

        userRepository.save(user);

        var jwt = jwtService.generateToken(new UserDetailsImpl(user));
        return AuthenticationResponse.builder()
                .jwt(jwt)
                .build();
    }
}
