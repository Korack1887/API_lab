//package com.nure.API_lab.site;
//
//
//import com.nure.API_lab.entities.Category;
//import com.nure.API_lab.entities.EventOrder;
//import com.nure.API_lab.entities.Details;
//import com.nure.API_lab.repository.CategoryRepository;
//import com.nure.API_lab.repository.OrderRepository;
//import com.nure.API_lab.repository.DetailsRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//
//@Controller
//public class EventOrderController {
//    OrderRepository repository;
//    DetailsRepository detailsRepository;
//    CategoryRepository categoryRepository;
//
//    public EventOrderController(OrderRepository repository, DetailsRepository detailsRepository, CategoryRepository categoryRepository) {
//        this.repository = repository;
//        this.detailsRepository = detailsRepository;
//        this.categoryRepository = categoryRepository;
//    }
//
//    @GetMapping(value = "/allEventOrders")
//    public String getAllEventOrders(Model model) {
//        model.addAttribute("eventOrders", repository.findAll());
//        return "eventOrdersPages/getAllEventOrders";
//    }
//
//    @GetMapping(value = "/allEventOrders/{id}")
//    public String getEventOrdersById(Model model, @PathVariable Integer id) {
//        ArrayList<EventOrder> list = new ArrayList<>();
//        list.add(repository.getById(id));
//        model.addAttribute("eventOrders", list);
//        model.addAttribute("getById", true);
//        return "eventOrdersPages/getAllEventOrders";
//    }
//
//    @GetMapping(value = "/deleteEventOrders/{id}")
//    public String deleteEventOrders(@PathVariable Integer id) {
//        repository.deleteById(id);
//        return "redirect:/allEventOrders";
//    }
//
//    @GetMapping(value = "/insertEventOrders")
//    public String insertEventOrders(Model model) {
//        model.addAttribute("eventOrder", new EventOrder());
//        model.addAttribute("detailsList", detailsRepository.findAll());
//        model.addAttribute("categoryList", categoryRepository.findAll());
//        model.addAttribute("path", "/insertEventOrders");
//        return "eventOrdersPages/insertEventOrders";
//    }
//
//    @PostMapping(value = "/insertEventOrders")
//    public String insertEventOrders(@ModelAttribute EventOrder eventOrders, @RequestParam("detailsId") Long detailsId, @RequestParam("categoryId") Long categoryId) {
//        Details details = detailsRepository.getById(detailsId);
//        Category category = categoryRepository.getById(categoryId);
//        eventOrders.setDetails(details);
//        eventOrders.setCategory(category);
//        repository.save(eventOrders);
//        return "redirect:/allEventOrders";
//    }
//
//    @GetMapping(value = "/updateEventOrder/{id}")
//    public String updateEventOrders(@PathVariable Integer id, Model model) {
//        model.addAttribute("eventOrder", repository.getById(id));
//        model.addAttribute("detailsList", detailsRepository.findAll());
//        model.addAttribute("categoryList", categoryRepository.findAll());
//        model.addAttribute("path", "/updateEventOrder/" + id);
//        model.addAttribute("update", true);
//        return "eventOrdersPages/insertEventOrders";
//    }
//
//    @PostMapping(value = "/updateEventOrder/{id}")
//    public String updateEventOrders(@ModelAttribute EventOrder eventOrders, @RequestParam("detailsId") Long detailsId, @RequestParam("categoryId") Long categoryId) {
//        Details details = detailsRepository.getById(detailsId);
//        Category category = categoryRepository.getById(categoryId);
//        eventOrders.setDetails(details);
//        eventOrders.setCategory(category);
//        repository.save(eventOrders);
//        return "redirect:/allEventOrders";
//    }
//
//    @GetMapping(value = "/countEventOrders")
//    public String countEventOrders(Model model, @RequestParam(value = "count", required = false) Long count) {
//        model.addAttribute("count", count);
//        model.addAttribute("categoryList", categoryRepository.findAll());
//        return "eventOrdersPages/orderCount";
//    }
//
//    @PostMapping(value = "/countEventOrders")
//    public String countEventOrders(@RequestParam("categoryId") Long categoryId, @RequestParam(value = "status") String status, Model model) {
//        Long count = repository.findAll().stream().filter(order -> order.getStatus().toString().equals(status) && order.getCategory().getId().equals(categoryId)).count();
//        model.addAttribute("count", count);
//        return countEventOrders(model, count);
//    }
//}
