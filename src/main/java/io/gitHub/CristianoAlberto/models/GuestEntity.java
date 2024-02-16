package io.gitHub.CristianoAlberto.models;

import jakarta.persistence.Entity;
@Entity
public class GuestEntity {
    private Integer id;
    private String name;
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
