package com.EduTech.educationportal.interfaces.presenter;

import com.EduTech.educationportal.model.User;
import javafx.collections.ObservableList;

public interface ManagerPresenterInterface {
    void getUsers(ObservableList<User> teacherList);
}
