package com.EduTech.educationportal.model;

import com.EduTech.educationportal.interfaces.view.CourseContentItem;

public class SubTopic implements CourseContentItem {
    private int ID;
    private int topicID;
    private String title;

    private String content;
    private String image;
    private String assignment;

    public SubTopic(String title){
        this.title = title;
    }
    public SubTopic(int topicID , String title, String content){
        this.topicID = topicID;
        this.title = title;
        this.content = content;
    }

    public SubTopic(int ID, int topicID, String title, String content, String image, String assignment){
        this.ID = ID;
        this.topicID = topicID;
        this.title = title;
        this.content = content;
        this.image = image;
        this.assignment = assignment;
    }
    public int getID(){
        return ID;
    }
    public void setTopicID(int topicID){
        this.topicID = topicID;
    }
    public int getTopicID(){
        return topicID;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
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
