//package com.nure.API_lab;
//
//
//import com.nure.API_lab.entities.Category;
//import com.nure.API_lab.entities.Costume;
//import com.nure.API_lab.entities.Scenario;
//import com.nure.API_lab.repository.CategoryRepository;
//import com.nure.API_lab.repository.CostumeRepository;
//import com.nure.API_lab.repository.ScenarioRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
//@Configuration
//public class LoadDatabase {
//
//    @Bean
//    CommandLineRunner initDatabase(CategoryRepository repository, CostumeRepository costumeRepository, ScenarioRepository scenarioRepository) {
//        Category category = new Category();
//        Scenario scenario = new Scenario();
//        Costume costume = new Costume();
//        category.setName("qwe");
//        category.setDescription("asd");
//        scenario.setName("zxc");
//        scenario.setDescription("dfg");
//        scenario.setPrice(123);
//        costume.setName("qweqwe");
//        costume.setDescription("zxcasd");
//        costume.setPrice(5464);
//        scenario.setCostumes(Arrays.asList(costume));
//        category.setScenarios(Arrays.asList(scenario));
//        return args -> {
//            costumeRepository.save(costume);
//            scenarioRepository.save(scenario);
//            repository.save(category);
//        };
//    }
//}
