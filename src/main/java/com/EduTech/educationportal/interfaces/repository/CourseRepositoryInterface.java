package com.EduTech.educationportal.interfaces.repository;

import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.Topic;
import javafx.collections.ObservableList;

import java.util.List;

public interface CourseRepositoryInterface {
    void addCourse(String courseTitle, String courseCode, Integer teacherID, String courseDescription, int courseDuration);
    void getNonAssignedCourses(List<Course> courses);
    void setSubject(String courseTitle, Integer teacherID );
    void setTeacher(String email, Integer courseID);
    void getAllCourses(ObservableList<Course> courses);
    void updateCourseDescription(Course course);
    void getCoursesByTeacherID(int teacherID, ObservableList<Course> courses);
    void unassignCourse(int id);
    List<Course> getAllCourses();

}
