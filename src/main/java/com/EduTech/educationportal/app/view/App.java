package com.EduTech.educationportal.app.view;
import com.EduTech.educationportal.app.view.auth.LoginView;
import com.EduTech.educationportal.app.view.auth.RegisterViewController;
import com.EduTech.educationportal.data.DBConnection;


import com.EduTech.educationportal.data.StudentRepository;
import com.EduTech.educationportal.interfaces.presenter.AuthPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.StudentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AuthViewInterface;
import com.EduTech.educationportal.presenter.auth.AuthenticationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;


import java.sql.SQLException;

public class App extends Application {

     public static void main(String[] args) throws SQLException {









        System.out.println("Hello world");

        launch(args);


    }

    @Override
    public void start(Stage stage) throws Exception {


        DBConnection.init();
        Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        webServer.start();


        Parent root = FXMLLoader.load(getClass().getResource("/MainMenuView.fxml"));
        initializeInterfaces();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Main View");
        stage.show();
    }

    private void initializeInterfaces() {

    }
}
