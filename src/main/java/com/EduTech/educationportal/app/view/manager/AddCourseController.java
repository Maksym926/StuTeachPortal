package com.EduTech.educationportal.app.view.manager;

import com.EduTech.educationportal.interfaces.view.AddCourseViewInterface;
import com.EduTech.educationportal.presenter.manager.AddCoursePresenter;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddCourseController implements AddCourseViewInterface {

    AddCoursePresenter presenter;

    @FXML TextField courseTitle;
    @FXML TextField courseCode;
    @FXML MenuButton teacher;
    @FXML TextArea courseDescription;
    @FXML TextField courseDuration;


    public void setPresenter(AddCoursePresenter presenter) {
        this.presenter = presenter;
    }
    @FXML
    public void addCourse() {
        String courseTitleText = courseTitle.getText();
        String courseCodeText = courseCode.getText();
//        int teacherText = Integer.parseInt(teacher.getText());
        String courseDescriptionText = courseDescription.getText();
//        int courseDurationText = Integer.parseInt(courseDuration.getText());
        presenter.addCourse(courseTitleText, courseCodeText , 121, courseDescriptionText, 12);

    }
}
