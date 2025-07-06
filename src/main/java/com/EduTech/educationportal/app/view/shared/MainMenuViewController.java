package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.app.view.auth.RegisterViewController;
import com.EduTech.educationportal.data.StudentRepository;
import com.EduTech.educationportal.interfaces.presenter.AuthPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.StudentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AuthViewInterface;
import com.EduTech.educationportal.presenter.auth.AuthenticationPresenter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.io.IOException;

public class MainMenuViewController {

    @FXML
    private void openRegisterWindow() {
        try {
            StudentRepositoryInterface studentRepository = new StudentRepository();
            AuthViewInterface registerView = new RegisterViewController();
            AuthPresenterInterface authenticationPresenter = new AuthenticationPresenter(studentRepository, registerView);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegisterView.fxml"));
            loader.setController(registerView); // Don't forget: REMOVE fx:controller from FXML
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Register");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}