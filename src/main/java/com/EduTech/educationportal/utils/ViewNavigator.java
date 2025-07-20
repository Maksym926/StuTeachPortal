package com.EduTech.educationportal.utils;

import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class ViewNavigator {
    private static Stack<SceneData> scenes = new Stack<>();
    public static void switchScene(Node source, String fxmlPath, String title, Object controller){
        try{
            //Pushing current stage
            Stage stage = (Stage)source.getScene().getWindow();
            Scene scene = stage.getScene();
            if(scene != null){
                scenes.push(new SceneData(fxmlPath, title, controller));
            }
            FXMLLoader loader = new FXMLLoader(ViewNavigator.class.getResource(fxmlPath));
            loader.setController(controller);
            Parent root = loader.load();
            if(controller instanceof SetupControllerInterface setupController){
                Log.info("Setting up controller using setup interface");
                setupController.setup();
            }
            stage.setScene(new Scene(root));
            stage.setTitle(title);

            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void addScene(String fxmlPath, String title){
        scenes.push(new SceneData(fxmlPath, title));
    }
    public static void goBack(Node source){
        if(!scenes.isEmpty()){
            Log.info("Going back");
            scenes.pop();
            SceneData sceneData = scenes.peek();

            switchScene(source, sceneData.getFxmlPath(), sceneData.getTitle(), sceneData.getController());


        }
    }
}
