package com.EduTech.educationportal.interfaces.presenter;

import com.EduTech.educationportal.model.entities.Teacher;

import java.util.List;

public interface AddCoursePresenterInterface {
    void addCourse(String courseTitle, String courseCode, Integer teacherID, String courseDescription, int courseDuration);
    void getTeachers(List<Teacher> teacherList);
    void setSubject(String courseTitle, Integer teacherID );

}
