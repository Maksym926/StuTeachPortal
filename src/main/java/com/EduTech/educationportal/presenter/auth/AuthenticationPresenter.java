package com.EduTech.educationportal.presenter.auth;

import com.EduTech.educationportal.app.view.auth.RegisterViewController;
import com.EduTech.educationportal.data.StudentRepository;
import com.EduTech.educationportal.interfaces.presenter.AuthPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.StudentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AuthViewInterface;

public class AuthenticationPresenter implements AuthPresenterInterface {
    StudentRepositoryInterface studentRepository;
    AuthViewInterface registerView;


    public AuthenticationPresenter(StudentRepositoryInterface studentRepository, AuthViewInterface registerView) {
        this.studentRepository = studentRepository;
        this.registerView = registerView;
        registerView.setAuthPresenter(this);
    }

    public void registerUser(String username, String email, String password) {

        studentRepository.registerStudent( username, email, password);

    }
}
