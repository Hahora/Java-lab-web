<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         isErrorPage="true" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>500 - Внутренняя ошибка сервера</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/jspf/header.jsp" />

    <div class="error-container">
        <div class="error-content">
            <h1><i class="fas fa-server"></i> 500</h1>
            <h2>Внутренняя ошибка сервера</h2>
            <p>На сервере произошла непредвиденная ошибка.</p>
            <%
                if (exception != null) {
            %>
                <div class="error-details">
                    <h3>Детали ошибки:</h3>
                    <p><%= exception.getMessage() %></p>
                </div>
            <%
                }
            %>
            <a href="${pageContext.request.contextPath}/" class="btn">
                <i class="fas fa-home"></i> Вернуться на главную
            </a>
        </div>
    </div>

    <jsp:include page="/jspf/footer.jsp" />
</body>
</html>