#!/bin/bash

echo "=== Настройка Git репозитория для проекта games-project ==="

# Инициализация репозитория
git init

# Создание веток
git checkout -b master
echo "Ветка master создана"

git checkout -b dev1
echo "Ветка dev1 создана (для лабораторной работы)"

# Создание .gitignore если его нет
if [ ! -f .gitignore ]; then
    cat > .gitignore << 'EOF'
# IntelliJ IDEA
.idea/
*.iml
*.iws
*.ipr
out/

# Eclipse
.settings/
.classpath
.project

# NetBeans
nbproject/
nbactions.xml

# Maven
target/
.mvn/

# Tomcat
Tomcat_10.1/

# OS
.DS_Store
Thumbs.db

# Logs
*.log

# IDE
.vscode/
*.swp
EOF
    echo ".gitignore создан"
fi

# Первоначальный коммит
git add .
git commit -m "Initial commit: Проект Games Management System"

echo ""
echo "=== Настройка завершена ==="
echo "Текущая ветка: $(git branch --show-current)"
echo "Для создания удаленного репозитория выполните:"
echo "git remote add origin <your-repo-url>"
echo "git push -u origin master"
echo "git push origin dev1"