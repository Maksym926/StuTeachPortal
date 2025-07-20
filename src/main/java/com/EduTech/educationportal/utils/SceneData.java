package com.EduTech.educationportal.utils;

public class SceneData {
    private String fxmlPath;
    private String title;
    private Object controller;
    public SceneData(String fxmlPath, String title){
        this.fxmlPath = fxmlPath;
        this.title = title;
    }
    public SceneData(String fxmlPath, String title, Object controller) {
        this.fxmlPath = fxmlPath;
        this.title = title;
        this.controller = controller;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }
    public String getTitle() {
        return title;
    }
    public Object getController() {
        return controller;
    }

}
