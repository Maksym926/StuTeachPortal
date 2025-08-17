package com.EduTech.educationportal.interfaces.presenter;

import com.EduTech.educationportal.model.entities.SubTopic;

import java.io.File;

public interface AddNewSubTopicPresenterInterface {
    void addSubTopic(SubTopic subTopic);
    void setSelectedFile(File file);
    void uploadAssignment(String teacherName, File selectedFile);
    void updateSubTopic(SubTopic subTopic);
}
