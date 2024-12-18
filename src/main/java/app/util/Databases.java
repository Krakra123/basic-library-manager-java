package app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Databases {
    
    private static final String ROOT_URL = "jdbc:mysql://localhost:3306/";
    private static final String ROOT_USER = "root";
    private static final String ROOT_PASSWORD = "your_root_password";
    
    private static final String NEW_DATABASE_NAME = "_basic_library_manager_database";
    private static final String NEW_DB_USER = "admin";
    private static final String NEW_DB_PASSWORD = "admin";

    public static void createLocalHost() {
        try {
            Connection rootConnection = DriverManager.getConnection(ROOT_URL, ROOT_USER, ROOT_PASSWORD);
            Statement statement = rootConnection.createStatement();

            boolean databaseExists = checkDatabaseExists(statement, NEW_DATABASE_NAME);

            if (!databaseExists) {
                statement.executeUpdate("CREATE DATABASE " + NEW_DATABASE_NAME);
                System.out.println("Database created successfully");

                statement.executeUpdate("CREATE USER '" + NEW_DB_USER + "'@'localhost' IDENTIFIED BY '" + NEW_DB_PASSWORD + "'");
                statement.executeUpdate("GRANT ALL PRIVILEGES ON " + NEW_DATABASE_NAME + ".* TO '" + NEW_DB_USER + "'@'localhost'");
                statement.executeUpdate("FLUSH PRIVILEGES");
                System.out.println("User created and privileges granted");
            } else {
                System.out.println("Database already exists");
            }

            statement.close();
            rootConnection.close();

        } catch (SQLException e) {
            System.err.println("Error creating localhost database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static boolean checkDatabaseExists(Statement statement, String databaseName) throws SQLException {
        return statement.executeQuery("SHOW DATABASES LIKE '" + databaseName + "'").next();
    }
}
