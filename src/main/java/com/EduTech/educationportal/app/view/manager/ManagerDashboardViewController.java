package com.EduTech.educationportal.app.view.manager;

import com.EduTech.educationportal.data.CourseRepository;
import com.EduTech.educationportal.data.UserRepository;
import com.EduTech.educationportal.interfaces.presenter.AddCoursePresenterInterface;
import com.EduTech.educationportal.interfaces.presenter.AddTeacherPresenterInterface;
import com.EduTech.educationportal.interfaces.presenter.ManagerPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AddCourseViewInterface;
import com.EduTech.educationportal.interfaces.view.AddTeacherViewInterface;
import com.EduTech.educationportal.interfaces.view.ManagerDashboardViewInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.Teacher;
import com.EduTech.educationportal.presenter.manager.AddCoursePresenter;
import com.EduTech.educationportal.presenter.manager.AddTeacherPresenter;
import com.EduTech.educationportal.presenter.manager.ManagerDashboardPresenter;
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

public class ManagerDashboardViewController  implements ManagerDashboardViewInterface, SetupControllerInterface {
    private ManagerDashboardPresenter presenter;

    @FXML
    ListView<Teacher> teacherListView;

    private final ObservableList<Teacher> teacherList = FXCollections.observableArrayList();


    @Override
    public void setManagerDashboardPresenter(ManagerDashboardPresenter presenter) {
        this.presenter = presenter;
    }

    public void setup() {
        teacherList.clear();
        Log.info("Setting up teacher list");

        teacherListView.setItems(teacherList);

        teacherListView.setCellFactory(list -> new ListCell<Teacher>() {
            @Override
            protected void updateItem(Teacher teacher, boolean empty) {
                super.updateItem(teacher, empty);
                if (empty || teacher == null) {
                    setText(null);
                } else {
                    setText(teacher.getID() + " - " +   teacher.getName() + " - " + teacher.getEmail());
                }
            }
        });
        presenter.getTeachers(teacherList);

    }
    @FXML
    private void openAddTeacherWindow(ActionEvent event){
        Log.info("Opening add teacher window");
        AddTeacherViewInterface addTeacherViewInterface = new AddTeacherController();
        UserRepositoryInterface userRepositoryInterface = new UserRepository();
        CourseRepositoryInterface courseRepositoryInterface = new CourseRepository();
        AddTeacherPresenterInterface addTeacherPresenterInterface = new AddTeacherPresenter(addTeacherViewInterface, userRepositoryInterface, courseRepositoryInterface);
        ViewNavigator.switchScene((Node) event.getSource(), "/AddTeacherView.fxml", "Add Teacher", addTeacherViewInterface);

//        CourseRepository courseRepository = new CourseRepository();
//        courseRepository.printCourseInfo();
    }
    @FXML
    private void openAddCourseWindow(ActionEvent event){
        Log.info("Opening add course window");
        AddCourseViewInterface addCourseViewInterface = new AddCourseController();
        CourseRepositoryInterface courseRepositoryInterface = new CourseRepository();
        UserRepositoryInterface userRepositoryInterface = new UserRepository();
        AddCoursePresenterInterface addCoursePresenterInterface = new AddCoursePresenter(addCourseViewInterface, courseRepositoryInterface, userRepositoryInterface);
        ViewNavigator.switchScene((Node) event.getSource(), "/AddCourseView.fxml", "Add Course", addCourseViewInterface);

    }
    @FXML
    public void returnToPreviousForm(ActionEvent event){
        Log.info("Returning to previous form");
        ViewNavigator.goBack((Node) event.getSource());
    }
}
