package com.EduTech.educationportal.model;

public class Teacher extends User {

    private Integer subject;

    public Teacher(){

    }
    public Teacher(int ID, String name, String email, String city, String password, Integer subject) {
        super(ID, name, email, city, password, "teacher");
        this.subject = subject;
    }


    public int getSubject() {
        return subject;
    }

}
