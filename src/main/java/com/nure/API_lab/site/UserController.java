package com.nure.API_lab.site;

import com.nure.API_lab.Services.UserService;
import com.nure.API_lab.entities.User;
import com.nure.API_lab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class UserController {
    UserRepository repository;
    @Autowired
    UserService userService;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/login")
    public String login(Model model, @RequestParam(required = false) String errorMessage) {
        model.addAttribute("user", new User());
        model.addAttribute("errorMessage", errorMessage);
        return "userPages/login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute User user, HttpSession session) {
        if (userService.login(user)) {
            session.setAttribute("userRole", userService.getUserByEmail(user.email).role);
            return "redirect:/allUsers";
        }
        return "redirect:/login?errorMessage=Wrong+email+or+password";
    }

    @GetMapping(value = "/allUsers")
    public String getAllUsers(Model model) {
        model.addAttribute("users", repository.findAll());
        return "userPages/getAllUsers";
    }

    @GetMapping(value = "/allUsers/{id}")
    public String getUserById(Model model, @PathVariable Long id) {
        ArrayList<User> list = new ArrayList<>();
        list.add(repository.getById(id));
        model.addAttribute("users", list);
        model.addAttribute("getById", true);
        return "userPages/getAllUsers";
    }

    @GetMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/allUsers";
    }

    @GetMapping(value = "/registration")
    public String insertUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("path", "/registration");
        return "userPages/insertUser";
    }

    @PostMapping(value = "/registration")
    public String insertUser(@ModelAttribute User user, HttpSession session) {
        session.setAttribute("userRole", userService.getUserByEmail(user.email).role);
        repository.save(user);
        return "redirect:/allUsers";
    }

    @GetMapping(value = "/updateUser/{id}")
    public String updateUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", repository.getById(id));
        model.addAttribute("path", "/updateUser/" + id);
        model.addAttribute("update", true);
        return "userPages/insertUser";
    }

    @PostMapping(value = "/updateUser/{id}")
    public String updateUser(@ModelAttribute User user) {
        repository.save(user);
        return "redirect:/allUsers";
    }
}
