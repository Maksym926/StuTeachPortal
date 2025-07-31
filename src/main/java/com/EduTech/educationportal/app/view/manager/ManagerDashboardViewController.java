package com.EduTech.educationportal.app.view.manager;

import com.EduTech.educationportal.data.CourseRepository;
import com.EduTech.educationportal.data.UserRepository;
import com.EduTech.educationportal.interfaces.presenter.AddCoursePresenterInterface;
import com.EduTech.educationportal.interfaces.presenter.AddTeacherPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AddCourseViewInterface;
import com.EduTech.educationportal.interfaces.view.AddTeacherViewInterface;
import com.EduTech.educationportal.interfaces.view.ManagerDashboardViewInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.User;
import com.EduTech.educationportal.presenter.manager.AddCoursePresenter;
import com.EduTech.educationportal.presenter.manager.AddTeacherPresenter;
import com.EduTech.educationportal.presenter.manager.ManagerDashboardPresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


public class ManagerDashboardViewController  implements ManagerDashboardViewInterface, SetupControllerInterface {
    private ManagerDashboardPresenter presenter;

    @FXML


    private final ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML TableView<User> userTable ;
    @FXML TableColumn<User, String> fullNameColumn = new TableColumn<>("Name");
    @FXML TableColumn<User, String> emailColumn = new TableColumn<>("Email");
    @FXML TableColumn<User, String> roleColumn = new TableColumn<>("Role");
    @Override
    public void setManagerDashboardPresenter(ManagerDashboardPresenter presenter) {
        this.presenter = presenter;
    }

    public void setup() {
        Log.info("Setting up manage view...");

        // Configure the columns
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        // Add some test data or fetch from presenter
        presenter.getUsers(userList);

        userTable.setItems(userList);



    }
    @FXML
    private void openAddTeacherWindow(ActionEvent event){
        Log.info("Opening add teacher window");
        AddTeacherViewInterface addTeacherViewInterface = new AddTeacherController();
        UserRepositoryInterface userRepositoryInterface = new UserRepository();
        CourseRepositoryInterface courseRepositoryInterface = new CourseRepository();
        AddTeacherPresenterInterface addTeacherPresenterInterface = new AddTeacherPresenter(addTeacherViewInterface, userRepositoryInterface, courseRepositoryInterface);
        ViewNavigator.switchScene((Node) event.getSource(), "/AddTeacherView.fxml", "Add Teacher", addTeacherViewInterface);

//        CourseRepository courseRepository = new CourseRepository();
//        courseRepository.printCourseInfo();
    }
    @FXML
    private void openAddCourseWindow(ActionEvent event){
        Log.info("Opening add course window");
        AddCourseViewInterface addCourseViewInterface = new AddCourseController();
        CourseRepositoryInterface courseRepositoryInterface = new CourseRepository();
        UserRepositoryInterface userRepositoryInterface = new UserRepository();
        AddCoursePresenterInterface addCoursePresenterInterface = new AddCoursePresenter(addCourseViewInterface, courseRepositoryInterface, userRepositoryInterface);
        ViewNavigator.switchScene((Node) event.getSource(), "/AddCourseView.fxml", "Add Course", addCourseViewInterface);

    }
    @FXML
    public void returnToPreviousForm(ActionEvent event){
        Log.info("Returning to previous form");
        ViewNavigator.goBack((Node) event.getSource());
    }
}
