<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Разработчики игр - GameHub</title>
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
            grid-template-columns: repeat(3, 1fr);
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

        .developer-name-cell {
            font-weight: 700;
            color: #2c3e50;
            font-size: 16px;
        }

        .developer-description {
            color: #7f8c8d;
            font-size: 13px;
            margin-top: 5px;
            line-height: 1.4;
        }

        .rating-badge {
            background: linear-gradient(45deg, #ff9900, #ff6600);
            color: white;
            padding: 6px 12px;
            border-radius: 20px;
            font-weight: 600;
            font-size: 14px;
            display: inline-flex;
            align-items: center;
            gap: 5px;
        }

        .country-badge {
            background: #3498db;
            color: white;
            padding: 4px 10px;
            border-radius: 12px;
            font-size: 12px;
            display: inline-flex;
            align-items: center;
            gap: 5px;
        }

        .games-count-badge {
            background: #2ecc71;
            color: white;
            padding: 4px 10px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: 600;
        }

        .website-link {
            color: #667eea;
            text-decoration: none;
            font-weight: 600;
            display: inline-flex;
            align-items: center;
            gap: 5px;
        }

        .website-link:hover {
            color: #764ba2;
            text-decoration: underline;
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

        /* Модальные окна (стили как в games.jsp) */
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
            max-width: 500px;
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
                <i class="fas fa-users"></i>
                Каталог разработчиков
            </h1>
            <button class="add-btn" onclick="openAddModal()">
                <i class="fas fa-plus"></i>
                Добавить разработчика
            </button>
        </div>

        <!-- Статистика -->
        <div class="stats-cards">
            <div class="stat-card">
                <div class="stat-icon">
                    <i class="fas fa-users"></i>
                </div>
                <div class="stat-value">${developers.size()}</div>
                <div class="stat-label">Всего разработчиков</div>
            </div>

            <div class="stat-card">
                <div class="stat-icon">
                    <i class="fas fa-star"></i>
                </div>
                <div class="stat-value">
                    <c:set var="avgRating" value="0" />
                    <c:forEach var="dev" items="${developers}">
                        <c:set var="avgRating" value="${avgRating + dev.rating}" />
                    </c:forEach>
                    <c:if test="${developers.size() > 0}">
                        <fmt:formatNumber value="${avgRating / developers.size()}" maxFractionDigits="1" />
                    </c:if>
                </div>
                <div class="stat-label">Средний рейтинг</div>
            </div>

            <div class="stat-card">
                <div class="stat-icon">
                    <i class="fas fa-globe"></i>
                </div>
                <div class="stat-value">
                    <c:set var="countries" value="${developers.stream().map(d -> d.country).filter(c -> c != null && !c.isEmpty()).distinct().count()}" />
                    ${countries}
                </div>
                <div class="stat-label">Стран</div>
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

        <!-- Таблица с разработчиками -->
        <div class="table-container">
            <div class="table-header">
                <h3>Список всех разработчиков</h3>
                <div class="search-box">
                    <i class="fas fa-search search-icon"></i>
                    <input type="text" class="search-input" placeholder="Поиск разработчиков..." id="developerSearch">
                </div>
            </div>

            <table class="data-table" id="developersTable">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Компания</th>
                        <th>Рейтинг</th>
                        <th>Основана</th>
                        <th>Страна</th>
                        <th>Игр в каталоге</th>
                        <th>Веб-сайт</th>
                        <th>Действия</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="developer" items="${developers}">
                        <tr data-developer-id="${developer.id}">
                            <td style="font-weight: bold; color: #667eea;">#${developer.id}</td>
                            <td>
                                <div class="developer-name-cell">${developer.name}</div>
                                <c:if test="${not empty developer.description}">
                                    <div class="developer-description">
                                        ${developer.description.length() > 100 ? developer.description.substring(0, 100) + '...' : developer.description}
                                    </div>
                                </c:if>
                            </td>
                            <td>
                                <span class="rating-badge">
                                    <i class="fas fa-star"></i> ${developer.rating}/10
                                </span>
                            </td>
                            <td>
                                <c:if test="${developer.foundedYear != null}">
                                    <div style="font-weight: 600;">${developer.foundedYear}</div>
                                    <div style="color: #7f8c8d; font-size: 13px;">
                                        <c:set var="currentYear" value="<%= java.time.Year.now().getValue() %>" />
                                        ${currentYear - developer.foundedYear} лет
                                    </div>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${not empty developer.country}">
                                    <span class="country-badge">
                                        <i class="fas fa-flag"></i> ${developer.country}
                                    </span>
                                </c:if>
                            </td>
                           <td>
                               <span class="games-count-badge">
                                   <i class="fas fa-gamepad"></i>
                                   <c:set var="gamesCount" value="${gamesCountMap[developer.id]}" />
                                   <c:choose>
                                       <c:when test="${gamesCount != null}">
                                           ${gamesCount}
                                       </c:when>
                                       <c:otherwise>
                                           0
                                       </c:otherwise>
                                   </c:choose>
                               </span>
                           </td>
                            <td>
                                <c:if test="${not empty developer.website}">
                                    <a href="${developer.website}" target="_blank" class="website-link">
                                        <i class="fas fa-external-link-alt"></i> Сайт
                                    </a>
                                </c:if>
                            </td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-small btn-edit" onclick="openEditModal(${developer.id}, '${developer.name}', ${developer.rating}, ${developer.foundedYear != null ? developer.foundedYear : 'null'}, '${developer.country}', '${developer.website}', '${developer.description}')">
                                        <i class="fas fa-edit"></i> Изменить
                                    </button>
                                    <button class="btn-small btn-delete" onclick="deleteDeveloper(${developer.id}, '${developer.name}')">
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

    <!-- Модальное окно добавления разработчика -->
    <div class="modal-overlay" id="addModal">
        <div class="modal-content">
            <div class="modal-header">
                <h3><i class="fas fa-plus-circle"></i> Добавить разработчика</h3>
                <button class="close-btn" onclick="closeAddModal()">&times;</button>
            </div>
            <form id="addDeveloperForm" method="POST" action="${pageContext.request.contextPath}/developers">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="form-label" for="addName">Название компании *</label>
                        <input type="text" id="addName" name="name" class="form-control" required>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="addRating">Рейтинг (0-10) *</label>
                                <input type="number" id="addRating" name="rating" class="form-control"
                                       required min="0" max="10" step="0.1" placeholder="9.5">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="addFoundedYear">Год основания</label>
                                <input type="number" id="addFoundedYear" name="foundedYear" class="form-control"
                                       min="1800" max="<%= java.time.Year.now().getValue() %>"
                                       placeholder="Например: 1998">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-label" for="addCountry">Страна</label>
                        <select id="addCountry" name="country" class="form-control">
                            <option value="">Выберите страну</option>
                            <option value="USA">США</option>
                            <option value="Japan">Япония</option>
                            <option value="Poland">Польша</option>
                            <option value="Canada">Канада</option>
                            <option value="UK">Великобритания</option>
                            <option value="Germany">Германия</option>
                            <option value="France">Франция</option>
                            <option value="Sweden">Швеция</option>
                            <option value="Russia">Россия</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="form-label" for="addWebsite">Веб-сайт</label>
                        <input type="url" id="addWebsite" name="website" class="form-control"
                               placeholder="https://example.com">
                    </div>

                    <div class="form-group">
                        <label class="form-label" for="addDescription">Описание компании</label>
                        <textarea id="addDescription" name="description"
                                  class="form-control" rows="4"
                                  placeholder="Краткое описание компании..."></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="closeAddModal()">Отмена</button>
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-save"></i> Добавить
                    </button>
                </div>
            </form>
        </div>
    </div>

    <!-- Модальное окно редактирования разработчика -->
    <div class="modal-overlay" id="editModal">
        <div class="modal-content">
            <div class="modal-header">
                <h3><i class="fas fa-edit"></i> Редактировать разработчика</h3>
                <button class="close-btn" onclick="closeEditModal()">&times;</button>
            </div>
            <form id="editDeveloperForm" method="POST" action="${pageContext.request.contextPath}/developers">
                <input type="hidden" id="editId" name="id">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="form-label" for="editName">Название компании *</label>
                        <input type="text" id="editName" name="name" class="form-control" required>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="editRating">Рейтинг (0-10) *</label>
                                <input type="number" id="editRating" name="rating" class="form-control"
                                       required min="0" max="10" step="0.1">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label" for="editFoundedYear">Год основания</label>
                                <input type="number" id="editFoundedYear" name="foundedYear" class="form-control"
                                       min="1800" max="<%= java.time.Year.now().getValue() %>">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-label" for="editCountry">Страна</label>
                        <select id="editCountry" name="country" class="form-control">
                            <option value="">Выберите страну</option>
                            <option value="USA">США</option>
                            <option value="Japan">Япония</option>
                            <option value="Poland">Польша</option>
                            <option value="Canada">Канада</option>
                            <option value="UK">Великобритания</option>
                            <option value="Germany">Германия</option>
                            <option value="France">Франция</option>
                            <option value="Sweden">Швеция</option>
                            <option value="Russia">Россия</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="form-label" for="editWebsite">Веб-сайт</label>
                        <input type="url" id="editWebsite" name="website" class="form-control">
                    </div>

                    <div class="form-group">
                        <label class="form-label" for="editDescription">Описание компании</label>
                        <textarea id="editDescription" name="description"
                                  class="form-control" rows="4"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="closeEditModal()">Отмена</button>
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-save"></i> Сохранить
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
                    Вы уверены, что хотите удалить этого разработчика?
                </p>
                <p id="deleteDeveloperName" style="text-align: center; font-weight: bold; color: #e74c3c; font-size: 18px;"></p>
                <p style="text-align: center; color: #7f8c8d; font-size: 14px;">
                    Все связанные игры останутся в каталоге без разработчика.
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
        // Переменные для управления модальными окнами
        let developerToDelete = null;
        let developerToDeleteName = '';

        // Функции для модального окна добавления
        function openAddModal() {
            document.getElementById('addModal').style.display = 'flex';
            document.getElementById('addName').focus();
        }

        function closeAddModal() {
            document.getElementById('addModal').style.display = 'none';
            document.getElementById('addDeveloperForm').reset();
        }

        // Функции для модального окна редактирования
        function openEditModal(id, name, rating, foundedYear, country, website, description) {
            // Заполняем форму данными
            document.getElementById('editId').value = id;
            document.getElementById('editName').value = name;
            document.getElementById('editRating').value = rating;
            document.getElementById('editFoundedYear').value = foundedYear === 'null' ? '' : foundedYear;
            document.getElementById('editCountry').value = country;
            document.getElementById('editWebsite').value = website;
            document.getElementById('editDescription').value = description || '';

            // Показываем модальное окно
            document.getElementById('editModal').style.display = 'flex';
            document.getElementById('editName').focus();
        }

        function closeEditModal() {
            document.getElementById('editModal').style.display = 'none';
        }

        // Функции для модального окна удаления
        function deleteDeveloper(id, name) {
            developerToDelete = id;
            developerToDeleteName = name;

            // Показываем название разработчика в модальном окне
            document.getElementById('deleteDeveloperName').textContent = name;

            // Показываем модальное окно
            document.getElementById('deleteModal').style.display = 'flex';
        }

        function closeDeleteModal() {
            document.getElementById('deleteModal').style.display = 'none';
            developerToDelete = null;
            developerToDeleteName = '';
        }

        function confirmDelete() {
            if (developerToDelete) {
                // Отправляем DELETE запрос
                fetch('${pageContext.request.contextPath}/developers?id=' + developerToDelete, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    }
                })
                .then(response => {
                    if (response.ok) {
                        // Перезагружаем страницу после успешного удаления
                        window.location.reload();
                    } else {
                        alert('Ошибка при удалении разработчика');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Ошибка при удалении разработчика');
                });
            }
            closeDeleteModal();
        }

        // Поиск по таблице
        document.getElementById('developerSearch').addEventListener('input', function(e) {
            const searchTerm = e.target.value.toLowerCase();
            const rows = document.querySelectorAll('#developersTable tbody tr');

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
                const bsAlert = new bootstrap.Alert(alert);
                bsAlert.close();
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

        // Экранирование кавычек для JavaScript
        function escapeHtml(text) {
            const div = document.createElement('div');
            div.textContent = text;
            return div.innerHTML;
        }
    </script>

</body>
</html>