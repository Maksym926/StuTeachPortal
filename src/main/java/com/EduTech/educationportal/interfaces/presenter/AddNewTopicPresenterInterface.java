package com.EduTech.educationportal.interfaces.presenter;

import com.EduTech.educationportal.model.entities.Topic;

public interface AddNewTopicPresenterInterface {
    void insertNewTopic(Topic topic);
    void updateExistingTopic(Topic topic);
}
