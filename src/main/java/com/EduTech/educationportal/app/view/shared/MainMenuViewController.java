package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.app.view.auth.AuthViewController;
import com.EduTech.educationportal.data.UserRepository;
import com.EduTech.educationportal.interfaces.presenter.AuthPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AuthViewInterface;
import com.EduTech.educationportal.presenter.auth.AuthenticationPresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;


import java.io.IOException;

public class MainMenuViewController {

    @FXML
    public void openRegisterWindow(ActionEvent event) {
        Log.info("Opening register window");
        UserRepositoryInterface studentRepository = new UserRepository();
        AuthViewInterface registerView = new AuthViewController();
        AuthPresenterInterface authenticationPresenter = new AuthenticationPresenter(studentRepository, registerView);


        ViewNavigator.switchScene((Node) event.getSource(), "/RegisterView.fxml", "Register", registerView);
    }
    @FXML
    public void openLoginWindow(ActionEvent event) {
        Log.info("Opening login window");
        UserRepositoryInterface studentRepository = new UserRepository();
        AuthViewInterface loginView = new AuthViewController();
            AuthPresenterInterface authenticationPresenter = new AuthenticationPresenter(studentRepository, loginView);

        ViewNavigator.switchScene((Node) event.getSource(), "/LoginView.fxml", "Login", loginView);
    }
}