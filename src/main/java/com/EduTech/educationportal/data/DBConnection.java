package com.EduTech.educationportal.data;


import com.EduTech.educationportal.utils.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    public static final String URL = "jdbc:h2:./data/stuTeachdb";
    public static final String USER = "sa";
    public static final String PASSWORD = "";


    public static void init() {
        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement()) {

            String usersDBSQL = "CREATE TABLE IF NOT EXISTS usersDB (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255), " +
                    "email VARCHAR(255), " +
                    "password VARCHAR(255), " +
                    "role VARCHAR(255)" +
                    ")";
            stmt.execute(usersDBSQL);
            Log.info("Users table created or already exists.");


            String subjectsDBSQL = "CREATE TABLE IF NOT EXISTS subjectsDB (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255), " +
                    "description VARCHAR(255), " +
                    "teacherID INT" +
                    ")";
            stmt.execute(subjectsDBSQL);
            Log.info("Subjects table created or already exists.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}
