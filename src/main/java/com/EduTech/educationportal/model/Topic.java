package com.EduTech.educationportal.model;

public class Topic {
    private int ID;
    private int courseID;
    private String title;

    public Topic(int courseID, String title){
        this.courseID = courseID;
        this.title = title;
    }
    public int getID(){
        return ID;
    }
    public void setCourseID(int courseID){
        this.courseID = courseID;
    }
    public int getCourseID(){
        return courseID;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}
