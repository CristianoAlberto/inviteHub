package io.gitHub.CristianoAlberto.models.repositories;

import io.gitHub.CristianoAlberto.models.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Integer> {
}
