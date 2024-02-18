package io.gitHub.CristianoAlberto.services;

import io.gitHub.CristianoAlberto.models.GuestEntity;
import io.gitHub.CristianoAlberto.repositories.GuestRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public CompletableFuture<GuestEntity> updateGuest(GuestEntity guest,Integer id){
        return CompletableFuture.supplyAsync(() -> {
            Optional<GuestEntity> guestOptional = guestRepository.findById(id);
            if (guestOptional.isPresent()) {
                GuestEntity existingGuest = guestOptional.get();
                existingGuest.setName(guest.getName());
                existingGuest.setNumber(guest.getNumber());
                existingGuest.setGender(guest.getGender());
                existingGuest.setConfirmation(guest.getConfirmation());
                return guestRepository.save(existingGuest);
            } else {
                throw new EntityNotFoundException();
            }
        });
    }
    @Async
    public CompletableFuture<Boolean> deleteGuest(Integer id) {
        Optional<GuestEntity> guestOptional = guestRepository.findById(id);
        if (guestOptional.isPresent()) {
            guestRepository.deleteById(id);
            return CompletableFuture.completedFuture(true);
        } else {
            return CompletableFuture.completedFuture(false);
        }
    }
}
