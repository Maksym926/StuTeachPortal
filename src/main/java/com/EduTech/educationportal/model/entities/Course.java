package com.EduTech.educationportal.model.entities;

public class Course {
    int ID;
    private String title;
    private String code;
    private int teacherId;
    private String description;
    private int duration;
    public Course(){

    }
    public Course(int ID, String title, String code, int teacherId, String description, int duration){
        this.ID = ID;
        this.title = title;
        this.code = code;
        this.teacherId = teacherId;
        this.description = description;
        this.duration = duration;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    public int getTeacherId() {
        return teacherId;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getDuration() {
        return duration;
    }
}
