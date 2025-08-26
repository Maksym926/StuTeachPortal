package com.EduTech.educationportal.model.entities;

public class Manager extends User {
    public String name = "System Manager";
    public String email = "admin";
    public String password = "123";

    public String role = "manager";

    public Manager(){
    }

    public Manager(int ID, String name, String email, String city, String password){
        super(ID, name, email, city, password, "manager");

    }

}
