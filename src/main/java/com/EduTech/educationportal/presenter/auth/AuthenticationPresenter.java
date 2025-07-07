package com.EduTech.educationportal.presenter.auth;

import com.EduTech.educationportal.data.StudentRepository;
import com.EduTech.educationportal.interfaces.presenter.AuthPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.StudentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AuthViewInterface;
import com.EduTech.educationportal.model.Student;

import java.util.ArrayList;

public class AuthenticationPresenter implements AuthPresenterInterface {
    StudentRepositoryInterface studentRepository;
    AuthViewInterface authenticationView;


    public AuthenticationPresenter(StudentRepositoryInterface studentRepository, AuthViewInterface authenticationView) {
        this.studentRepository = studentRepository;
        this.authenticationView = authenticationView;
        authenticationView.setAuthPresenter(this);
    }


    public void registerUser(String username, String email, String password) {

        studentRepository.registerStudent( username, email, password);

    }

    @Override
    public void loginUser(String username, String password) {
        studentRepository.loginUser(username, password);
    }


}
