package io.gitHub.CristianoAlberto.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GuestEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "O campo nome não pode estar vazio")
    private String name;
    @NotEmpty(message = "O campo número não pode estar vazio")
    private Integer number;
    private Character gender;
    private Boolean confirmation;
    private GuestEntity(){}

    public static class Builder{
        private Integer id;
        private String name;
        private Integer number;
        private Character gender;
        private Boolean confirmation;

        public Builder(){}

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder number(Integer number){
            this.number = number;
            return this;
        }

        public Builder gender(Character gender){
            this.gender = gender;
            return this;
        }

        public Builder confirmation(Boolean confirmation){
            this.confirmation = confirmation;
            return this;
        }

        public GuestEntity build(){
            GuestEntity guest  = new GuestEntity();
            guest.id = this.id;
            guest.number = this.number;
            guest.gender = this.gender;
            guest.confirmation = this.confirmation;
            return guest;
        }
    }
}
