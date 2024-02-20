package io.gitHub.CristianoAlberto.services;

import io.gitHub.CristianoAlberto.configurations.TokenService;
import io.gitHub.CristianoAlberto.models.user.AuthenticationDTO;
import io.gitHub.CristianoAlberto.models.user.RegisterDTO;
import io.gitHub.CristianoAlberto.models.user.UserEntity;
import io.gitHub.CristianoAlberto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    public String login(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((UserEntity) auth.getPrincipal());
    }

    public Optional<UserEntity> register(RegisterDTO data) {

        if (userRepository.findByEmail(data.email()) == null) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

            UserEntity newUser = new UserEntity.Builder()
                    .name(data.name())
                    .phoneNumber(data.phoneNumber())
                    .password(encryptedPassword)
                    .email(data.email())
                    .role(data.role())
                    .build();

            UserEntity savedUser = this.userRepository.save(newUser);

            return Optional.of(savedUser);
        } else {
            return Optional.empty();
        }
    }
}
