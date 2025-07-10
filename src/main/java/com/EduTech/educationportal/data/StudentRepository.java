package com.EduTech.educationportal.data;

import com.EduTech.educationportal.interfaces.repository.StudentRepositoryInterface;
import com.EduTech.educationportal.model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentRepository implements StudentRepositoryInterface {

    private final String URL = "jdbc:h2:./data/stuTeachdb";
    private final String USER = "sa";
    private final String PASSWORD = "";


    public void registerStudent(String firstName,String email, String password) {
        String sql = "INSERT INTO users (name, email, password) Values(?, ?, ?)";
        try(Connection conn = DBConnection.getConnection()){
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
//    public ArrayList<Student> getAllStudents() {
//        ArrayList<Student> students = new ArrayList<>();
//        String sql = "SELECT * FROM studentInfo";
//        try(Connection conn = DBConnection.getConnection();
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery()){
//            while (rs.next()){
//                Student student = new Student();
//                student.setId(rs.getInt("id"));
//                student.setName(rs.getString("name"));
//                student.setEmail(rs.getString("email"));
//                students.add(student);
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return students;
//    }
    public boolean loginUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }

        catch (SQLException e){
            e.printStackTrace();
            return false;
        }


    }

    public boolean checkUserPresence(String email){
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
