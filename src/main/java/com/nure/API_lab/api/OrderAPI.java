package com.nure.API_lab.api;


import com.nure.API_lab.entities.EventOrder;
import com.nure.API_lab.repository.OrderRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class OrderAPI {

    private final OrderRepository repository;

    OrderAPI(OrderRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/orders")
    CollectionModel<EntityModel<EventOrder>> getAllOrders() {

        List<EntityModel<EventOrder>> orders = repository.findAll().stream()
                .map(eventOrder -> EntityModel.of(eventOrder,
                        linkTo(methodOn(OrderAPI.class).getOrderById(eventOrder.getId())).withSelfRel(),
                        linkTo(methodOn(OrderAPI.class).getAllOrders()).withRel("orders")))
                .collect(Collectors.toList());

        return CollectionModel.of(orders, linkTo(methodOn(OrderAPI.class).getAllOrders()).withSelfRel());
    }


    @GetMapping("/orders/{id}")
    EntityModel<EventOrder> getOrderById(@PathVariable Long id) {
        EventOrder eventOrder = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());

        return EntityModel.of(eventOrder,
                linkTo(methodOn(OrderAPI.class).getOrderById(id)).withSelfRel(),
                linkTo(methodOn(OrderAPI.class).getAllOrders()).withRel("orders"));
    }

    @PostMapping("/orders")
    EventOrder insertOrder(@RequestBody EventOrder newEventOrder) {
        return repository.save(newEventOrder);
    }

    @PatchMapping("/orders/{id}")
    EventOrder updateOrder(@RequestBody EventOrder newEventOrder, @PathVariable Long id) {

        return repository.findById(id)
                .map(eventOrder -> {
                    eventOrder.setStatus(newEventOrder.getStatus());
                    eventOrder.setDetails(newEventOrder.getDetails());
                    eventOrder.setCategory(newEventOrder.getCategory());
                    eventOrder.setDate(newEventOrder.getDate());
                    return repository.save(eventOrder);
                })
                .orElseGet(() -> {
                    throw new NoSuchElementException();
                });
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
