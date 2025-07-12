package com.EduTech.educationportal.presenter.student;

import com.EduTech.educationportal.interfaces.presenter.StudentPresenterInterface;
import com.EduTech.educationportal.interfaces.view.StudentDashboardViewInterface;

public class StudentDashboardPresenter implements StudentPresenterInterface {
    StudentDashboardViewInterface view;
    
    public StudentDashboardPresenter(StudentDashboardViewInterface view) {

        this.view = view;
        view.setStudentDashboardPresenter(this);
    }


}
