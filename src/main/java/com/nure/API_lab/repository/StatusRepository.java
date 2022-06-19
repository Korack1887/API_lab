package com.nure.API_lab.repository;

import com.nure.API_lab.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    List<Status> findByName(String name);
}
