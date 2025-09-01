package com.EduTech.educationportal.app.view.manager;

import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.interfaces.view.TeacherInformationViewInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.Teacher;
import com.EduTech.educationportal.model.entities.User;
import com.EduTech.educationportal.presenter.manager.TeacherInfoPresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;

public class TeacherInformationController implements TeacherInformationViewInterface, SetupControllerInterface {

    @FXML
    private Text teacherName;
    @FXML
    private Text teacherLocation;
    @FXML
    private Text teacherEmail;

    @FXML ListView<Course> teachingCoursesListView;

    private ObservableList<Course> teachingCourses = FXCollections.observableArrayList();

    Teacher selectedTeacher;

    public TeacherInformationController(Teacher selectedTeacher){

        this.selectedTeacher = selectedTeacher;

    }

    TeacherInfoPresenter presenter;
    @Override
    public void setPresenter(TeacherInfoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setup() {

        teacherName.setText(selectedTeacher.getName());
        teacherLocation.setText(selectedTeacher.getCity());
        teacherEmail.setText(selectedTeacher.getEmail());
        setUpTeachingCoursesList();


    }
    private void setUpTeachingCoursesList(){
        teachingCourses.clear();
        presenter.setTeachingCourses(selectedTeacher.getID(), teachingCourses);
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

    @FXML
    public void unassignCourse(ActionEvent event){
        Log.info("Unassign course");
        presenter.unassignTeachingCourse(selectedTeacher.getID());
        setUpTeachingCoursesList();

    }
    @FXML
    public void returnToPreviousForm(ActionEvent event){
        Log.info("Returning to previous form");
        ViewNavigator.goBack((Node) event.getSource());
    }


}
