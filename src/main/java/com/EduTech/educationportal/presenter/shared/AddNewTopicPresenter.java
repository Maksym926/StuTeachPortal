package com.EduTech.educationportal.presenter.shared;

import com.EduTech.educationportal.interfaces.presenter.AddNewTopicPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AddNewTopicInterface;
import com.EduTech.educationportal.model.entities.Topic;

public class AddNewTopicPresenter implements AddNewTopicPresenterInterface {
    AddNewTopicInterface view;
    CourseContentRepositoryInterface courseContentRepository;
    public AddNewTopicPresenter(AddNewTopicInterface view, CourseContentRepositoryInterface courseContentRepository){
        this.view = view;
        this.courseContentRepository = courseContentRepository;
        view.setPresenter(this);
    }
    public void insertNewTopic(Topic topic){
        courseContentRepository.addNewTopic(topic);
    }
    public void updateExistingTopic(Topic topic){
        courseContentRepository.updateExistingTopic(topic);
    }

}
