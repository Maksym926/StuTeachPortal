package com.EduTech.educationportal.interfaces.presenter;

import com.EduTech.educationportal.model.entities.Course;

import java.util.List;

public interface AddTeacherPresenterInterface {
    void addTeacherToDB(String name, String email, String city, String password, Integer courseID);
    boolean checkUserPresence(String email);
    void getCourses(List<Course> course);
    void setTeacher(String email, Integer courseID);
}
