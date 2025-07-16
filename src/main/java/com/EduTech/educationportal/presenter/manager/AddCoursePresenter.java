package com.EduTech.educationportal.presenter.manager;

import com.EduTech.educationportal.interfaces.presenter.AddCoursePresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AddCourseViewInterface;

public class AddCoursePresenter implements AddCoursePresenterInterface {
    AddCourseViewInterface view;
    CourseRepositoryInterface courseRepository;
    public AddCoursePresenter(AddCourseViewInterface view, CourseRepositoryInterface courseRepository) {
        this.courseRepository = courseRepository;
        this.view = view;
        view.setPresenter(this);
    }
    public void addCourse(String courseTitle, String courseCode, int teacherID, String courseDescription, int courseDuration) {
        courseRepository.addCourse(courseTitle, courseCode, teacherID, courseDescription, courseDuration);
    }
}
