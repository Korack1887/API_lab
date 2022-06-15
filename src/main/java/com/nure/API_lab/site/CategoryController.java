package com.nure.API_lab.site;

import com.nure.API_lab.entities.Animator;
import com.nure.API_lab.entities.Category;
import com.nure.API_lab.entities.Costume;
import com.nure.API_lab.repository.AnimatorRepository;
import com.nure.API_lab.repository.CategoryRepository;
import com.nure.API_lab.repository.CostumeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CategoryController {
    CategoryRepository repository;
    AnimatorRepository animatorRepository;
    CostumeRepository costumeRepository;

    public CategoryController(CategoryRepository repository, AnimatorRepository animatorRepository, CostumeRepository costumeRepository) {
        this.repository = repository;
        this.animatorRepository = animatorRepository;
        this.costumeRepository = costumeRepository;
    }

    @GetMapping(value = "/")
    public String getDefaultPage(Model model) {
        model.addAttribute("categories", repository.findAll());
        return "categoryPages/getAllCategories";
    }

    @GetMapping(value = "/allCategories")
    public String getAllCategories(Model model) {
        model.addAttribute("categories", repository.findAll());
        return "categoryPages/getAllCategories";
    }

    @GetMapping(value = "/allCategories/{id}")
    public String getCategoryById(Model model, @PathVariable Long id) {
        ArrayList<Category> list = new ArrayList<>();
        list.add(repository.getById(id));
        model.addAttribute("categories", list);
        model.addAttribute("getById", true);
        return "categoryPages/getAllCategories";
    }

    @GetMapping(value = "/deleteCategory/{id}")
    public String deleteCategory(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/allCategories";
    }

    @GetMapping(value = "/insertCategory")
    public String insertCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("animatorList", animatorRepository.findAll());
        model.addAttribute("costumeList", costumeRepository.findAll());
        model.addAttribute("category", new Category());
        model.addAttribute("path", "/insertCategory");
        return "categoryPages/insertCategory";
    }

    @PostMapping(value = "/insertCategory")
    public String insertCategory(@ModelAttribute Category category, @RequestParam("animatorId") Long[] animatorId, @RequestParam("costumeId") Long[] costumeId) {
        List<Animator> animator = animatorRepository.findAllById(Arrays.asList(animatorId));
        List<Costume> costume = costumeRepository.findAllById(Arrays.asList(costumeId));
        category.setAnimators(animator);
        category.setCostumes(costume);
        repository.save(category);
        return "redirect:/allCategories";
    }

    @GetMapping(value = "/updateCategory/{id}")
    public String updateCategory(@PathVariable Long id, Model model) {
        model.addAttribute("category", repository.getById(id));
        model.addAttribute("animatorList", animatorRepository.findAll());
        model.addAttribute("costumeList", costumeRepository.findAll());
        model.addAttribute("path", "/updateCategory/" + id);
        model.addAttribute("update", true);
        return "categoryPages/insertCategory";
    }

    @PostMapping(value = "/updateCategory/{id}")
    public String updateCategory(@ModelAttribute Category category, @RequestParam("animatorId") Long[] animatorId, @RequestParam("costumeId") Long[] costumeId) {
        List<Animator> animator = animatorRepository.findAllById(Arrays.asList(animatorId));
        List<Costume> costume = costumeRepository.findAllById(Arrays.asList(costumeId));
        category.setAnimators(animator);
        category.setCostumes(costume);
        repository.save(category);
        return "redirect:/allCategories";
    }
}
