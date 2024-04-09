package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Utility class for managing a single instance of database connection. This
 * class uses a properties file named {@code database.properties} for retrieving
 * database connection details and ensures that only one instance of the
 * {@link Connection} is active at any given time.
 *
 * @author Zimeng Wang, Wenxin Li, Mengying Liu.
 * @since 1.0
 * @version 1.5
 */
public class DBConnection {

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the database connection using parameters specified in the
     * {@code database.properties} file.
     */
    private static void initializeConnection() {
        Properties properties = new Properties();

        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new RuntimeException("database.properties file not found in the classpath");
            }
            properties.load(input);
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

    /**
     * Provides access to the singleton instance of the database connection. If
     * the connection is not already initialized or has been closed, it will
     * initialize the connection.
     *
     * @return The singleton {@link Connection} instance.
     * @throws RuntimeException if an error occurs while checking the connection
     * status or initializing the connection.
     */
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
