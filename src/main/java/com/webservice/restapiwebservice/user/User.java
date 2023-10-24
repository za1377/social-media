package com.webservice.restapiwebservice.user;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class User {
    private int id;

    @Size(min = 2, message = "Name should have atleast 2 characters")
    private String name;
    @Past(message = "Date should be in past")
    private LocalDate birthDate;

    @Override
    public String toString() {
        return "user{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", birthDate=" + birthDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public User(int id, String name, LocalDate birthDate) {
        this.name = name;
        this.id = id;
        this.birthDate = birthDate;
    }
}
