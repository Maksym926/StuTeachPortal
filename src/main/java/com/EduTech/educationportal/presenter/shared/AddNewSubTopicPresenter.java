package com.EduTech.educationportal.presenter.shared;


import com.EduTech.educationportal.data.CourseContentRepository;
import com.EduTech.educationportal.interfaces.presenter.AddNewSubTopicPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AddNewSubTopicInterface;
import com.EduTech.educationportal.model.SubTopic;

public class AddNewSubTopicPresenter implements AddNewSubTopicPresenterInterface {
    AddNewSubTopicInterface view;
    CourseContentRepositoryInterface contentRepository;
    public AddNewSubTopicPresenter(AddNewSubTopicInterface view, CourseContentRepositoryInterface contentRepository){
        this.view = view;
        this.contentRepository = contentRepository;
        view.setPresenter(this);
    }
    public void addSubTopic(SubTopic subTopic){
        contentRepository.addNewSubTopic(subTopic);
    }
}
