package com.example.gamehub.repository;

import com.example.gamehub.domain.Developer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcDeveloperRepository implements DeveloperRepository {

    private final JdbcTemplate jdbc;

    public JdbcDeveloperRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Developer> findAll() {
        return jdbc.query(
            "SELECT id, name, rating, founded_year, country, description, website FROM developers ORDER BY name",
            (rs, row) -> new Developer(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDouble("rating"),
                rs.getInt("founded_year"),
                rs.getString("country"),
                rs.getString("description"),
                rs.getString("website")
            )
        );
    }

    @Override
    public Optional<Developer> findById(Long id) {
        List<Developer> results = jdbc.query(
            "SELECT id, name, rating, founded_year, country, description, website FROM developers WHERE id = ?",
            (rs, row) -> new Developer(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDouble("rating"),
                rs.getInt("founded_year"),
                rs.getString("country"),
                rs.getString("description"),
                rs.getString("website")
            ),
            id
        );
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public Developer save(Developer developer) {
        if (developer.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO developers (name, rating, founded_year, country, description, website) VALUES (?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, developer.getName());
                ps.setDouble(2, developer.getRating() != null ? developer.getRating() : 0.0);
                ps.setObject(3, developer.getFoundedYear());
                ps.setString(4, developer.getCountry());
                ps.setString(5, developer.getDescription());
                ps.setString(6, developer.getWebsite());
                return ps;
            }, keyHolder);
            developer.setId(keyHolder.getKey().longValue());
        } else {
            jdbc.update(
                "UPDATE developers SET name=?, rating=?, founded_year=?, country=?, description=?, website=? WHERE id=?",
                developer.getName(), developer.getRating(), developer.getFoundedYear(),
                developer.getCountry(), developer.getDescription(), developer.getWebsite(),
                developer.getId()
            );
        }
        return developer;
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("DELETE FROM developers WHERE id = ?", id);
    }
}
