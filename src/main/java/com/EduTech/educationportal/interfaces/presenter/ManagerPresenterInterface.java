package com.EduTech.educationportal.interfaces.presenter;

import com.EduTech.educationportal.model.Teacher;
import javafx.collections.ObservableList;

public interface ManagerPresenterInterface {
    void getTeachers(ObservableList<Teacher> teacherList);
}
