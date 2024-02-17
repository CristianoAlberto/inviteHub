package io.gitHub.CristianoAlberto.controllers;

import io.gitHub.CristianoAlberto.models.GuestEntity;
import io.gitHub.CristianoAlberto.services.GuestService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/guests")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping()
    public CompletableFuture<ResponseEntity<List<GuestEntity>>> getAllGuests() {
        return guestService.getAllGuests()
                .thenApply(guests -> new ResponseEntity<>(guests, HttpStatus.OK));
    }

    @PostMapping("/saveGuest")
    public CompletableFuture<ResponseEntity<GuestEntity>> createGuest(@RequestBody GuestEntity guest) {
        return guestService.createGuest(guest).thenApply(guestData -> new ResponseEntity<>(guestData, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<GuestEntity>> getById(@PathVariable Integer id) {
       return guestService.getById(id).thenApplyAsync(guest->ResponseEntity.ok().body(guest)).exceptionally(e->{
           if (e.getCause() instanceof EntityNotFoundException) {
               return ResponseEntity.notFound().build();
           } else {
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
           }
       });
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<ResponseEntity<Boolean>> deleteGuest(@PathVariable Integer id) {
        CompletableFuture<Boolean> guest = guestService.deleteGuest(id);
        return guest.thenApplyAsync(guestResult -> {
            if (guestResult == true) {
                return new ResponseEntity<>(guestResult, HttpStatus.OK);
            } else return new ResponseEntity<>(guestResult, HttpStatus.NOT_FOUND);
        }).exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("/update/{id}")
    public CompletableFuture<ResponseEntity<GuestEntity>> updateGuest(@PathVariable Integer id, @RequestBody GuestEntity guestData) {
        return guestService.updateGuest(guestData, id)
                .thenApplyAsync(updatedGuest -> ResponseEntity.ok().body(updatedGuest))
                .exceptionally(e -> {
                    if (e.getCause() instanceof EntityNotFoundException) {
                        return ResponseEntity.notFound().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                });
    }
}
