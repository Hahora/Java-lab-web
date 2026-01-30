<table class="data-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Год</th>
            <th>Жанр</th>
            <th>Разработчик</th>
            <th>Цена ($)</th>
            <th>Multiplayer</th>
            <th>Metacritic</th>
            <th>Действия</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="game" items="${games}">
            <tr>
                <td>${game.id}</td>
                <td><strong>${game.title}</strong>
                    <c:if test="${not empty game.description}">
                        <br><small>${game.description}</small>
                    </c:if>
                </td>
                <td><span class="year-badge">${game.releaseYear}</span></td>
                <td><span class="genre-badge">${game.genre}</span></td>
                <td>
                    <c:choose>
                        <c:when test="${game.developer != null}">
                            ${game.developer.name}
                            <br><small>${game.developer.country} (${game.developer.foundedYear})</small>
                        </c:when>
                        <c:otherwise>
                            Не указан
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${game.price > 0}">
                        $${game.price}
                    </c:if>
                    <c:if test="${game.price == 0}">
                        <span style="color:green;">Free</span>
                    </c:if>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${game.multiplayer}">
                            <i class="fas fa-users" style="color:green;"></i> Да
                        </c:when>
                        <c:otherwise>
                            <i class="fas fa-user" style="color:blue;"></i> Нет
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${game.metacriticScore > 0}">
                        <span class="rating-badge">${game.metacriticScore}</span>
                    </c:if>
                </td>
                <td>
                    <button class="btn-small"><i class="fas fa-edit"></i></button>
                    <button class="btn-small btn-danger"><i class="fas fa-trash"></i></button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>