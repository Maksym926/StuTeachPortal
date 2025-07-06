package com.EduTech.educationportal.app.view.auth;

import com.EduTech.educationportal.interfaces.presenter.AuthPresenterInterface;
import com.EduTech.educationportal.interfaces.view.AuthViewInterface;
import com.EduTech.educationportal.presenter.auth.AuthenticationPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class RegisterViewController implements AuthViewInterface {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private AuthenticationPresenter presenter;



    @FXML
    private void handleRegisterData(){

        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        presenter.registerUser(name, email, password);
    }

    @Override
    public void setAuthPresenter(AuthenticationPresenter authPresenter) {
        this.presenter = authPresenter;
    }
}
