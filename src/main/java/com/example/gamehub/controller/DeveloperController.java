package com.example.gamehub.controller;

import com.example.gamehub.domain.Developer;
import com.example.gamehub.repository.DeveloperRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperRepository developerRepo;

    public DeveloperController(DeveloperRepository developerRepo) {
        this.developerRepo = developerRepo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("developers", developerRepo.findAll());
        return "developers/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        developerRepo.findById(id).ifPresent(d -> model.addAttribute("developer", d));
        return "developers/detail";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("developer", new Developer());
        return "developers/form";
    }

    @PostMapping
    public String save(@ModelAttribute Developer developer) {
        developerRepo.save(developer);
        return "redirect:/developers";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        developerRepo.findById(id).ifPresent(d -> model.addAttribute("developer", d));
        return "developers/form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        developerRepo.deleteById(id);
        return "redirect:/developers";
    }
}
