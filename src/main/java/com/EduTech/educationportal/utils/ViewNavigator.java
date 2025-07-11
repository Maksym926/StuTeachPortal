package com.EduTech.educationportal.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewNavigator {
    public static void switchScene(Node source, String fxmlPath, String title, Object controller){
        try{
            FXMLLoader loader = new FXMLLoader(ViewNavigator.class.getResource(fxmlPath));
            loader.setController(controller);
            Parent root = loader.load();
            Stage stage = (Stage)source.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);

            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
