package com.EduTech.educationportal.data;

import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.utils.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseRepository implements CourseRepositoryInterface {
    String sql = "INSERT INTO coursesDB (courseTitle, courseCode, teacherID, courseDescription, courseDuration) VALUES (?, ?, ?, ?, ?)";
    public void addCourse(String courseTitle, String courseCode, int teacherID, String courseDescription, int courseDuration){
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
}
