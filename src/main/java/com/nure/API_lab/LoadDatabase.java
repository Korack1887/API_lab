package com.nure.API_lab;


import com.nure.API_lab.entities.*;
import com.nure.API_lab.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;

@Configuration
public class LoadDatabase {
@Autowired
    RoleRepository roleRepository;
@Autowired
    StatusRepository statusRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            try {
                roleRepository.save(new Role("USER", 1));
                roleRepository.save(new Role("ADMIN", 2));
                statusRepository.save(new Status("IN DRAFT", 1));
                statusRepository.save(new Status("FORMED", 2));
                statusRepository.save(new Status("ACCEPTED", 3));
                statusRepository.save(new Status("DECLINED", 4));
                statusRepository.save(new Status("DONE", 5));
            }
            catch (DataIntegrityViolationException e){
                e.printStackTrace();
            }
        };
    }
}
