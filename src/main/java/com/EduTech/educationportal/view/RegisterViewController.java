package com.EduTech.educationportal.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import com.EduTech.educationportal.presenter.RegisterPresenter;

public class RegisterViewController {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private final RegisterPresenter presenter = new RegisterPresenter();

    @FXML
    private void handleRegisterData(){
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        presenter.registerUser(name, email, password);
    }
}
