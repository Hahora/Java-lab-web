-- Начальные данные для разработчиков
INSERT INTO developers (name, rating, founded_year, country, description, website)
VALUES
('CD Projekt Red', 9.5, 1994, 'Польша', 'Польская компания, разработчик серии Witcher и Cyberpunk 2077', 'https://www.cdprojektred.com'),
('Rockstar Games', 9.2, 1998, 'США', 'Создатели серий GTA и Red Dead Redemption', 'https://www.rockstargames.com'),
('Naughty Dog', 8.9, 1984, 'США', 'Студия Sony, разработчик Uncharted и The Last of Us', 'https://www.naughtydog.com');

-- Начальные данные для игр
INSERT INTO games (title, release_year, genre, developer_id, system_requirements, price, multiplayer, metacritic_score, description)
VALUES
('The Witcher 3: Wild Hunt', 2015, 'RPG', 1, 'Intel Core i5-2500K, GTX 660, 6GB RAM', 19.99, TRUE, 93, 'Открытый мир, лучшая RPG по мнению критиков'),
('Cyberpunk 2077', 2020, 'RPG', 1, 'Intel Core i7-6700K, GTX 1060, 12GB RAM', 59.99, FALSE, 86, 'Киберпанк-RPG в мегаполисе будущего'),
('GTA V', 2013, 'Action', 2, 'Intel Core i5-3470, GTX 660, 8GB RAM', 29.99, TRUE, 97, 'Трёхперсонажный открытый мир в Лос-Сантосе'),
('The Last of Us Part I', 2022, 'Action', 3, 'Intel Core i7-8700, RTX 2080 Super, 16GB RAM', 59.99, FALSE, 89, 'Постапокалиптический экшн с глубоким сюжетом');
