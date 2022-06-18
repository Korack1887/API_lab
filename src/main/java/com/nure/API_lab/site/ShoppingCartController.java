package com.nure.API_lab.site;

import com.nure.API_lab.entities.Costume;
import com.nure.API_lab.entities.EventOrder;
import com.nure.API_lab.entities.Scenario;
import com.nure.API_lab.entities.ShoppingCart;
import com.nure.API_lab.repository.CostumeRepository;
import com.nure.API_lab.repository.EventOrderRepository;
import com.nure.API_lab.repository.ScenarioRepository;
import com.nure.API_lab.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class ShoppingCartController {
    @Autowired
    ShoppingCartRepository repository;
    @Autowired
    ScenarioRepository scenarioRepository;
    @Autowired
    CostumeRepository costumeRepository;
    @Autowired
    EventOrderRepository orderRepository;

    @GetMapping(value = "/addScenarioToCart/{id}")
    public String addScenarioToCard(@PathVariable Integer id, HttpSession session) {
        Scenario scenarioToAdd = scenarioRepository.getById(id);
        if (scenarioRepository.existsById(scenarioToAdd.id)) {
            if (session.getAttribute("userCart") != null) {
                ShoppingCart userCart = (ShoppingCart) session.getAttribute("userCart");
                userCart.scenario = scenarioToAdd;
                session.setAttribute("userCart", userCart);
            } else {
                ShoppingCart userCart = new ShoppingCart();
                userCart.scenario = scenarioToAdd;
                session.setAttribute("userCart", userCart);
            }
        }
        return "redirect:/allScenario";
    }

    @GetMapping(value = "/addCostumeToCart/{id}")
    public String addCostumeToCard(@PathVariable Integer id, HttpSession session) {
        Costume costumeToAdd = costumeRepository.getById(id);
        ShoppingCart userCart = (ShoppingCart) session.getAttribute("userCart");
        if (costumeRepository.existsById(costumeToAdd.id) && userCart!=null && userCart.scenario.getCostumes().contains(costumeToAdd)) {
            if (userCart.eventOrder==null){
                EventOrder eventOrder = new EventOrder();
                userCart.eventOrder = orderRepository.save(eventOrder);
                userCart.costume = costumeToAdd;
                repository.save(userCart);
            }
        }
        return "redirect:/allCostumes";
    }
}
