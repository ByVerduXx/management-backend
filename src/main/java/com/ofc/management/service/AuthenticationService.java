package com.ofc.management.service;


import com.ofc.management.repository.UserRepository;
import com.ofc.management.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTService jwtService;

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
}
