package com.EduTech.educationportal.presenter.shared;

import com.EduTech.educationportal.data.UserRepository;
import com.EduTech.educationportal.interfaces.presenter.MainMenuPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.MainMenuViewInterface;
import com.EduTech.educationportal.model.entities.Manager;

public class MainMenuPresenter implements MainMenuPresenterInterface {

    UserRepositoryInterface userRepositoryInterface;
    UserRepository userRepository = new UserRepository();
    MainMenuViewInterface view;

    public MainMenuPresenter(MainMenuViewInterface view , UserRepositoryInterface userRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface;
        this.view = view;
        view.setMainMenuPresenter(this);
    }

    @Override
    public void setManager() {
        Manager manager = new Manager();
        userRepositoryInterface.createDefaultManager(manager.name, manager.email, manager.password, manager.role);

    }
    public void deleteUser(String email){
        userRepository.deleteUser(email);
    }
}
