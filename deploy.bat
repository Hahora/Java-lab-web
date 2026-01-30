@echo off
echo === Сборка и деплой games-project ===

REM Настройки
set PROJECT_NAME=games-project
set TOMCAT_HOME=C:\apache-tomcat-10.1.52
set WAR_FILE=target\%PROJECT_NAME%.war

echo 1. Очистка и сборка проекта...
call mvn clean package

if %ERRORLEVEL% neq 0 (
    echo Ошибка при сборке!
    pause
    exit /b 1
)

echo 2. Остановка Tomcat...
call "%TOMCAT_HOME%\bin\shutdown.bat"

echo 3. Удаление старой версии...
if exist "%TOMCAT_HOME%\webapps\%PROJECT_NAME%" (
    rmdir /s /q "%TOMCAT_HOME%\webapps\%PROJECT_NAME%"
)
if exist "%TOMCAT_HOME%\webapps\%PROJECT_NAME%.war" (
    del "%TOMCAT_HOME%\webapps\%PROJECT_NAME%.war"
)

echo 4. Копирование нового WAR...
copy "%WAR_FILE%" "%TOMCAT_HOME%\webapps\"

echo 5. Запуск Tomcat...
start "" "%TOMCAT_HOME%\bin\startup.bat"

echo 6. Ожидание запуска Tomcat...
timeout /t 10 /nobreak

echo === Готово! ===
echo Приложение доступно по адресу:
echo http://localhost:8080/%PROJECT_NAME%/
echo.
pause