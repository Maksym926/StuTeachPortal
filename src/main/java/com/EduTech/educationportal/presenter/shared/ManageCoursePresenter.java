package com.EduTech.educationportal.presenter.shared;

import com.EduTech.educationportal.data.CourseRepository;
import com.EduTech.educationportal.interfaces.presenter.ManageCoursePresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.ManageCourseViewInterface;
import com.EduTech.educationportal.model.Course;
import javafx.collections.ObservableList;

public class ManageCoursePresenter  implements ManageCoursePresenterInterface {
    ManageCourseViewInterface view;
    CourseRepositoryInterface courseRepository;
    public ManageCoursePresenter(ManageCourseViewInterface view, CourseRepositoryInterface courseRepository){
        this.view = view;
        this.courseRepository = courseRepository;
        view.setPresenter(this);
    }
    public void getAllCourses(ObservableList<Course> courseList){
        courseRepository.getAllCourses(courseList);
    }
}
