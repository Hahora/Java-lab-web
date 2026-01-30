package domain;

/**
 * Класс данных о разработчиках игр
 */
public class Developer {
    private Long id;
    private String name;
    private Double rating;
    private Integer foundedYear;
    private String country;
    private String description;
    private String website;

    // Конструкторы
    public Developer() {}

    public Developer(Long id, String name, Double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    // Полный конструктор
    public Developer(Long id, String name, Double rating,
                     Integer foundedYear, String country,
                     String description, String website) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.foundedYear = foundedYear;
        this.country = country;
        this.description = description;
        this.website = website;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Integer getFoundedYear() { return foundedYear; }
    public void setFoundedYear(Integer foundedYear) { this.foundedYear = foundedYear; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    @Override
    public String toString() {
        return "Developer {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", foundedYear=" + foundedYear +
                ", country='" + country + '\'' +
                ", description='" + (description != null ? description.substring(0, Math.min(50, description.length())) + "..." : "null") + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}