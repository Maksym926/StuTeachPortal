package com.EduTech.educationportal.presenter.shared;

import com.EduTech.educationportal.interfaces.presenter.ManageCourseContentPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.ManageCourseContentInterface;
import com.EduTech.educationportal.model.entities.SubTopic;
import com.EduTech.educationportal.model.entities.Topic;

import java.util.List;

public class ManageCourseContentPresenter implements  ManageCourseContentPresenterInterface{
    ManageCourseContentInterface view;
    CourseContentRepositoryInterface courseContentRepository;
    public ManageCourseContentPresenter(ManageCourseContentInterface view, CourseContentRepositoryInterface courseContentRepository){
        this.view = view;
        this.courseContentRepository = courseContentRepository;
        view.setPresenter(this);
    }
    public void getTopicByCourseID(int ID, List<Topic> topics){
        courseContentRepository.getTopicByCourseID(ID, topics);
    }
    public void getSubTopicByCourseID(int ID, List<SubTopic> subTopics){
        courseContentRepository.getSubTopicByTopicID(ID, subTopics);
    }

    public void deleteTopic(int ID){
        courseContentRepository.deleteTopic(ID);
    }
    public void deleteSubTopic(int ID){
        courseContentRepository.deleteSubTopic(ID);
    }
}
