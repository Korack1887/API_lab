package com.nure.API_lab.api;


import com.nure.API_lab.entities.Details;
import com.nure.API_lab.repository.DetailsRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class DetailsAPI {

    private final DetailsRepository repository;

    DetailsAPI(DetailsRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/details")
    CollectionModel<EntityModel<Details>> getAllDetails() {

        List<EntityModel<Details>> details = repository.findAll().stream()
                .map(detailsItem -> EntityModel.of(detailsItem,
                        linkTo(methodOn(DetailsAPI.class).getDetailsById(detailsItem.getId())).withSelfRel(),
                        linkTo(methodOn(DetailsAPI.class).getAllDetails()).withRel("details")))
                .collect(Collectors.toList());

        return CollectionModel.of(details, linkTo(methodOn(DetailsAPI.class).getAllDetails()).withSelfRel());
    }


    @GetMapping("/details/{id}")
    EntityModel<Details> getDetailsById(@PathVariable Long id) {
        Details details = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());

        return EntityModel.of(details,
                linkTo(methodOn(DetailsAPI.class).getDetailsById(id)).withSelfRel(),
                linkTo(methodOn(DetailsAPI.class).getAllDetails()).withRel("details"));
    }

    @PostMapping("/details")
    Details insertDetails(@RequestBody Details newDetails) {
        return repository.save(newDetails);
    }

    @PatchMapping("/details/{id}")
    Details updateDetails(@RequestBody Details newDetails, @PathVariable Long id) {

        return repository.findById(id)
                .map(details -> {
                    details.setAddress(newDetails.getAddress());
                    details.setUser(newDetails.getUser());
                    return repository.save(details);
                })
                .orElseGet(() -> {
                    throw new NoSuchElementException();
                });
    }

    @DeleteMapping("/details/{id}")
    void deleteDetails(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
