package com.EduTech.educationportal.presenter.manager;

import com.EduTech.educationportal.interfaces.presenter.AddCoursePresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AddCourseViewInterface;
import com.EduTech.educationportal.model.Teacher;

import java.util.List;

public class AddCoursePresenter implements AddCoursePresenterInterface {
    AddCourseViewInterface view;
    CourseRepositoryInterface courseRepository;
    UserRepositoryInterface userRepository;
    public AddCoursePresenter(AddCourseViewInterface view, CourseRepositoryInterface courseRepository, UserRepositoryInterface userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.view = view;
        view.setPresenter(this);
    }
    public void addCourse(String courseTitle, String courseCode, int teacherID, String courseDescription, int courseDuration) {
        courseRepository.addCourse(courseTitle, courseCode, teacherID, courseDescription, courseDuration);
    }
    public void getTeachers(List<Teacher> teacherList){
        userRepository.getTeachers(teacherList);
    }
}
