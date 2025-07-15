package com.EduTech.educationportal.interfaces.repository;

import com.EduTech.educationportal.model.Teacher;
import javafx.collections.ObservableList;

public interface UserRepositoryInterface {
    void registerStudent(String firstName,String email, String password);

    boolean loginUser(String email, String password);
    boolean checkUserPresence(String email);
    String checkUserRole(String email);
    void createDefaultManager(String firstName, String email, String password, String role);
    void getTeachers(ObservableList<Teacher> teacherList);
    void addTeacher(String name, String email, String city, String password, String subject);
}
