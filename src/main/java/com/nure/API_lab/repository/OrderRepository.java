package com.nure.API_lab.repository;

import com.nure.API_lab.entities.EventOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<EventOrder, Long> {
}
