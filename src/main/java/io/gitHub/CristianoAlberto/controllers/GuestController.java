package io.gitHub.CristianoAlberto.controllers;

import io.gitHub.CristianoAlberto.models.GuestEntity;
import io.gitHub.CristianoAlberto.services.GuestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/guests")
@CrossOrigin(origins = "http://localhost:4200")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping()
    @ResponseStatus(OK)
    public List<GuestEntity> getAllGuests() {
        return guestService.getAllGuests();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public GuestEntity getById(@PathVariable Integer id) {
        Optional<GuestEntity> guest = guestService.getById(id);
        return guest.orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND,
                        "Convidado não encontrado"));
    }

    @PostMapping("/saveGuest")
    public GuestEntity createGuest(@RequestBody GuestEntity guest) {
        return guestService.createGuest(guest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(OK)
    public void updateGuest(@PathVariable Integer id, @RequestBody GuestEntity guestData) {
        guestService.updateGuest(guestData, id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Nao foi possivel actualizar este user"));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteGuest(@PathVariable Integer id) {
        guestService.getById(id).map(guests -> {
            guestService.deleteGuest(id);
            return guests;
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Convidado nao encontrado"));
    }

}
