package com.EduTech.educationportal.data;

import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.Teacher;
import com.EduTech.educationportal.utils.Log;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements CourseRepositoryInterface {

    public void addCourse(String courseTitle, String courseCode, Integer teacherID, String courseDescription, int courseDuration){
        String sql = "INSERT INTO coursesDB (courseTitle, courseCode, teacherID, courseDescription, courseDuration) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, courseTitle);
            stmt.setString(2, courseCode);
            if(teacherID != null){
                stmt.setInt(3, teacherID);
            }else {
                Log.warn("Teacher is not selected for this course");
                stmt.setNull(3, java.sql.Types.INTEGER);
            }

            stmt.setString(4, courseDescription);
            stmt.setInt(5, courseDuration);
            stmt.executeUpdate();
            Log.info("Course was successfully inserted");
        }catch(SQLException e){
            Log.error("Error while inserting a course to the database");
            e.printStackTrace();
        }
    }
    public void getNonAssignedCourses(List<Course> courses){
        String sql = "SELECT * FROM coursesDB where teacherID is NULL";
        Log.info("Getting courses from db");
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                Course course = new Course();
                course.setID(rs.getInt("id"));
                course.setTitle(rs.getString("courseTitle"));
                course.setCode(rs.getString("courseCode"));
                course.setTeacherId(rs.getInt("teacherID"));
                course.setDescription(rs.getString("courseDescription"));
                course.setDuration(rs.getInt("courseDuration"));
                courses.add(course);
                Log.info("Course was successfully parsed");
            }

        }catch (SQLException e){
            Log.error("Error while getting courses from db");
            e.printStackTrace();
        }

    }
    public void getCoursesByTeacherID(int teacherID, ObservableList<Course> courses){
        String sql = "SELECT * FROM coursesDB WHERE teacherID = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            Log.info("getting teaching courses by teacher ID");
            stmt.setInt(1, teacherID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Course course = new Course();
                course.setID(rs.getInt("id"));
                course.setTitle(rs.getString("courseTitle"));
                course.setCode(rs.getString("courseCode"));
                course.setTeacherId(teacherID);
                course.setDescription(rs.getString("courseDescription"));
                course.setDuration(rs.getInt("courseDuration"));
                courses.add(course);
                Log.info("Courses was successfully parsed");
            }
        }catch (SQLException e){
            Log.error("error while getting courses from DB");
            e.printStackTrace();
        }
    }
    public void unassignCourse(int id){
        Log.info("Deleting teaching course");
        String sql = "UPDATE coursesDB SET teacherID = NULL WHERE teacherID = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            Log.info("Teaching course was deleted");

        }catch (SQLException e){
            Log.error("error occur while trying to delete teaching course");
        }
    }
    public void getAllCourses(ObservableList<Course> courses){
        Log.info("Getting all courses from db");
        String sql = "SELECT * From coursesDB";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Course course = new Course();
                course.setID(rs.getInt("id"));
                course.setTitle(rs.getString("courseTitle"));
                course.setCode(rs.getString("courseCode"));
                course.setTeacherId(rs.getInt("teacherID"));
                course.setDescription(rs.getString("courseDescription"));
                course.setDuration(rs.getInt("courseDuration"));
                courses.add(course);
                Log.info("Course was successfully parsed");
            }

        }catch (SQLException e){
            Log.error("Error while getting all courses");
            e.printStackTrace();
        }
    }
    public List<Course> getAllCourses(){
        List<Course> allCoursesList = new ArrayList<>();
        Log.info("Getting all courses from db");
        String sql = "SELECT * From coursesDB";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Course course = new Course();
                course.setID(rs.getInt("id"));
                course.setTitle(rs.getString("courseTitle"));
                course.setCode(rs.getString("courseCode"));
                course.setTeacherId(rs.getInt("teacherID"));
                course.setDescription(rs.getString("courseDescription"));
                course.setDuration(rs.getInt("courseDuration"));
                allCoursesList.add(course);
                Log.info("Course was successfully parsed");
            }
            return allCoursesList;

        }catch (SQLException e){
            Log.error("Error while getting all courses");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setSubject(String courseTitle, Integer teacherID) {
        Log.info("Entering setSubject method");
        String sql = "SELECT * FROM coursesDB where courseTitle = ?";
        try(Connection conn = DBConnection.getConnection();PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            Log.info("Setting subject");
            stmt.setString(1, courseTitle);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int courseID = rs.getInt("id");
                String sql2 = "UPDATE usersDB SET subject = ? WHERE id = ?";
                try(Connection conn2 = DBConnection.getConnection();PreparedStatement stmt2 = conn2.prepareStatement(sql2)){
                    stmt2.setInt(1, courseID);
                    stmt2.setInt(2, teacherID);

                    int rowsUpdated = stmt2.executeUpdate();
                    if(rowsUpdated > 0){
                        Log.info("Subject was successfully set");
                    }else
                        Log.warn("Subject was not set");



                }catch (SQLException e){
                    Log.error("Error while setting subject");
                    e.printStackTrace();
                }
            }
            else {
                Log.warn("No course found with title: " + courseTitle);
            }

        }catch (SQLException e){
            Log.error("Error setSubject method");
            e.printStackTrace();
        }
    }

    @Override
    public void setTeacher(String email, Integer courseID) {
        Log.info("Entering setCourse method");
        String sql = "SELECT * FROM usersDB where email = ?";
        try(Connection conn = DBConnection.getConnection();PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            Log.info("Setting teacher");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int teacherID = rs.getInt("id");
                String sql2 = "UPDATE coursesDB SET teacherID = ? WHERE id = ?";
                try(Connection conn2 = DBConnection.getConnection();PreparedStatement stmt2 = conn2.prepareStatement(sql2)){
                    stmt2.setInt(1, teacherID);
                    stmt2.setInt(2, courseID);

                    int rowsUpdated = stmt2.executeUpdate();
                    if(rowsUpdated > 0){
                        Log.info("Teacher was successfully set");
                    }else
                        Log.warn("Teacher was not set");



                }catch (SQLException e){
                    Log.error("Error while setting teacher");
                    e.printStackTrace();
                }
            }
            else {
                Log.warn("No teacher is found with email: " + email);
            }

        }catch (SQLException e){
            Log.error("Error in setCourse method");
            e.printStackTrace();
        }
    }
    public void updateCourseDescription(Course course){
        Log.info("Starting updating course");
        String sql = "UPDATE coursesDB SET courseTitle = ?, courseCode = ?, teacherID = ?, courseDescription = ?, courseDuration = ? WHERE id = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,course.getTitle());
            stmt.setString(2, course.getCode());
            stmt.setInt(3, course.getTeacherId());
            stmt.setString(4, course.getDescription());
            stmt.setInt(5, course.getDuration());
            stmt.setInt(6, course.getID());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void printCourseInfo(){
        String sql = "SELECT * FROM coursesDB";
        Log.info("Getting course info from db");
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Course course = new Course();
                course.setID(rs.getInt("id"));
                course.setTitle(rs.getString("courseTitle"));
                course.setCode(rs.getString("courseCode"));
                course.setTeacherId(rs.getInt("teacherID"));
                course.setDescription(rs.getString("courseDescription"));
                course.setDuration(rs.getInt("courseDuration"));
                Log.info("printing course.... " + "ID: " + course.getID() + " " + course.getTitle() + " " + course.getCode() + " " + course.getTeacherId() + " " + course.getDescription() + " " + course.getDuration());
            }

        }catch (SQLException e){
            Log.error("Error while getting course info from db");
            e.printStackTrace();
        }

    }
}
