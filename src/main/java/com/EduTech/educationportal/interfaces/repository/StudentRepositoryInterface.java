package com.EduTech.educationportal.interfaces.repository;

public interface StudentRepositoryInterface {
    void registerStudent(String firstName,String email, String password);

    boolean loginUser(String email, String password);
    boolean checkUserPresence(String email);
}
