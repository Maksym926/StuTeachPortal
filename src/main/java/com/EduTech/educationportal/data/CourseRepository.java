package com.EduTech.educationportal.data;

import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.model.Course;
import com.EduTech.educationportal.model.Teacher;
import com.EduTech.educationportal.utils.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void getCourses(List<Course> courses){
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
