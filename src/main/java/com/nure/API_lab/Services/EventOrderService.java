package com.nure.API_lab.Services;


import com.nure.API_lab.entities.EventOrder;
import com.nure.API_lab.entities.ShoppingCart;
import com.nure.API_lab.entities.User;
import com.nure.API_lab.repository.EventOrderRepository;
import com.nure.API_lab.repository.ShoppingCartRepository;
import com.nure.API_lab.repository.StatusRepository;
import com.nure.API_lab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EventOrderService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public StatusRepository statusRepository;
    @Autowired
    public EventOrderRepository repository;
    @Autowired
    public ShoppingCartRepository shoppingCartRepository;

    public EventOrder createNewOrder(User user){
        EventOrder eventOrder = new EventOrder();
        eventOrder.setUser(user);
        eventOrder.setStatus(statusRepository.getById(1));
        return eventOrder;
    }


    public List<ShoppingCart> getAllCartsForOrder(EventOrder eventOrder){
        List<ShoppingCart> cartsForOrder = shoppingCartRepository
                .findAll().stream()
                .filter(cart-> cart.getEventOrder().getId()==eventOrder.getId())
                .collect(Collectors.toList());
        return cartsForOrder;
    }

    public double getSumPrice(EventOrder eventOrder){
        double priceSum = 0;
        List<ShoppingCart> carts = getAllCartsForOrder(eventOrder);
        if (carts.size()>0){
            priceSum=carts.get(0).scenario.price;
            for (ShoppingCart shoppingCart : getAllCartsForOrder(eventOrder)) {
                priceSum+=shoppingCart.getCostume().getPrice();
            }
        }
        return priceSum;
    }
}
