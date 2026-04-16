package com.example.gamehub;

import com.example.gamehub.domain.Developer;
import com.example.gamehub.repository.DeveloperRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Интеграционные тесты JDBC-репозитория с использованием встроенной БД H2.
 * @SpringBootTest поднимает полный контекст, H2 инициализируется schema.sql и data.sql.
 */
@SpringBootTest
@Transactional
class JdbcDeveloperRepositoryTest {

    @Autowired
    private DeveloperRepository developerRepository;

    /**
     * Тест: findAll() возвращает список разработчиков, загруженных из data.sql.
     */
    @Test
    void testFindAllReturnsInitialData() {
        List<Developer> developers = developerRepository.findAll();
        assertThat(developers).isNotEmpty();
        assertThat(developers.size()).isGreaterThanOrEqualTo(3);
    }

    /**
     * Тест: save() сохраняет нового разработчика и присваивает ему ID.
     */
    @Test
    void testSaveNewDeveloper() {
        Developer dev = new Developer();
        dev.setName("Valve Corporation");
        dev.setCountry("США");
        dev.setFoundedYear(1996);
        dev.setRating(9.0);
        dev.setWebsite("https://www.valvesoftware.com");

        Developer saved = developerRepository.save(dev);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Valve Corporation");
    }

    /**
     * Тест: findById() возвращает сохранённого разработчика.
     */
    @Test
    void testFindByIdAfterSave() {
        Developer dev = new Developer();
        dev.setName("Ubisoft");
        dev.setCountry("Франция");
        dev.setFoundedYear(1986);
        dev.setRating(7.5);

        Developer saved = developerRepository.save(dev);
        Optional<Developer> found = developerRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Ubisoft");
        assertThat(found.get().getCountry()).isEqualTo("Франция");
    }

    /**
     * Тест: save() обновляет существующего разработчика (UPDATE по ID).
     */
    @Test
    void testUpdateExistingDeveloper() {
        Developer dev = new Developer();
        dev.setName("Electronic Arts");
        dev.setCountry("США");
        dev.setFoundedYear(1982);
        dev.setRating(6.0);
        Developer saved = developerRepository.save(dev);

        saved.setRating(6.5);
        saved.setDescription("Крупный издатель игр");
        Developer updated = developerRepository.save(saved);

        Optional<Developer> found = developerRepository.findById(updated.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getRating()).isEqualTo(6.5);
        assertThat(found.get().getDescription()).isEqualTo("Крупный издатель игр");
    }

    /**
     * Тест: deleteById() удаляет разработчика из БД.
     */
    @Test
    void testDeleteDeveloper() {
        Developer dev = new Developer();
        dev.setName("Test Studio");
        dev.setCountry("Тест");
        Developer saved = developerRepository.save(dev);
        Long id = saved.getId();

        developerRepository.deleteById(id);

        Optional<Developer> found = developerRepository.findById(id);
        assertThat(found).isEmpty();
    }

}
