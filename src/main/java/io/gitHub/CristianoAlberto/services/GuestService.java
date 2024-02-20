package io.gitHub.CristianoAlberto.services;

import io.gitHub.CristianoAlberto.models.GuestEntity;
import io.gitHub.CristianoAlberto.models.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public GuestEntity createGuest(GuestEntity guest) {
        return guestRepository.save(guest);
    }

    @Async
    public Optional<GuestEntity> updateGuest(GuestEntity guest, Integer id) {
        return this.getById(id).map(guestFound -> {
            GuestEntity.Builder builder = new GuestEntity.Builder()
                    .name(guest.getName() != null ? guest.getName() : guestFound.getName())
                    .number(guest.getNumber() != null ? guest.getNumber() : guestFound.getNumber())
                    .gender(guest.getGender() != null ? guest.getGender() : guestFound.getGender())
                    .confirmation(guest.getConfirmation() != null ? guest.getConfirmation() : guestFound.getConfirmation());
            return guestRepository.save(builder.build());
        });
    }

    @Async
    public void deleteGuest(Integer id) {
        guestRepository.deleteById(id);
    }
}
