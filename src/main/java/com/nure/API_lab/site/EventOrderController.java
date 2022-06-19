package com.nure.API_lab.site;


import com.nure.API_lab.Services.EventOrderService;
import com.nure.API_lab.entities.EventOrder;
import com.nure.API_lab.entities.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

@Controller
public class EventOrderController {
    @Autowired
    EventOrderService service;

    @GetMapping(value = "/allEventOrders")
    public String getAllEventOrders(Model model) {
        HashMap<EventOrder, List<ShoppingCart>> map = new HashMap();
        HashMap<EventOrder, Double> priceMap = new HashMap();
        service.repository.findAll().forEach(order -> map.put(order, service.getAllCartsForOrder(order)));
        map.entrySet().forEach(entry -> priceMap.put(entry.getKey(), service.getSumPrice(entry.getKey())));
        model.addAttribute("map", map);
        model.addAttribute("priceMap", priceMap);
        return "eventOrdersPages/getAllEventOrders";
    }

    @GetMapping(value = "/allEventOrders/{id}")
    public String getEventOrdersById(Model model, @PathVariable Integer id) {
        HashMap<EventOrder, List<ShoppingCart>> map = new HashMap();
        EventOrder order = service.repository.getById(id);
        map.put(order, service.getAllCartsForOrder(order));
        model.addAttribute("map", map);
        model.addAttribute("getById", true);
        return "eventOrdersPages/getAllEventOrders";
    }
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
}
