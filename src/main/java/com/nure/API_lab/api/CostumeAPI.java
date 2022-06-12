package com.nure.API_lab.api;


import com.nure.API_lab.entities.Costume;
import com.nure.API_lab.repository.CostumeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class CostumeAPI {

    private final CostumeRepository repository;

    CostumeAPI(CostumeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/costumes")
    CollectionModel<EntityModel<Costume>> getAllCostumes() {

        List<EntityModel<Costume>> costumes = repository.findAll().stream()
                .map(Costume -> EntityModel.of(Costume,
                        linkTo(methodOn(CostumeAPI.class).getCostumeById(Costume.getId())).withSelfRel(),
                        linkTo(methodOn(CostumeAPI.class).getAllCostumes()).withRel("costumes")))
                .collect(Collectors.toList());

        return CollectionModel.of(costumes, linkTo(methodOn(CostumeAPI.class).getAllCostumes()).withSelfRel());
    }


    @GetMapping("/costumes/{id}")
    EntityModel<Costume> getCostumeById(@PathVariable Long id) {
        Costume costume = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());

        return EntityModel.of(costume,
                linkTo(methodOn(CostumeAPI.class).getCostumeById(id)).withSelfRel(),
                linkTo(methodOn(CostumeAPI.class).getAllCostumes()).withRel("costumes"));
    }

    @PostMapping("/costumes")
    Costume insertCostume(@RequestBody Costume newCostume) {
        return repository.save(newCostume);
    }

    @PatchMapping("/costumes/{id}")
    Costume updateCostume(@RequestBody Costume newCostume, @PathVariable Long id) {

        return repository.findById(id)
                .map(Costume -> {
                    Costume.setName(newCostume.getName());
                    Costume.setDescription(newCostume.getDescription());
                    return repository.save(Costume);
                })
                .orElseGet(() -> {
                    throw new NoSuchElementException();
                });
    }

    @DeleteMapping("/costumes/{id}")
    void deleteCostume(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
