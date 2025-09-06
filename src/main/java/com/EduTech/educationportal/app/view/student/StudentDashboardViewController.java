package com.EduTech.educationportal.app.view.student;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.interfaces.view.StudentDashboardViewInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.Student;
import com.EduTech.educationportal.model.entities.User;
import com.EduTech.educationportal.presenter.student.StudentDashboardPresenter;
import com.EduTech.educationportal.utils.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.List;

public class StudentDashboardViewController implements StudentDashboardViewInterface, SetupControllerInterface {
    StudentDashboardPresenter presenter;
    @FXML TabPane courseTabPane;
    @FXML Tab allCoursesTab;
    @FXML Tab subscribedCoursesTab;
    @FXML ListView<Course> allCoursesListView;
    @FXML ListView<Course> subscribedCoursesListView;
    private final ObservableList<Course> allCoursesList = FXCollections.observableArrayList();
    private  final ObservableList<Course> subscribedCoursesList = FXCollections.observableArrayList();

    User student;

    @Override
    public void setStudentDashboardPresenter(StudentDashboardPresenter presenter) {
        this.presenter = presenter;
    }


    public StudentDashboardViewController(User currentStudent){
        student = currentStudent;
    }

    @Override
    public void setup() {

        Tab selectedTab = courseTabPane.getSelectionModel().getSelectedItem();

        if (selectedTab == allCoursesTab) {
            allCoursesList.clear();
            presenter.setAllCoursesTab();
            allCoursesListView.setItems(allCoursesList);
            allCoursesListView.setCellFactory(list -> new ListCell<>() {
                @Override
                protected void updateItem(Course course, boolean empty) {
                    super.updateItem(course, empty);
                    if (empty || course == null) {
                        setText(null);
                    } else {
                        setText(course.getTitle());
                    }
                }
            });
        }
    }

    public void setAllCoursesList(List<Course> newAllCoursesList){
        allCoursesList.addAll(newAllCoursesList);
    }
    public void setSubscribedCoursesList(List<Course> newSubscribedCoursesList){
        subscribedCoursesList.addAll(newSubscribedCoursesList);
    }
}
