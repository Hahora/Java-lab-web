package com.example.gamehub.controller;

import com.example.gamehub.domain.Developer;
import com.example.gamehub.repository.DeveloperRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperRepository developerRepo;

    public DeveloperController(DeveloperRepository developerRepo) {
        this.developerRepo = developerRepo;
    }

    // Чтение — список всех разработчиков
    @GetMapping
    public String list(Model model) {
        model.addAttribute("developers", developerRepo.findAll());
        return "developers/list";
    }

    // Чтение — детали одного разработчика
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        developerRepo.findById(id).ifPresent(d -> model.addAttribute("developer", d));
        return "developers/detail";
    }

    // Запись — форма создания
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("developer", new Developer());
        return "developers/form";
    }

    // Запись — сохранение нового разработчика с валидацией
    @PostMapping
    public String save(@Valid @ModelAttribute Developer developer, Errors errors) {
        if (errors.hasErrors()) {
            return "developers/form";
        }
        developerRepo.save(developer);
        return "redirect:/developers";
    }

    // Модификация — форма редактирования
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        developerRepo.findById(id).ifPresent(d -> model.addAttribute("developer", d));
        return "developers/form";
    }

    // Модификация — сохранение изменений с валидацией
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute Developer developer,
                         Errors errors) {
        if (errors.hasErrors()) {
            return "developers/form";
        }
        developer.setId(id);
        developerRepo.save(developer);
        return "redirect:/developers";
    }

    // Удаление
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        developerRepo.deleteById(id);
        return "redirect:/developers";
    }
}
