package com.EduTech.educationportal.app.view.teacher;

import com.EduTech.educationportal.interfaces.view.TeacherDashboardViewInterface;
import com.EduTech.educationportal.model.entities.Teacher;
import com.EduTech.educationportal.model.entities.User;
import com.EduTech.educationportal.presenter.teacher.TeacherDashboardPresenter;

public class TeacherDashboardViewController implements TeacherDashboardViewInterface {

    TeacherDashboardPresenter presenter;

    User teacher;

    @Override
    public void setTeacherDashboardPresenter(TeacherDashboardPresenter presenter) {
        this.presenter = presenter;
    }

    public TeacherDashboardViewController(User currentTeacher){
        teacher = currentTeacher;
    }
}
