package com.EduTech.educationportal.presenter.student;

import com.EduTech.educationportal.interfaces.repository.EnrolmentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.CourseDescriptionPreviewInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.User;
import com.EduTech.educationportal.interfaces.presenter.CourseDescriptionPresenterInterface;
public class CourseDescriptionPresenter implements CourseDescriptionPresenterInterface{

    EnrolmentRepositoryInterface enrolmentRepository;

    CourseDescriptionPreviewInterface view;

    public CourseDescriptionPresenter(EnrolmentRepositoryInterface enrolmentRepository, CourseDescriptionPreviewInterface view){
        this.enrolmentRepository = enrolmentRepository;
        this.view = view;
        view.setPresenter(this);

    }

    public void subscribeStudentOnCourse(Course course, User student){
        enrolmentRepository.subscribeStudentOnCourse(course, student);
    }
}
