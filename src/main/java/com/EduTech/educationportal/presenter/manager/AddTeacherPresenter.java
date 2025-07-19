package com.EduTech.educationportal.presenter.manager;

import com.EduTech.educationportal.interfaces.presenter.AddTeacherPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AddTeacherViewInterface;
import com.EduTech.educationportal.model.Course;

import java.util.List;

public class AddTeacherPresenter implements AddTeacherPresenterInterface {
    CourseRepositoryInterface courseRepository;
    UserRepositoryInterface userRepository;
    AddTeacherViewInterface view;
    public AddTeacherPresenter(AddTeacherViewInterface view, UserRepositoryInterface userRepository, CourseRepositoryInterface courseRepository) {
        this.view = view;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        view.setPresenter(this);
    }

    @Override
    public void addTeacherToDB(String name, String email, String city, String password, Integer courseID ) {
        userRepository.addTeacher(name, email, city, password, courseID);
    }

    @Override
    public boolean checkUserPresence(String email) {
        userRepository.checkUserPresence(email);
        return false;
    }

    public void getCourses(List<Course> course){
        courseRepository.getCourses(course);
    }

    @Override
    public void setTeacher(String email, Integer courseID) {
        courseRepository.setTeacher(email, courseID);
    }
}
