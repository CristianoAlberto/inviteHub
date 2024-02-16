package io.gitHub.CristianoAlberto.controllers;

import io.gitHub.CristianoAlberto.models.GuestEntity;
import io.gitHub.CristianoAlberto.services.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/guests")
public class GuestController {
    private final GuestService guestService;
    public GuestController(GuestService guestService){
        this.guestService = guestService;
    }
    @GetMapping
    public CompletableFuture<ResponseEntity<List<GuestEntity>>> getAllGuests(){
        return guestService.getAllGuests()
                .thenApply(guests -> new ResponseEntity<>(guests, HttpStatus.OK));
    }
}
