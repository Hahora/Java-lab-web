package com.example.gamehub.repository;

import com.example.gamehub.domain.Developer;
import java.util.List;
import java.util.Optional;

public interface DeveloperRepository {
    List<Developer> findAll();
    Optional<Developer> findById(Long id);
    Developer save(Developer developer);
    void deleteById(Long id);
}
