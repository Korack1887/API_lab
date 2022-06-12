package com.nure.API_lab.site;

import com.nure.API_lab.entities.Animator;
import com.nure.API_lab.repository.AnimatorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AnimatorController {
    AnimatorRepository repository;

    public AnimatorController(AnimatorRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/allActors")
    public String getAllAnimators(Model model){
        model.addAttribute("animators", repository.findAll());
        return "animatorPages/getAllAnimators";
    }
    @GetMapping(value = "/allActors/{id}")
    public String getAnimatorById(Model model, @PathVariable Long id){
        ArrayList<Animator> list = new ArrayList<>();
        list.add(repository.getById(id));
        model.addAttribute("animators", list);
        model.addAttribute("getById", true);
        return "animatorPages/getAllAnimators";
    }
    @GetMapping(value = "/deleteActor/{id}")
    public String deleteAnimator(@PathVariable Long id){
        repository.deleteById(id);
        return "redirect:/allActors";
    }
    @GetMapping(value = "/insertActor")
    public String insertAnimator(Model model){
        model.addAttribute("animator", new Animator());
        model.addAttribute("path", "/insertAnimator");
        return "animatorPages/insertAnimator";
    }
    @PostMapping(value = "/insertActor")
    public String insertAnimator(@ModelAttribute Animator animator){
        repository.save(animator);
        return "redirect:/allAnimators";
    }
    @GetMapping(value = "/updateActor/{id}")
    public String updateAnimator(@PathVariable Long id, Model model){
        model.addAttribute("animator", repository.getById(id));
        model.addAttribute("path", "/updateAnimator/"+id);
        model.addAttribute("update", true);
        return "animatorPages/insertAnimator";
    }
    @PostMapping(value = "/updateActor/{id}")
    public String updateAnimator(@ModelAttribute Animator animator){
        repository.save(animator);
        return "redirect:/allActors";
    }
}
