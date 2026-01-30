<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Главная страница - Управление играми</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <jsp:include page="/jspf/header.jsp" />

    <div class="main-container">
        <div class="welcome-section">
            <h1><i class="fas fa-gamepad"></i> Добро пожаловать в систему управления играми!</h1>
            <p class="subtitle">Веб-приложение для управления каталогом компьютерных игр и разработчиков</p>
        </div>

        <div class="cards-container">
            <div class="card">
                <div class="card-icon">
                    <i class="fas fa-gamepad fa-3x"></i>
                </div>
                <h2>Компьютерные игры</h2>
                <p>Управление каталогом компьютерных игр</p>
                <ul class="features">
                    <li><i class="fas fa-check-circle"></i> Добавление новых игр</li>
                    <li><i class="fas fa-check-circle"></i> Редактирование информации</li>
                    <li><i class="fas fa-check-circle"></i> Удаление записей</li>
                    <li><i class="fas fa-check-circle"></i> Поиск и фильтрация</li>
                </ul>
                <a href="${pageContext.request.contextPath}/games" class="btn">
                    <i class="fas fa-arrow-right"></i> Перейти к играм
                </a>
            </div>

            <div class="card">
                <div class="card-icon">
                    <i class="fas fa-users fa-3x"></i>
                </div>
                <h2>Разработчики</h2>
                <p>Управление информацией о разработчиках</p>
                <ul class="features">
                    <li><i class="fas fa-check-circle"></i> Регистрация компаний</li>
                    <li><i class="fas fa-check-circle"></i> Управление рейтингом</li>
                    <li><i class="fas fa-check-circle"></i> Связь игр с разработчиками</li>
                    <li><i class="fas fa-check-circle"></i> Статистика по разработчикам</li>
                </ul>
                <a href="${pageContext.request.contextPath}/developers" class="btn">
                    <i class="fas fa-arrow-right"></i> Перейти к разработчикам
                </a>
            </div>

            <div class="card">
                <div class="card-icon">
                    <i class="fas fa-chart-bar fa-3x"></i>
                </div>
                <h2>Статистика</h2>
                <p>Аналитика и отчеты</p>
                <ul class="features">
                    <li><i class="fas fa-check-circle"></i> Количество игр по жанрам</li>
                    <li><i class="fas fa-check-circle"></i> Рейтинг разработчиков</li>
                    <li><i class="fas fa-check-circle"></i> Динамика выпуска игр</li>
                    <li><i class="fas fa-check-circle"></i> Системные требования</li>
                </ul>
                <a href="#" class="btn">
                    <i class="fas fa-chart-line"></i> Показать статистику
                </a>
            </div>
        </div>

        <div class="tech-info">
            <h3><i class="fas fa-cogs"></i> Техническая информация</h3>
            <div class="tech-grid">
                <div class="tech-item">
                    <h4><i class="fas fa-server"></i> Сервер</h4>
                    <p>Apache Tomcat 10.1.52</p>
                </div>
                <div class="tech-item">
                    <h4><i class="fas fa-code"></i> Технологии</h4>
                    <p>Jakarta EE 10, Java 21</p>
                </div>
                <div class="tech-item">
                    <h4><i class="fas fa-database"></i> Данные</h4>
                    <p>Игры и разработчики</p>
                </div>
                <div class="tech-item">
                    <h4><i class="fas fa-git-alt"></i> Контроль версий</h4>
                    <p>Git (ветки master/dev1)</p>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/jspf/footer.jsp" />
</body>
</html>