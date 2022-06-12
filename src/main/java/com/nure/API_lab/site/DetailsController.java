package com.nure.API_lab.site;

import com.nure.API_lab.entities.Details;
import com.nure.API_lab.entities.User;
import com.nure.API_lab.repository.DetailsRepository;
import com.nure.API_lab.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class DetailsController {
    DetailsRepository repository;
    UserRepository userRepository;

    public DetailsController(DetailsRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/allDetails")
    public String getAllDetails(Model model) {
        model.addAttribute("details", repository.findAll());
        return "detailsPages/getAllDetails";
    }

    @GetMapping(value = "/allDetails/{id}")
    public String getDetailsById(Model model, @PathVariable Long id) {
        ArrayList<Details> list = new ArrayList<>();
        list.add(repository.getById(id));
        model.addAttribute("details", list);
        model.addAttribute("getById", true);
        return "detailsPages/getAllDetails";
    }

    @GetMapping(value = "/deleteDetails/{id}")
    public String deleteDetails(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/allDetails";
    }

    @GetMapping(value = "/insertDetails")
    public String insertDetails(Model model) {
        model.addAttribute("details", new Details());
        model.addAttribute("userList", userRepository.findAll());
        model.addAttribute("path", "/insertDetails");
        return "detailsPages/insertDetails";
    }

    @PostMapping(value = "/insertDetails")
    public String insertDetails(@ModelAttribute Details details, @RequestParam("userId") Long userId) {
        User user = userRepository.getById(userId);
        details.setUser(user);
        repository.save(details);
        return "redirect:/allDetails";
    }

    @GetMapping(value = "/updateDetails/{id}")
    public String updateDetails(@PathVariable Long id, Model model) {
        model.addAttribute("details", repository.getById(id));
        model.addAttribute("userList", userRepository.findAll());
        model.addAttribute("path", "/updateDetails/" + id);
        model.addAttribute("update", true);
        return "detailsPages/insertDetails";
    }

    @PostMapping(value = "/updateDetails/{id}")
    public String updateDetails(@ModelAttribute Details details, @RequestParam("userId") Long userId) {
        User user = userRepository.getById(userId);
        details.setUser(user);
        repository.save(details);
        return "redirect:/allDetails";
    }
}
