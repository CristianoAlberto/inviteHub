package io.gitHub.CristianoAlberto.controllers;

import io.gitHub.CristianoAlberto.configurations.TokenService;
import io.gitHub.CristianoAlberto.models.user.*;
import io.gitHub.CristianoAlberto.repositories.UserRepository;
import io.gitHub.CristianoAlberto.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        return ResponseEntity.ok(new LoginDTO(authenticationService.login(data)));
    }

    @PostMapping("/register")
    public UserEntity register(@RequestBody @Valid RegisterDTO data) {
        return authenticationService.register(data)
                .orElseThrow();
    }
}
