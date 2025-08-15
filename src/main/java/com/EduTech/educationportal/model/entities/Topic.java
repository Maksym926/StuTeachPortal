package com.EduTech.educationportal.model.entities;

import com.EduTech.educationportal.interfaces.view.CourseContentItem;

public class Topic implements CourseContentItem {
    private int ID;
    private int courseID;
    private String title;

    public Topic(String title){
        this.title = title;
    }
    public Topic(int ID, int courseID, String title){
        this.title = title;
        this.ID = ID;
        this.courseID= courseID;
    }
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
