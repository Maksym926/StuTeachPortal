package com.EduTech.educationportal.app.view.teacher;

import com.EduTech.educationportal.interfaces.view.TeacherDashboardViewInterface;
import com.EduTech.educationportal.presenter.teacher.TeacherDashboardPresenter;

public class TeacherDashboardViewController implements TeacherDashboardViewInterface {

    TeacherDashboardPresenter presenter;

    @Override
    public void setTeacherDashboardPresenter(TeacherDashboardPresenter presenter) {
        this.presenter = presenter;
    }
}
