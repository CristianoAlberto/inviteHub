package io.gitHub.CristianoAlberto.services;

import io.gitHub.CristianoAlberto.models.GuestEntity;
import io.gitHub.CristianoAlberto.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Async
    public CompletableFuture<List<GuestEntity>> getAllGuests() {
        return CompletableFuture.completedFuture(guestRepository.findAll());
    }

    @Async
    public CompletableFuture<GuestEntity> getById(Integer id) {
        return CompletableFuture.completedFuture(guestRepository.getReferenceById(id));
    }

    @Async
    public CompletableFuture<GuestEntity> createGuest(GuestEntity guest) {
        return CompletableFuture.completedFuture(guestRepository.save(guest));
    }

    @Async
    public CompletableFuture<Boolean> deleteGuest(Integer id) {
        try {
            guestRepository.deleteById(id);
            return CompletableFuture.completedFuture(true);
        } catch (EmptyResultDataAccessException e) {
            return CompletableFuture.completedFuture(false);
        }
    }
}
