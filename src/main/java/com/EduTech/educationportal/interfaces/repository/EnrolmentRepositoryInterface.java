package com.EduTech.educationportal.interfaces.repository;

import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.User;

public interface EnrolmentRepositoryInterface {
    void subscribeStudentOnCourse(Course course, User student);
}
