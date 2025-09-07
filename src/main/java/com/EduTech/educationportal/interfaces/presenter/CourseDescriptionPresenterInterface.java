package com.EduTech.educationportal.interfaces.presenter;

import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.User;

public interface CourseDescriptionPresenterInterface {
    void subscribeStudentOnCourse(Course course, User student);
}
