<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         isErrorPage="true" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>404 - Страница не найдена</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/jspf/header.jsp" />

    <div class="error-container">
        <div class="error-content">
            <h1><i class="fas fa-exclamation-triangle"></i> 404</h1>
            <h2>Страница не найдена</h2>
            <p>Запрошенная страница не существует или была перемещена.</p>
            <a href="${pageContext.request.contextPath}/" class="btn">
                <i class="fas fa-home"></i> Вернуться на главную
            </a>
        </div>
    </div>

    <jsp:include page="/jspf/footer.jsp" />
</body>
</html>