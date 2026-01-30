package domain;

public class Game {
    private Long id;
    private String title;
    private Integer releaseYear;
    private String genre;
    private Developer developer;
    private String systemRequirements;
    private Double price;
    private Boolean multiplayer;
    private Integer metacriticScore;
    private String description;

    // Конструкторы
    public Game() {}

    // Конструктор для совместимости со старым кодом
    public Game(Long id, String title, Integer releaseYear, String genre,
                String systemRequirements, Long developerId, Developer developer) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.systemRequirements = systemRequirements;
        this.developer = developer;
        this.price = 0.0;
        this.multiplayer = false;
        this.metacriticScore = 0;
        this.description = "";
    }

    // Полный конструктор
    public Game(Long id, String title, Integer releaseYear, String genre,
                Developer developer, String systemRequirements,
                Double price, Boolean multiplayer,
                Integer metacriticScore, String description) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.developer = developer;
        this.systemRequirements = systemRequirements;
        this.price = price;
        this.multiplayer = multiplayer;
        this.metacriticScore = metacriticScore;
        this.description = description;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Developer getDeveloper() { return developer; }
    public void setDeveloper(Developer developer) { this.developer = developer; }

    public String getSystemRequirements() { return systemRequirements; }
    public void setSystemRequirements(String systemRequirements) { this.systemRequirements = systemRequirements; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Boolean getMultiplayer() { return multiplayer; }
    public void setMultiplayer(Boolean multiplayer) { this.multiplayer = multiplayer; }

    public Integer getMetacriticScore() { return metacriticScore; }
    public void setMetacriticScore(Integer metacriticScore) { this.metacriticScore = metacriticScore; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // Методы для обратной совместимости (если нужно)
    public Long getDeveloperId() {
        return developer != null ? developer.getId() : null;
    }

    public String getDeveloperName() {
        return developer != null ? developer.getName() : null;
    }

    public Double getDeveloperRating() {
        return developer != null ? developer.getRating() : null;
    }
}