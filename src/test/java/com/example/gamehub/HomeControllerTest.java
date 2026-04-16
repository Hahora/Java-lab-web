package com.example.gamehub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.gamehub.controller.HomeController;
import com.example.gamehub.security.SecurityConfig;

/**
 * Тест HomeController с использованием MockMvc и Spring Security.
 * @WebMvcTest загружает только слой MVC без полного контекста приложения.
 */
@WebMvcTest(HomeController.class)
@Import(SecurityConfig.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Тест: страница входа доступна без аутентификации (HTTP 200).
     */
    @Test
    void testLoginPageAccessible() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    /**
     * Тест: неаутентифицированный запрос к "/" перенаправляется на страницу входа.
     */
    @Test
    void testHomePageRedirectsToLogin() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Тест: аутентифицированный пользователь видит домашнюю страницу.
     * Аннотация @WithMockUser имитирует вошедшего пользователя с именем "Alex".
     */
    @Test
    @WithMockUser(username = "Alex", roles = {"USER"})
    void testHomePageForAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

}
