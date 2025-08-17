package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.interfaces.view.AddNewTopicInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.Topic;
import com.EduTech.educationportal.presenter.shared.AddNewTopicPresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class AddNewTopicController implements AddNewTopicInterface, SetupControllerInterface {
    Course course;
    AddNewTopicPresenter presenter;
    @FXML TextField title;
    Topic topic;
    public AddNewTopicController(Course course, Topic topic){
        this.course = course;
        this.topic = topic;
    }
    @FXML
    public void addTopic(){
        if(topic == null){
            String newTitle = title.getText();
            Log.info("Course ID: " + course.getID() + " title " + newTitle);
            Topic newTopic = new Topic(course.getID(), newTitle);
            presenter.insertNewTopic(newTopic);
        }
        else {
            topic.setTitle(title.getText());
            presenter.updateExistingTopic(topic);
        }


    }

    @Override
    public void setPresenter(AddNewTopicPresenter presenter) {
        this.presenter = presenter;
    }
    public void returnToPreviousForm(ActionEvent event){
        Log.info("Returning to previous form");
        ViewNavigator.goBack((Node) event.getSource());
    }

    @Override
    public void setup() {
        if(topic!=null){
            title.setText(topic.getTitle());
        }

    }
}
