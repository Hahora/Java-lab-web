package controller;

import dao.GameHubConnBuilder;
import domain.Game;
import domain.Developer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/games")
public class GameServlet extends HttpServlet {

    private static final String SELECT_ALL_GAMES =
            "SELECT g.id, g.title, g.release_year, g.genre, " +
                    "g.system_requirements, g.price, g.multiplayer, " +
                    "g.metacritic_score, g.description, " +
                    "d.id as dev_id, d.name as dev_name, d.rating as dev_rating, " +
                    "d.founded_year as dev_founded, d.country as dev_country, " +
                    "d.description as dev_description, d.website as dev_website " +
                    "FROM games g " +
                    "LEFT JOIN developers d ON g.developer_id = d.id " +
                    "ORDER BY g.title";

    private static final String SELECT_ALL_DEVELOPERS =
            "SELECT id, name, rating, founded_year, country, description, website " +
                    "FROM developers ORDER BY name";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Game> games = new ArrayList<>();
        List<Developer> developers = new ArrayList<>();

        GameHubConnBuilder builder = new GameHubConnBuilder();

        try (Connection conn = builder.getConnection()) {

            System.out.println("✅ GameServlet: Подключение к БД установлено");

            // Проверяем наличие сообщений из сессии
            String message = (String) request.getSession().getAttribute("message");
            String error = (String) request.getSession().getAttribute("error");

            if (message != null) {
                request.setAttribute("message", message);
                request.getSession().removeAttribute("message");
            }

            if (error != null) {
                request.setAttribute("error", error);
                request.getSession().removeAttribute("error");
            }

            // 1. Загружаем разработчиков для выпадающего списка
            try (Statement devStmt = conn.createStatement();
                 ResultSet devRs = devStmt.executeQuery(SELECT_ALL_DEVELOPERS)) {

                while (devRs.next()) {
                    Developer developer = new Developer(
                            devRs.getLong("id"),
                            devRs.getString("name"),
                            devRs.getDouble("rating"),
                            devRs.getInt("founded_year"),
                            devRs.getString("country"),
                            devRs.getString("description"),
                            devRs.getString("website")
                    );
                    developers.add(developer);
                }
                System.out.println("✅ Загружено разработчиков: " + developers.size());
            }

            // 2. Загружаем игры с информацией о разработчиках
            try (Statement gameStmt = conn.createStatement();
                 ResultSet gameRs = gameStmt.executeQuery(SELECT_ALL_GAMES)) {

                while (gameRs.next()) {
                    Developer developer = null;
                    Long devId = gameRs.getLong("dev_id");

                    if (!gameRs.wasNull()) {
                        developer = new Developer(
                                devId,
                                gameRs.getString("dev_name"),
                                gameRs.getDouble("dev_rating"),
                                gameRs.getInt("dev_founded"),
                                gameRs.getString("dev_country"),
                                gameRs.getString("dev_description"),
                                gameRs.getString("dev_website")
                        );
                    }

                    Game game = new Game(
                            gameRs.getLong("id"),
                            gameRs.getString("title"),
                            gameRs.getInt("release_year"),
                            gameRs.getString("genre"),
                            developer,
                            gameRs.getString("system_requirements"),
                            gameRs.getDouble("price"),
                            gameRs.getBoolean("multiplayer"),
                            gameRs.getInt("metacritic_score"),
                            gameRs.getString("description")
                    );

                    games.add(game);
                }
                System.out.println("✅ Загружено игр: " + games.size());
            }

        } catch (SQLException e) {
            System.err.println("❌ Ошибка SQL в GameServlet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Ошибка загрузки данных: " + e.getMessage());
        }

        request.setAttribute("games", games);
        request.setAttribute("developers", developers);
        request.getRequestDispatcher("/WEB-INF/views/games.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Проверяем, это редактирование или добавление
        String idStr = request.getParameter("id");

        // Если есть ID в запросе, это редактирование
        if (idStr != null && !idStr.trim().isEmpty()) {
            updateGame(request, response);
        } else {
            // Иначе это добавление новой игры
            addGame(request, response);
        }
    }

