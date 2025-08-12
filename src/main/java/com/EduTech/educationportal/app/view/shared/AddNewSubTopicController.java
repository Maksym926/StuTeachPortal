package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.interfaces.view.AddNewSubTopicInterface;
import com.EduTech.educationportal.model.SubTopic;
import com.EduTech.educationportal.model.Topic;
import com.EduTech.educationportal.presenter.shared.AddNewSubTopicPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;


public class AddNewSubTopicController implements AddNewSubTopicInterface {
    @FXML TextField title;
    @FXML TextArea content;
    AddNewSubTopicPresenter presenter;
    Topic topic;
    public AddNewSubTopicController(Topic topic){
        this.topic = topic;
    }
    @FXML
    public void addSubTopic(ActionEvent event){
        String newTitle = title.getText();
        String newContent = content.getText();
        SubTopic newSubTopic = new SubTopic(topic.getID(), newTitle, newContent);
        presenter.addSubTopic(newSubTopic);

    }

    @Override
    public void setPresenter(AddNewSubTopicPresenter presenter) {
        this.presenter = presenter;
    }
}
