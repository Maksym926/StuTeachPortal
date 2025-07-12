package com.EduTech.educationportal.interfaces.presenter;

import com.EduTech.educationportal.presenter.auth.AuthenticationPresenter;

public interface AuthPresenterInterface {
    void registerUser(String username, String email, String password);

    boolean loginUser(String username, String password);

    boolean checkUserPresence(String email);

    boolean checkUserRole(String email);




}
