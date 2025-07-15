package com.EduTech.educationportal.interfaces.presenter;

public interface AddTeacherPresenterInterface {
    void addTeacherToDB(String name, String email, String city, String password, String subject);
    boolean checkUserPresence(String email);
}
