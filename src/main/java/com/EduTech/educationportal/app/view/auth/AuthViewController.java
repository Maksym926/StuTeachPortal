package com.EduTech.educationportal.app.view.auth;

import com.EduTech.educationportal.interfaces.view.AuthViewInterface;
import com.EduTech.educationportal.presenter.auth.AuthenticationPresenter;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.util.regex.Pattern;

import javafx.scene.Node;

public class AuthViewController implements AuthViewInterface {
    //registerWindow inputs

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    // loginWindow inputs

    @FXML private TextField emailFieldLogin;
    @FXML private PasswordField passwordFieldLogin;

    @FXML private Label errorMassageLogin;
    @FXML private Label errorMassageRegister;
    @FXML private PasswordField repeatPasswordField;

    private AuthenticationPresenter presenter;



    @FXML
    private void handleRegisterData(ActionEvent event){
        String name = nameField.getText();
        String email = emailField.getText();
        if(passwordField.getText().equals(repeatPasswordField.getText()) && isValidPassword(passwordField.getText())){
            if(!presenter.checkUserPresence(email)){
                errorMassageRegister.setText("");
                String password = passwordField.getText();
                presenter.registerUser(name, email, password);
                ViewNavigator.switchScene((Node)event.getSource(), "/LoginView.fxml", "Login", this);
            }else{
                errorMassageRegister.setText("Account with this email already exists");
            }
        }
        else{
            errorMassageRegister.setText("Password does not match or is not valid");
        }




    }



    @FXML

    private void handleLoginData(){
        String email = emailFieldLogin.getText();
        String password = passwordFieldLogin.getText();
        if(!presenter.loginUser(email, password)){
            errorMassageLogin.setText("Email or password is not correct");
        }
        else errorMassageLogin.setText("");



    }
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    @Override
    public void setAuthPresenter(AuthenticationPresenter authPresenter) {
        this.presenter = authPresenter;
    }
}
