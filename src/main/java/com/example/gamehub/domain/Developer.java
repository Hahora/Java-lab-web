package com.example.gamehub.domain;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Класс данных о разработчике игр.
 * Аннотация @Data автоматически генерирует геттеры, сеттеры,
 * equals(), hashCode(), toString() через библиотеку Lombok.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer {

    private Long id;

    @NotBlank(message = "Название обязательно")
    @Size(min = 2, max = 255, message = "Название должно быть от 2 до 255 символов")
    private String name;

    @DecimalMin(value = "0.0", message = "Рейтинг не может быть отрицательным")
    @DecimalMax(value = "10.0", message = "Рейтинг не может превышать 10")
    private Double rating;

    @Min(value = 1900, message = "Год основания не может быть раньше 1900")
    @Max(value = 2030, message = "Год основания не может быть позже 2030")
    private Integer foundedYear;

    @Size(max = 100, message = "Название страны не более 100 символов")
    private String country;

    @Size(max = 2000, message = "Описание не более 2000 символов")
    private String description;

    @Size(max = 255, message = "URL сайта не более 255 символов")
    private String website;

}
