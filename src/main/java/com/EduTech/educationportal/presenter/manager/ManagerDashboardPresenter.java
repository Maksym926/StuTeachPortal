package com.EduTech.educationportal.presenter.manager;

import com.EduTech.educationportal.data.UserRepository;
import com.EduTech.educationportal.interfaces.presenter.ManagerPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.ManagerDashboardViewInterface;
import com.EduTech.educationportal.model.Teacher;
import javafx.collections.ObservableList;

public class ManagerDashboardPresenter implements ManagerPresenterInterface {
    ManagerDashboardViewInterface view;
    UserRepositoryInterface userRepositoryInterface;
    public ManagerDashboardPresenter(ManagerDashboardViewInterface view, UserRepositoryInterface userRepositoryInterface){
        this.view = view;
        this.userRepositoryInterface = userRepositoryInterface;
        view.setManagerDashboardPresenter(this);
    }

    @Override
    public void getTeachers(ObservableList<Teacher> teacherList) {
        userRepositoryInterface.getTeachers(teacherList);
    }
}
