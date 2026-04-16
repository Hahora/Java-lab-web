package com.example.gamehub;

import com.example.gamehub.controller.DeveloperController;
import com.example.gamehub.domain.Developer;
import com.example.gamehub.repository.DeveloperRepository;
import com.example.gamehub.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тесты веб-слоя для DeveloperController с использованием MockMvc.
 * @WebMvcTest загружает только MVC-слой, репозиторий заменяется mock-объектом.
 */
@WebMvcTest(DeveloperController.class)
@Import(SecurityConfig.class)
class DeveloperControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeveloperRepository developerRepository;

    /**
     * Тест: GET /developers возвращает HTTP 200 и view "developers/list".
     */
    @Test
    @WithMockUser(username = "Alex", roles = {"USER"})
    void testDevelopersList() throws Exception {
        Developer dev = new Developer(1L, "CD Projekt Red", 9.5, 1994,
                "Польша", "Создатели Witcher", "https://cdprojektred.com");
        when(developerRepository.findAll()).thenReturn(Arrays.asList(dev));

        mockMvc.perform(get("/developers"))
                .andExpect(status().isOk())
                .andExpect(view().name("developers/list"))
                .andExpect(model().attributeExists("developers"));
    }

    /**
     * Тест: GET /developers/new показывает форму создания разработчика.
     */
    @Test
    @WithMockUser(username = "Alice", roles = {"USER"})
    void testNewDeveloperForm() throws Exception {
        mockMvc.perform(get("/developers/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("developers/form"))
                .andExpect(model().attributeExists("developer"));
    }

    /**
     * Тест: GET /developers/{id} возвращает детали разработчика.
     */
    @Test
    @WithMockUser(username = "Alex", roles = {"USER"})
    void testDeveloperDetail() throws Exception {
        Developer dev = new Developer(1L, "Rockstar Games", 9.2, 1998,
                "США", "Создатели GTA", "https://rockstargames.com");
        when(developerRepository.findById(1L)).thenReturn(Optional.of(dev));

        mockMvc.perform(get("/developers/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("developers/detail"));
    }

    /**
     * Тест: POST /developers с невалидными данными возвращает форму с ошибками.
     */
    @Test
    @WithMockUser(username = "Alex", roles = {"USER"})
    void testSaveDeveloperValidationError() throws Exception {
        mockMvc.perform(post("/developers")
                        .with(csrf())
                        .param("name", "")  // пустое имя — нарушение @NotBlank
                )
                .andExpect(status().isOk())
                .andExpect(view().name("developers/form"));
    }

    /**
     * Тест: неаутентифицированный запрос к /developers перенаправляется на /login.
     */
    @Test
    void testUnauthenticatedRedirectsToLogin() throws Exception {
        mockMvc.perform(get("/developers"))
                .andExpect(status().is3xxRedirection());
    }

}
