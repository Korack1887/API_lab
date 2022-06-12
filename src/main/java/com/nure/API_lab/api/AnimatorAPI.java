package com.nure.API_lab.api;


import com.nure.API_lab.entities.Animator;
import com.nure.API_lab.repository.AnimatorRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class AnimatorAPI {

    private final AnimatorRepository repository;

    AnimatorAPI(AnimatorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/animators")
    CollectionModel<EntityModel<Animator>> getAllAnimators() {

        List<EntityModel<Animator>> animators = repository.findAll().stream()
                .map(animator -> EntityModel.of(animator,
                        linkTo(methodOn(AnimatorAPI.class).getAnimatorById(animator.getId())).withSelfRel(),
                        linkTo(methodOn(AnimatorAPI.class).getAllAnimators()).withRel("animators")))
                .collect(Collectors.toList());

        return CollectionModel.of(animators, linkTo(methodOn(AnimatorAPI.class).getAllAnimators()).withSelfRel());
    }




    @GetMapping("/animators/{id}")
    EntityModel<Animator> getAnimatorById(@PathVariable Long id) {
        Animator animator = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());

        return EntityModel.of(animator,
                linkTo(methodOn(AnimatorAPI.class).getAnimatorById(id)).withSelfRel(),
                linkTo(methodOn(AnimatorAPI.class).getAllAnimators()).withRel("animators"));
    }
    @PostMapping("/animators")
    Animator insertAnimator(@RequestBody Animator newAnimator) {
        return repository.save(newAnimator);
    }


    @PatchMapping("/animators/{id}")
    Animator updateAnimator(@RequestBody Animator newAnimator, @PathVariable Long id) {

        return repository.findById(id)
                .map(animator -> {
                    animator.setName(newAnimator.getName());
                    return repository.save(animator);
                })
                .orElseGet(() -> {
                    throw new NoSuchElementException();
                });
    }

    @DeleteMapping("/animators/{id}")
    void deleteAnimator(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
