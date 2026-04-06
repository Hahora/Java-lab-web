package com.example.gamehub.repository;

import com.example.gamehub.domain.Developer;
import com.example.gamehub.domain.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcGameRepository implements GameRepository {

    private final JdbcTemplate jdbc;

    public JdbcGameRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private Game mapGame(java.sql.ResultSet rs, int row) throws java.sql.SQLException {
        Developer dev = new Developer(
            rs.getLong("dev_id"),
            rs.getString("dev_name"),
            rs.getDouble("dev_rating"),
            rs.getInt("dev_founded"),
            rs.getString("dev_country"),
            null, null
        );
        return new Game(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getInt("release_year"),
            rs.getString("genre"),
            dev,
            rs.getString("system_requirements"),
            rs.getDouble("price"),
            rs.getBoolean("multiplayer"),
            rs.getInt("metacritic_score"),
            rs.getString("description")
        );
    }

    @Override
    public List<Game> findAll() {
        return jdbc.query(
            "SELECT g.id, g.title, g.release_year, g.genre, g.system_requirements, " +
            "g.price, g.multiplayer, g.metacritic_score, g.description, " +
            "d.id as dev_id, d.name as dev_name, d.rating as dev_rating, " +
            "d.founded_year as dev_founded, d.country as dev_country " +
            "FROM games g LEFT JOIN developers d ON g.developer_id = d.id ORDER BY g.title",
            this::mapGame
        );
    }

    @Override
    public Optional<Game> findById(Long id) {
        List<Game> results = jdbc.query(
            "SELECT g.id, g.title, g.release_year, g.genre, g.system_requirements, " +
            "g.price, g.multiplayer, g.metacritic_score, g.description, " +
            "d.id as dev_id, d.name as dev_name, d.rating as dev_rating, " +
            "d.founded_year as dev_founded, d.country as dev_country " +
            "FROM games g LEFT JOIN developers d ON g.developer_id = d.id WHERE g.id = ?",
            this::mapGame, id
        );
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public Game save(Game game) {
        Long devId = game.getDeveloper() != null ? game.getDeveloper().getId() : null;
        if (game.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO games (title, release_year, genre, developer_id, system_requirements, price, multiplayer, metacritic_score, description) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, game.getTitle());
                ps.setObject(2, game.getReleaseYear());
                ps.setString(3, game.getGenre());
                ps.setObject(4, devId);
                ps.setString(5, game.getSystemRequirements());
                ps.setObject(6, game.getPrice());
                ps.setObject(7, game.getMultiplayer());
                ps.setObject(8, game.getMetacriticScore());
                ps.setString(9, game.getDescription());
                return ps;
            }, keyHolder);
            game.setId(keyHolder.getKey().longValue());
        } else {
            jdbc.update(
                "UPDATE games SET title=?, release_year=?, genre=?, developer_id=?, " +
                "system_requirements=?, price=?, multiplayer=?, metacritic_score=?, description=? WHERE id=?",
                game.getTitle(), game.getReleaseYear(), game.getGenre(), devId,
                game.getSystemRequirements(), game.getPrice(), game.getMultiplayer(),
                game.getMetacriticScore(), game.getDescription(), game.getId()
            );
        }
        return game;
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("DELETE FROM games WHERE id = ?", id);
    }
}
