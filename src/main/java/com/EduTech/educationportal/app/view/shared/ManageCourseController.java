package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.interfaces.view.ManageCourseViewInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.Course;
import com.EduTech.educationportal.presenter.shared.ManageCoursePresenter;
import com.EduTech.educationportal.utils.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageCourseController implements SetupControllerInterface, ManageCourseViewInterface  {
    ManageCoursePresenter presenter;

    @FXML TableView<Course> courseTable;
    @FXML TableColumn<Course, String> courseTitleColumn = new TableColumn<>("title");

    private final ObservableList<Course> courseList = FXCollections.observableArrayList();

    @Override
    public void setPresenter(ManageCoursePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setup() {
        Log.info("Setting up Manage Course Window");
        courseList.clear();


        courseTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        presenter.getAllCourses(courseList);

        courseTable.setItems(courseList);

    }
}
