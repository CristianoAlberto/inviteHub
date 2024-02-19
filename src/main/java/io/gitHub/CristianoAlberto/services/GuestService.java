package io.gitHub.CristianoAlberto.services;

import io.gitHub.CristianoAlberto.models.GuestEntity;
import io.gitHub.CristianoAlberto.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Async
    public List<GuestEntity> getAllGuests() {
        return guestRepository.findAll();
    }

    @Async
    public Optional<GuestEntity> getById(Integer id) {
        return guestRepository.findById(id);
    }

    @Async
    public CompletableFuture<GuestEntity> createGuest(GuestEntity guest) {
        return CompletableFuture.completedFuture(guestRepository.save(guest));
    }

    @Async
    public Optional<GuestEntity> updateGuest(GuestEntity guest, Integer id) {
        return this.getById(id).map(guestFound -> {
            guestFound.setName(guest.getName() != null ? guest.getName() : guestFound.getName());
            guestFound.setNumber(guest.getNumber() != null ? guest.getNumber() : guestFound.getNumber());
            guestFound.setGender(guest.getGender() != null ? guest.getGender() : guestFound.getGender());
            guestFound.setConfirmation(guest.getConfirmation() != null ? guest.getConfirmation() : guestFound.getConfirmation());
            return guestRepository.save(guestFound);
        });
    }

    @Async
    public void deleteGuest(Integer id) {
        guestRepository.deleteById(id);
    }
}
