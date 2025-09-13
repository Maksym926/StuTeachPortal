package com.EduTech.educationportal.interfaces.view;

import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.presenter.manager.StudentInfoPresenter;

import java.util.List;

public interface StudentInformationViewInterface {
    void setPresenter(StudentInfoPresenter presenter);
    void setSubscribedCoursesList(List<Course> courses);

}
