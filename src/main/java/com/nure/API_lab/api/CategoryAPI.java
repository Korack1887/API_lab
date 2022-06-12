package com.nure.API_lab.api;


import com.nure.API_lab.entities.Category;
import com.nure.API_lab.repository.CategoryRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class CategoryAPI {

    private final CategoryRepository repository;

    CategoryAPI(CategoryRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/categories")
    CollectionModel<EntityModel<Category>> getAllCategories() {

        List<EntityModel<Category>> categories = repository.findAll().stream()
                .map(category -> EntityModel.of(category,
                        linkTo(methodOn(CategoryAPI.class).getCategoryById(category.getId())).withSelfRel(),
                        linkTo(methodOn(CategoryAPI.class).getAllCategories()).withRel("categories")))
                .collect(Collectors.toList());

        return CollectionModel.of(categories, linkTo(methodOn(CategoryAPI.class).getAllCategories()).withSelfRel());
    }



    @GetMapping("/categories/{id}")
    EntityModel<Category> getCategoryById(@PathVariable Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());

        return EntityModel.of(category,
                linkTo(methodOn(CategoryAPI.class).getCategoryById(id)).withSelfRel(),
                linkTo(methodOn(CategoryAPI.class).getAllCategories()).withRel("categories"));
    }
    @PostMapping("/categories")
    Category newCategory(@RequestBody Category newCategory) {
        return repository.save(newCategory);
    }

    @PatchMapping("/categories/{id}")
    Category updateCategory(@RequestBody Category newCategory, @PathVariable Long id) {

        return repository.findById(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    category.setAnimators(newCategory.getAnimators());
                    category.setCostumes(newCategory.getCostumes());
                    category.setPrice(newCategory.getPrice());
                    return repository.save(category);
                })
                .orElseGet(() -> {
                    throw new NoSuchElementException();
                });
    }

    @DeleteMapping("/categories/{id}")
    void deleteCategory(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
