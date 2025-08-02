package com.EduTech.educationportal.app.view;
import com.EduTech.educationportal.app.view.shared.MainMenuViewController;
import com.EduTech.educationportal.data.DBConnection;
import com.EduTech.educationportal.data.UserRepository;
import com.EduTech.educationportal.interfaces.presenter.MainMenuPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.MainMenuViewInterface;
import com.EduTech.educationportal.model.Teacher;
import com.EduTech.educationportal.model.User;
import com.EduTech.educationportal.presenter.shared.MainMenuPresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;
import java.sql.SQLException;

public class App extends Application {

     public static void main(String[] args) throws SQLException {
         Log.info("Starting application");



         UserRepositoryInterface studentRepository = new UserRepository();
         MainMenuViewInterface mainMenuView = new MainMenuViewController();
         MainMenuPresenterInterface presenterInterface = new MainMenuPresenter(mainMenuView, studentRepository);
         Log.info("Deleting manager");
         presenterInterface.deleteUser("admin@school.com");
         Log.info("Setting up default system manager");
         presenterInterface.setManager();
         ObservableList<User> testList = FXCollections.observableArrayList();
         UserRepository userRepository = new UserRepository();
         userRepository.getUsers(testList);

         for(User user : testList){
             Log.warn("All user's locations and name : " + user.getName() + " " + user.getCity() + " " + user.getRole());
         }


        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        DBConnection.init();
        Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        webServer.start();

        Parent root = FXMLLoader.load(getClass().getResource("/MainMenuView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main View");
        stage.show();
        ViewNavigator.addScene("/MainMenuView.fxml", "Main Menu");
    }
    public void stop() throws Exception {
        Log.info("Application is closing");
        super.stop();
    }
}
