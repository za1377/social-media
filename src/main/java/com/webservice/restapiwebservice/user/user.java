package com.webservice.restapiwebservice.user;

import java.time.LocalDate;

public class user {

    private String name;
    private int id;
    private LocalDate birthDate;

    public user(String name, int id, LocalDate birthDate) {
        this.name = name;
        this.id = id;
        this.birthDate = birthDate;
    }
}
