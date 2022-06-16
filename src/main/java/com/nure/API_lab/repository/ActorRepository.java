package com.nure.API_lab.repository;

import com.nure.API_lab.entities.Actor;
import com.nure.API_lab.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
