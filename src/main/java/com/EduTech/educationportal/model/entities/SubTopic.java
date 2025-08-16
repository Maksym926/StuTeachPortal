package com.EduTech.educationportal.model.entities;

import com.EduTech.educationportal.interfaces.view.CourseContentItem;

public class SubTopic implements CourseContentItem {
    private int ID;
    private int topicID;
    private String title;

    private String content;
    private String image;

    private String fileName;
    private String filePath;

    public SubTopic(String title){
        this.title = title;
    }
    public SubTopic(int topicID , String title, String content, String filePath,  String selectedFile){
        this.topicID = topicID;
        this.title = title;
        this.content = content;
        this.fileName = selectedFile;
        this.filePath = filePath;


    }
    public SubTopic(int topicID , String title, String content, String selectedFile){
        this.topicID = topicID;
        this.title = title;
        this.content = content;
        this.fileName = selectedFile;

    }

    public SubTopic(int ID, int topicID, String title, String content, String image, String filePath, String fileName){
        this.ID = ID;
        this.topicID = topicID;
        this.title = title;
        this.content = content;
        this.image = image;
        this.filePath = filePath;
        this.fileName = fileName;
    }
    public String getFilePath(){
        return filePath;
    }
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
    public String getFileName(){
        return fileName;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
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



}
