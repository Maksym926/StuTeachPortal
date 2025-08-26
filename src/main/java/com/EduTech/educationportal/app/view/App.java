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
import com.EduTech.educationportal.model.aws.S3Downloader;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.User;
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

import java.nio.file.Paths;
import java.sql.SQLException;


import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
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
            Log.warn("All user's locations and name : " + user.getEmail() + " " + user.getPassword());
        }


        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        DBConnection.init();
        Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        webServer.stop();
//        String bucketName = "assignments-bucket1";
//        String keyName = "assignments/assignment1.pdf"; // S3 path
//        String filePath = "G:/CV/Maksym_Chechotkin_CV.pdf";    // Local file path
//
//        try (S3Client s3 = S3Client.builder().build()) {
//            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
//                    .bucket(bucketName)
//                    .key(keyName)
//                    .contentType("application/pdf")
//                    .build();
//
//            s3.putObject(putObjectRequest, RequestBody.fromFile(Paths.get(filePath)));
//            Log.info("File was successfully uploaded to the server");
//        }
        Parent root = FXMLLoader.load(getClass().getResource("/MainMenuView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main View");
        stage.show();
        ViewNavigator.addScene("/MainMenuView.fxml", "Main Menu");
//        Course course = new Course(17, "", "", 2, "", 1); // however you're getting it
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ManageCourseContent.fxml"));
//
//// Tell FXMLLoader how to create the controller
//        loader.setControllerFactory(param -> {
//            if (param == ManageCourseContentController.class) {
//                return new ManageCourseContentController(course);
//            } else {
//                try {
//                    return param.getDeclaredConstructor().newInstance();
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//
//        Parent root = loader.load();
//
//// Get controller instance with course set
//        ManageCourseContentInterface manageCourseContentInterface = loader.getController();
//
//// Setup scene
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setTitle("Manage course");
//        stage.show();
//
//
//
//// Setup logic
//
//        CourseContentRepositoryInterface courseContentRepositoryInterface= new CourseContentRepository();
//        CourseContentRepository test = new CourseContentRepository();
//        S3Downloader downloader = new S3Downloader();
//        test.printTopicInfo();
//        ManageCourseContentPresenterInterface manageCourseContentPresenterInterface = new ManageCourseContentPresenter(manageCourseContentInterface, courseContentRepositoryInterface, downloader);
//        SetupControllerInterface setupController = (SetupControllerInterface) manageCourseContentInterface;
//
//        setupController.setup();

    }
}