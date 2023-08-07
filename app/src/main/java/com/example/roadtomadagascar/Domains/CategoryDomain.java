package com.example.roadtomadagascar.Domains;

import java.io.Serializable;

public class CategoryDomain implements Serializable {
    private String id;
    private String title;
    private String picPath;

    public CategoryDomain(String title, String picPath) {
        this.title = title;
        this.picPath = picPath;
    }

    public CategoryDomain(String id, String title, String picPath) {
        this.id = id;
        this.title = title;
        this.picPath = picPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
