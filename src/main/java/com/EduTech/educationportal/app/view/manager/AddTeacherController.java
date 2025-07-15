package com.EduTech.educationportal.app.view.manager;

import com.EduTech.educationportal.interfaces.presenter.AddTeacherPresenterInterface;
import com.EduTech.educationportal.interfaces.view.AddTeacherViewInterface;
import com.EduTech.educationportal.presenter.manager.AddTeacherPresenter;
import com.EduTech.educationportal.utils.Log;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AddTeacherController implements AddTeacherViewInterface {

    AddTeacherPresenterInterface presenter;

    @FXML TextField name;
    @FXML TextField email;
    @FXML TextField city;
    @FXML PasswordField password;
    @FXML TextField subject;

    public void setPresenter(AddTeacherPresenter presenter) {
        this.presenter = presenter;
    }
    @FXML
    private void addTeacher() {
        String nameText = name.getText();
        String emailText = email.getText();
        String cityText = city.getText();
        String passwordText = password.getText();
        String subjectText = subject.getText();
        if(!presenter.checkUserPresence(emailText))
            presenter.addTeacherToDB(nameText, emailText, cityText, passwordText, subjectText);
        else{
            Log.warn("The teacher with this email already exists");
        }


    }
}
