package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection; 

    static {
        initializeConnection();
    }

    private static void initializeConnection() {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("database.properties");
            properties.load(fis);
            String url = properties.getProperty("jdbc.url");
            String user = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            throw new RuntimeException("Error loading database properties", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error establishing database connection", e);
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                initializeConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking connection status", e);
        }
        return connection;
    }
}
