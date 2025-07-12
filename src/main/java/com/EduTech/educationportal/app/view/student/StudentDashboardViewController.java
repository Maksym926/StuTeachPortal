package com.EduTech.educationportal.app.view.student;
import com.EduTech.educationportal.interfaces.view.StudentDashboardViewInterface;
import com.EduTech.educationportal.presenter.student.StudentDashboardPresenter;

public class StudentDashboardViewController implements StudentDashboardViewInterface{
    StudentDashboardPresenter presenter;

    @Override
    public void setStudentDashboardInterfacePresenter(StudentDashboardPresenter presenter) {
        this.presenter = presenter;
    }
}
