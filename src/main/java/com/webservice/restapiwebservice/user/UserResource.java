package com.webservice.restapiwebservice.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findUser(@PathVariable int id) {
        return userService.findById(id);
    }

    @PostMapping("/user")
    public void createUser(@RequestBody User user) {
        userService.save(user);
    }
}
