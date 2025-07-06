package com.EduTech.educationportal.data;

import com.EduTech.educationportal.interfaces.repository.StudentRepositoryInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRepository implements StudentRepositoryInterface {

    private final String URL = "jdbc:h2:./data/stuTeachdb";
    private final String USER = "sa";
    private final String PASSWORD = "";


    public void registerStudent(String firstName,String email, String password) {
        String sql = "INSERT INTO studentInfo (name, email, password) Values(?, ?, ?)";
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, firstName);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();
            System.out.println("Students were registered");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
