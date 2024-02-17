package io.gitHub.CristianoAlberto.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GuestEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer number;
    private Character gender;
    private Boolean confirmation;

    public Integer getId() {
        return id;
    }

    private GuestEntity(){}

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public Character getGender() {
        return gender;
    }

    public Boolean getConfirmation() {
        return confirmation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setConfirmation(Boolean confirmation) {
        this.confirmation = confirmation;
    }

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
