package com.EduTech.educationportal.interfaces.repository;

import com.EduTech.educationportal.model.Teacher;
import com.EduTech.educationportal.model.User;
import javafx.collections.ObservableList;

import java.util.List;

public interface UserRepositoryInterface {
    void registerStudent(String firstName,String email, String password);

    boolean loginUser(String email, String password);
    boolean checkUserPresence(String email);
    String checkUserRole(String email);
    void createDefaultManager(String firstName, String email, String password, String role);
    void getUsers(ObservableList<User> teacherList);
    void addTeacher(String name, String email, String city, String password, Integer courseID );

    void getTeachers(List<Teacher> teacherList);
}
