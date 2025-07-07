package com.EduTech.educationportal.app.view.auth;

import com.EduTech.educationportal.interfaces.view.AuthViewInterface;
import com.EduTech.educationportal.presenter.auth.AuthenticationPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class AuthViewController implements AuthViewInterface {
    //registerWindow inputs

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    // loginWindow inputs

    @FXML private TextField emailFieldLogin;
    @FXML private PasswordField passwordFieldLogin;

    private AuthenticationPresenter presenter;



    @FXML
    private void handleRegisterData(){

        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        presenter.registerUser(name, email, password);

    }

    @FXML

    private void handleLoginData(){
        String email = emailFieldLogin.getText();
        String password = passwordFieldLogin.getText();
        presenter.loginUser(email, password);
    }

    @Override
    public void setAuthPresenter(AuthenticationPresenter authPresenter) {
        this.presenter = authPresenter;
    }
}
