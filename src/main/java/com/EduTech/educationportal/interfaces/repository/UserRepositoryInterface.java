package com.EduTech.educationportal.interfaces.repository;

public interface UserRepositoryInterface {
    void registerStudent(String firstName,String email, String password);

    boolean loginUser(String email, String password);
    boolean checkUserPresence(String email);
    String checkUserRole(String email);
    void createDefaultManager(String firstName, String email, String password, String role);
}
