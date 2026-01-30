package controller;

import domain.Developer;
import domain.Game;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/test-domain")
public class TestDomainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Создаем разработчиков
            Developer cdProject = new Developer(1L, "CD Projekt Red", 9.5);
            Developer valve = new Developer(2L, "Valve Corporation", 9.2);
            Developer rockstar = new Developer(3L, "Rockstar Games", 9.7);

            // Создаем игры
            List<Game> games = new ArrayList<>();
            games.add(new Game(1L, "The Witcher 3: Wild Hunt", 2015,
                    "RPG", "Windows 7/8/10, Intel Core i5, 6GB RAM", 1L, cdProject));
            games.add(new Game(2L, "Cyberpunk 2077", 2020,
                    "Action RPG", "Windows 10, Intel Core i7, 12GB RAM", 1L, cdProject));
            games.add(new Game(3L, "Half-Life 2", 2004,
                    "FPS", "Windows XP, 1.7GHz CPU, 512MB RAM", 2L, valve));
            games.add(new Game(4L, "Grand Theft Auto V", 2013,
                    "Action-adventure", "Windows 10, Intel Core i5, 8GB RAM", 3L, rockstar));

            // Выводим HTML
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <title>Тест домена данных</title>");
            out.println("    <link rel='stylesheet' type='text/css' href='css/style.css'>");
            out.println("    <style>");
            out.println("        .domain-test { max-width: 1200px; margin: 0 auto; padding: 20px; }");
            out.println("        .developer-card, .game-card { background: white; padding: 20px; margin: 10px 0; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            out.println("        .rating { color: #ff9900; font-weight: bold; }");
            out.println("        h2 { color: #333; margin-bottom: 20px; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <jsp:include page='/jspf/header.jsp' />");
            out.println("    <div class='domain-test'>");
            out.println("        <h2>Тестирование домена данных</h2>");

            // Разработчики
            out.println("        <h3>Разработчики:</h3>");
            Developer[] developers = {cdProject, valve, rockstar};
            for (Developer dev : developers) {
                out.println("        <div class='developer-card'>");
                out.println("            <h4>" + dev.getName() + "</h4>");
                out.println("            <p>ID: " + dev.getId() + "</p>");
                out.println("            <p class='rating'>Рейтинг: " + dev.getRating() + "/10</p>");
                out.println("            <p>toString(): " + dev.toString() + "</p>");
                out.println("        </div>");
            }

            // Игры
            out.println("        <h3>Компьютерные игры:</h3>");
            for (Game game : games) {
                out.println("        <div class='game-card'>");
                out.println("            <h4>" + game.getTitle() + " (" + game.getReleaseYear() + ")</h4>");
                out.println("            <p><strong>Жанр:</strong> " + game.getGenre() + "</p>");
                out.println("            <p><strong>Системные требования:</strong> " + game.getSystemRequirements() + "</p>");
                out.println("            <p><strong>Разработчик:</strong> " + game.getDeveloperName() +
                        " (рейтинг: " + game.getDeveloperRating() + ")</p>");
                out.println("            <p><strong>Developer ID:</strong> " + game.getDeveloperId() + "</p>");
                out.println("            <p>toString(): " + game.toString() + "</p>");
                out.println("        </div>");
            }

            out.println("    </div>");
            out.println("    <jsp:include page='/jspf/footer.jsp' />");
            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();
        }
    }
}