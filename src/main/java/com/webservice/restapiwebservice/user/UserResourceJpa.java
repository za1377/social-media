package com.webservice.restapiwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.webservice.restapiwebservice.ExceptionError.PostNOtFoundException;
import com.webservice.restapiwebservice.ExceptionError.UserNOtFoundException;
import com.webservice.restapiwebservice.post.Post;
import com.webservice.restapiwebservice.post.PostRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResourceJpa {

    private UserRepository userService;
    private PostRepository postService;

    public UserResourceJpa(UserRepository userService, PostRepository postService) {

        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {

        List<User> users = userService.findAll();
//        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "birthDate");
//
//        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserBeanFilter", filter);
//
//        mappingJacksonValue.setFilters(filterProvider);
        return users;
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> findUser(@PathVariable int id){
        Optional<User> user = userService.findById(id);

        if(user.isEmpty())
            throw new UserNOtFoundException("id: " + id);

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> getPostsUser(@PathVariable int id){
        Optional<User> user = userService.findById(id);

        if(user.isEmpty())
            throw new UserNOtFoundException("id: " + id);

        return user.get().getPosts();
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {

        User savedUser = userService.save(user);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
//
//        return ResponseEntity.created(location).build();
        return savedUser;
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<User> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {

        Optional<User> user = userService.findById(id);

        if(user.isEmpty())
            throw new UserNOtFoundException("id: " + id);
        post.setUser(user.get());

        Post savedPost = postService.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{post_id}").buildAndExpand(savedPost.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/posts/{post_id}")
    public Post postOfUsre(@PathVariable int id, @PathVariable int post_id) {

        Optional<User> user = userService.findById(id);

        if(user.isEmpty())
            throw new UserNOtFoundException("id: " + id);

        Optional<Post> post = postService.findById(post_id);

        if(post.isEmpty())
            throw new PostNOtFoundException("post_id: " + post_id);
        return post.get();

    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteById(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @Valid @RequestBody User user) {

        User userUpdate = userService.findById(id).get();
        userUpdate.setName(user.getName());
        userUpdate.setBirthDate(user.getBirthDate());
        userService.save(userUpdate);
        return userUpdate;
    }
}
