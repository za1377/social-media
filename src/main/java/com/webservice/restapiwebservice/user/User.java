package com.webservice.restapiwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webservice.restapiwebservice.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

//@JsonFilter("UserBeanFilter")
@Entity(name = "user_detail")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Size(min = 2, message = "Name should have atleast 2 characters")
    private String name;
    @Past(message = "Date should be in past")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;



    public User() {

    }

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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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
