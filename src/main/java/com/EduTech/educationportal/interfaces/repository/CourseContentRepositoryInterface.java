package com.EduTech.educationportal.interfaces.repository;

import com.EduTech.educationportal.model.SubTopic;
import com.EduTech.educationportal.model.Topic;

import java.util.List;

public interface CourseContentRepositoryInterface {
    void addNewTopic(Topic topic);
    void getTopicByCourseID(int ID, List<Topic> topics);
    void addNewSubTopic(SubTopic subTopic);
    void getSubTopicByTopicID(int topicID, List<SubTopic> subTopics);
    void deleteTopic(int ID);
    void deleteSubTopic(int ID);
}
