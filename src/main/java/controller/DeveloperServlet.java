package controller;

import dao.GameHubConnBuilder;
import domain.Developer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/developers")
public class DeveloperServlet extends HttpServlet {

    private static final String SELECT_ALL_DEVELOPERS =
            "SELECT id, name, rating, founded_year, country, description, website " +
                    "FROM developers ORDER BY name";

    private static final String COUNT_GAMES_BY_DEVELOPER =
            "SELECT developer_id, COUNT(*) as game_count FROM games WHERE developer_id IS NOT NULL GROUP BY developer_id";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Developer> developers = new ArrayList<>();
        Map<Long, Integer> gamesCountMap = new HashMap<>();
        GameHubConnBuilder builder = new GameHubConnBuilder();

        try (Connection conn = builder.getConnection()) {

            System.out.println("✅ DeveloperServlet: Подключение к БД установлено");

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

            // 1. Загружаем разработчиков
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SELECT_ALL_DEVELOPERS)) {

                while (rs.next()) {
                    Developer developer = new Developer(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getDouble("rating"),
                            rs.getInt("founded_year"),
                            rs.getString("country"),
                            rs.getString("description"),
                            rs.getString("website")
                    );
                    developers.add(developer);
                }
                System.out.println("✅ Загружено разработчиков: " + developers.size());
            }

            // 2. Подсчитываем количество игр для каждого разработчика
            try (Statement countStmt = conn.createStatement();
                 ResultSet countRs = countStmt.executeQuery(COUNT_GAMES_BY_DEVELOPER)) {

                while (countRs.next()) {
                    Long developerId = countRs.getLong("developer_id");
                    int gameCount = countRs.getInt("game_count");
                    gamesCountMap.put(developerId, gameCount);
                    System.out.println("📊 Разработчик ID " + developerId + " имеет " + gameCount + " игр");
                }
            }

            // 3. Подсчитываем общую статистику
            int totalGames = gamesCountMap.values().stream().mapToInt(Integer::intValue).sum();
            double avgRating = developers.stream()
                    .mapToDouble(Developer::getRating)
                    .average()
                    .orElse(0.0);

            long countriesCount = developers.stream()
                    .filter(d -> d.getCountry() != null && !d.getCountry().isEmpty())
                    .map(Developer::getCountry)
                    .distinct()
                    .count();

            // Добавляем статистику в запрос
            request.setAttribute("totalGames", totalGames);
            request.setAttribute("avgRating", String.format("%.1f", avgRating));
            request.setAttribute("countriesCount", countriesCount);

        } catch (SQLException e) {
            System.err.println("❌ Ошибка SQL в DeveloperServlet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Ошибка загрузки данных: " + e.getMessage());
        }

        request.setAttribute("developers", developers);
        request.setAttribute("gamesCountMap", gamesCountMap);
        request.getRequestDispatcher("/WEB-INF/views/developers.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Проверяем, это редактирование или добавление
        String idStr = request.getParameter("id");

        // Если есть ID в запросе (приходит из формы редактирования), это редактирование
        if (idStr != null && !idStr.trim().isEmpty()) {
            updateDeveloper(request, response);
        } else {
            // Иначе это добавление нового разработчика
            addDeveloper(request, response);
        }
    }

    private void addDeveloper(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String ratingStr = request.getParameter("rating");
        String foundedYearStr = request.getParameter("foundedYear");
        String country = request.getParameter("country");
        String website = request.getParameter("website");
        String description = request.getParameter("description");

        System.out.println("➕ DeveloperServlet: Добавление нового разработчика: " + name);

        try {
            // Парсим числовые значения
            Double rating = (ratingStr != null && !ratingStr.trim().isEmpty())
                    ? Double.parseDouble(ratingStr.trim())
                    : 0.0;

            Integer foundedYear = (foundedYearStr != null && !foundedYearStr.trim().isEmpty())
                    ? Integer.parseInt(foundedYearStr.trim())
                    : null;

            // Валидация
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Название компании не может быть пустым");
            }

            if (rating < 0 || rating > 10) {
                throw new IllegalArgumentException("Рейтинг должен быть от 0 до 10");
            }

            if (foundedYear != null) {
                int currentYear = java.time.Year.now().getValue();
                if (foundedYear < 1800 || foundedYear > currentYear) {
                    throw new IllegalArgumentException("Год основания должен быть между 1800 и " + currentYear);
                }
            }

            // Валидация URL
            if (website != null && !website.trim().isEmpty() && !website.trim().startsWith("http")) {
                website = "https://" + website.trim();
            }

            GameHubConnBuilder builder = new GameHubConnBuilder();
            try (Connection conn = builder.getConnection()) {
                String insertQuery = "INSERT INTO developers (name, rating, founded_year, country, website, description) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

                try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                    pstmt.setString(1, name.trim());
                    pstmt.setDouble(2, rating);

                    if (foundedYear != null) {
                        pstmt.setInt(3, foundedYear);
                    } else {
                        pstmt.setNull(3, Types.INTEGER);
                    }

                    pstmt.setString(4, country != null ? country.trim() : "");
                    pstmt.setString(5, website != null ? website.trim() : "");
                    pstmt.setString(6, description != null ? description.trim() : "");

                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("✅ Разработчик успешно добавлен в БД: " + name);
                        request.getSession().setAttribute("message", "Разработчик '" + name + "' успешно добавлен!");
                    } else {
                        System.err.println("❌ Разработчик не был добавлен");
                        request.getSession().setAttribute("error", "Ошибка при добавлении разработчика в базу данных");
                    }
                }

            } catch (SQLException e) {
                System.err.println("❌ Ошибка SQL при добавлении разработчика: " + e.getMessage());
                e.printStackTrace();

                if (e.getMessage().contains("нарушает ограничение уникальности")) {
                    request.getSession().setAttribute("error", "Разработчик с таким названием уже существует в базе");
                } else if (e.getMessage().contains("check constraint")) {
                    request.getSession().setAttribute("error", "Ошибка валидации данных. Проверьте правильность ввода.");
                } else {
                    request.getSession().setAttribute("error", "Ошибка базы данных: " + e.getMessage());
                }
            }

        } catch (NumberFormatException e) {
            System.err.println("❌ Ошибка формата числа: " + e.getMessage());
            request.getSession().setAttribute("error",
                    "Ошибка в числовых полях. Проверьте правильность ввода рейтинга и года основания.");

        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка валидации: " + e.getMessage());
            request.getSession().setAttribute("error", e.getMessage());

        } catch (Exception e) {
            System.err.println("❌ Неожиданная ошибка: " + e.getMessage());
            e.printStackTrace();
            request.getSession().setAttribute("error", "Неожиданная ошибка: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/developers");
    }

    private void updateDeveloper(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String ratingStr = request.getParameter("rating");
        String foundedYearStr = request.getParameter("foundedYear");
        String country = request.getParameter("country");
        String website = request.getParameter("website");
        String description = request.getParameter("description");

        System.out.println("✏️ DeveloperServlet: Редактирование разработчика ID: " + idStr + ", имя: " + name);

        try {
            Long id = Long.parseLong(idStr.trim());
            Double rating = (ratingStr != null && !ratingStr.trim().isEmpty())
                    ? Double.parseDouble(ratingStr.trim())
                    : 0.0;

            Integer foundedYear = (foundedYearStr != null && !foundedYearStr.trim().isEmpty())
                    ? Integer.parseInt(foundedYearStr.trim())
                    : null;

            // Валидация
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Название компании не может быть пустым");
            }

            if (rating < 0 || rating > 10) {
                throw new IllegalArgumentException("Рейтинг должен быть от 0 до 10");
            }

            // Валидация URL
            if (website != null && !website.trim().isEmpty() && !website.trim().startsWith("http")) {
                website = "https://" + website.trim();
            }

            GameHubConnBuilder builder = new GameHubConnBuilder();
            try (Connection conn = builder.getConnection()) {
                String updateQuery = "UPDATE developers SET name = ?, rating = ?, founded_year = ?, " +
                        "country = ?, website = ?, description = ? WHERE id = ?";

                try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                    pstmt.setString(1, name.trim());
                    pstmt.setDouble(2, rating);

                    if (foundedYear != null) {
                        pstmt.setInt(3, foundedYear);
                    } else {
                        pstmt.setNull(3, Types.INTEGER);
                    }

                    pstmt.setString(4, country != null ? country.trim() : "");
                    pstmt.setString(5, website != null ? website.trim() : "");
                    pstmt.setString(6, description != null ? description.trim() : "");
                    pstmt.setLong(7, id);

                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("✅ Разработчик успешно обновлен: " + name + " (ID: " + id + ")");
                        request.getSession().setAttribute("message", "Разработчик '" + name + "' успешно обновлен!");
                    } else {
                        System.err.println("❌ Разработчик не был обновлен (ID " + id + " не найден)");
                        request.getSession().setAttribute("error", "Разработчик не найден или не был обновлен");
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.err.println("❌ Неверный формат ID или чисел: " + e.getMessage());
            request.getSession().setAttribute("error", "Неверный формат данных. Проверьте правильность ввода.");

        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка валидации: " + e.getMessage());
            request.getSession().setAttribute("error", e.getMessage());

        } catch (SQLException e) {
            System.err.println("❌ Ошибка SQL при обновлении разработчика: " + e.getMessage());
            e.printStackTrace();
            request.getSession().setAttribute("error", "Ошибка базы данных: " + e.getMessage());

        } catch (Exception e) {
            System.err.println("❌ Неожиданная ошибка при обновлении: " + e.getMessage());
            e.printStackTrace();
            request.getSession().setAttribute("error", "Неожиданная ошибка: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/developers");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.trim().isEmpty()) {
            System.err.println("❌ ID разработчика не указан для удаления");
            request.getSession().setAttribute("error", "ID разработчика не указан");
            response.sendRedirect(request.getContextPath() + "/developers");
            return;
        }

        System.out.println("🗑️ DeveloperServlet.doDelete(): Удаление разработчика ID: " + idStr);

        try {
            Long id = Long.parseLong(idStr.trim());
            String developerName = "";

            GameHubConnBuilder builder = new GameHubConnBuilder();
            try (Connection conn = builder.getConnection()) {

                // Сначала получаем имя разработчика для сообщения
                String getNameQuery = "SELECT name FROM developers WHERE id = ?";
                try (PreparedStatement getNameStmt = conn.prepareStatement(getNameQuery)) {
                    getNameStmt.setLong(1, id);
                    ResultSet rs = getNameStmt.executeQuery();
                    if (rs.next()) {
                        developerName = rs.getString("name");
                    }
                }

                // Сначала обновляем игры, чтобы убрать ссылку на разработчика
                String updateGamesQuery = "UPDATE games SET developer_id = NULL WHERE developer_id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateGamesQuery)) {
                    updateStmt.setLong(1, id);
                    int updatedGames = updateStmt.executeUpdate();
                    if (updatedGames > 0) {
                        System.out.println("✅ Обновлено " + updatedGames + " игр (убрана ссылка на разработчика)");
                    }
                }

                // Теперь удаляем разработчика
                String deleteQuery = "DELETE FROM developers WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                    pstmt.setLong(1, id);
                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        String message = "Разработчик '" + developerName + "' успешно удален";
                        if (!developerName.isEmpty()) {
                            message += ".";
                        }
                        System.out.println("✅ " + message);
                        request.getSession().setAttribute("message", message);
                    } else {
                        System.err.println("❌ Разработчик с ID " + id + " не найден");
                        request.getSession().setAttribute("error", "Разработчик с ID " + id + " не найден");
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.err.println("❌ Неверный формат ID: " + e.getMessage());
            request.getSession().setAttribute("error", "Неверный формат ID разработчика");

        } catch (SQLException e) {
            System.err.println("❌ Ошибка SQL при удалении разработчика: " + e.getMessage());
            e.printStackTrace();

            if (e.getMessage().contains("violates foreign key constraint")) {
                request.getSession().setAttribute("error",
                        "Невозможно удалить разработчика. Сначала удалите или обновите связанные игры.");
            } else {
                request.getSession().setAttribute("error", "Ошибка базы данных: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ Неожиданная ошибка при удалении: " + e.getMessage());
            e.printStackTrace();
            request.getSession().setAttribute("error", "Неожиданная ошибка: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/developers");
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
}