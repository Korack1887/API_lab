package com.nure.API_lab.site;

import com.nure.API_lab.Services.EventOrderService;
import com.nure.API_lab.entities.*;
import com.nure.API_lab.repository.CostumeRepository;
import com.nure.API_lab.repository.ScenarioRepository;
import com.nure.API_lab.repository.ShoppingCartRepository;
import com.nure.API_lab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    ShoppingCartRepository repository;
    @Autowired
    ScenarioRepository scenarioRepository;
    @Autowired
    CostumeRepository costumeRepository;
    @Autowired
    EventOrderService orderService;
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/shoppingCart")
    public String insertCategory(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        double priceSum;
        EventOrder eventOrder = orderService.repository
                .findAll().stream()
                .filter(order -> order.getUser().getId() == user.getId())
                .filter(order -> order.getStatus().getName().equals("IN DRAFT"))
                .findFirst().get();
        List<ShoppingCart> cartsForOrder = orderService.getAllCartsForOrder(eventOrder);
        priceSum = orderService.getSumPrice(eventOrder);
        model.addAttribute("carts", cartsForOrder);
        model.addAttribute("priceSum", priceSum);
        return "shoppingCardPage/shoppingCartPage";
    }

    @GetMapping(value = "/addScenarioToCart/{id}")
    public String addScenarioToCard(@PathVariable Integer id, HttpSession session) {
        Scenario scenarioToAdd = scenarioRepository.getById(id);
        if (session.getAttribute("userCart") != null) {
            ShoppingCart userCart = (ShoppingCart) session.getAttribute("userCart");
            userCart.scenario = scenarioToAdd;
            session.setAttribute("userCart", userCart);
        } else {
            ShoppingCart userCart = new ShoppingCart();
            userCart.scenario = scenarioToAdd;
            session.setAttribute("userCart", userCart);
        }
        return "redirect:/allScenario";
    }

    @GetMapping(value = "/addCostumeToCart/{id}")
    public String addCostumeToCard(@PathVariable Integer id, HttpSession session) {
        Costume costumeToAdd = costumeRepository.getById(id);
        ShoppingCart userCart = (ShoppingCart) session.getAttribute("userCart");
        if (costumeRepository.existsById(costumeToAdd.getId()) && userCart != null && !userCart.scenario.getCostumes().stream().filter(costume -> costume.getId() == costumeToAdd.getId()).findAny().isEmpty()) {
            if (userCart.eventOrder == null) {
                EventOrder eventOrder = orderService.createNewOrder((User) session.getAttribute("user"));
                userCart.setEventOrder(orderService.repository.save(eventOrder));
                userCart.setCostume(costumeToAdd);
            } else {
                userCart.setCostume(costumeToAdd);
                userCart.setId(0);
            }
            repository.save(userCart);
        }
        return "redirect:/allCostumes";
    }
}
