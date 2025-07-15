package com.EduTech.educationportal.presenter.manager;

import com.EduTech.educationportal.interfaces.presenter.AddTeacherPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AddTeacherViewInterface;

public class AddTeacherPresenter implements AddTeacherPresenterInterface {
    UserRepositoryInterface userRepository;
    AddTeacherViewInterface view;
    public AddTeacherPresenter(AddTeacherViewInterface view, UserRepositoryInterface userRepository) {
        this.view = view;
        this.userRepository = userRepository;
        view.setPresenter(this);
    }

    @Override
    public void addTeacherToDB(String name, String email, String city, String password, String subject) {
        userRepository.addTeacher(name, email, city, password, subject);
    }

    @Override
    public boolean checkUserPresence(String email) {
        userRepository.checkUserPresence(email);
        return false;
    }
}
