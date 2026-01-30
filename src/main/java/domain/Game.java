package domain;

/**
 * Класс данных о компьютерных играх
 */
public class Game {

    // Идентификатор игры
    private Long id;

    // Название игры
    private String title;

    // Год выпуска
    private Integer releaseYear;

    // Жанр игры
    private String genre;

    // Системные требования
    private String systemRequirements;

    // Внешний ключ - ссылка на разработчика
    private Long developerId;

    // Навигационное свойство - ссылка на объект Developer
    private Developer developer;

    // Конструктор по умолчанию
    public Game() {
    }

    // Конструктор с основными полями
    public Game(String title, Integer releaseYear, String genre,
                String systemRequirements, Developer developer) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.systemRequirements = systemRequirements;
        this.developer = developer;
    }

    // Конструктор с developerId
    public Game(String title, Integer releaseYear, String genre,
                String systemRequirements, Long developerId, Developer developer) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.systemRequirements = systemRequirements;
        this.developerId = developerId;
        this.developer = developer;
    }

    // Полный конструктор
    public Game(Long id, String title, Integer releaseYear, String genre,
                String systemRequirements, Long developerId, Developer developer) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.systemRequirements = systemRequirements;
        this.developerId = developerId;
        this.developer = developer;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSystemRequirements() {
        return systemRequirements;
    }

    public void setSystemRequirements(String systemRequirements) {
        this.systemRequirements = systemRequirements;
    }

    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    // Метод для получения названия разработчика
    public String getDeveloperName() {
        return developer != null ? developer.getName() : "Не указан";
    }

    // Метод для получения рейтинга разработчика
    public Double getDeveloperRating() {
        return developer != null ? developer.getRating() : 0.0;
    }

    @Override
    public String toString() {
        return "Game {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre='" + genre + '\'' +
                ", systemRequirements='" + systemRequirements + '\'' +
                ", developerId=" + developerId +
                ", developer=" + (developer != null ? developer.getName() : "null") +
                '}';
    }
}