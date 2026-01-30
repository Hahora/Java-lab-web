package domain;

/**
 * Класс данных о разработчиках игр
 */
public class Developer {

    // Идентификатор разработчика
    private Long id;

    // Название компании-разработчика
    private String name;

    // Рейтинг разработчика (0-10)
    private Double rating;

    // Конструктор по умолчанию
    public Developer() {
    }

    // Конструктор с названием
    public Developer(String name) {
        this.name = name;
    }

    // Конструктор с названием и рейтингом
    public Developer(String name, Double rating) {
        this.name = name;
        this.rating = rating;
    }

    // Полный конструктор
    public Developer(Long id, String name, Double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Developer {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}