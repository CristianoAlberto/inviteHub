package io.gitHub.CristianoAlberto.repositories;

import io.gitHub.CristianoAlberto.models.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserDetails findByEmail(String email);
}
