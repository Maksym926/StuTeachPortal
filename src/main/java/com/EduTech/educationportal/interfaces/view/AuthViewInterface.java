package com.EduTech.educationportal.interfaces.view;

import com.EduTech.educationportal.presenter.auth.AuthenticationPresenter;
import javafx.event.ActionEvent;

public interface AuthViewInterface {
    void setAuthPresenter(AuthenticationPresenter presenter);
    void handleRegisterData(ActionEvent event);
    void handleLoginData(ActionEvent event);

}
