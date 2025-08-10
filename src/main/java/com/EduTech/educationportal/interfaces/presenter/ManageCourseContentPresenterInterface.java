package com.EduTech.educationportal.interfaces.presenter;

import com.EduTech.educationportal.model.Topic;

import java.util.List;

public interface ManageCourseContentPresenterInterface {
    void getTopicByCourseID(int ID, List<Topic> topics);
}
