package com.nure.API_lab.site;

import com.nure.API_lab.Services.AuthorizationService;
import com.nure.API_lab.entities.Authorization;
import com.nure.API_lab.entities.User;
import com.nure.API_lab.repository.AuthorizationRepository;
import com.nure.API_lab.repository.RoleRepository;
import com.nure.API_lab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthorizationController {
    @Autowired
    AuthorizationService service;
    @Autowired
    AuthorizationRepository repository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/credentials")
    public String insertUser(Model model) {
        model.addAttribute("authorization", new Authorization());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("path", "/credentials");
        return "authorizationPages/credentialsUser";
    }

    @PostMapping(value = "/credentials")
    public String insertUser(@ModelAttribute Authorization authorization, HttpSession session, @RequestParam("roleId") Integer roleId) {
        User newUser = (User) session.getAttribute("user");
        session.removeAttribute("user");
        authorization.setUser(newUser);
        authorization.setRole(roleRepository.getById(roleId));
        session.setAttribute("userRole", authorization.role);
        userRepository.save(authorization.user);
        repository.save(authorization);
        return "redirect:/allUsers";
    }

    @GetMapping(value = "/login")
    public String login(Model model, @RequestParam(required = false) String errorMessage) {
        model.addAttribute("authorization", new Authorization());
        model.addAttribute("errorMessage", errorMessage);
        return "authorizationPages/login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute Authorization authorization, HttpSession session) {
        if (service.login(authorization)) {
            session.setAttribute("userRole", service.getByEmail(authorization.login).role);
            return "redirect:/allUsers";
        }
        return "redirect:/login?errorMessage=Wrong+email+or+password";
    }
}
