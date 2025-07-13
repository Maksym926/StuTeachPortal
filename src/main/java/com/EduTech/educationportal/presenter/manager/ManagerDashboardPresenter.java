package com.EduTech.educationportal.presenter.manager;

import com.EduTech.educationportal.interfaces.presenter.ManagerPresenterInterface;
import com.EduTech.educationportal.interfaces.view.ManagerDashboardViewInterface;

public class ManagerDashboardPresenter implements ManagerPresenterInterface {
    ManagerDashboardViewInterface view;
    public ManagerDashboardPresenter(ManagerDashboardViewInterface view){
        this.view = view;
        view.setManagerDashboardPresenter(this);
    }
}
