package com.example.gamehub.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Класс данных о компьютерной игре.
 * Lombok @Data генерирует все необходимые методы JavaBean.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
