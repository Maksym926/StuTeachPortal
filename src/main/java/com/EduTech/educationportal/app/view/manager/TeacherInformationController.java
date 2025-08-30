package com.EduTech.educationportal.app.view.manager;

import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.interfaces.view.TeacherInformationViewInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.Teacher;
import com.EduTech.educationportal.model.entities.User;
import com.EduTech.educationportal.presenter.manager.TeacherInfoPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class TeacherInformationController implements TeacherInformationViewInterface, SetupControllerInterface {

    @FXML
    private Text teacherName;
    @FXML
    private Text teacherLocation;
    @FXML
    private Text teacherEmail;

    @FXML ListView<Course> teachingCoursesListView;

    private ObservableList<Course> teachingCourses = FXCollections.observableArrayList();

    User selectedUser;

    public TeacherInformationController(User selectedUser){
        this.selectedUser = selectedUser;
    }

    TeacherInfoPresenter presenter;
    @Override
    public void setPresenter(TeacherInfoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setup() {
        presenter.setTeachingCourses(selectedUser.getID(), teachingCourses);
        teacherName.setText(selectedUser.getName());
        teacherLocation.setText(selectedUser.getCity());
        teacherEmail.setText(selectedUser.getEmail());
        teachingCoursesListView.setItems(teachingCourses);
        teachingCoursesListView.setCellFactory(list -> new ListCell<Course>(){
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
}
