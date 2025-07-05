package com.EduTech.educationportal.presenter;
import com.EduTech.educationportal.model.repository.StudentRepository;
public class RegisterPresenter {
    public void registerUser(String username, String email, String password) {
        StudentRepository studentRepository = new StudentRepository();
        studentRepository.registerStudent( username, email, password);

    }
}
