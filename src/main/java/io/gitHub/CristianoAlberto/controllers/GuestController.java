package io.gitHub.CristianoAlberto.controllers;

import io.gitHub.CristianoAlberto.models.GuestEntity;
import io.gitHub.CristianoAlberto.services.GuestService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/guests")
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

    @PostMapping("/saveGuest")
    public CompletableFuture<ResponseEntity<GuestEntity>> createGuest(@RequestBody GuestEntity guest) {
        return guestService.createGuest(guest).thenApply(guestData -> new ResponseEntity<>(guestData, CREATED));
    }

    @GetMapping("/{id}")
    public GuestEntity getById(@PathVariable Integer id) {
        Optional <GuestEntity> guest = guestService.getById(id);
        return guest.orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Convidado não encontrado"));
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<ResponseEntity<Boolean>> deleteGuest(@PathVariable Integer id) {
        CompletableFuture<Boolean> guest = guestService.deleteGuest(id);
        return guest.thenApplyAsync(guestResult -> {
            if (guestResult) {
                return new ResponseEntity<>(true,
                        OK);
            } else return new ResponseEntity<>(false,
                    NOT_FOUND);
        }).exceptionally(e -> ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("/update/{id}")
    public CompletableFuture<ResponseEntity<GuestEntity>> updateGuest(@PathVariable Integer id, @RequestBody GuestEntity guestData) {
        return guestService.updateGuest(guestData, id)
                .thenApplyAsync(updatedGuest -> ResponseEntity.ok().body(updatedGuest))
                .exceptionally(e -> {
                    if (e.getCause() instanceof EntityNotFoundException) {
                        return ResponseEntity.notFound().build();
                    } else {
                        return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
                    }
                });
    }
}
