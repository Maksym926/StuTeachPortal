package com.EduTech.educationportal.presenter.teacher;

import com.EduTech.educationportal.app.view.teacher.TeacherDashboardViewController;
import com.EduTech.educationportal.interfaces.presenter.TeacherPresenterInterface;
import com.EduTech.educationportal.interfaces.view.TeacherDashboardViewInterface;

public class TeacherDashboardPresenter implements TeacherPresenterInterface {

    TeacherDashboardViewInterface view;

    public TeacherDashboardPresenter(TeacherDashboardViewInterface view){
        this.view = view;
    }

}
