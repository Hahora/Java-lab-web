<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Компьютерные игры - GameHub</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        /* Основные стили */
        .content-container {
            max-width: 1400px;
            margin: 20px auto;
            padding: 20px;
        }

        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            padding: 20px;
            background: linear-gradient(45deg, #667eea, #764ba2);
            border-radius: 15px;
            color: white;
        }

        .page-header h1 {
            margin: 0;
            font-size: 32px;
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .add-btn {
            background: white;
            color: #667eea;
            padding: 12px 25px;
            border: none;
            border-radius: 10px;
            font-size: 16px;
            font-weight: 600;
            display: flex;
            align-items: center;
            gap: 10px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .add-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(255, 255, 255, 0.2);
        }

        .stats-cards {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
            margin-bottom: 30px;
        }

        .stat-card {
            background: white;
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            text-align: center;
            transition: all 0.3s ease;
        }

        .stat-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0,0,0,0.15);
        }

        .stat-icon {
            font-size: 40px;
            margin-bottom: 15px;
            color: #667eea;
        }

        .stat-value {
            font-size: 32px;
            font-weight: 700;
            color: #2c3e50;
            margin-bottom: 5px;
        }

        .stat-label {
            color: #7f8c8d;
            font-size: 14px;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .table-container {
            background: white;
            border-radius: 15px;
            padding: 25px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }

        .table-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .table-header h3 {
            margin: 0;
            color: #2c3e50;
            font-size: 24px;
        }

        .search-box {
            position: relative;
            width: 300px;
        }

        .search-input {
            width: 100%;
            padding: 12px 15px 12px 45px;
            border: 2px solid #e0e0e0;
            border-radius: 10px;
            font-size: 15px;
            transition: border-color 0.3s ease;
        }

        .search-input:focus {
            outline: none;
            border-color: #667eea;
        }

        .search-icon {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #95a5a6;
        }

        .data-table {
            width: 100%;
            border-collapse: collapse;
        }

        .data-table thead {
            background: #f8f9fa;
        }

        .data-table th {
            padding: 18px 15px;
            text-align: left;
            font-weight: 600;
            color: #2c3e50;
            border-bottom: 2px solid #667eea;
            font-size: 16px;
        }

        .data-table tbody tr {
            border-bottom: 1px solid #eee;
            transition: all 0.3s ease;
        }

        .data-table tbody tr:hover {
            background-color: #f8f9fa;
        }

        .data-table td {
            padding: 16px 15px;
            vertical-align: middle;
        }

        .game-title-cell {
            display: flex;
            flex-direction: column;
            gap: 5px;
        }

        .game-title {
            font-weight: 700;
            color: #2c3e50;
            font-size: 16px;
        }

        .game-description {
            color: #7f8c8d;
            font-size: 13px;
            line-height: 1.4;
        }

        .badge {
            display: inline-block;
            padding: 6px 12px;
            border-radius: 20px;
            font-size: 13px;
            font-weight: 600;
            margin: 2px;
        }

        .genre-badge {
            background: #4CAF50;
            color: white;
        }

        .year-badge {
            background: #2196F3;
            color: white;
        }

        .price-badge {
            background: #ff9800;
            color: white;
        }

        .free-badge {
            background: #4CAF50;
            color: white;
        }

        .metacritic-badge {
            background: #9c27b0;
            color: white;
        }

        .multiplayer-badge {
            background: #e91e63;
            color: white;
            padding: 4px 10px;
            border-radius: 12px;
            font-size: 12px;
        }

        .developer-info {
            display: flex;
            flex-direction: column;
            gap: 4px;
        }

        .developer-name {
            font-weight: 600;
            color: #2c3e50;
        }

        .developer-country {
            font-size: 13px;
            color: #7f8c8d;
            display: flex;
            align-items: center;
            gap: 5px;
        }

        .action-buttons {
            display: flex;
            gap: 8px;
        }

        .btn-small {
            padding: 8px 12px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-weight: 600;
            display: inline-flex;
            align-items: center;
            gap: 5px;
            transition: all 0.3s ease;
            font-size: 13px;
        }

        .btn-edit {
            background: #3498db;
            color: white;
        }

        .btn-edit:hover {
            background: #2980b9;
            transform: translateY(-2px);
        }

        .btn-delete {
            background: #e74c3c;
            color: white;
        }

        .btn-delete:hover {
            background: #c0392b;
            transform: translateY(-2px);
        }

        .system-requirements {
            font-size: 12px;
            color: #666;
            background: #f8f9fa;
            padding: 8px;
            border-radius: 5px;
            margin-top: 5px;
            line-height: 1.4;
        }

        /* Модальные окна */
        .modal-overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            z-index: 1000;
            justify-content: center;
            align-items: center;
        }

        .modal-content {
            background: white;
            border-radius: 15px;
            width: 90%;
            max-width: 600px;
            max-height: 90vh;
            overflow-y: auto;
            animation: modalSlideIn 0.3s ease;
        }

        @keyframes modalSlideIn {
            from {
                opacity: 0;
                transform: translateY(-50px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .modal-header {
            padding: 20px 25px;
            border-bottom: 2px solid #667eea;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .modal-header h3 {
            margin: 0;
            color: #2c3e50;
            font-size: 24px;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .close-btn {
            background: none;
            border: none;
            font-size: 28px;
            color: #95a5a6;
            cursor: pointer;
            padding: 0;
            line-height: 1;
        }

        .close-btn:hover {
            color: #e74c3c;
        }

        .modal-body {
            padding: 25px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #34495e;
            font-size: 15px;
        }

        .form-control {
            width: 100%;
            padding: 12px 15px;
            border: 2px solid #ddd;
            border-radius: 8px;
            font-size: 15px;
            transition: border-color 0.3s ease;
        }

        .form-control:focus {
            outline: none;
            border-color: #667eea;
        }

        .form-textarea {
            min-height: 100px;
            resize: vertical;
        }

        .modal-footer {
            padding: 20px 25px;
            border-top: 1px solid #eee;
            display: flex;
            justify-content: flex-end;
            gap: 15px;
        }

        .btn {
            padding: 12px 25px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-weight: 600;
            font-size: 15px;
            transition: all 0.3s ease;
        }

        .btn-primary {
            background: #667eea;
            color: white;
        }

        .btn-primary:hover {
            background: #5a6fd8;
            transform: translateY(-2px);
        }

        .btn-secondary {
            background: #95a5a6;
            color: white;
        }

        .btn-secondary:hover {
            background: #7f8c8d;
        }

        /* Адаптивность */
        @media (max-width: 1200px) {
            .stats-cards {
                grid-template-columns: repeat(2, 1fr);
            }
        }

        @media (max-width: 768px) {
            .stats-cards {
                grid-template-columns: 1fr;
            }

            .page-header {
                flex-direction: column;
                gap: 20px;
                text-align: center;
            }

            .table-header {
                flex-direction: column;
                gap: 15px;
            }

            .search-box {
                width: 100%;
            }

            .data-table {
                display: block;
                overflow-x: auto;
            }

            .action-buttons {
                flex-direction: column;
            }

            .btn-small {
                width: 100%;
                justify-content: center;
            }
        }
    </style>
</head>
<body>

    <div class="content-container">
        <!-- Шапка страницы -->
        <div class="page-header">
            <h1>
                <i class="fas fa-gamepad"></i>
                Каталог компьютерных игр
            </h1>
            <button class="add-btn" onclick="openAddModal()">
                <i class="fas fa-plus"></i>
                Добавить игру
            </button>
        </div>

        <!-- Статистика -->
        <div class="stats-cards">
            <div class="stat-card">
                <div class="stat-icon">
                    <i class="fas fa-gamepad"></i>
                </div>
                <div class="stat-value">${games.size()}</div>
                <div class="stat-label">Всего игр</div>
            </div>

            <div class="stat-card">
                <div class="stat-icon">
                    <i class="fas fa-dollar-sign"></i>
                </div>
                <div class="stat-value">
                    <fmt:formatNumber value="${games.stream().filter(g -> g.getPrice() > 0).count()}" />
                </div>
                <div class="stat-label">Платных игр</div>
            </div>

            <div class="stat-card">
                <div class="stat-icon">
                    <i class="fas fa-users"></i>
                </div>
                <div class="stat-value">
                    <fmt:formatNumber value="${games.stream().filter(g -> g.getMultiplayer() != null && g.getMultiplayer()).count()}" />
                </div>
                <div class="stat-label">Multiplayer</div>
            </div>

            <div class="stat-card">
                <div class="stat-icon">
                    <i class="fas fa-star"></i>
                </div>
                <div class="stat-value">
                    <fmt:formatNumber value="${games.stream().filter(g -> g.getMetacriticScore() != null && g.getMetacriticScore() > 80).count()}" />
                </div>
                <div class="stat-label">Высокий рейтинг</div>
            </div>
        </div>

        <!-- Сообщения -->
        <c:if test="${not empty message}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <i class="fas fa-check-circle"></i> ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <i class="fas fa-exclamation-circle"></i> ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>

        <!-- Таблица с играми -->
        <div class="table-container">
            <div class="table-header">
                <h3>Список всех игр</h3>
                <div class="search-box">
                    <i class="fas fa-search search-icon"></i>
                    <input type="text" class="search-input" placeholder="Поиск игр..." id="gameSearch">
                </div>
            </div>

            <table class="data-table" id="gamesTable">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Название</th>
                        <th>Год / Жанр</th>
                        <th>Разработчик</th>
                        <th>Цена / Multiplayer</th>
                        <th>Metacritic</th>
                        <th>Действия</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="game" items="${games}">
                        <tr data-game-id="${game.id}">
                            <td style="font-weight: bold; color: #667eea;">#${game.id}</td>
                            <td>
                                <div class="game-title-cell">
                                    <div class="game-title">${game.title}</div>
                                    <c:if test="${not empty game.description}">
                                        <div class="game-description">${game.description}</div>
                                    </c:if>
                                    <c:if test="${not empty game.systemRequirements}">
                                        <div class="system-requirements">
                                            <i class="fas fa-desktop"></i> ${game.systemRequirements}
                                        </div>
                                    </c:if>
                                </div>
                            </td>
                            <td>
                                <div style="display: flex; flex-direction: column; gap: 8px;">
                                    <span class="badge year-badge">
                                        <i class="fas fa-calendar-alt"></i> ${game.releaseYear}
                                    </span>
                                    <c:if test="${not empty game.genre}">
                                        <span class="badge genre-badge">${game.genre}</span>
                                    </c:if>
                                </div>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${game.developer != null}">
                                        <div class="developer-info">
                                            <span class="developer-name">${game.developer.name}</span>
                                            <c:if test="${not empty game.developer.country}">
                                                <span class="developer-country">
                                                    <i class="fas fa-globe"></i> ${game.developer.country}
                                                </span>
                                            </c:if>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: #95a5a6; font-style: italic;">Не указан</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <div style="display: flex; flex-direction: column; gap: 8px;">
                                    <c:choose>
                                        <c:when test="${game.price > 0}">
                                            <span class="badge price-badge">
                                                <i class="fas fa-dollar-sign"></i> ${game.price}
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge free-badge">
                                                <i class="fas fa-gift"></i> Бесплатно
                                            </span>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${game.multiplayer}">
                                            <span class="multiplayer-badge">
                                                <i class="fas fa-users"></i> Multiplayer
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: #7f8c8d; font-size: 13px;">
                                                <i class="fas fa-user"></i> Singleplayer
                                            </span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </td>
                            <td>
                                <c:if test="${game.metacriticScore > 0}">
                                    <span class="badge metacritic-badge">
                                        ${game.metacriticScore}/100
                                    </span>
                                </c:if>
                            </td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-small btn-edit" onclick="openEditModal(${game.id}, '${game.title}', ${game.releaseYear}, '${game.genre}', ${game.developer != null ? game.developer.id : 'null'}, '${game.systemRequirements}', ${game.price}, ${game.multiplayer}, ${game.metacriticScore}, '${game.description}')">
                                        <i class="fas fa-edit"></i> Изменить
                                    </button>
                                    <button class="btn-small btn-delete" onclick="deleteGame(${game.id})">
                                        <i class="fas fa-trash"></i> Удалить
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Модальное окно добавления игры -->
    <div class="modal-overlay" id="addModal">
        <div class="modal-content">
            <div class="modal-header">
                <h3><i class="fas fa-plus-circle"></i> Добавить новую игру</h3>
                <button class="close-btn" onclick="closeAddModal()">&times;</button>
            </div>
            <form id="addGameForm" method="POST" action="${pageContext.request.contextPath}/games">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="addTitle">Название игры *</label>
                                <input type="text" id="addTitle" name="title" class="form-control" required>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="addReleaseYear">Год выпуска *</label>
                                <input type="number" id="addReleaseYear" name="releaseYear" class="form-control"
                                       required min="1970" max="2030" value="2023">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="addGenre">Жанр</label>
                                <select id="addGenre" name="genre" class="form-control">
                                    <option value="">Выберите жанр</option>
                                    <option value="RPG">RPG</option>
                                    <option value="FPS">FPS</option>
                                    <option value="Strategy">Стратегия</option>
                                    <option value="Adventure">Приключение</option>
                                    <option value="Action">Экшен</option>
                                    <option value="Sports">Спортивные</option>
                                    <option value="Racing">Гонки</option>
                                    <option value="MMO">MMO</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="addDeveloperId">Разработчик</label>
                                <select id="addDeveloperId" name="developerId" class="form-control">
                                    <option value="">Выберите разработчика</option>
                                    <c:forEach var="dev" items="${developers}">
                                        <option value="${dev.id}">${dev.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="addPrice">Цена ($)</label>
                                <input type="number" id="addPrice" name="price" class="form-control"
                                       step="0.01" min="0" placeholder="0.00 (бесплатно)">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="addMetacriticScore">Metacritic (0-100)</label>
                                <input type="number" id="addMetacriticScore" name="metacriticScore" class="form-control"
                                       min="0" max="100" placeholder="85">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-label">Multiplayer</label>
                        <div style="display: flex; gap: 20px;">
                            <label style="display: flex; align-items: center; gap: 8px;">
                                <input type="radio" name="multiplayer" value="true">
                                <span>Да</span>
                            </label>
                            <label style="display: flex; align-items: center; gap: 8px;">
                                <input type="radio" name="multiplayer" value="false" checked>
                                <span>Нет</span>
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-label" for="addSystemRequirements">Системные требования</label>
                        <textarea id="addSystemRequirements" name="systemRequirements"
                                  class="form-control form-textarea"
                                  placeholder="Минимальные системные требования"></textarea>
                    </div>

                    <div class="form-group">
                        <label class="form-label" for="addDescription">Описание игры</label>
                        <textarea id="addDescription" name="description"
                                  class="form-control form-textarea"
                                  placeholder="Краткое описание игры..."></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="closeAddModal()">Отмена</button>
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-save"></i> Добавить игру
                    </button>
                </div>
            </form>
        </div>
    </div>

    <!-- Модальное окно редактирования игры -->
    <div class="modal-overlay" id="editModal">
        <div class="modal-content">
            <div class="modal-header">
                <h3><i class="fas fa-edit"></i> Редактировать игру</h3>
                <button class="close-btn" onclick="closeEditModal()">&times;</button>
            </div>
            <form id="editGameForm" method="POST" action="${pageContext.request.contextPath}/games">
                <input type="hidden" id="editId" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="editTitle">Название игры *</label>
                                <input type="text" id="editTitle" name="title" class="form-control" required>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="editReleaseYear">Год выпуска *</label>
                                <input type="number" id="editReleaseYear" name="releaseYear" class="form-control" required>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="editGenre">Жанр</label>
                                <select id="editGenre" name="genre" class="form-control">
                                    <option value="">Выберите жанр</option>
                                    <option value="RPG">RPG</option>
                                    <option value="FPS">FPS</option>
                                    <option value="Strategy">Стратегия</option>
                                    <option value="Adventure">Приключение</option>
                                    <option value="Action">Экшен</option>
                                    <option value="Sports">Спортивные</option>
                                    <option value="Racing">Гонки</option>
                                    <option value="MMO">MMO</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="editDeveloperId">Разработчик</label>
                                <select id="editDeveloperId" name="developerId" class="form-control">
                                    <option value="">Выберите разработчика</option>
                                    <c:forEach var="dev" items="${developers}">
                                        <option value="${dev.id}">${dev.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="editPrice">Цена ($)</label>
                                <input type="number" id="editPrice" name="price" class="form-control" step="0.01">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="editMetacriticScore">Metacritic (0-100)</label>
                                <input type="number" id="editMetacriticScore" name="metacriticScore" class="form-control" min="0" max="100">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-label">Multiplayer</label>
                        <div style="display: flex; gap: 20px;">
                            <label style="display: flex; align-items: center; gap: 8px;">
                                <input type="radio" id="editMultiplayerYes" name="multiplayer" value="true">
                                <span>Да</span>
                            </label>
                            <label style="display: flex; align-items: center; gap: 8px;">
                                <input type="radio" id="editMultiplayerNo" name="multiplayer" value="false">
                                <span>Нет</span>
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-label" for="editSystemRequirements">Системные требования</label>
                        <textarea id="editSystemRequirements" name="systemRequirements"
                                  class="form-control form-textarea"></textarea>
                    </div>

                    <div class="form-group">
                        <label class="form-label" for="editDescription">Описание игры</label>
                        <textarea id="editDescription" name="description"
                                  class="form-control form-textarea"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="closeEditModal()">Отмена</button>
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-save"></i> Сохранить изменения
                    </button>
                </div>
            </form>
        </div>
    </div>

    <!-- Модальное окно подтверждения удаления -->
    <div class="modal-overlay" id="deleteModal">
        <div class="modal-content" style="max-width: 400px;">
            <div class="modal-header">
                <h3><i class="fas fa-exclamation-triangle"></i> Подтверждение удаления</h3>
                <button class="close-btn" onclick="closeDeleteModal()">&times;</button>
            </div>
            <div class="modal-body">
                <p style="text-align: center; font-size: 16px; margin-bottom: 20px;">
                    Вы уверены, что хотите удалить эту игру?
                </p>
                <p id="deleteGameName" style="text-align: center; font-weight: bold; color: #e74c3c; font-size: 18px;"></p>
                <p style="text-align: center; color: #7f8c8d; font-size: 14px;">
                    Это действие нельзя отменить.
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="closeDeleteModal()">Отмена</button>
                <button type="button" class="btn btn-danger" onclick="confirmDelete()">
                    <i class="fas fa-trash"></i> Удалить
                </button>
            </div>
        </div>
    </div>

   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
   <script>
       // Функция для экранирования строк для JavaScript
       function escapeJS(str) {
           if (!str) return '';
           return String(str)
               .replace(/\\/g, '\\\\')
               .replace(/'/g, "\\'")
               .replace(/"/g, '\\"')
               .replace(/\n/g, '\\n')
               .replace(/\r/g, '\\r');
       }

       // Переменные для управления модальными окнами
       let gameToDelete = null;
       let gameToDeleteName = '';

       // Функции для модального окна добавления
       function openAddModal() {
           document.getElementById('addModal').style.display = 'flex';
           document.getElementById('addTitle').focus();
       }

       function closeAddModal() {
           document.getElementById('addModal').style.display = 'none';
           document.getElementById('addGameForm').reset();
       }

       // Функции для модального окна редактирования
       function openEditModal(id, title, releaseYear, genre, developerId, requirements, price, multiplayer, metacriticScore, description) {
           console.log('Opening edit modal for game ID:', id);

           try {
               // Заполняем форму данными игры
               document.getElementById('editId').value = id;
               document.getElementById('editTitle').value = title || '';
               document.getElementById('editReleaseYear').value = releaseYear || '';
               document.getElementById('editGenre').value = genre || '';
               document.getElementById('editDeveloperId').value = (developerId === 'null' || developerId === null) ? '' : developerId;
               document.getElementById('editSystemRequirements').value = requirements || '';
               document.getElementById('editPrice').value = price || 0;
               document.getElementById('editMetacriticScore').value = metacriticScore || '';
               document.getElementById('editDescription').value = description || '';

               // Устанавливаем radio button для multiplayer
               if (multiplayer === true || multiplayer === 'true') {
                   document.getElementById('editMultiplayerYes').checked = true;
                   document.getElementById('editMultiplayerNo').checked = false;
               } else {
                   document.getElementById('editMultiplayerYes').checked = false;
                   document.getElementById('editMultiplayerNo').checked = true;
               }

               // Показываем модальное окно
               document.getElementById('editModal').style.display = 'flex';
               document.getElementById('editTitle').focus();

           } catch (error) {
               console.error('Error opening edit modal:', error);
               alert('Ошибка при открытии формы редактирования');
           }
       }

       function closeEditModal() {
           document.getElementById('editModal').style.display = 'none';
       }

       function deleteGame(gameId) {
           console.log('Preparing to delete game ID:', gameId, 'Type:', typeof gameId);

           try {
               // Преобразуем ID в строку для селектора
               const gameIdStr = String(gameId);

               // Ищем строку таблицы с правильным селектором
               const row = document.querySelector(`tr[data-game-id="${gameIdStr}"]`);

               console.log('Searching for selector:', `tr[data-game-id="${gameIdStr}"]`);
               console.log('Found rows with data-game-id attribute:');
               document.querySelectorAll('tr[data-game-id]').forEach(tr => {
                   console.log('  -', tr.getAttribute('data-game-id'));
               });

               if (row) {
                   // Ищем название игры разными способами
                   let gameName = 'Игра #' + gameId;

                   // Способ 1: Ищем по классу game-title
                   const gameNameElement = row.querySelector('.game-title');
                   if (gameNameElement) {
                       gameName = gameNameElement.textContent.trim();
                   }
                   // Способ 2: Ищем в ячейке с названием (вторая ячейка в строке)
                   else {
                       const cells = row.querySelectorAll('td');
                       if (cells.length > 1) {
                           const titleCell = cells[1];
                           // Берем первый элемент с текстом
                           const textElements = titleCell.querySelectorAll('*');
                           for (let el of textElements) {
                               if (el.textContent && el.textContent.trim()) {
                                   gameName = el.textContent.trim();
                                   break;
                               }
                           }
                       }
                   }

                   gameToDelete = gameId;
                   gameToDeleteName = gameName;

                   console.log('Found game:', gameName, 'with ID:', gameId);

                   // Показываем название игры в модальном окне
                   document.getElementById('deleteGameName').textContent = gameName;

                   // Показываем модальное окно
                   document.getElementById('deleteModal').style.display = 'flex';
               } else {
                   console.error('Game row not found for ID:', gameId);
                   console.error('Available data-game-id attributes:');
                   const allRows = document.querySelectorAll('tr');
                   allRows.forEach((tr, index) => {
                       const idAttr = tr.getAttribute('data-game-id');
                       if (idAttr) {
                           console.error(`  Row ${index}: data-game-id="${idAttr}"`);
                       }
                   });

                   // Альтернативный подход: спросить подтверждение напрямую
                   if (confirm('Вы уверены, что хотите удалить игру с ID ' + gameId + '?')) {
                       submitDeleteForm(gameId, 'Игра #' + gameId);
                   }
               }
           } catch (error) {
               console.error('Error in deleteGame:', error);
               // Простой fallback
               if (confirm('Удалить игру с ID ' + gameId + '?')) {
                   submitDeleteForm(gameId, 'Игра #' + gameId);
               }
           }
       }

       // Функция для отправки формы удаления
       function submitDeleteForm(gameId, gameName) {
           try {
               console.log('Submitting delete form for game ID:', gameId);

               // Создаем форму для отправки DELETE запроса
               const form = document.createElement('form');
               form.method = 'POST';
               form.action = '${pageContext.request.contextPath}/games';

               // Добавляем скрытое поле _method
               const methodInput = document.createElement('input');
               methodInput.type = 'hidden';
               methodInput.name = '_method';
               methodInput.value = 'DELETE';
               form.appendChild(methodInput);

               // Добавляем скрытое поле с ID
               const idInput = document.createElement('input');
               idInput.type = 'hidden';
               idInput.name = 'id';
               idInput.value = gameId;
               form.appendChild(idInput);

               // Добавляем форму на страницу и отправляем
               document.body.appendChild(form);
               form.submit();

               // Показываем сообщение о процессе удаления
               alert('Удаление игры "' + gameName + '"...');

           } catch (error) {
               console.error('Error submitting delete form:', error);
               alert('Ошибка при удалении игры');
           }
       }

       function closeDeleteModal() {
           document.getElementById('deleteModal').style.display = 'none';
           gameToDelete = null;
           gameToDeleteName = '';
       }

       function confirmDelete() {
           console.log('Confirming deletion of game ID:', gameToDelete);

           if (gameToDelete) {
               submitDeleteForm(gameToDelete, gameToDeleteName);
           } else {
               console.error('No game selected for deletion');
               alert('Не выбрана игра для удаления');
           }
           closeDeleteModal();
       }

       // Поиск по таблице
       document.getElementById('gameSearch').addEventListener('input', function(e) {
           const searchTerm = e.target.value.toLowerCase();
           const rows = document.querySelectorAll('#gamesTable tbody tr');

           rows.forEach(row => {
               const text = row.textContent.toLowerCase();
               row.style.display = text.includes(searchTerm) ? '' : 'none';
           });
       });

       // Закрытие модальных окон при клике вне их
       window.addEventListener('click', function(e) {
           if (e.target.id === 'addModal') {
               closeAddModal();
           }
           if (e.target.id === 'editModal') {
               closeEditModal();
           }
           if (e.target.id === 'deleteModal') {
               closeDeleteModal();
           }
       });

       // Автоматическое закрытие сообщений через 5 секунд
       setTimeout(function() {
           const alerts = document.querySelectorAll('.alert');
           alerts.forEach(alert => {
               try {
                   const bsAlert = new bootstrap.Alert(alert);
                   bsAlert.close();
               } catch (error) {
                   console.error('Error closing alert:', error);
               }
           });
       }, 5000);

       // Эскейп закрывает модальные окна
       document.addEventListener('keydown', function(e) {
           if (e.key === 'Escape') {
               closeAddModal();
               closeEditModal();
               closeDeleteModal();
           }
       });

       // Логирование для отладки
       console.log('Game page JavaScript loaded successfully');
   </script>

</body>
</html>