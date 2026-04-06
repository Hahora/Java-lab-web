CREATE TABLE IF NOT EXISTS developers (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    rating      DOUBLE,
    founded_year INT,
    country     VARCHAR(100),
    description VARCHAR(2000),
    website     VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS games (
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    title                VARCHAR(255) NOT NULL,
    release_year         INT,
    genre                VARCHAR(100),
    developer_id         BIGINT,
    system_requirements  VARCHAR(1000),
    price                DOUBLE,
    multiplayer          BOOLEAN DEFAULT FALSE,
    metacritic_score     INT,
    description          VARCHAR(2000),
    FOREIGN KEY (developer_id) REFERENCES developers(id)
);
