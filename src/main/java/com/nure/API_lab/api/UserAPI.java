package com.nure.API_lab.api;


import com.nure.API_lab.entities.User;
import com.nure.API_lab.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class UserAPI {

    private final UserRepository repository;

    UserAPI(UserRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/users")
    CollectionModel<EntityModel<User>> getAllUsers() {

        List<EntityModel<User>> users = repository.findAll().stream()
                .map(user -> EntityModel.of(user,
                        linkTo(methodOn(UserAPI.class).getUserById(user.getId())).withSelfRel(),
                        linkTo(methodOn(UserAPI.class).getAllUsers()).withRel("users")))
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserAPI.class).getAllUsers()).withSelfRel());
    }


    @GetMapping("/users/{id}")
    EntityModel<User> getUserById(@PathVariable Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());

        return EntityModel.of(user,
                linkTo(methodOn(UserAPI.class).getUserById(id)).withSelfRel(),
                linkTo(methodOn(UserAPI.class).getAllUsers()).withRel("users"));
    }

    @PostMapping("/users")
    User insertUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @PatchMapping("/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setPassword(newUser.getPassword());
                    user.setEmail(newUser.getEmail());
                    user.setPhone(newUser.getPhone());
                    user.setRole(newUser.getRole());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    throw new NoSuchElementException();
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
