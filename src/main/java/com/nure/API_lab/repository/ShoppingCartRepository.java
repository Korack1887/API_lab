package com.nure.API_lab.repository;

import com.nure.API_lab.entities.EventOrder;
import com.nure.API_lab.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}
