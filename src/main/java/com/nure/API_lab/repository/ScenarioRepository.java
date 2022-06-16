package com.nure.API_lab.repository;

import com.nure.API_lab.entities.EventOrder;
import com.nure.API_lab.entities.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioRepository extends JpaRepository<Scenario, Integer> {
}
