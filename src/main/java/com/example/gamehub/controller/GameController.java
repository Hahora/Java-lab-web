package com.example.gamehub.controller;

import com.example.gamehub.domain.Game;
import com.example.gamehub.repository.DeveloperRepository;
import com.example.gamehub.repository.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameRepository gameRepo;
    private final DeveloperRepository developerRepo;

    public GameController(GameRepository gameRepo, DeveloperRepository developerRepo) {
        this.gameRepo = gameRepo;
        this.developerRepo = developerRepo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("games", gameRepo.findAll());
        return "games/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        gameRepo.findById(id).ifPresent(g -> model.addAttribute("game", g));
        return "games/detail";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("developers", developerRepo.findAll());
        return "games/form";
    }

    @PostMapping
    public String save(@ModelAttribute Game game) {
        if (game.getDeveloper() != null && game.getDeveloper().getId() != null) {
            developerRepo.findById(game.getDeveloper().getId())
                    .ifPresent(game::setDeveloper);
        }
        gameRepo.save(game);
        return "redirect:/games";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        gameRepo.findById(id).ifPresent(g -> model.addAttribute("game", g));
        model.addAttribute("developers", developerRepo.findAll());
        return "games/form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        gameRepo.deleteById(id);
        return "redirect:/games";
    }
}
