package com.EduTech.educationportal.data;

import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.model.Teacher;
import com.EduTech.educationportal.utils.Log;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class UserRepository implements UserRepositoryInterface {

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
    public void getTeachers(ObservableList<Teacher> teacherList){
        Log.info("Start getting teachers from database");
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT id, name, email, city, password, subject FROM usersDB WHERE role = 'teacher'");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Log.info("Get teachers from database");
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String city = rs.getString("city");
                String password = rs.getString("password");
                String subject = rs.getString("subject");
                teacherList.add(new Teacher(id, name, email, city, password, subject));
            }

        } catch (SQLException e) {
            Log.error("Error while getting teacher from database");
            e.printStackTrace();
        }
    }
    public void getTeachers(List<Teacher> teacherList){
        Log.info("Start getting teachers from database");
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT id, name, email, city, password, subject FROM usersDB WHERE role = 'teacher' AND subject IS NUll");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Log.info("Get teachers from database");
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String city = rs.getString("city");
                String password = rs.getString("password");
                String subject = rs.getString("subject");
                teacherList.add(new Teacher(id, name, email, city, password, subject));
            }

        } catch (SQLException e) {
            Log.error("Error while getting teacher from database");
            e.printStackTrace();
        }
    }
    public void addTeacher(String name, String email, String city, String password, Integer courseID){
        Log.info("Add teacher to database");
        String sql = "INSERT INTO usersDB (name, email, city, password, subject, role) Values(?, ?, ?, ?, ?, ?)";
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, city);
            stmt.setString(4, password);
            if(courseID != null){
                stmt.setInt(5, courseID);
            }
            else{
                Log.warn("course was not selected");
                stmt.setNull(5, java.sql.Types.INTEGER);
            }

            stmt.setString(6, "teacher");
            stmt.executeUpdate();
            Log.info("Teacher was successfully added");


        }catch (SQLException e){
            Log.error("Error while adding teacher to database");
            e.printStackTrace();
        }

    }
    public String checkUserRole(String email){
        Log.info("Check user role");
        String sql = "SELECT role FROM usersDB WHERE email = ?";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return switch (rs.getString("role")) {
                case "student" -> "student";
                case "teacher" -> "teacher";
                default -> "manager";
            };

        }catch (SQLException e){
            Log.error("Error while checking user role");
            e.printStackTrace();
            return null;
        }

    }
    public void createDefaultManager(String firstName, String email, String password, String role){
        String sql = "SELECT * FROM usersDB where role = ?";
        Log.info("Creating a default manager");
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, role);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){
                String insert  = "INSERT INTO usersDB (name, email, password, role) Values(?, ?, ?, ?)";
                try(PreparedStatement insertStmt = conn.prepareStatement(insert);){

                    insertStmt.setString(1, firstName);
                    insertStmt.setString(2, email);
                    insertStmt.setString(3, password);
                    insertStmt.setString(4, role);
                    insertStmt.executeUpdate();
                    Log.info("Default manager created");

                }catch (SQLException e){
                    Log.error("Error while registering student in database");
                    e.printStackTrace();
                }

            }

        }catch (SQLException e){
            Log.error("Error while creating default manager");
            e.printStackTrace();
        }
    }
    public void deleteUser(String email){
        String sql = "DELETE FROM usersDB WHERE email = ?";
        Log.info("Deleting user");
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, email);
            stmt.executeUpdate();
            Log.info("User deleted was successfully");
        }catch (SQLException e){
            Log.error("Error while deleting user");
            e.printStackTrace();
        }
    }

}
