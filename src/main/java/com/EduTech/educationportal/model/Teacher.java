package com.EduTech.educationportal.model;

public class Teacher {
    private int ID;
    private String name;
    private String email;
    private String city;
    private String password;
    private Integer subject;

    public Teacher(){

    }
    public Teacher(int ID, String name, String email, String city, String password, Integer subject) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.subject = subject;
    }

    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public int getSubject() {
        return subject;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setSubject(int subject) {
        this.subject = subject;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String toString() {
        return ID + name + " (" + email + ")";
    }
}
