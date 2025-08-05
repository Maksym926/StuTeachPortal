package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.interfaces.view.EditCourseDescriptionInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.Course;
import com.EduTech.educationportal.presenter.shared.EditCourseDescriptionPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditCourseDescriptionController implements EditCourseDescriptionInterface, SetupControllerInterface {
    EditCourseDescriptionPresenter presenter;

    Course course;

    @FXML TextField courseTitle;
    @FXML TextField courseCode;
    @FXML TextField courseInstructor;
    @FXML TextField courseDuration;
    @FXML TextArea courseDescription;

    public void setup() {
        courseTitle.setText(course.getTitle());
        courseCode.setText(course.getCode());
//        courseInstructor(course.getTeacherId());

        courseDuration.setText(String.valueOf(course.getDuration()));
        courseDescription.setText(course.getDescription());
    }
    public EditCourseDescriptionController(Course course){
        this.course = course;
    }
    @Override
    public void setPresenter(EditCourseDescriptionPresenter presenter) {
        this.presenter = presenter;
    }
    @FXML
    public void updateDescription(ActionEvent event){
        course.setTitle(courseTitle.getText());
        course.setCode(courseCode.getText());
//        course.setTeacherId();
        course.setDuration(Integer.valueOf(courseDuration.getText()));
        course.setDescription(courseDescription.getText());
        presenter.updateCourseDescription(course);
    }
}
