package com.nure.API_lab.repository;

import com.nure.API_lab.entities.Actor;
import com.nure.API_lab.entities.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository extends JpaRepository<Authorization, Integer> {
}
