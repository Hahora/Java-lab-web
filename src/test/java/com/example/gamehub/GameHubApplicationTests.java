package com.example.gamehub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Интеграционный тест загрузки контекста Spring.
 * @SpringBootTest поднимает полный контекст приложения.
 */
@SpringBootTest
class GameHubApplicationTests {

    @Autowired
    private ApplicationContext context;

    /**
     * Тест: контекст Spring успешно загружается без ошибок.
     * Проверяет корректность всей конфигурации приложения.
     */
    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }

    /**
     * Тест: все ключевые bean-компоненты зарегистрированы в контексте.
     */
    @Test
    void allBeansDefined() {
        assertThat(context.containsBean("developerController")).isTrue();
        assertThat(context.containsBean("gameController")).isTrue();
        assertThat(context.containsBean("homeController")).isTrue();
        assertThat(context.containsBean("securityConfig")).isTrue();
    }

}
