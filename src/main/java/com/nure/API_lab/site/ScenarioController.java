package com.nure.API_lab.site;

import com.nure.API_lab.entities.Scenario;
import com.nure.API_lab.repository.CostumeRepository;
import com.nure.API_lab.repository.ScenarioRepository;
import com.nure.API_lab.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class ScenarioController {
    @Autowired
    CostumeRepository costumeRepository;
    @Autowired
    ScenarioRepository repository;


    @GetMapping(value = "/allScenario")
    public String getAllScenario(Model model){
        model.addAttribute("scenarios", repository.findAll());
        return "scenarioPages/getAllScenario";
    }
    @GetMapping(value = "/allScenario/{id}")
    public String getScenarioById(Model model, @PathVariable Integer id){
        ArrayList<Scenario> list = new ArrayList<>();
        list.add(repository.getById(id));
        model.addAttribute("scenarios", list);
        model.addAttribute("getById", true);
        return "scenarioPages/getAllScenario";
    }
    @GetMapping(value = "/deleteScenario/{id}")
    public String deleteScenario(@PathVariable Integer id){
        repository.deleteById(id);
        return "redirect:/allScenario";
    }
    @GetMapping(value = "/insertScenario")
    public String insertScenario(Model model){
        model.addAttribute("scenario", new Scenario());
        model.addAttribute("costumeList", costumeRepository.findAll());
        model.addAttribute("path", "/insertScenario");
        return "scenarioPages/insertScenario";
    }
    @PostMapping(value = "/insertScenario")
    public String insertScenario(@ModelAttribute Scenario scenario, @RequestParam("costumeId") Integer[] costumeId){
        scenario.setCostumes(costumeRepository.findAllById(Arrays.asList(costumeId)));
        repository.save(scenario);
        return "redirect:/allScenario";
    }
    @GetMapping(value = "/updateScenario/{id}")
    public String updateScenario(@PathVariable Integer id, Model model){
        model.addAttribute("scenario", repository.getById(id));
        model.addAttribute("costumeList", costumeRepository.findAll());
        model.addAttribute("path", "/updateScenario/"+id);
        model.addAttribute("update", true);
        return "scenarioPages/insertScenario";
    }
    @PostMapping(value = "/updateScenario/{id}")
    public String updateScenario(@ModelAttribute Scenario scenario, @RequestParam("costumeId") Integer[] costumeId){
        scenario.setCostumes(costumeRepository.findAllById(Arrays.asList(costumeId)));
        repository.save(scenario);
        return "redirect:/allScenario";
    }
}
