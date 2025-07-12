package com.EduTech.educationportal.data;

import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.utils.Log;

import java.sql.*;

public class UserRepository implements UserRepositoryInterface {

    private final String URL = "jdbc:h2:./data/stuTeachdb";
    private final String USER = "sa";
    private final String PASSWORD = "";


    public void registerStudent(String firstName,String email, String password) {
        Log.info("Insert student into database");
        String sql = "INSERT INTO usersDB (name, email, password, role) Values(?, ?, ?, ?)";
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, firstName);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, "student");
            stmt.executeUpdate();
            System.out.println("Students were registered");

        }catch (SQLException e){
            Log.error("Error while registering student in database");
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
        Log.info("Login user");
        String sql = "SELECT * FROM usersDB WHERE email = ? AND password = ?";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }

        catch (SQLException e){
            Log.error("Error while logging in user");
            e.printStackTrace();
            return false;
        }


    }

    public boolean checkUserPresence(String email){
        Log.info("Check in user present in database");
        String sql = "SELECT * FROM usersDB WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }catch (SQLException e){
            Log.error("Error while checking user presence");
            e.printStackTrace();
            return false;
        }
    }
    public void addTeacher(String firstName, String email, String password){
        Log.info("Add teacher to database");
        String sql = "INSERT INTO usersDB (name, email, password, role) Values(?, ?, ?, ?)";
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, firstName);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, "teacher");
            stmt.executeUpdate();


        }catch (SQLException e){
            Log.error("Error while adding teacher to database");
            e.printStackTrace();
        }

    }

}
