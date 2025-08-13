package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.interfaces.view.AddNewTopicInterface;
import com.EduTech.educationportal.model.Course;
import com.EduTech.educationportal.model.Topic;
import com.EduTech.educationportal.presenter.shared.AddNewTopicPresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class AddNewTopicController implements AddNewTopicInterface {
    Course course;
    AddNewTopicPresenter presenter;
    @FXML TextField title;
    public AddNewTopicController(Course course){
        this.course = course;
    }
    @FXML
    public void addTopic(){
        String newTitle = title.getText();
        Log.info("Course ID: " + course.getID() + " title " + newTitle);
        Topic newTopic = new Topic(course.getID(), newTitle);
        presenter.insertNewTopic(newTopic);

    }

    @Override
    public void setPresenter(AddNewTopicPresenter presenter) {
        this.presenter = presenter;
    }
    public void returnToPreviousForm(ActionEvent event){
        Log.info("Returning to previous form");
        ViewNavigator.goBack((Node) event.getSource());
    }
}
