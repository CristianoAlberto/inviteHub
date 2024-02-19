package io.gitHub.CristianoAlberto.models.user;

public enum UserRols {
    ADMIN("admin"),
    USER("user");
    private final String role;
    UserRols(String role) {
        this.role = role;
    }
    public String getRole(){
        return role;
    }
}
