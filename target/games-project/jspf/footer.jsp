<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<footer>
    <div class="footer-container">
        <div class="footer-section">
            <h3><i class="fas fa-info-circle"></i> О проекте</h3>
            <p>Веб-приложение для лабораторной работы по Java EE.</p>
            <p>Цель: управление данными о компьютерных играх и разработчиках.</p>
        </div>

        <div class="footer-section">
            <h3><i class="fas fa-tasks"></i> Функции</h3>
            <ul>
                <li>CRUD операции для игр</li>
                <li>Управление разработчиками</li>
                <li>Валидация данных</li>
                <li>Поиск и фильтрация</li>
            </ul>
        </div>

        <div class="footer-section">
            <h3><i class="fas fa-code"></i> Технологии</h3>
            <div class="tech-tags">
                <span class="tech-tag">Java 21</span>
                <span class="tech-tag">Jakarta EE 10</span>
                <span class="tech-tag">Tomcat 10.1</span>
                <span class="tech-tag">JSP/Servlets</span>
                <span class="tech-tag">Git</span>
            </div>
        </div>

        <div class="footer-section">
            <h3><i class="fas fa-graduation-cap"></i> Учебная информация</h3>
            <p><strong>Дисциплина:</strong> Java EE разработка</p>
            <p><strong>Лабораторная:</strong> 2.9 - Создание главной страницы</p>
            <p><strong>Ветка Git:</strong> dev1</p>
        </div>
    </div>

    <div class="footer-bottom">
        <div class="copyright">
            <i class="far fa-copyright"></i> 2024 Games Management System. Все права защищены.
        </div>
        <div class="version">
            Версия 1.0.0 |
            <span id="page-load-time"></span>
        </div>
    </div>

    <script>
        // Измерение времени загрузки страницы
        window.addEventListener('load', function() {
            const loadTime = performance.now();
            document.getElementById('page-load-time').textContent =
                `Загружено за ${Math.round(loadTime)}мс`;
        });
    </script>
</footer>