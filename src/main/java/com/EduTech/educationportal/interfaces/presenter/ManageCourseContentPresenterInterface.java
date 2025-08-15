package com.EduTech.educationportal.interfaces.presenter;

import com.EduTech.educationportal.model.entities.SubTopic;
import com.EduTech.educationportal.model.entities.Topic;

import java.util.List;

public interface ManageCourseContentPresenterInterface {
    void getTopicByCourseID(int ID, List<Topic> topics);
    void getSubTopicByCourseID(int ID, List<SubTopic> subTopics);
    void deleteTopic(int ID);
    void deleteSubTopic(int ID);
}
