package com.EduTech.educationportal.model.entities;

public class Student extends User {


    public Student(int ID, String name, String email, String city, String password) {
        super(ID, name, email, city, password, "teacher");
    }
}
