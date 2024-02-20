package io.gitHub.CristianoAlberto.models.user;

public record RegisterDTO(String name,Integer phoneNumber, String email, String password, UserRols role) {
}
