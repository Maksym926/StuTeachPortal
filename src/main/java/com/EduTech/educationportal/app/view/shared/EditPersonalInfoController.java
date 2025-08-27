package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.interfaces.view.EditPersonalInfoViewInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.entities.User;
import com.EduTech.educationportal.presenter.shared.EditPersonalInfoPresenter;
import com.EduTech.educationportal.utils.Log;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class EditPersonalInfoController implements EditPersonalInfoViewInterface, SetupControllerInterface {

    EditPersonalInfoPresenter presenter;

    User user;

    @FXML TextField nameField;
    @FXML TextField cityField;
    @FXML TextField emailField;


    public EditPersonalInfoController(User user){
        this.user = user;
    }

    @Override
    public void setPresenter(EditPersonalInfoPresenter presenter) {
        this.presenter = presenter;
    }

    @FXML
    public void submitChanges(ActionEvent event){
        Log.info("Submitting changes");
        user.setName(nameField.getText());
        user.setCity(cityField.getText());
        user.setEmail(emailField.getText());
        presenter.updateUser(user);

    }


    @Override
    public void setup() {
        nameField.setText(user.getName());
        cityField.setText(user.getCity());
        emailField.setText(user.getEmail());
    }


}
