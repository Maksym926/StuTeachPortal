package com.EduTech.educationportal.interfaces.view;

import com.EduTech.educationportal.model.entities.Course;
import  com.EduTech.educationportal.presenter.student.StudentDashboardPresenter;

import java.util.List;

public interface StudentDashboardViewInterface {
    void setStudentDashboardPresenter(StudentDashboardPresenter presenter);
    void setAllCoursesList(List<Course> newAllCoursesList);
    void setSubscribedCoursesList(List<Course> newSubscribedCoursesList);
}
