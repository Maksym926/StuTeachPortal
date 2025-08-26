package com.EduTech.educationportal.app.view.student;
import com.EduTech.educationportal.interfaces.view.StudentDashboardViewInterface;
import com.EduTech.educationportal.model.entities.Student;
import com.EduTech.educationportal.model.entities.User;
import com.EduTech.educationportal.presenter.student.StudentDashboardPresenter;

public class StudentDashboardViewController implements StudentDashboardViewInterface{
    StudentDashboardPresenter presenter;

    User student;

    @Override
    public void setStudentDashboardPresenter(StudentDashboardPresenter presenter) {
        this.presenter = presenter;
    }


    public StudentDashboardViewController(User currentStudent){
        student = currentStudent;
    }
}
