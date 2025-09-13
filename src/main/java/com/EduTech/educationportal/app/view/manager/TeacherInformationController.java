package com.EduTech.educationportal.app.view.manager;

import com.EduTech.educationportal.app.view.shared.EditPersonalInfoController;
import com.EduTech.educationportal.data.UserRepository;
import com.EduTech.educationportal.interfaces.presenter.EditPersonalInfoPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.EditPersonalInfoViewInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.interfaces.view.TeacherInformationViewInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.Teacher;
import com.EduTech.educationportal.presenter.manager.TeacherInfoPresenter;
import com.EduTech.educationportal.presenter.shared.EditPersonalInfoPresenter;
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

    private final ObservableList<Course> teachingCoursesList = FXCollections.observableArrayList();

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
        teachingCoursesList.clear();
        presenter.setTeachingCourses(selectedTeacher.getID(), teachingCoursesList);
        teachingCoursesListView.setItems(teachingCoursesList);
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
    @FXML
    public void editPersonalInfo(ActionEvent event){
        EditPersonalInfoViewInterface editPersonalInfoViewInterface = new EditPersonalInfoController(selectedTeacher);
        UserRepositoryInterface userRepositoryInterface = new UserRepository();
        EditPersonalInfoPresenterInterface editPersonalInfoPresenterInterface = new EditPersonalInfoPresenter(editPersonalInfoViewInterface, userRepositoryInterface);
        ViewNavigator.switchScene((Node)event.getSource(), "/EditPersonalInfo.fxml", "Edit personal info", editPersonalInfoViewInterface, true);
    }
    @FXML
    public void deleteAccount(ActionEvent event){
        presenter.deleteTeacherAccount(selectedTeacher.getEmail());
    }


}
