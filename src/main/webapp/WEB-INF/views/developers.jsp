<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Developer" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Разработчики игр</title>
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

        .rating-badge {
            display: inline-block;
            padding: 3px 8px;
            background: #ff9900;
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

        .form-group input, .form-group select {
            width: 100%;
            padding: 8px 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
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
    </style>
</head>
<body>

    <div class="content-container">
        <div class="table-section">
            <h3><i class="fas fa-list"></i> Список разработчиков</h3>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Название компании</th>
                        <th>Рейтинг</th>
                        <th>Действия</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="developer" items="${developers}">
                        <tr>
                            <td>${developer.id}</td>
                            <td>${developer.name}</td>
                            <td>
                                <span class="rating-badge">${developer.rating}/10</span>
                            </td>
                            <td>
                                <button class="btn-small"><i class="fas fa-edit"></i> Изменить</button>
                                <button class="btn-small btn-danger"><i class="fas fa-trash"></i> Удалить</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="form-section">
            <h3><i class="fas fa-plus-circle"></i> Добавить нового разработчика</h3>
            <form method="POST" action="">
                <div class="form-group">
                    <label for="name">Название компании:</label>
                    <input type="text" id="name" name="name" required
                           placeholder="Введите название компании">
                </div>

                <div class="form-group">
                    <label for="rating">Рейтинг (0-10):</label>
                    <input type="number" id="rating" name="rating" min="0" max="10" step="0.1"
                           required placeholder="9.5">
                </div>

                <button type="submit" class="submit-btn">
                    <i class="fas fa-save"></i> Добавить разработчика
                </button>
            </form>
        </div>
    </div>

</body>
</html>