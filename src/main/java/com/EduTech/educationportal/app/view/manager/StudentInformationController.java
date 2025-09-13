package com.EduTech.educationportal.app.view.manager;

import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.interfaces.view.StudentInformationViewInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.Student;
import com.EduTech.educationportal.presenter.manager.StudentInfoPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.List;

public class StudentInformationController implements StudentInformationViewInterface, SetupControllerInterface {
    @FXML ListView<Course> subscribedCoursesListView;
    @FXML Text studentName;
    @FXML Text studentLocation;
    @FXML Text studentEmail;
    StudentInfoPresenter presenter;
    private final ObservableList<Course> subscribedCoursesList = FXCollections.observableArrayList();
    Student selectedStudent;
    public StudentInformationController(Student selectedStudent){
        this.selectedStudent = selectedStudent;
    }

    @Override
    public void setup() {
        studentName.setText(selectedStudent.getName());
        studentLocation.setText(selectedStudent.getCity());
        studentEmail.setText(selectedStudent.getEmail());
        loadSubscribedCoursesList();
    }

    private void loadSubscribedCoursesList() {
        subscribedCoursesList.clear();
        presenter.getSubscribedCourses(selectedStudent.getID());
        subscribedCoursesListView.setItems(subscribedCoursesList);
        subscribedCoursesListView.setCellFactory(list -> new ListCell<>(){
            @Override
            protected void updateItem(Course course, boolean empty){
                super.updateItem(course, empty);
                if(empty || course == null){
                    setText(null);
                }
                else {
                    setText(course.getTitle());
                }
            }
        });
    }

    @Override
    public void setPresenter(StudentInfoPresenter presenter) {
        this.presenter = presenter;
    }
        public void setSubscribedCoursesList(List<Course> courses){
        subscribedCoursesList.addAll(courses);
    }
}
