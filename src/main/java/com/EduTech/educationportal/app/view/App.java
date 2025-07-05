package com.EduTech.educationportal.app.view;
import com.EduTech.educationportal.data.DBConnection;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;


import java.sql.SQLException;

public class App extends Application {

     public static void main(String[] args) throws SQLException {
        DBConnection.init();
        Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        webServer.start();

        System.out.println("Hello world");

        launch(args);


    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/EduTech/educationportal/app/view/shared/MainMenuView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main View");
        stage.show();
    }
}
