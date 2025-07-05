package com.EduTech.educationportal.model.repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    public static final String URL = "jdbc:h2:./data/stuTeachdb";
    public static final String USER = "sa";
    public static final String PASSWORD = "";


    public static void init() {
        try (Connection conn = DriverManager.getConnection(URL, "sa", "");
        Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS studentInfo (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "email VARCHAR(255) UNIQUE," +
                    "password VARCHAR(255) UNIQUE" +
                    ")";
            stmt.execute(sql);
            System.out.println("Student table created or already exists.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
