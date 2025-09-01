package com.EduTech.educationportal.interfaces.presenter;

import com.EduTech.educationportal.model.entities.Course;
import javafx.collections.ObservableList;

public interface TeacherInfoPresenterInterface {
    void setTeachingCourses(int teacherID, ObservableList<Course> courses);
    void unassignTeachingCourse(int courseID);
}
