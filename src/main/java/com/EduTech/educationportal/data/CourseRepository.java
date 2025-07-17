package com.EduTech.educationportal.data;

import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.model.Course;
import com.EduTech.educationportal.utils.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRepository implements CourseRepositoryInterface {

    public void addCourse(String courseTitle, String courseCode, int teacherID, String courseDescription, int courseDuration){
        String sql = "INSERT INTO coursesDB (courseTitle, courseCode, teacherID, courseDescription, courseDuration) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, courseTitle);
            stmt.setString(2, courseCode);
            stmt.setInt(3, teacherID);
            stmt.setString(4, courseDescription);
            stmt.setInt(5, courseDuration);
            stmt.executeUpdate();
            Log.info("Course was successfully inserted");
        }catch(SQLException e){
            Log.error("Error while inserting a course to the database");
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
                course.setTitle(rs.getString("courseTitle"));
                course.setCode(rs.getString("courseCode"));
                course.setTeacherId(rs.getInt("teacherID"));
                course.setDescription(rs.getString("courseDescription"));
                course.setDuration(rs.getInt("courseDuration"));
                Log.info("printing course.... " + course.getTitle() + " " + course.getCode() + " " + course.getTeacherId() + " " + course.getDescription() + " " + course.getDuration());
            }

        }catch (SQLException e){
            Log.error("Error while getting course info from db");
            e.printStackTrace();
        }

    }
}
