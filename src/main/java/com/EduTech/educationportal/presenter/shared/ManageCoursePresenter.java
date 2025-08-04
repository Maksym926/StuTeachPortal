package com.EduTech.educationportal.presenter.shared;

import com.EduTech.educationportal.data.CourseRepository;
import com.EduTech.educationportal.data.UserRepository;
import com.EduTech.educationportal.interfaces.presenter.ManageCoursePresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.ManageCourseViewInterface;
import com.EduTech.educationportal.model.Course;
import com.EduTech.educationportal.model.User;
import javafx.collections.ObservableList;

public class ManageCoursePresenter  implements ManageCoursePresenterInterface {
    ManageCourseViewInterface view;
    CourseRepositoryInterface courseRepository;
    UserRepositoryInterface userRepository;
    public ManageCoursePresenter(ManageCourseViewInterface view, CourseRepositoryInterface courseRepository, UserRepositoryInterface userRepository){
        this.view = view;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        view.setPresenter(this);
    }
    public void getAllCourses(ObservableList<Course> courseList){
        courseRepository.getAllCourses(courseList);
    }
    public User getTeacherByID(int ID){
        return userRepository.getTeacherByID(ID);
    }
}
