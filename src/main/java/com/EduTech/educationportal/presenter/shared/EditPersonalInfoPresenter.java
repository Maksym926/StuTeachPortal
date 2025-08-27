package com.EduTech.educationportal.presenter.shared;

import com.EduTech.educationportal.interfaces.presenter.EditPersonalInfoPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.EditPersonalInfoViewInterface;
import com.EduTech.educationportal.model.entities.User;

public class EditPersonalInfoPresenter implements EditPersonalInfoPresenterInterface {
    EditPersonalInfoViewInterface view;
    UserRepositoryInterface userRepository;
    public EditPersonalInfoPresenter(EditPersonalInfoViewInterface view, UserRepositoryInterface userRepository){
        this.view = view;
        this.userRepository = userRepository;
        view.setPresenter(this);
    }
    public void updateUser(User user){
        userRepository.updateUserInfo(user);
    }
}