    private void addGame(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String releaseYearStr = request.getParameter("releaseYear");
        String genre = request.getParameter("genre");
        String developerIdStr = request.getParameter("developerId");
        String requirements = request.getParameter("systemRequirements");
        String priceStr = request.getParameter("price");
        String metacriticStr = request.getParameter("metacriticScore");
        String multiplayerStr = request.getParameter("multiplayer");
        String description = request.getParameter("description");

        System.out.println("➕ GameServlet: Добавление новой игры: " + title);

        try {
            // Парсим значения
            Integer releaseYear = (releaseYearStr != null && !releaseYearStr.trim().isEmpty())
                    ? Integer.parseInt(releaseYearStr.trim())
                    : 2023;

            Long developerId = (developerIdStr != null && !developerIdStr.trim().isEmpty())
                    ? Long.parseLong(developerIdStr.trim())
                    : null;

            Double price = (priceStr != null && !priceStr.trim().isEmpty())
                    ? Double.parseDouble(priceStr.trim())
                    : 0.0;

            Integer metacriticScore = (metacriticStr != null && !metacriticStr.trim().isEmpty())
                    ? Integer.parseInt(metacriticStr.trim())
                    : null;

            Boolean multiplayer = (multiplayerStr != null)
                    ? Boolean.parseBoolean(multiplayerStr)
                    : false;

            // Валидация
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Название игры не может быть пустым");
            }

            if (releaseYear < 1970 || releaseYear > 2030) {
                throw new IllegalArgumentException("Год выпуска должен быть между 1970 и 2030");
            }

            if (price < 0) {
                throw new IllegalArgumentException("Цена не может быть отрицательной");
            }

            if (metacriticScore != null && (metacriticScore < 0 || metacriticScore > 100)) {
                throw new IllegalArgumentException("Оценка Metacritic должна быть от 0 до 100");
            }

            GameHubConnBuilder builder = new GameHubConnBuilder();
            try (Connection conn = builder.getConnection()) {
                String insertQuery = "INSERT INTO games (title, release_year, genre, developer_id, " +
                        "system_requirements, price, multiplayer, metacritic_score, description) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                    pstmt.setString(1, title.trim());
                    pstmt.setInt(2, releaseYear);
                    pstmt.setString(3, genre != null ? genre.trim() : "");

                    if (developerId != null) {
                        pstmt.setLong(4, developerId);
                    } else {
                        pstmt.setNull(4, Types.INTEGER);
                    }

                    pstmt.setString(5, requirements != null ? requirements.trim() : "");
                    pstmt.setDouble(6, price);
                    pstmt.setBoolean(7, multiplayer);

                    if (metacriticScore != null) {
                        pstmt.setInt(8, metacriticScore);
                    } else {
                        pstmt.setNull(8, Types.INTEGER);
                    }

                    pstmt.setString(9, description != null ? description.trim() : "");

                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("✅ Игра успешно добавлена в БД: " + title);
                        request.getSession().setAttribute("message", "Игра '" + title + "' успешно добавлена!");
                    } else {
                        System.err.println("❌ Игра не была добавлена");
                        request.getSession().setAttribute("error", "Ошибка при добавлении игры в базу данных");
                    }
                }
            }

        } catch (Exception e) {
            handleError(e, request, "добавлении игры");
        }

        response.sendRedirect(request.getContextPath() + "/games");
    }

    private void updateGame(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String title = request.getParameter("title");
        String releaseYearStr = request.getParameter("releaseYear");
        String genre = request.getParameter("genre");
        String developerIdStr = request.getParameter("developerId");
        String requirements = request.getParameter("systemRequirements");
        String priceStr = request.getParameter("price");
        String metacriticStr = request.getParameter("metacriticScore");
        String multiplayerStr = request.getParameter("multiplayer");
        String description = request.getParameter("description");

        System.out.println("✏️ GameServlet: Редактирование игры ID: " + idStr + ", название: " + title);

        try {
            Long id = Long.parseLong(idStr.trim());
            Integer releaseYear = Integer.parseInt(releaseYearStr.trim());
            Long developerId = (developerIdStr != null && !developerIdStr.trim().isEmpty())
                    ? Long.parseLong(developerIdStr.trim())
                    : null;
            Double price = (priceStr != null && !priceStr.trim().isEmpty())
                    ? Double.parseDouble(priceStr.trim())
                    : 0.0;
            Integer metacriticScore = (metacriticStr != null && !metacriticStr.trim().isEmpty())
                    ? Integer.parseInt(metacriticStr.trim())
                    : null;
            Boolean multiplayer = (multiplayerStr != null)
                    ? Boolean.parseBoolean(multiplayerStr)
                    : false;

            // Валидация
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Название игры не может быть пустым");
            }

            GameHubConnBuilder builder = new GameHubConnBuilder();
            try (Connection conn = builder.getConnection()) {
                String updateQuery = "UPDATE games SET title = ?, release_year = ?, genre = ?, developer_id = ?, " +
                        "system_requirements = ?, price = ?, multiplayer = ?, metacritic_score = ?, description = ? " +
                        "WHERE id = ?";

                try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                    pstmt.setString(1, title.trim());
                    pstmt.setInt(2, releaseYear);
                    pstmt.setString(3, genre != null ? genre.trim() : "");

                    if (developerId != null) {
                        pstmt.setLong(4, developerId);
                    } else {
                        pstmt.setNull(4, Types.INTEGER);
                    }

                    pstmt.setString(5, requirements != null ? requirements.trim() : "");
                    pstmt.setDouble(6, price);
                    pstmt.setBoolean(7, multiplayer);

                    if (metacriticScore != null) {
                        pstmt.setInt(8, metacriticScore);
                    } else {
                        pstmt.setNull(8, Types.INTEGER);
                    }

                    pstmt.setString(9, description != null ? description.trim() : "");
                    pstmt.setLong(10, id);

                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("✅ Игра успешно обновлена: " + title + " (ID: " + id + ")");
                        request.getSession().setAttribute("message", "Игра '" + title + "' успешно обновлена!");
                    } else {
                        System.err.println("❌ Игра не была обновлена (ID " + id + " не найден)");
                        request.getSession().setAttribute("error", "Игра не найдена или не была обновлена");
                    }
                }
            }

        } catch (Exception e) {
            handleError(e, request, "обновлении игры");
        }

        response.sendRedirect(request.getContextPath() + "/games");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.trim().isEmpty()) {
            System.err.println("❌ ID игры не указан для удаления");
            request.getSession().setAttribute("error", "ID игры не указан");
            response.sendRedirect(request.getContextPath() + "/games");
            return;
        }

        System.out.println("🗑️ GameServlet.doDelete(): Удаление игры ID: " + idStr);

        try {
            Long id = Long.parseLong(idStr.trim());
            String gameTitle = "";

            GameHubConnBuilder builder = new GameHubConnBuilder();
            try (Connection conn = builder.getConnection()) {

                // Сначала получаем название игры для сообщения
                String getNameQuery = "SELECT title FROM games WHERE id = ?";
                try (PreparedStatement getNameStmt = conn.prepareStatement(getNameQuery)) {
                    getNameStmt.setLong(1, id);
                    ResultSet rs = getNameStmt.executeQuery();
                    if (rs.next()) {
                        gameTitle = rs.getString("title");
                    }
                }

                // Удаляем игру
                String deleteQuery = "DELETE FROM games WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                    pstmt.setLong(1, id);
                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("✅ Игра успешно удалена: " + gameTitle + " (ID: " + id + ")");
                        request.getSession().setAttribute("message", "Игра '" + gameTitle + "' успешно удалена!");
                    } else {
                        System.err.println("❌ Игра с ID " + id + " не найдена");
                        request.getSession().setAttribute("error", "Игра с ID " + id + " не найдена");
                    }
                }
            }

        } catch (Exception e) {
            handleError(e, request, "удалении игры");
        }

        response.sendRedirect(request.getContextPath() + "/games");
    }

    // Для обработки DELETE через POST (для совместимости с формами)
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String methodParam = request.getParameter("_method");

        // Если есть параметр _method=DELETE, обрабатываем как DELETE
        if ("DELETE".equalsIgnoreCase(methodParam)) {
            doDelete(request, response);
            return;
        }

        // Иначе используем стандартную обработку
        super.service(request, response);
    }

    // Вспомогательный метод для обработки ошибок
    private void handleError(Exception e, HttpServletRequest request, String action) {
        System.err.println("❌ Ошибка при " + action + ": " + e.getMessage());
        e.printStackTrace();

        if (e instanceof NumberFormatException) {
            request.getSession().setAttribute("error",
                    "Ошибка в числовых полях. Проверьте правильность ввода.");
        } else if (e instanceof IllegalArgumentException) {
            request.getSession().setAttribute("error", e.getMessage());
        } else if (e instanceof SQLException) {
            SQLException sqlEx = (SQLException) e;
            if (sqlEx.getMessage().contains("нарушает ограничение уникальности")) {
                request.getSession().setAttribute("error", "Игра с таким названием уже существует");
            } else if (sqlEx.getMessage().contains("developer_id")) {
                request.getSession().setAttribute("error", "Указанный разработчик не найден");
            } else {
                request.getSession().setAttribute("error", "Ошибка базы данных: " + sqlEx.getMessage());
            }
        } else {
            request.getSession().setAttribute("error", "Ошибка: " + e.getMessage());
        }
    }
}