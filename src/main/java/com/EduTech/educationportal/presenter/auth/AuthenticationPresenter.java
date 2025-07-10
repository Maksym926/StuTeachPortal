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
    public boolean loginUser(String username, String password) {
        return studentRepository.loginUser(username, password);
    }

    @Override
    public boolean checkUserPresence(String email) {
        return studentRepository.checkUserPresence(email);
    }


}
