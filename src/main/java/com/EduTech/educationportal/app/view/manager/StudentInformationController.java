package com.EduTech.educationportal.app.view.manager;

import com.EduTech.educationportal.app.view.shared.CourseDescriptionPreviewController;
import com.EduTech.educationportal.app.view.student.StudentDashboardViewController;
import com.EduTech.educationportal.data.CourseRepository;
import com.EduTech.educationportal.data.DBConnection;
import com.EduTech.educationportal.data.EnrolmentRepository;
import com.EduTech.educationportal.interfaces.presenter.CourseDescriptionPresenterInterface;
import com.EduTech.educationportal.interfaces.presenter.StudentDashboardPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.interfaces.repository.EnrolmentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.CourseDescriptionPreviewInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.interfaces.view.StudentDashboardViewInterface;
import com.EduTech.educationportal.interfaces.view.StudentInformationViewInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.Student;
import com.EduTech.educationportal.presenter.manager.StudentInfoPresenter;
import com.EduTech.educationportal.presenter.student.CourseDescriptionPresenter;
import com.EduTech.educationportal.presenter.student.StudentDashboardPresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    @FXML
    public void unsubscribeCourse(ActionEvent event){
        Course course = subscribedCoursesListView.getSelectionModel().getSelectedItem();
        presenter.unsubscribeCourse(selectedStudent.getID(), course.getID());
        loadSubscribedCoursesList();
    }
    @FXML
    public void subscribeOnCourse(ActionEvent event){
        StudentDashboardViewInterface StudentDashboardView = new StudentDashboardViewController(selectedStudent);
        CourseRepositoryInterface courseRepository = new CourseRepository();
        EnrolmentRepositoryInterface enrolmentRepositoryInterface = new EnrolmentRepository();
        StudentDashboardPresenterInterface StudentDashboardPresenter = new StudentDashboardPresenter(StudentDashboardView, courseRepository, enrolmentRepositoryInterface);
        ViewNavigator.switchScene((Node)event.getSource(), "/StudentDashboard.fxml", "Student Dashboard", StudentDashboardView, true);
    }

    @Override
    public void setPresenter(StudentInfoPresenter presenter) {
        this.presenter = presenter;
    }
        public void setSubscribedCoursesList(List<Course> courses){
        subscribedCoursesList.addAll(courses);
    }
    @FXML
    public void deleteUser(ActionEvent event){
        presenter.deleteUserAccount(selectedStudent);
    }
}
