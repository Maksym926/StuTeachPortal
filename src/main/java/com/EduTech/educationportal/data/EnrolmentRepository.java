package com.EduTech.educationportal.data;

import com.EduTech.educationportal.interfaces.repository.EnrolmentRepositoryInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.User;
import com.EduTech.educationportal.utils.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrolmentRepository implements EnrolmentRepositoryInterface {
    public void subscribeStudentOnCourse(Course course, User student){
        String sql = "INSERT INTO enrolmentTable (studentID, courseID) Values(?,?)";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, student.getID());
            stmt.setInt(2, course.getID());
            stmt.executeUpdate();
            Log.info("User was subscribed on course ");
        }catch(SQLException e){
            Log.error("Error while subscribing student on course");
            e.printStackTrace();
        }

    }
}
