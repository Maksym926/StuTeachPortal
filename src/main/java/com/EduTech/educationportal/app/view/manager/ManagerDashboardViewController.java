package com.EduTech.educationportal.app.view.manager;

import com.EduTech.educationportal.interfaces.presenter.ManagerPresenterInterface;
import com.EduTech.educationportal.interfaces.view.ManagerDashboardViewInterface;
import com.EduTech.educationportal.presenter.manager.ManagerDashboardPresenter;
import com.EduTech.educationportal.presenter.student.StudentDashboardPresenter;

public class ManagerDashboardViewController  implements ManagerDashboardViewInterface {
    private ManagerDashboardPresenter presenter;
    @Override
    public void setManagerDashboardPresenter(ManagerDashboardPresenter presenter) {
        this.presenter = presenter;
    }
}
