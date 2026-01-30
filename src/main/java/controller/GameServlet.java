package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.Game;
import domain.Developer;

@WebServlet("/games")
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Тестовые данные
        Developer cdProject = new Developer(1L, "CD Projekt Red", 9.5);
        Developer valve = new Developer(2L, "Valve Corporation", 9.2);

        List<Game> games = new ArrayList<>();
        games.add(new Game(1L, "The Witcher 3: Wild Hunt", 2015, "RPG",
                "Windows 7+, Intel Core i5, 6GB RAM", 1L, cdProject));
        games.add(new Game(2L, "Half-Life 2", 2004, "FPS",
                "Windows XP, 1.7GHz CPU, 512MB RAM", 2L, valve));

        List<Developer> developers = Arrays.asList(cdProject, valve);

        // Передаем данные в JSP
        request.setAttribute("games", games);
        request.setAttribute("developers", developers);

        // Перенаправляем на JSP страницу
        request.getRequestDispatcher("/WEB-INF/views/games.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}