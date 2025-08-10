package com.EduTech.educationportal.interfaces.repository;

import com.EduTech.educationportal.model.Topic;

import java.util.List;

public interface CourseContentRepositoryInterface {
    void addNewTopic(Topic topic);
    void getTopicByCourseID(int ID, List<Topic> topics);
}
