package com.EduTech.educationportal.presenter.manager;

import com.EduTech.educationportal.interfaces.presenter.StudentInfoPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.EnrolmentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.StudentInformationViewInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.Student;

import java.util.List;

public class StudentInfoPresenter implements StudentInfoPresenterInterface {
    StudentInformationViewInterface view;
    EnrolmentRepositoryInterface enrolmentRepository;
    public StudentInfoPresenter(StudentInformationViewInterface view, EnrolmentRepositoryInterface enrolmentRepository){
        this.view = view;
        this.enrolmentRepository = enrolmentRepository;
        view.setPresenter(this);
    }
    public void getSubscribedCourses(int studentID){
        List<Course> subscribedCourses = enrolmentRepository.getSubscribedCourses(studentID);
        view.setSubscribedCoursesList(subscribedCourses);

    }
}
