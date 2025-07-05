package com.EduTech.educationportal.app.view.auth;

import com.EduTech.educationportal.presenter.auth.AuthentificationPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class RegisterViewController {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private final AuthentificationPresenter presenter = new AuthentificationPresenter();

    @FXML
    private void handleRegisterData(){
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        presenter.registerUser(name, email, password);
    }
}
