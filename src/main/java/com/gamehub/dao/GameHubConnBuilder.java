package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GameHubConnBuilder implements ConnectionBuilder {

    public GameHubConnBuilder() {
        try {
            Class.forName(ConnectionProperty.getProperty("db.driver.class"));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Драйвер PostgreSQL не найден", ex);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = ConnectionProperty.getProperty("db.url");
        String login = ConnectionProperty.getProperty("db.login");
        String password = ConnectionProperty.getProperty("db.password");

        return DriverManager.getConnection(url, login, password);
    }
}