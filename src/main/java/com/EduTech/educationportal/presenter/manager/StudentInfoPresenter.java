package com.EduTech.educationportal.presenter.manager;

import com.EduTech.educationportal.interfaces.presenter.StudentInfoPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.EnrolmentRepositoryInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.StudentInformationViewInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.Student;

import java.util.List;

public class StudentInfoPresenter implements StudentInfoPresenterInterface {
    StudentInformationViewInterface view;
    EnrolmentRepositoryInterface enrolmentRepository;
    UserRepositoryInterface userRepository;
    public StudentInfoPresenter(StudentInformationViewInterface view, EnrolmentRepositoryInterface enrolmentRepository, UserRepositoryInterface userRepository){
        this.view = view;
        this.enrolmentRepository = enrolmentRepository;
        this.userRepository = userRepository;
        view.setPresenter(this);
    }
    public void getSubscribedCourses(int studentID){
        List<Course> subscribedCourses = enrolmentRepository.getSubscribedCourses(studentID);
        view.setSubscribedCoursesList(subscribedCourses);

    }
    public void unsubscribeCourse(int studentID, int courseID){
        enrolmentRepository.unassignCourse(studentID,courseID);
    }
    public void deleteUserAccount(Student student){

        enrolmentRepository.deleteUser(student.getID());
        userRepository.deleteUser(student.getEmail());
    }
}
