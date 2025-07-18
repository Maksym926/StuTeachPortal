package com.EduTech.educationportal.interfaces.repository;

import com.EduTech.educationportal.model.Course;

import java.util.List;

public interface CourseRepositoryInterface {
    void addCourse(String courseTitle, String courseCode, Integer teacherID, String courseDescription, int courseDuration);
    void getCourses(List<Course> courses);
}
