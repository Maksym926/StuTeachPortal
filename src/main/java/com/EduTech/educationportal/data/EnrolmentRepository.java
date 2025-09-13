package com.EduTech.educationportal.data;

import com.EduTech.educationportal.interfaces.repository.EnrolmentRepositoryInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.User;
import com.EduTech.educationportal.utils.Log;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Course> getSubscribedCourses(int studentId) {
        List<Course> subscribedCourses = new ArrayList<>();
        Log.info("Getting subscribed courses");

        String sql = """
        SELECT c.id, c.courseCode, c.courseTitle, 
               c.teacherID, c.courseDescription, c.courseDuration
        FROM enrolmentTable e
        JOIN coursesDB c ON e.courseID = c.id
        WHERE e.studentID = ?
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setID(rs.getInt("id"));
                course.setCode(rs.getString("courseCode"));
                course.setTitle(rs.getString("courseTitle"));
                course.setTeacherId(rs.getInt("teacherID")); // from DB
                course.setDescription(rs.getString("courseDescription"));
                course.setDuration(rs.getInt("courseDuration"));

                subscribedCourses.add(course);
            }

        } catch (SQLException e) {
            Log.error("Error while getting subscribed courses: " + e.getMessage());
        }

        return subscribedCourses;
    }

    public boolean isSubscribedOnCourse(User student, Course course){
        String sql = "SELECT * FROM enrolmentTable WHERE studentID = ? AND courseID = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, student.getID());
            stmt.setInt(2, course.getID());

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // returns true if a row exists
            }
        }catch (SQLException e){
            Log.info("Error while checking subscribed course" + e);
        }
        return false;
    }
    public List<Course> getCoursesByStudentID(int studentID){
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM enrolmentTable WHERE studentID = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            Log.info("getting teaching courses by teacher ID");
            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Course course = new Course();
                course.setID(rs.getInt("id"));
                course.setTitle(rs.getString("courseTitle"));
                course.setCode(rs.getString("courseCode"));
                course.setTeacherId(studentID);
                course.setDescription(rs.getString("courseDescription"));
                course.setDuration(rs.getInt("courseDuration"));
                courses.add(course);
                Log.info("Courses was successfully parsed");
                return courses;
            }
        }catch (SQLException e){
            Log.error("error while getting courses from DB");
            e.printStackTrace();
        }
        return null;
    }
//    public void showSubscribedCourses(){
//        String sql = "SELECT * FROM enrolmentTable";
//        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()){
//                Log.info("StudentID " + rs.getInt("studentID") + " CourseID" + rs.getInt("courseID"));
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
}
