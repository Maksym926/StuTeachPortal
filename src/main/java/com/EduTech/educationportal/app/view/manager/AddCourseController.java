package com.EduTech.educationportal.app.view.manager;

import com.EduTech.educationportal.data.CourseRepository;
import com.EduTech.educationportal.interfaces.view.AddCourseViewInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.entities.Teacher;
import com.EduTech.educationportal.presenter.manager.AddCoursePresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class AddCourseController implements AddCourseViewInterface, SetupControllerInterface {

    AddCoursePresenter presenter;

    @FXML TextField courseTitle;
    @FXML TextField courseCode;
    @FXML MenuButton teacherMenuButton;
    @FXML TextArea courseDescription;
    @FXML TextField courseDuration;
    CourseRepository courseRepository = new CourseRepository();
    private List<Teacher> teachers = new ArrayList<>();

    private Teacher selectedTeacher;

    public void setPresenter(AddCoursePresenter presenter) {
        this.presenter = presenter;
    }
    @FXML
    public void addCourse(ActionEvent event) {
        String courseTitleText = courseTitle.getText();
        String courseCodeText = courseCode.getText();
        String courseDescriptionText = courseDescription.getText();
        Integer teacherID = null;
        if(selectedTeacher != null){
            teacherID = selectedTeacher.getID();
        }
        int courseDurationText = Integer.parseInt(courseDuration.getText());
        presenter.addCourse(courseTitleText, courseCodeText , teacherID, courseDescriptionText, 12);
        if(teacherID != null)
            presenter.setSubject(courseTitleText, teacherID);
//        courseRepository.printCourseInfo();

    }
    public void setup(){
//        Map<MenuItem, Teacher> teacherMap = new HashMap<>();
        presenter.getTeachers(teachers);

        for(Teacher teacher : teachers){
            MenuItem item = new MenuItem(teacher.getName() + " - " + teacher.getEmail());
//            teacherMap.put(item, teacher);
            item.setOnAction(event -> {
                teacherMenuButton.setText(teacher.getName() + " - " + teacher.getEmail());
//                MenuItem selectedMenuItem = (MenuItem) event.getSource();
//                selectedTeacher = teacherMap.get(selectedMenuItem);
                selectedTeacher = teacher;

            });
            teacherMenuButton.getItems().add(item);
        }
        teacherMenuButton.setText("Select Teacher");
    }
    @FXML
    public void returnToPreviousForm(ActionEvent event){
        Log.info("Returning to previous form");
        ViewNavigator.goBack((Node) event.getSource());
    }
}
