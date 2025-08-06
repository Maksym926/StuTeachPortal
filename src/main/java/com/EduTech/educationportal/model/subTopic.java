package com.EduTech.educationportal.model;

public class subTopic {
    private int ID;
    private int courseID;
    private String title;
    private String subTitle;
    private String content;
    private String image;
    private String assignment;

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
    public void setSubTitle(String subTitle){
        this.subTitle = subTitle;
    }
    public String getSubTitle(){
        return subTitle;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return content;
    }
    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return image;
    }
    public void setAssignment(String assignment){
        this.assignment = assignment;
    }
    public String getAssignment(){
        return assignment;
    }


}
