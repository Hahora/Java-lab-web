package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import domain.Developer;

@WebServlet("/developers")
public class DeveloperServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Тестовые данные
        List<Developer> developers = Arrays.asList(
                new Developer(1L, "CD Projekt Red", 9.5),
                new Developer(2L, "Valve Corporation", 9.2),
                new Developer(3L, "Rockstar Games", 9.7),
                new Developer(4L, "Nintendo", 9.3)
        );

        request.setAttribute("developers", developers);
        request.getRequestDispatcher("/WEB-INF/views/developers.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}