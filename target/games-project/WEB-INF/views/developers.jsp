<table class="data-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Название компании</th>
            <th>Рейтинг</th>
            <th>Год основания</th>
            <th>Страна</th>
            <th>Действия</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="developer" items="${developers}">
            <tr>
                <td>${developer.id}</td>
                <td>
                    <strong>${developer.name}</strong>
                    <c:if test="${not empty developer.description}">
                        <br><small>${developer.description}</small>
                    </c:if>
                    <c:if test="${not empty developer.website}">
                        <br><small><a href="${developer.website}" target="_blank">${developer.website}</a></small>
                    </c:if>
                </td>
                <td>
                    <span class="rating-badge">${developer.rating}/10</span>
                </td>
                <td>
                    <c:if test="${developer.foundedYear != null}">
                        ${developer.foundedYear}
                    </c:if>
                </td>
                <td>
                    <c:if test="${not empty developer.country}">
                        ${developer.country}
                    </c:if>
                </td>
                <td>
                    <button class="btn-small"><i class="fas fa-edit"></i> Изменить</button>
                    <button class="btn-small btn-danger"><i class="fas fa-trash"></i> Удалить</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>