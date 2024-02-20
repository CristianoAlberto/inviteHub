package io.gitHub.CristianoAlberto.models.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer phoneNumber;
    private String password;
    private String email;
    private UserRols role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRols.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static class Builder {
        private String name;
        private Integer phoneNumber;
        private String password;
        private String email;
        private UserRols role;

        public UserEntity.Builder name(String name) {
            this.name = name;
            return this;
        }

        public UserEntity.Builder phoneNumber(Integer phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserEntity.Builder password(String password) {
            this.password = password;
            return this;
        }

        public UserEntity.Builder email(String email) {
            this.email = email;
            return this;
        }

        public UserEntity.Builder role(UserRols role) {
            this.role = role;
            return this;
        }

        public UserEntity build() {
            UserEntity user = new UserEntity();
            user.name = this.name;
            user.phoneNumber = this.phoneNumber;
            user.password = this.password;
            user.email = this.email;
            user.role = this.role;
            return user;
        }
    }

}
