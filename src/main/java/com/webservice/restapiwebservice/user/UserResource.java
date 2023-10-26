package com.webservice.restapiwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.webservice.restapiwebservice.ExceptionError.UserNOtFoundException;
import com.webservice.restapiwebservice.helloWorld.HelloWorldController;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {

        List<User> users = userService.findAll();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "birthDate");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserBeanFilter", filter);

        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @GetMapping(path = "/hello-world2")
    public List<User> helloWorld2() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> findUser(@PathVariable int id){
        User user = userService.findById(id);

        if(user == null)
            throw new UserNOtFoundException("id: " + id);

        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).helloWorld2());
        entityModel.add(link2.withRel("hello-world"));

        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteById(id);
    }
}
