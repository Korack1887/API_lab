package com.nure.API_lab.site;


import com.nure.API_lab.Services.EventOrderService;
import com.nure.API_lab.entities.EventOrder;
import com.nure.API_lab.entities.ShoppingCart;
import com.nure.API_lab.repository.ActorRepository;
import com.nure.API_lab.repository.ShoppingCartRepository;
import com.nure.API_lab.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class EventOrderController {
    @Autowired
    EventOrderService service;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

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

    @GetMapping(value = "/deleteEventOrders/{id}")
    public String deleteEventOrders(@PathVariable Integer id) {
        service.repository.deleteById(id);
        return "redirect:/allEventOrders";
    }

    @GetMapping(value = "/createOrder/{id}")
    public String insertEventOrders(Model model, @PathVariable Integer id) {
        model.addAttribute("eventOrder", service.repository.getById(id));
        model.addAttribute("path", "/createOrder");
        return "eventOrdersPages/insertEventOrders";
    }

    @PostMapping(value = "/createOrder")
    public String insertEventOrders(@ModelAttribute EventOrder eventOrders) {
        eventOrders.setStatus(statusRepository.findByName("FORMED").get(0));
        service.repository.save(eventOrders);
        return "redirect:/allEventOrders";
    }

    @GetMapping(value = "/updateEventOrder/{id}")
    public String updateEventOrders(@PathVariable Integer id, Model model) {
        EventOrder orderToUpdate = service.repository.getById(id);
//        String orderStatus = orderToUpdate.getStatus().getName();
//        if (!orderStatus.equals("IN DRAFT") && !orderStatus.equals("FORMED")){
//            return "redirect:allEventOrders";
//        }
        model.addAttribute("actorList", actorRepository.findAll());
        model.addAttribute("statusList", statusRepository.findAll());
        model.addAttribute("eventOrder", orderToUpdate);
        model.addAttribute("path", "/updateEventOrder/" + id);
        model.addAttribute("update", true);
        return "eventOrdersPages/insertEventOrders";
    }

    @PostMapping(value = "/updateEventOrder/{id}")
    public String updateEventOrders(@ModelAttribute EventOrder eventOrders,
                                    @RequestParam(name = "actorId", required = false) Integer[] actorId,
                                    @RequestParam(name = "statusId", required = false) Integer statusId) {
        List<ShoppingCart> carts = service.getAllCartsForOrder(eventOrders);
        if (actorId!=null && actorId.length>0){
            for (int i = 0; i < carts.size(); i++) {
                try {
                    ShoppingCart cartToSave = carts.get(i);
                    cartToSave.setActor(actorRepository.getById(actorId[i]));
                    shoppingCartRepository.save(cartToSave);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    break;
                }
            }
        }
        if (statusId!=null){
            eventOrders.setStatus(statusRepository.getById(statusId));
        }
        service.repository.save(eventOrders);
        return "redirect:/allEventOrders";
    }
}
