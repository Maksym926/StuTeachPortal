package com.EduTech.educationportal.model;


public class User {
    private int ID;
    private String name;
    private String email;
    private String password;
    private String role;
    private String city;


    public User(){

    }
    public User(String name, String email, String role){
        this.name = name;
        this.email = email;
        this.role = role;
    }
    public User(int ID, String name, String email, String city, String password, String role) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.city = city;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }
    public void setID(int ID) {
        this.ID = ID;
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
    public void setRole(String role) {
        this.role = role;
    }
    public String toString() {
        return ID + name + " (" + email + ")";
    }
}
