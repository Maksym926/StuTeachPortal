package com.EduTech.educationportal.app.view.manager;

import com.EduTech.educationportal.interfaces.presenter.AddTeacherPresenterInterface;
import com.EduTech.educationportal.interfaces.view.AddTeacherViewInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.presenter.manager.AddTeacherPresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class AddTeacherController implements AddTeacherViewInterface, SetupControllerInterface {

    AddTeacherPresenterInterface presenter;

    @FXML TextField name;
    @FXML TextField email;
    @FXML TextField city;
    @FXML PasswordField password;
    @FXML MenuButton courseMenuButton;

    List<Course> courses = new ArrayList<>();

    private Course selectedCourse;

    public void setPresenter(AddTeacherPresenter presenter) {
        this.presenter = presenter;
    }
    @FXML
    private void addTeacher() {
        String nameText = name.getText();
        String emailText = email.getText();
        String cityText = city.getText();
        String passwordText = password.getText();
        Integer courseID = null;
        if(selectedCourse != null){
            courseID = selectedCourse.getID();
        }
        if(!presenter.checkUserPresence(emailText))
            presenter.addTeacherToDB(nameText, emailText, cityText, passwordText, courseID);
            if(courseID != null){
                presenter.setTeacher(emailText, courseID);
            }
        else{
            Log.warn("The teacher with this email already exists");
        }


    }
    public void setup(){
        Log.info("Setting up add teacher view");
        presenter.getCourses(courses);
        for(Course course : courses){
            MenuItem menuItem = new MenuItem(course.getTitle());
            menuItem.setOnAction(event -> {
                courseMenuButton.setText(course.getTitle());
                selectedCourse = course;
            });
            courseMenuButton.getItems().add(menuItem);
        }
        courseMenuButton.setText("Select Course");
    }
    @FXML
    public void returnToPreviousForm(ActionEvent event){
        Log.info("Returning to previous form");
        ViewNavigator.goBack((Node) event.getSource());
    }
}
