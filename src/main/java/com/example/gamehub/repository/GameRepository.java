package com.example.gamehub.repository;

import com.example.gamehub.domain.Game;
import java.util.List;
import java.util.Optional;

public interface GameRepository {
    List<Game> findAll();
    Optional<Game> findById(Long id);
    Game save(Game game);
    void deleteById(Long id);
}
