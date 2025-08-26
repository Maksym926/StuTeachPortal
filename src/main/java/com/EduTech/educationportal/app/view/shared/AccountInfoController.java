package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.interfaces.view.AccountInfoViewInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.entities.User;
import com.EduTech.educationportal.presenter.shared.AccountInfoPresenter;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AccountInfoController implements AccountInfoViewInterface, SetupControllerInterface {
    AccountInfoPresenter presenter;

    User user;
    @FXML Text userName;
    @FXML Text userEmail;
    @FXML Text userCity;

    @FXML Text userRole;

    public AccountInfoController(User user){
        this.user = user;
    }
    @Override
    public void setPresenter(AccountInfoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setup() {
        userName.setText(user.getName());
        userEmail.setText(user.getEmail());
        userCity.setText(user.getCity());
        userRole.setText(user.getRole());
    }
}
