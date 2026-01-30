package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionProperty {
    public static final String CONFIG_NAME = "config.properties";
    private static final Properties GLOBAL_CONFIG = new Properties();

    static {
        try {
            // Загружаем из classpath (из папки resources)
            InputStream inputStream = ConnectionProperty.class
                    .getClassLoader()
                    .getResourceAsStream(CONFIG_NAME);

            if (inputStream == null) {
                System.err.println("Файл " + CONFIG_NAME + " не найден в classpath!");
            } else {
                GLOBAL_CONFIG.load(inputStream);
                inputStream.close();
                System.out.println("✅ Конфигурация загружена из: " + CONFIG_NAME);
            }
        } catch (IOException e) {
            System.err.println("Ошибка загрузки " + CONFIG_NAME + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getProperty(String property) {
        String value = GLOBAL_CONFIG.getProperty(property);
        if (value == null) {
            System.err.println("⚠️ Свойство '" + property + "' не найдено в конфигурации!");
        }
        return value;
    }

    // Метод для отладки
    public static void printConfig() {
        System.out.println("=== Конфигурация подключения к БД ===");
        System.out.println("URL: " + getProperty("db.url"));
        System.out.println("Пользователь: " + getProperty("db.login"));
        System.out.println("Драйвер: " + getProperty("db.driver.class"));
        System.out.println("=====================================");
    }
}