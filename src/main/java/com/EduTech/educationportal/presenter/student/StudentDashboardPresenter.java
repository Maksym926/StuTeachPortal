package com.EduTech.educationportal.presenter.student;

import com.EduTech.educationportal.data.CourseRepository;
import com.EduTech.educationportal.interfaces.presenter.StudentPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.StudentDashboardViewInterface;
import com.EduTech.educationportal.model.entities.Course;

import java.util.List;

public class StudentDashboardPresenter implements StudentPresenterInterface {
    StudentDashboardViewInterface view;
    CourseRepositoryInterface courseRepository;
    
    public StudentDashboardPresenter(StudentDashboardViewInterface view, CourseRepositoryInterface courseRepository) {

        this.view = view;
        this.courseRepository = courseRepository;
        view.setStudentDashboardPresenter(this);
    }
    public void setAllCoursesTab(){
        List<Course> allCourses = courseRepository.getAllCourses();
        view.setAllCoursesList(allCourses);

    }


}
