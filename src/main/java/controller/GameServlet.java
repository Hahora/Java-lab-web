package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GameServlet", urlPatterns = {"/games", "/games/*"})
public class GameServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public GameServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <title>Управление играми</title>");
            out.println("    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/css/style.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <jsp:include page='/jspf/header.jsp' />");
            out.println("    <div class='content'>");
            out.println("        <h2>Компьютерные игры</h2>");
            out.println("        <div class='actions'>");
            out.println("            <button onclick=\"location.href='games?action=create'\">Добавить игру</button>");
            out.println("            <button onclick=\"location.href='games?action=list'\">Список игр</button>");
            out.println("        </div>");
            out.println("        <div class='info'>");
            out.println("            <h3>Поля для игры:</h3>");
            out.println("            <ul>");
            out.println("                <li>Название</li>");
            out.println("                <li>Год выпуска</li>");
            out.println("                <li>Жанр</li>");
            out.println("                <li>Системные требования</li>");
            out.println("            </ul>");
            out.println("        </div>");
            out.println("    </div>");
            out.println("    <jsp:include page='/jspf/footer.jsp' />");
            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Сервлет для управления компьютерными играми";
    }
}