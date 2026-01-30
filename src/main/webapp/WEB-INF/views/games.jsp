<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Game" %>
<%@ page import="domain.Developer" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Компьютерные игры</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .content-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        .table-section, .form-section {
            background: white;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .table-section h3, .form-section h3 {
            color: #333;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 2px solid #667eea;
        }

        .data-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        .data-table th {
            background: #667eea;
            color: white;
            padding: 12px;
            text-align: left;
        }

        .data-table td {
            padding: 12px;
            border-bottom: 1px solid #eee;
        }

        .data-table tr:hover {
            background: #f5f5f5;
        }

        .genre-badge {
            display: inline-block;
            padding: 3px 8px;
            background: #4CAF50;
            color: white;
            border-radius: 10px;
            font-size: 0.8em;
            font-weight: bold;
        }

        .year-badge {
            display: inline-block;
            padding: 3px 8px;
            background: #2196F3;
            color: white;
            border-radius: 10px;
            font-size: 0.8em;
            font-weight: bold;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }

        .form-group input, .form-group select, .form-group textarea {
            width: 100%;
            padding: 8px 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }

        .form-group textarea {
            min-height: 100px;
            resize: vertical;
        }

        .submit-btn {
            background: linear-gradient(45deg, #667eea, #764ba2);
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .submit-btn:hover {
            opacity: 0.9;
        }

        .btn-small {
            padding: 5px 10px;
            margin-right: 5px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            font-size: 12px;
        }

        .btn-danger {
            background: #f44336;
            color: white;
        }
    </style>
</head>
<body>

    <div class="content-container">
        <div class="table-section">
            <h3><i class="fas fa-gamepad"></i> Список компьютерных игр</h3>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Название</th>
                        <th>Год</th>
                        <th>Жанр</th>
                        <th>Разработчик</th>
                        <th>Системные требования</th>
                        <th>Действия</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="game" items="${games}">
                        <tr>
                            <td>${game.id}</td>
                            <td><strong>${game.title}</strong></td>
                            <td><span class="year-badge">${game.releaseYear}</span></td>
                            <td><span class="genre-badge">${game.genre}</span></td>
                            <td>${game.developer.name}</td>
                            <td>${game.systemRequirements}</td>
                            <td>
                                <button class="btn-small"><i class="fas fa-edit"></i></button>
                                <button class="btn-small btn-danger"><i class="fas fa-trash"></i></button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="form-section">
            <h3><i class="fas fa-plus-circle"></i> Добавить новую игру</h3>
            <form method="POST" action="">
                <div class="form-group">
                    <label for="title">Название игры:</label>
                    <input type="text" id="title" name="title" required
                           placeholder="Введите название игры">
                </div>

                <div class="form-group">
                    <label for="releaseYear">Год выпуска:</label>
                    <input type="number" id="releaseYear" name="releaseYear"
                           min="1950" max="2030" value="2023" required>
                </div>

                <div class="form-group">
                    <label for="genre">Жанр:</label>
                    <select id="genre" name="genre" required>
                        <option value="">Выберите жанр</option>
                        <option value="RPG">RPG</option>
                        <option value="FPS">FPS (Шутер)</option>
                        <option value="Strategy">Стратегия</option>
                        <option value="Simulator">Симулятор</option>
                        <option value="Adventure">Приключение</option>
                        <option value="Sports">Спортивные</option>
                        <option value="Racing">Гонки</option>
                        <option value="MMO">MMO</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="developer">Разработчик:</label>
                    <select id="developer" name="developerId" required>
                        <option value="">Выберите разработчика</option>
                        <c:forEach var="dev" items="${developers}">
                            <option value="${dev.id}">${dev.name} (рейтинг: ${dev.rating})</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="requirements">Системные требования:</label>
                    <textarea id="requirements" name="systemRequirements"
                              placeholder="Минимальные системные требования" required></textarea>
                </div>

                <button type="submit" class="submit-btn">
                    <i class="fas fa-save"></i> Добавить игру
                </button>
            </form>
        </div>
    </div>

</body>
</html>