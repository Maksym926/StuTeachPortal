package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.interfaces.view.AddNewTopicInterface;
import com.EduTech.educationportal.model.Course;
import com.EduTech.educationportal.model.Topic;
import com.EduTech.educationportal.presenter.shared.AddNewTopicPresenter;
import javafx.fxml.FXML;
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
        Topic newTopic = new Topic(course.getID(), newTitle);
        presenter.insertNewTopic(newTopic);

    }

    @Override
    public void setPresenter(AddNewTopicPresenter presenter) {
        this.presenter = presenter;
    }
}
