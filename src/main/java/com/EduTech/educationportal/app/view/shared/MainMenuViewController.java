package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.app.view.auth.AuthViewController;
import com.EduTech.educationportal.data.StudentRepository;
import com.EduTech.educationportal.interfaces.presenter.AuthPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.StudentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AuthViewInterface;
import com.EduTech.educationportal.presenter.auth.AuthenticationPresenter;
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
        try {
            StudentRepositoryInterface studentRepository = new StudentRepository();
            AuthViewInterface registerView = new AuthViewController();
            AuthPresenterInterface authenticationPresenter = new AuthenticationPresenter(studentRepository, registerView);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegisterView.fxml"));
            loader.setController(registerView);
            Parent root = loader.load();

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Register");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void openLoginWindow(ActionEvent event) {
        try{
            StudentRepositoryInterface studentRepository = new StudentRepository();
            AuthViewInterface loginView = new AuthViewController();
            AuthPresenterInterface authenticationPresenter = new AuthenticationPresenter(studentRepository, loginView);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
            loader.setController(loginView);
            Parent root = loader.load();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}