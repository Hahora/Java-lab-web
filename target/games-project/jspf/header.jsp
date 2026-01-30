<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header>
    <div class="header-container">
        <div class="logo-section">
             <a href="${pageContext.request.contextPath}/" class="logo-link">
                 <div class="logo">
                     <!-- Вместо иконки можно использовать изображение -->
                     <img src="${pageContext.request.contextPath}/images/gamehub.png"
                          alt="GameHub" class="logo-icon" style="height: 40px;">
                     <span class="logo-text">GameHub</span>
                 </div>
             </a>
            <div class="tagline">Управление игровым каталогом</div>
        </div>

        <div class="nav-section">
           <nav class="main-nav">
               <ul>
                   <li><a href="${pageContext.request.contextPath}/" class="nav-link">
                       <i class="fas fa-home"></i> Главная
                   </a></li>
                   <li><a href="${pageContext.request.contextPath}/games" class="nav-link">
                       <i class="fas fa-gamepad"></i> Игры
                   </a></li>
                   <li><a href="${pageContext.request.contextPath}/developers" class="nav-link">
                       <i class="fas fa-users"></i> Разработчики
                   </a></li>
               </ul>
           </nav>
        </div>

        <div class="user-section">
            <div class="user-info">
                <i class="fas fa-user-circle user-icon"></i>
                <span class="username">Далиба Б.Я.</span>
            </div>
            <div class="system-info">
                <span class="time-info" id="current-time"></span>
            </div>
        </div>
    </div>

    <script>
        function updateTime() {
            const now = new Date();
            const timeString = now.toLocaleTimeString('ru-RU');
            document.getElementById('current-time').textContent = timeString;
        }

        // Обновлять время каждую секунду
        setInterval(updateTime, 1000);
        updateTime(); // Инициализация
    </script>
</header>