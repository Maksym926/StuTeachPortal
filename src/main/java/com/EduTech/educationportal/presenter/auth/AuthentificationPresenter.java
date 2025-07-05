package com.EduTech.educationportal.presenter.auth;

import com.EduTech.educationportal.data.StudentRepository;

public class AuthentificationPresenter {
    public void registerUser(String username, String email, String password) {
        StudentRepository studentRepository = new StudentRepository();
        studentRepository.registerStudent( username, email, password);

    }
}
