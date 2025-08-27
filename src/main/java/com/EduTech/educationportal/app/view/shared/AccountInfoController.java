package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.data.UserRepository;
import com.EduTech.educationportal.interfaces.presenter.EditPersonalInfoPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AccountInfoViewInterface;
import com.EduTech.educationportal.interfaces.view.EditPersonalInfoViewInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.entities.User;
import com.EduTech.educationportal.presenter.shared.AccountInfoPresenter;
import com.EduTech.educationportal.presenter.shared.EditPersonalInfoPresenter;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;

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
    @FXML
    public void editUserInfo(ActionEvent event){
        EditPersonalInfoViewInterface editPersonalInfoViewInterface = new EditPersonalInfoController(user);
        UserRepositoryInterface userRepositoryInterface = new UserRepository();
        EditPersonalInfoPresenterInterface editPersonalInfoPresenterInterface = new EditPersonalInfoPresenter(editPersonalInfoViewInterface, userRepositoryInterface);
        ViewNavigator.switchScene((Node)event.getSource(), "/EditPersonalInfo.fxml", "Edit personal info", editPersonalInfoViewInterface, true);
    }
}
