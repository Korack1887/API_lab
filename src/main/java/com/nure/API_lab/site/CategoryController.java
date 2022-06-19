package com.nure.API_lab.site;


import com.nure.API_lab.entities.Category;
import com.nure.API_lab.entities.Scenario;
import com.nure.API_lab.repository.CategoryRepository;
import com.nure.API_lab.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository repository;
    @Autowired
    ScenarioRepository scenarioRepository;


    @GetMapping(value = "/")
    public String getDefaultPage(Model model) {
        return getAllCategories(model);
    }

    @GetMapping(value = "/allCategories")
    public String getAllCategories(Model model) {
        model.addAttribute("categories", repository.findAll());
        model.addAttribute("scenarioList", scenarioRepository.findAll());
        return "categoryPages/getAllCategories";
    }

    @GetMapping(value = "/allCategories/{id}")
    public String getCategoryById(Model model, @PathVariable Integer id) {
        ArrayList<Category> list = new ArrayList<>();
        list.add(repository.getById(id));
        model.addAttribute("categories", list);
        model.addAttribute("getById", true);
        return "categoryPages/getAllCategories";
    }

    @GetMapping(value = "/deleteCategory/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        repository.deleteById(id);
        return "redirect:/allCategories";
    }

    @GetMapping(value = "/insertCategory")
    public String insertCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("scenarioList", scenarioRepository.findAll());
        model.addAttribute("path", "/insertCategory");
        return "categoryPages/insertCategory";
    }

    @PostMapping(value = "/insertCategory")
    public String insertCategory(@ModelAttribute Category category, @RequestParam("scenarioId") Integer[] scenarioId) {
        List<Scenario> scenarios = scenarioRepository.findAllById(Arrays.asList(scenarioId));
        category.setScenarios(scenarios);
        repository.save(category);
        return "redirect:/allCategories";
    }

    @GetMapping(value = "/updateCategory/{id}")
    public String updateCategory(@PathVariable Integer id, Model model) {
        model.addAttribute("category", repository.getById(id));
        model.addAttribute("scenarioList", scenarioRepository.findAll());
        model.addAttribute("path", "/updateCategory/" + id);
        model.addAttribute("update", true);
        return "categoryPages/insertCategory";
    }

    @PostMapping(value = "/updateCategory/{id}")
    public String updateCategory(@ModelAttribute Category category, @RequestParam("scenarioId") Integer[] scenarioId) {
        List<Scenario> animator = scenarioRepository.findAllById(Arrays.asList(scenarioId));
        category.setScenarios(animator);
        repository.save(category);
        return "redirect:/allCategories";
    }
}
