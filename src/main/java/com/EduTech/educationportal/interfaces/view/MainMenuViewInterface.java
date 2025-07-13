package com.EduTech.educationportal.interfaces.view;

import com.EduTech.educationportal.presenter.shared.MainMenuPresenter;
import javafx.event.ActionEvent;

public interface MainMenuViewInterface {
    void setMainMenuPresenter(MainMenuPresenter presenter);
    void openRegisterWindow(ActionEvent event);
    void openLoginWindow(ActionEvent event);

}
