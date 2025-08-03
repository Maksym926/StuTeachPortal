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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ManagerDashboardViewController  implements ManagerDashboardViewInterface, SetupControllerInterface {
    private ManagerDashboardPresenter presenter;

    @FXML


    private final ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML TableView<User> userTable ;
    @FXML TableColumn<User, String> fullNameColumn = new TableColumn<>("Name");
    @FXML TableColumn<User, String> emailColumn = new TableColumn<>("Email");
    @FXML TableColumn<User, String> roleColumn = new TableColumn<>("Role");

    @FXML MenuButton locationMenu;

    @FXML CheckBox teacherCheckBox;
    @FXML CheckBox studentCheckBox;

    @FXML MenuItem allLocations;

    @FXML TextField userSearch;

    public String selectedLocation;



    @Override
    public void setManagerDashboardPresenter(ManagerDashboardPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public List<User> getFilteredUsers(String location, boolean isStudent, boolean isTeacher) {
        userList.clear();
        presenter.getAllUsers(userList);

        return userList.stream().filter(user -> {

            if(location.equals("All locations")) return true;

            if(location != null && !location.equals(user.getCity()))
                return false;

            if(isStudent && isTeacher && location!= null && location.equals(user.getCity()))
                return true;

            if(isStudent && !user.getRole().equals("student"))return false;
            if(isTeacher && !user.getRole().equals("teacher"))return false;

            return true;
        }).collect(Collectors.toList());

    }

    @Override
    public List<User> getFilteredUsers(String query) {
        userList.clear();
        presenter.getAllUsers(userList);
        List<User> filteredUsers = new ArrayList<>();
        for(User user: userList){
            if(user.getName().toLowerCase().contains(query.toLowerCase()) || user.getEmail().toLowerCase().contains(query.toLowerCase()))
                filteredUsers.add(user);
        }
        return filteredUsers;
    }

    public void setup() {
        Log.info("Setting up manage view...");

        userList.clear();


        // Configure the columns
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        // Add some test data or fetch from presenter

        presenter.getAllUsers(userList);




        userTable.setItems(userList);

        Log.info("Getting all users locations from DB ");
        for(User user : userList){

            MenuItem menuItem = new MenuItem(user.getCity());
            menuItem.setOnAction(event -> {

                locationMenu.setText(user.getCity());
                selectedLocation = user.getCity();
            });
            locationMenu.getItems().add(menuItem);


        }
        allLocations.setOnAction(e ->{
            locationMenu.setText("All locations");
            selectedLocation = "All locations";
        });
        locationMenu.setText("Select Location");

        userSearch.setOnAction(event ->{
            String query = userSearch.getText();
            userTable.setItems(FXCollections.observableArrayList(getFilteredUsers(query)));



        });


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
    @FXML
    public void applyFilters(ActionEvent event){
        Log.info("Applying filters");

        userList.clear();
        userTable.getItems().clear();
        if( selectedLocation == null && teacherCheckBox.isSelected() && studentCheckBox.isSelected() || selectedLocation == null && !teacherCheckBox.isSelected() && !studentCheckBox.isSelected() ){
            presenter.getAllUsers(userList);
            userTable.setItems(userList);

        } else {
          List<User> users = getFilteredUsers(selectedLocation, studentCheckBox.isSelected(), teacherCheckBox.isSelected());
          userTable.setItems(FXCollections.observableArrayList(users));
        }

    }


}
