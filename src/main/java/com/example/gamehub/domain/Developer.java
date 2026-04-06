package com.example.gamehub.domain;

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
    private String name;
    private Double rating;
    private Integer foundedYear;
    private String country;
    private String description;
    private String website;

}
