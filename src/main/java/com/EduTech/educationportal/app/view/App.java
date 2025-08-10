package com.EduTech.educationportal.app.view;
import com.EduTech.educationportal.app.view.shared.MainMenuViewController;
import com.EduTech.educationportal.app.view.shared.ManageCourseContentController;
import com.EduTech.educationportal.data.CourseContentRepository;
import com.EduTech.educationportal.data.DBConnection;
import com.EduTech.educationportal.data.UserRepository;
import com.EduTech.educationportal.interfaces.presenter.MainMenuPresenterInterface;
import com.EduTech.educationportal.interfaces.presenter.ManageCourseContentPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.MainMenuViewInterface;
import com.EduTech.educationportal.interfaces.view.ManageCourseContentInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.Course;
import com.EduTech.educationportal.model.Teacher;
import com.EduTech.educationportal.model.User;
import com.EduTech.educationportal.presenter.shared.MainMenuPresenter;
import com.EduTech.educationportal.presenter.shared.ManageCourseContentPresenter;
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

        for (User user : testList) {
            Log.warn("All user's locations and name : " + user.getName() + " " + user.getCity() + " " + user.getRole());
        }


        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        DBConnection.init();
        Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        webServer.start();

//        Parent root = FXMLLoader.load(getClass().getResource("/MainMenuView.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setTitle("Main View");
//        stage.show();
//        ViewNavigator.addScene("/MainMenuView.fxml", "Main Menu");
        Course course = new Course(17, "", "", 1, "", 1); // however you're getting it

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ManageCourseContent.fxml"));

// Tell FXMLLoader how to create the controller
        loader.setControllerFactory(param -> {
            if (param == ManageCourseContentController.class) {
                return new ManageCourseContentController(course);
            } else {
                try {
                    return param.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Parent root = loader.load();

// Get controller instance with course set
        ManageCourseContentInterface manageCourseContentInterface = loader.getController();

// Setup scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Manage course");
        stage.show();

// Setup logic
        CourseContentRepositoryInterface courseContentRepositoryInterface= new CourseContentRepository();
        CourseContentRepository test = new CourseContentRepository();
        test.printTopicInfo();
        ManageCourseContentPresenterInterface manageCourseContentPresenterInterface = new ManageCourseContentPresenter(manageCourseContentInterface, courseContentRepositoryInterface);
        SetupControllerInterface setupController = (SetupControllerInterface) manageCourseContentInterface;
        setupController.setup();
    }
}