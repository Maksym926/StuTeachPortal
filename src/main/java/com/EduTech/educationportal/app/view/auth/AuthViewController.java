package com.EduTech.educationportal.app.view.auth;

import com.EduTech.educationportal.app.view.manager.ManagerDashboardViewController;
import com.EduTech.educationportal.app.view.student.StudentDashboardViewController;
import com.EduTech.educationportal.app.view.teacher.TeacherDashboardViewController;
import com.EduTech.educationportal.data.UserRepository;
import com.EduTech.educationportal.interfaces.presenter.ManagerPresenterInterface;
import com.EduTech.educationportal.interfaces.presenter.StudentPresenterInterface;
import com.EduTech.educationportal.interfaces.presenter.TeacherPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AuthViewInterface;
import com.EduTech.educationportal.interfaces.view.ManagerDashboardViewInterface;
import com.EduTech.educationportal.interfaces.view.StudentDashboardViewInterface;
import com.EduTech.educationportal.interfaces.view.TeacherDashboardViewInterface;
import com.EduTech.educationportal.presenter.auth.AuthenticationPresenter;
import com.EduTech.educationportal.presenter.manager.ManagerDashboardPresenter;
import com.EduTech.educationportal.presenter.student.StudentDashboardPresenter;
import com.EduTech.educationportal.presenter.teacher.TeacherDashboardPresenter;
import com.EduTech.educationportal.utils.Log;
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
    public void handleRegisterData(ActionEvent event){
        Log.info("Register button was pressed");
        Log.info("Starting register");
        String name = nameField.getText();
        String email = emailField.getText();
        if(passwordField.getText().equals(repeatPasswordField.getText()) && isValidPassword(passwordField.getText())){
            if(!presenter.checkUserPresence(email)){
                errorMassageRegister.setText("");
                String password = passwordField.getText();
                presenter.registerUser(name, email, password);
                ViewNavigator.switchScene((Node)event.getSource(), "/LoginView.fxml", "Login", this);
            }else{
                Log.warn("Register failed, account with this email already exists");
                errorMassageRegister.setText("Account with this email already exists");
            }
        }
        else{
            Log.warn("Register failed, password does not match or is not valid");
            errorMassageRegister.setText("Password does not match or is not valid");
        }
    }

    @FXML
    public void handleLoginData(ActionEvent event){
        Log.info("Login button was pressed");
        Log.info("Starting login");
        String email = emailFieldLogin.getText();
        String password = passwordFieldLogin.getText();
        if(!presenter.loginUser(email, password)){
            Log.warn("Logging failed, incorrect credentials");
            errorMassageLogin.setText("Email or password is not correct");
        }
        else{
            Log.info("Login successful");
            openDashboardWindow(event, email);
        }
    }

    private void openDashboardWindow(ActionEvent event, String email) {
        Log.info("Opening dashboard window");
        switch (presenter.checkUserRole(email)){
            case "student":
                Log.info("Opening student dashboard");
                StudentDashboardViewInterface StudentDashboardView = new StudentDashboardViewController();
                StudentPresenterInterface StudentDashboardPresenter = new StudentDashboardPresenter(StudentDashboardView);
                ViewNavigator.switchScene((Node)event.getSource(), "/StudentDashboard.fxml", "Student Dashboard", StudentDashboardView);
                break;
            case "teacher":
                Log.info("Opening teacher dashboard");
                TeacherDashboardViewInterface TeacherDashboardView = new TeacherDashboardViewController();
                TeacherPresenterInterface TeacherDashboardPresenter = new TeacherDashboardPresenter(TeacherDashboardView);
                ViewNavigator.switchScene((Node)event.getSource(), "/TeacherDashboard.fxml", "Teacher Dashboard", TeacherDashboardView);
                break;
            case "manager":
                Log.info("Opening manager dashboard");
                ManagerDashboardViewInterface managerDashboardView = new ManagerDashboardViewController();
                UserRepositoryInterface userRepositoryInterface = new UserRepository();

                ManagerPresenterInterface managerDashboardPresenter = new ManagerDashboardPresenter(managerDashboardView, userRepositoryInterface);

                ViewNavigator.switchScene((Node)event.getSource(), "/ManagerDashboard.fxml", "Manager Dashboard", managerDashboardView);
                managerDashboardView.setup();
                break;
        }

    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    @Override
    public void setAuthPresenter(AuthenticationPresenter authPresenter) {
        this.presenter = authPresenter;
    }
}
