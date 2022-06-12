package com.nure.API_lab.site;

import com.nure.API_lab.entities.Costume;
import com.nure.API_lab.repository.CostumeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class CostumeController {
    CostumeRepository repository;

    public CostumeController(CostumeRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/allCostumes")
    public String getAllCostumes(Model model){
        model.addAttribute("costumes", repository.findAll());
        return "costumePages/getAllCostumes";
    }
    @GetMapping(value = "/allCostumes/{id}")
    public String getCostumeById(Model model, @PathVariable Long id){
        ArrayList<Costume> list = new ArrayList<>();
        list.add(repository.getById(id));
        model.addAttribute("costumes", list);
        model.addAttribute("getById", true);
        return "costumePages/getAllCostumes";
    }
    @GetMapping(value = "/deleteCostume/{id}")
    public String deleteCostume(@PathVariable Long id){
        repository.deleteById(id);
        return "redirect:/allCostumes";
    }
    @GetMapping(value = "/insertCostume")
    public String insertCostume(Model model){
        model.addAttribute("costume", new Costume());
        model.addAttribute("path", "/insertCostume");
        return "costumePages/insertCostume";
    }
    @PostMapping(value = "/insertCostume")
    public String insertCostume(@ModelAttribute Costume costume){
        repository.save(costume);
        return "redirect:/allCostumes";
    }
    @GetMapping(value = "/updateCostume/{id}")
    public String updateCostume(@PathVariable Long id, Model model){
        model.addAttribute("costume", repository.getById(id));
        model.addAttribute("path", "/updateCostume/"+id);
        model.addAttribute("update", true);
        return "costumePages/insertCostume";
    }
    @PostMapping(value = "/updateCostume/{id}")
    public String updateCostume(@ModelAttribute Costume costume){
        repository.save(costume);
        return "redirect:/allCostumes";
    }
}
