package com.EduTech.educationportal.interfaces.view;


import com.EduTech.educationportal.presenter.shared.ManageCourseContentPresenter;
import javafx.stage.Stage;

public interface ManageCourseContentInterface {
    void setPresenter(ManageCourseContentPresenter presenter);
    Stage getStage();
}
