package io.gitHub.CristianoAlberto.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class User {

    @PostMapping
    public User login(){
        return null;
    }
}
