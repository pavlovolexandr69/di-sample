package com.di;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {
    private static final String DB_URL = "jdbc:sqlite:ecology.db"; // Замість "ecology.db" вказуйте свій шлях до файлу SQLite
    
    // Підключення до бази даних
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Створення таблиці, якщо ще не існує
    public static void createTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS pollution_data (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sensor_type TEXT," +
                "pollution_level REAL," +
                "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        
        try (Connection connection = connect(); Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    // Збереження даних у таблиці
    public static void savePollutionData(String sensorType, double pollutionLevel) {
        String insertSQL = "INSERT INTO pollution_data (sensor_type, pollution_level) VALUES (?, ?)";
        
        try (Connection connection = connect(); PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, sensorType);
            pstmt.setDouble(2, pollutionLevel);
            pstmt.executeUpdate();
            System.out.println("Data inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
        }
    }

    // Виведення всіх даних з таблиці
    public static void displayPollutionData() {
        String selectSQL = "SELECT * FROM pollution_data";
        
        try (Connection connection = connect(); Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String sensorType = rs.getString("sensor_type");
                double pollutionLevel = rs.getDouble("pollution_level");
                String date = rs.getString("date");
                
                System.out.println("ID: " + id + ", Sensor Type: " + sensorType + ", Pollution Level: " + pollutionLevel + ", Date: " + date);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving data: " + e.getMessage());
        }
    }
    public static void addMissingColumns() {
        String addColumnsSQL = "ALTER TABLE pollution_data ADD COLUMN sensor_type TEXT";
        
        try (Connection connection = connect(); Statement stmt = connection.createStatement()) {
            stmt.execute(addColumnsSQL);
            System.out.println("Column 'sensor_type' added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding column: " + e.getMessage());
        }
    }
}
