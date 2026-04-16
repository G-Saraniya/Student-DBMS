package bus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection - Handles MySQL database connection for Bus Reservation System
 */
public class DBConnection {
    
    // Database configuration
    private static final String URL = "jdbc:mysql://localhost:3306/bus_reservation?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root"; // Change to your MySQL password
    
    private static Connection connection = null;
    
    /**
     * Get database connection
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to database!");
            System.err.println("Error: " + e.getMessage());
            System.err.println("\nTroubleshooting:");
            System.err.println("1. Ensure MySQL server is running");
            System.err.println("2. Verify password in DBConnection.java");
            System.err.println("3. Check if database 'bus_reservation' exists");
            e.printStackTrace();
        }
        return connection;
    }
    
    /**
     * Close connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection!");
            e.printStackTrace();
        }
    }
    
    /**
     * Test connection
     */
    public static boolean testConnection() {
        try {
            Connection conn = getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Database connected successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Database connection test failed!");
        }
        return false;
    }
}
