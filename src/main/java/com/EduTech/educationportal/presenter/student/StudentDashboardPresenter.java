package com.EduTech.educationportal.presenter.student;

import com.EduTech.educationportal.interfaces.presenter.StudentDashboardPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.interfaces.repository.EnrolmentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.StudentDashboardViewInterface;
import com.EduTech.educationportal.model.entities.Course;

import java.util.List;

public class StudentDashboardPresenter implements StudentDashboardPresenterInterface {
    StudentDashboardViewInterface view;
    CourseRepositoryInterface courseRepository;
    EnrolmentRepositoryInterface enrolmentRepository;
    
    public StudentDashboardPresenter(StudentDashboardViewInterface view, CourseRepositoryInterface courseRepository, EnrolmentRepositoryInterface enrolmentRepository) {
        this.enrolmentRepository = enrolmentRepository;
        this.view = view;
        this.courseRepository = courseRepository;
        view.setStudentDashboardPresenter(this);
    }
    public void setAllCoursesTab(){
        List<Course> allCourses = courseRepository.getAllCourses();
        view.setAllCoursesList(allCourses);

    }
    public void setSubscribedCourses(int studentID){
        List<Course> subscribedCourses = enrolmentRepository.getSubscribedCourses(studentID);
        view.setSubscribedCoursesList(subscribedCourses);
    }


}
