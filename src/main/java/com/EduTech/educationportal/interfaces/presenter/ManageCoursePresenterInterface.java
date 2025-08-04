package com.EduTech.educationportal.interfaces.presenter;

import com.EduTech.educationportal.model.Course;
import com.EduTech.educationportal.model.User;
import javafx.collections.ObservableList;

public interface ManageCoursePresenterInterface {
    User getTeacherByID(int ID);
    void getAllCourses(ObservableList<Course> courseList);
}
