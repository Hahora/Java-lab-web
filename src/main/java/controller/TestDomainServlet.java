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
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/test")
public class TestDomainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Тест доменных классов GameHub</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; padding: 20px; background-color: #f5f5f5; }");
        out.println(".container { max-width: 1200px; margin: 0 auto; }");
        out.println(".test-section { background: white; border-radius: 10px; padding: 20px; margin-bottom: 20px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
        out.println(".test-section h2 { color: #333; border-bottom: 2px solid #667eea; padding-bottom: 10px; }");
        out.println(".game-card { border: 1px solid #ddd; padding: 15px; margin: 10px 0; border-radius: 5px; background: #fafafa; }");
        out.println(".dev-card { background: #e8f4fd; padding: 10px; margin: 10px 0; border-radius: 5px; border-left: 4px solid #2196F3; }");
        out.println(".success { color: green; font-weight: bold; }");
        out.println(".error { color: red; font-weight: bold; }");
        out.println(".info { color: #666; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='container'>");
        out.println("<div class='test-section'>");
        out.println("<h1>🧪 Тестирование GameHub - Подключение к БД PostgreSQL</h1>");

        // Тест 1: Создание объектов
        out.println("<h2>Тест 1: Создание доменных объектов</h2>");

        Developer testDev = new Developer(999L, "Test Developer", 8.5, 2020, "Test Country", "Тестовый разработчик", "https://example.com");
        Game testGame = new Game(999L, "Test Game", 2023, "Test Genre", testDev, "Test Requirements", 49.99, true, 85, "Тестовая игра");

        out.println("<div class='dev-card'>");
        out.println("<h3>Создан разработчик:</h3>");
        out.println("<p><strong>ID:</strong> " + testDev.getId() + "</p>");
        out.println("<p><strong>Имя:</strong> " + testDev.getName() + "</p>");
        out.println("<p><strong>Рейтинг:</strong> " + testDev.getRating() + "/10</p>");
        out.println("<p><strong>Год основания:</strong> " + testDev.getFoundedYear() + "</p>");
        out.println("<p><strong>Страна:</strong> " + testDev.getCountry() + "</p>");
        out.println("</div>");

        out.println("<div class='game-card'>");
        out.println("<h3>Создана игра:</h3>");
        out.println("<p><strong>ID:</strong> " + testGame.getId() + "</p>");
        out.println("<p><strong>Название:</strong> " + testGame.getTitle() + "</p>");
        out.println("<p><strong>Год выпуска:</strong> " + testGame.getReleaseYear() + "</p>");
        out.println("<p><strong>Жанр:</strong> " + testGame.getGenre() + "</p>");
        out.println("<p><strong>Цена:</strong> $" + testGame.getPrice() + "</p>");
        out.println("<p><strong>Multiplayer:</strong> " + (testGame.getMultiplayer() ? "Да" : "Нет") + "</p>");
        out.println("<p><strong>Metacritic:</strong> " + testGame.getMetacriticScore() + "/100</p>");
        out.println("<p><strong>Разработчик:</strong> " + (testGame.getDeveloper() != null ? testGame.getDeveloper().getName() : "Не указан") + "</p>");
        out.println("</div>");

        out.println("<p class='success'>✅ Тест 1 пройден: доменные объекты созданы успешно</p>");

        // Тест 2: Подключение к БД
        out.println("<h2>Тест 2: Подключение к базе данных PostgreSQL</h2>");

        try {
            GameHubConnBuilder builder = new GameHubConnBuilder();

            out.println("<p class='info'>Попытка подключения к БД...</p>");
            out.println("<p class='info'>URL: jdbc:postgresql://localhost:5432/gamehub</p>");

            try (Connection conn = builder.getConnection()) {
                out.println("<p class='success'>✅ Подключение к PostgreSQL успешно установлено!</p>");

                // Тест 3: Проверка таблиц
                out.println("<h2>Тест 3: Проверка структуры базы данных</h2>");

                DatabaseMetaData meta = conn.getMetaData();

                // Проверяем таблицу developers
                try (ResultSet tables = meta.getTables(null, null, "developers", null)) {
                    if (tables.next()) {
                        out.println("<p class='success'>✅ Таблица 'developers' существует</p>");
                    } else {
                        out.println("<p class='error'>❌ Таблица 'developers' не найдена</p>");
                    }
                }

                // Проверяем таблицу games
                try (ResultSet tables = meta.getTables(null, null, "games", null)) {
                    if (tables.next()) {
                        out.println("<p class='success'>✅ Таблица 'games' существует</p>");
                    } else {
                        out.println("<p class='error'>❌ Таблица 'games' не найдена</p>");
                    }
                }

                // Тест 4: Чтение данных
                out.println("<h2>Тест 4: Чтение данных из базы</h2>");

                // Читаем разработчиков
                String devQuery = "SELECT COUNT(*) as count FROM developers";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(devQuery)) {
                    if (rs.next()) {
                        int devCount = rs.getInt("count");
                        out.println("<p>Найдено разработчиков: <strong>" + devCount + "</strong></p>");
                        if (devCount > 0) {
                            out.println("<p class='success'>✅ Данные разработчиков загружены</p>");
                        } else {
                            out.println("<p class='error'>❌ Нет данных в таблице developers</p>");
                        }
                    }
                }

                // Читаем игры
                String gameQuery = "SELECT COUNT(*) as count FROM games";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(gameQuery)) {
                    if (rs.next()) {
                        int gameCount = rs.getInt("count");
                        out.println("<p>Найдено игр: <strong>" + gameCount + "</strong></p>");
                        if (gameCount > 0) {
                            out.println("<p class='success'>✅ Данные игр загружены</p>");
                        } else {
                            out.println("<p class='error'>❌ Нет данных в таблице games</p>");
                        }
                    }
                }

                // Тест 5: Пример сложного запроса
                out.println("<h2>Тест 5: Пример данных из базы</h2>");

                String sampleQuery =
                        "SELECT g.title, g.release_year, g.genre, g.price, " +
                                "d.name as developer_name, d.country " +
                                "FROM games g " +
                                "LEFT JOIN developers d ON g.developer_id = d.id " +
                                "ORDER BY g.release_year DESC " +
                                "LIMIT 5";

                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(sampleQuery)) {

                    out.println("<table border='1' cellpadding='8' cellspacing='0' style='border-collapse: collapse; width: 100%;'>");
                    out.println("<thead><tr style='background-color: #667eea; color: white;'>");
                    out.println("<th>Игра</th><th>Год</th><th>Жанр</th><th>Цена</th><th>Разработчик</th><th>Страна</th>");
                    out.println("</tr></thead><tbody>");

                    int rowCount = 0;
                    while (rs.next()) {
                        rowCount++;
                        out.println("<tr>");
                        out.println("<td>" + rs.getString("title") + "</td>");
                        out.println("<td>" + rs.getInt("release_year") + "</td>");
                        out.println("<td>" + rs.getString("genre") + "</td>");
                        out.println("<td>$" + rs.getDouble("price") + "</td>");
                        out.println("<td>" + rs.getString("developer_name") + "</td>");
                        out.println("<td>" + rs.getString("country") + "</td>");
                        out.println("</tr>");
                    }

                    out.println("</tbody></table>");

                    if (rowCount > 0) {
                        out.println("<p class='success'>✅ Данные успешно прочитаны из базы (" + rowCount + " записей)</p>");
                    } else {
                        out.println("<p class='error'>❌ Нет данных для отображения</p>");
                    }
                }

                // Тест 6: Проверка конфигурации
                out.println("<h2>Тест 6: Проверка конфигурации</h2>");
                out.println("<p><strong>Драйвер:</strong> org.postgresql.Driver</p>");
                out.println("<p><strong>Версия JDBC драйвера:</strong> " + meta.getDriverVersion() + "</p>");
                out.println("<p><strong>Версия PostgreSQL:</strong> " + meta.getDatabaseProductVersion() + "</p>");
                out.println("<p><strong>URL соединения:</strong> " + meta.getURL() + "</p>");
                out.println("<p><strong>Пользователь:</strong> " + meta.getUserName() + "</p>");

            } catch (SQLException e) {
                out.println("<p class='error'>❌ Ошибка при работе с базой данных: " + e.getMessage() + "</p>");
                e.printStackTrace();
            }

        } catch (Exception e) {
            out.println("<p class='error'>❌ Ошибка инициализации подключения: " + e.getMessage() + "</p>");
            out.println("<p class='info'>Проверьте:</p>");
            out.println("<ul>");
            out.println("<li>Запущен ли PostgreSQL (порт 5432)</li>");
            out.println("<li>Существует ли база данных 'gamehub'</li>");
            out.println("<li>Правильные ли логин/пароль в config.properties</li>");
            out.println("<li>Добавлена ли зависимость PostgreSQL в pom.xml</li>");
            out.println("</ul>");
        }

        // Итоги
        out.println("<h2>Итоги тестирования</h2>");
        out.println("<p>Для продолжения работы:</p>");
        out.println("<ol>");
        out.println("<li>Убедитесь, что PostgreSQL установлен и запущен</li>");
        out.println("<li>Создайте базу данных 'gamehub'</li>");
        out.println("<li>Выполните SQL скрипт из database_setup.sql</li>");
        out.println("<li>Проверьте config.properties</li>");
        out.println("<li>Перейдите на <a href='/games'>Страницу игр</a></li>");
        out.println("<li>Перейдите на <a href='/developers'>Страницу разработчиков</a></li>");
        out.println("</ol>");

        out.println("<hr>");
        out.println("<p><strong>Статус ЛР 2.12:</strong> Подключение к БД - <span class='success'>В ПРОЦЕССЕ</span></p>");
        out.println("<p><strong>Следующий шаг:</strong> Проверить работу /games и /developers</p>");

        out.println("</div>"); // закрываем test-section
        out.println("</div>"); // закрываем container
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}