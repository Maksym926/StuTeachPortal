package com.EduTech.educationportal.app.view.manager;

import com.EduTech.educationportal.interfaces.presenter.ManagerPresenterInterface;
import com.EduTech.educationportal.interfaces.view.ManagerDashboardViewInterface;
import com.EduTech.educationportal.model.Teacher;
import com.EduTech.educationportal.presenter.manager.ManagerDashboardPresenter;
import com.EduTech.educationportal.presenter.student.StudentDashboardPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class ManagerDashboardViewController  implements ManagerDashboardViewInterface {
    private ManagerDashboardPresenter presenter;

    @FXML
    ListView<Teacher> teacherListView;

    private ObservableList<Teacher> teacherList = FXCollections.observableArrayList();


    @Override
    public void setManagerDashboardPresenter(ManagerDashboardPresenter presenter) {
        this.presenter = presenter;
    }

    public void setup() {
        teacherListView.setItems(teacherList);
        teacherListView.setCellFactory(list -> new ListCell<Teacher>() {
            @Override
            protected void updateItem(Teacher teacher, boolean empty) {
                super.updateItem(teacher, empty);
                if (empty || teacher == null) {
                    setText(null);
                } else {
                    setText(teacher.getID() + " - " + teacher.getName() + " - " + teacher.getEmail());
                }
            }
        });
        presenter.getTeachers(teacherList);
    }
}
