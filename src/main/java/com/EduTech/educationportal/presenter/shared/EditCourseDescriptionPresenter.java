package com.EduTech.educationportal.presenter.shared;

import com.EduTech.educationportal.interfaces.presenter.EditCourseDescriptionPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.CourseDescriptionPreviewInterface;
import com.EduTech.educationportal.interfaces.view.EditCourseDescriptionInterface;
import com.EduTech.educationportal.model.Course;

public class EditCourseDescriptionPresenter implements EditCourseDescriptionPresenterInterface {
    EditCourseDescriptionInterface view;
    CourseRepositoryInterface courseRepository;
    public EditCourseDescriptionPresenter(EditCourseDescriptionInterface view, CourseRepositoryInterface courseRepository){
        this.view = view;
        this.courseRepository = courseRepository;
        view.setPresenter(this);
    }

    @Override
    public void updateCourseDescription(Course course) {
        courseRepository.updateCourseDescription(course);
    }
}
