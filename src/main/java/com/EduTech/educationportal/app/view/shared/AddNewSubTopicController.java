package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.interfaces.view.AddNewSubTopicInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.entities.*;
import com.EduTech.educationportal.presenter.shared.AddNewSubTopicPresenter;
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
import org.h2.store.fs.FilePath;

import java.io.File;


public class AddNewSubTopicController implements AddNewSubTopicInterface, SetupControllerInterface {

    @FXML TextField title;
    @FXML TextArea content;
    @FXML Button uploadButton;

    @FXML Text selectedFileName;

    AddNewSubTopicPresenter presenter;
    Topic topic;
    Course course;
    SubTopic subTopic;
    File selectedFilePath;


    public AddNewSubTopicController(Topic topic, Course course,SubTopic subTopic){
        this.topic = topic; this.course = course; this.subTopic = subTopic;
    }
    @FXML
    public void addSubTopic(ActionEvent event){
        if(subTopic == null ){
            String newTitle = title.getText();
            String newContent = content.getText();
            String newSelectedFile  = selectedFileName.getText();


            if(selectedFilePath != null){
                User teacher = presenter.getTeacherByID(course.getTeacherId());
                String key = "assignments" + teacher.getName() + "/" + selectedFilePath.getName();
                presenter.uploadAssignment(teacher.getName(), selectedFilePath);
                SubTopic newSubTopic = new SubTopic(topic.getID(), newTitle, newContent, key, newSelectedFile );
                presenter.addSubTopic(newSubTopic);
            }
            else{
                SubTopic newSubTopic = new SubTopic(topic.getID(), newTitle, newContent, newSelectedFile);
                presenter.addSubTopic(newSubTopic);
            }

        }
        else{

            subTopic.setTitle(title.getText());
            subTopic.setContent(content.getText());
            subTopic.setFileName(selectedFileName.getText());
            if(selectedFilePath != null){
                User teacher = presenter.getTeacherByID(course.getTeacherId());
                String key = "assignments" + teacher.getName() + "/" + selectedFilePath.getName();
                presenter.uploadAssignment(teacher.getName(), selectedFilePath);
                subTopic.setFilePath(key);


            }
            presenter.updateSubTopic(subTopic);
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
            selectedFileName.setText(file.getName());

            selectedFilePath = file;

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


    @Override
    public void setup() {
        if(subTopic!=null){
            title.setText(subTopic.getTitle());
            content.setText(subTopic.getContent());
            selectedFileName.setText(subTopic.getFileName());
        }
    }
}
