package com.EduTech.educationportal.interfaces.repository;

import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.User;

import java.util.List;

public interface EnrolmentRepositoryInterface {
    void subscribeStudentOnCourse(Course course, User student);
    List<Course> getSubscribedCourses(int studentId);
    boolean isSubscribedOnCourse(User student, Course course);
    List<Course> getCoursesByStudentID(int studentID);
}
