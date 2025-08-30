package com.EduTech.educationportal.presenter.manager;

import com.EduTech.educationportal.data.CourseRepository;
import com.EduTech.educationportal.data.UserRepository;
import com.EduTech.educationportal.interfaces.presenter.TeacherInfoPresenterInterface;
import com.EduTech.educationportal.interfaces.view.TeacherInformationViewInterface;
import com.EduTech.educationportal.model.entities.Course;
import javafx.collections.ObservableList;

public class TeacherInfoPresenter implements TeacherInfoPresenterInterface {
    TeacherInformationViewInterface view;
    UserRepository userRepository;
    CourseRepository courseRepository;
    public TeacherInfoPresenter(TeacherInformationViewInterface view, UserRepository userRepository, CourseRepository courseRepository){
        this.view = view;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        view.setPresenter(this);
    }
    public void setTeachingCourses(int teacherID, ObservableList<Course> courses){
        courseRepository.getCoursesByTeacherID(teacherID, courses);

    }
}
