package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.data.CourseContentRepository;
import com.EduTech.educationportal.interfaces.presenter.ManageCourseContentPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.CourseDescriptionPreviewInterface;
import com.EduTech.educationportal.interfaces.view.ManageCourseContentInterface;
import com.EduTech.educationportal.interfaces.view.ManageCourseViewInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.aws.S3Downloader;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.User;
import com.EduTech.educationportal.presenter.shared.ManageCourseContentPresenter;
import com.EduTech.educationportal.presenter.shared.ManageCoursePresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.ArrayList;
import java.util.List;

public class ManageCourseController implements SetupControllerInterface, ManageCourseViewInterface  {
    ManageCoursePresenter presenter;

    @FXML TableView<Course> courseTable;
    @FXML TableColumn<Course, String> courseTitleColumn = new TableColumn<>("title");
    @FXML TableColumn<Course, String> courseTeacherColumn = new TableColumn<>("name");

    private final ObservableList<Course> courseList = FXCollections.observableArrayList();

    private final List<User> teachers = new ArrayList<>();

    @Override
    public void setPresenter(ManageCoursePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setup() {
        Log.info("Setting up Manage Course Window");
        courseList.clear();


        courseTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
//        courseTeacherColumn.setCellValueFactory(data -> {
//
//            Course course = data.getValue();
//            User teacher = presenter.getTeacherByID(course.getTeacherId());
//            return Bindings.createStringBinding(() -> (teacher!=null) ? teacher.getName() : "Unknown");
//
//        });
        presenter.getAllCourses(courseList);




        courseTable.setItems(courseList);

    }
    @FXML
    public void openManageDescriptionWindow(ActionEvent event){
        Log.info("Opening manageDescription Window ");
        Course course = courseTable.getSelectionModel().getSelectedItem();
        if(course == null){
            Log.info("Selected item is empty");
            return;
        }
        CourseDescriptionPreviewInterface courseDescriptionPreviewController = new CourseDescriptionPreviewController(course);
        ViewNavigator.switchScene((Node)event.getSource(), "/CourseDescriptionPreview.fxml", "Edit description", courseDescriptionPreviewController, true);
    }
    @FXML
    public void openManageCourseContentWindow(ActionEvent event){
        Log.info("Opening manage course content Window");
        Course course = courseTable.getSelectionModel().getSelectedItem();
        if(course == null){
            Log.info("Selected Item is empty");
            return;
        }

        CourseContentRepositoryInterface courseContentRepositoryInterface= new CourseContentRepository();
        S3Downloader downloader = new S3Downloader();
        ManageCourseContentInterface manageCourseContentController = new ManageCourseContentController(course);
        ManageCourseContentPresenterInterface manageCourseContentPresenterInterface = new ManageCourseContentPresenter(manageCourseContentController, courseContentRepositoryInterface, downloader);
        ViewNavigator.switchScene((Node)event.getSource(), "/ManageCourseContent.fxml", "Manage Course Content", manageCourseContentController, true);


    }

    @FXML
    public void returnToPreviousForm(ActionEvent event){
        Log.info("Returning to previous form");
        ViewNavigator.goBack((Node) event.getSource());
    }
}
