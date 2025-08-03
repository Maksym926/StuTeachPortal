package com.EduTech.educationportal.interfaces.view;

import com.EduTech.educationportal.model.User;
import com.EduTech.educationportal.presenter.manager.ManagerDashboardPresenter;
import com.EduTech.educationportal.presenter.student.StudentDashboardPresenter;

import java.util.List;

public interface ManagerDashboardViewInterface {
    void setManagerDashboardPresenter(ManagerDashboardPresenter presenter);
    List<User> getFilteredUsers(String location, boolean isStudent, boolean isTeacher);
    List<User> getFilteredUsers(String query);


}
