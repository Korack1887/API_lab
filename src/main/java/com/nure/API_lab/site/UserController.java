package com.nure.API_lab.site;

import com.nure.API_lab.entities.User;
import com.nure.API_lab.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class UserController {
    UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/allUsers")
    public String getAllUsers(Model model){
        model.addAttribute("users", repository.findAll());
        return "userPages/getAllUsers";
    }
    @GetMapping(value = "/allUsers/{id}")
    public String getUserById(Model model, @PathVariable Long id){
        ArrayList<User> list = new ArrayList<>();
        list.add(repository.getById(id));
        model.addAttribute("users", list);
        model.addAttribute("getById", true);
        return "userPages/getAllUsers";
    }
    @GetMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id){
        repository.deleteById(id);
        return "redirect:/allUsers";
    }
    @GetMapping(value = "/insertUser")
    public String insertUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("path", "/insertUser");
        return "userPages/insertUser";
    }
    @PostMapping(value = "/insertUser")
    public String insertUser(@ModelAttribute User user){
        repository.save(user);
        return "redirect:/allUsers";
    }
    @GetMapping(value = "/updateUser/{id}")
    public String updateUser(@PathVariable Long id, Model model){
        model.addAttribute("user", repository.getById(id));
        model.addAttribute("path", "/updateUser/"+id);
        model.addAttribute("update", true);
        return "userPages/insertUser";
    }
    @PostMapping(value = "/updateUser/{id}")
    public String updateUser(@ModelAttribute User user){
        repository.save(user);
        return "redirect:/allUsers";
    }
}
