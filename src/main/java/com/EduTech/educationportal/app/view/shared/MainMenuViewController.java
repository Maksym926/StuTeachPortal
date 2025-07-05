package com.EduTech.educationportal.app.view.shared;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuViewController{

    @FXML
    private void openRegisterWindow(){
        try {

            Parent registerRoot = FXMLLoader.load(getClass().getResource("/com/EduTech/educationportal/app/view/auth/RegisterView.fxml"));
            Stage registerStage = new Stage();
            Scene registerScene = new Scene(registerRoot);
            registerStage.setScene(registerScene);
            registerStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }

}
