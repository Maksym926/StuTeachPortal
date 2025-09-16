package com.EduTech.educationportal.interfaces.presenter;

import com.EduTech.educationportal.model.entities.Student;

public interface StudentInfoPresenterInterface {
    void unsubscribeCourse(int studentID, int courseID);
    void getSubscribedCourses(int studentID);
    void deleteUserAccount(Student student);
}
