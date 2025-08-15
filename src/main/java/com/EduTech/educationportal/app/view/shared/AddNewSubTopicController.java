package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.interfaces.view.AddNewSubTopicInterface;
import com.EduTech.educationportal.model.entities.*;
import com.EduTech.educationportal.presenter.shared.AddNewSubTopicPresenter;
import com.EduTech.educationportal.presenter.shared.ManageCoursePresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;


public class AddNewSubTopicController implements AddNewSubTopicInterface {

    @FXML TextField title;
    @FXML TextArea content;
    @FXML Button uploadButton;
    @FXML Text selectedFile;

    AddNewSubTopicPresenter presenter;
    Topic topic;
    Course course;
    File newSelectedfile;


    public AddNewSubTopicController(Topic topic, Course course){
        this.topic = topic; this.course = course;
    }
    @FXML
    public void addSubTopic(ActionEvent event){
        String newTitle = title.getText();
        String newContent = content.getText();
        String newSelectedFile  = selectedFile.getText();
        SubTopic newSubTopic = new SubTopic(topic.getID(), newTitle, newContent, newSelectedFile);
        presenter.addSubTopic(newSubTopic);
        if(newSelectedfile != null){
            User teacher = presenter.getTeacherByID(course.getTeacherId());
            presenter.uploadAssignment(teacher.getName(), newSelectedfile);
        }


    }
    @FXML
    public void uploadAssignment(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF files", "*.pdf"),
                new FileChooser.ExtensionFilter("Word files", "*.doc")

        );
        File file = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());
        if(file != null){
            selectedFile.setText(file.getName());

            newSelectedfile = file;

        }
    }
    public void returnToPreviousForm(ActionEvent event){
        Log.info("Returning to previous form");
        ViewNavigator.goBack((Node) event.getSource());
    }
    @Override
    public void setPresenter(AddNewSubTopicPresenter presenter) {
        this.presenter = presenter;
    }




}
