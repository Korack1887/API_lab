package com.nure.API_lab;


import com.nure.API_lab.entities.EventOrder;
import com.nure.API_lab.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(OrderRepository repository) {

        return args -> {
           List<EventOrder> orderList = repository.findAll();
            System.out.println(orderList);
        };
    }
}
