package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.data.CourseRepository;
import com.EduTech.educationportal.interfaces.presenter.EditCourseDescriptionPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.CourseDescriptionPreviewInterface;
import com.EduTech.educationportal.interfaces.view.EditCourseDescriptionInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.Course;
import com.EduTech.educationportal.presenter.shared.EditCourseDescriptionPresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class CourseDescriptionPreviewController implements CourseDescriptionPreviewInterface, SetupControllerInterface {

    private Course course;
    @FXML Text courseTitle;
    @FXML Text courseCode;
    @FXML Text courseInstructor;
    @FXML Text courseDescription;
    @FXML Text courseDuration;

    @Override
    public void setup() {
        courseTitle.setText(course.getTitle());
        courseCode.setText("Course code " + course.getCode());
//        courseInstructor.setText("Instructor " + course.getTeacherId());
        courseDescription.setText(course.getDescription());
        courseDuration.setText("Course duration " + course.getDuration());
    }

    public CourseDescriptionPreviewController(Course course){
        this.course = course;
    }
    @FXML
    public void returnToPreviousForm(ActionEvent event){
        Log.info("Returning to previous form");
        ViewNavigator.goBack((Node) event.getSource());
    }
    @FXML
    public void openEditDescriptionWindow(ActionEvent event){
        EditCourseDescriptionInterface editCourseDescriptionInterface = new EditCourseDescriptionController(course);
        CourseRepositoryInterface courseRepositoryInterface = new CourseRepository();
        EditCourseDescriptionPresenterInterface editCourseDescriptionPresenterInterface = new EditCourseDescriptionPresenter(editCourseDescriptionInterface, courseRepositoryInterface);
        ViewNavigator.switchScene((Node)event.getSource(), "/EditCourseDescriptionView.fxml", "Edit Course", editCourseDescriptionInterface, true);
    }
}
