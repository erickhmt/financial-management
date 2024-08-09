package com.financeapp.api.controller;

import com.financeapp.api.domain.user.AuthenticationRequestDTO;
import com.financeapp.api.domain.user.RegisterRequestDTO;
import com.financeapp.api.domain.user.UserAccount;
import com.financeapp.api.infra.security.AuthenticationResponseDTO;
import com.financeapp.api.infra.security.TokenService;
import com.financeapp.api.domain.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody @Valid AuthenticationRequestDTO authenticationRequest) {
        var authToken = new UsernamePasswordAuthenticationToken(authenticationRequest.username(), authenticationRequest.password());
        var authentication = manager.authenticate(authToken);
        var authenticatedUser = (UserAccount) authentication.getPrincipal();
        var jwtToken = tokenService.generateToken(authenticatedUser.getUsername());

        return ResponseEntity.ok(new AuthenticationResponseDTO(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDTO registerRequest) {
        authenticationService.register(registerRequest);
        return ResponseEntity.ok().build();
    }

}
