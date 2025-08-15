package com.EduTech.educationportal.presenter.shared;


import com.EduTech.educationportal.interfaces.presenter.AddNewSubTopicPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AddNewSubTopicInterface;
import com.EduTech.educationportal.model.aws.S3Uploader;
import com.EduTech.educationportal.model.entities.SubTopic;
import com.EduTech.educationportal.model.entities.User;

import java.io.File;

public class AddNewSubTopicPresenter implements AddNewSubTopicPresenterInterface {
    AddNewSubTopicInterface view;
    CourseContentRepositoryInterface contentRepository;
    UserRepositoryInterface userRepositoryInterface;
    S3Uploader uploader;
    private File selectedFile;
    public AddNewSubTopicPresenter(AddNewSubTopicInterface view, CourseContentRepositoryInterface contentRepository, S3Uploader uploader, UserRepositoryInterface userRepositoryInterface){
        this.view = view;
        this.contentRepository = contentRepository;
        this.uploader = uploader;
        this.userRepositoryInterface = userRepositoryInterface;
        view.setPresenter(this);
    }
    public void addSubTopic(SubTopic subTopic){
        contentRepository.addNewSubTopic(subTopic);
    }
    public void setSelectedFile(File file){
        selectedFile = file;
    }
    public void uploadAssignment(String teacherName, File selectedFile){
        uploader.uploadAssignment(teacherName, selectedFile);
    }
    public User getTeacherByID(int id){
        return userRepositoryInterface.getTeacherByID(id);

    }
}
