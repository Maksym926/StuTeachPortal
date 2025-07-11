package com.EduTech.educationportal.presenter.auth;

import com.EduTech.educationportal.interfaces.presenter.AuthPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AuthViewInterface;

public class AuthenticationPresenter implements AuthPresenterInterface {
    UserRepositoryInterface userRepositoryInterface;
    AuthViewInterface authenticationView;


    public AuthenticationPresenter(UserRepositoryInterface userRepositoryInterface, AuthViewInterface authenticationView) {
        this.userRepositoryInterface = userRepositoryInterface;
        this.authenticationView = authenticationView;
        authenticationView.setAuthPresenter(this);
    }


    public void registerUser(String username, String email, String password) {

        userRepositoryInterface.registerStudent( username, email, password);

    }

    @Override
    public boolean loginUser(String username, String password) {
        return userRepositoryInterface.loginUser(username, password);
    }

    @Override
    public boolean checkUserPresence(String email) {
        return userRepositoryInterface.checkUserPresence(email);
    }


}
