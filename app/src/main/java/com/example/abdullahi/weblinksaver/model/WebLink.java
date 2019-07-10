package com.example.abdullahi.weblinksaver.model;

import android.graphics.Bitmap;

import pl.droidsonroids.jspoon.annotation.Selector;

public class WebLink {
   private int id;
     private Web webTitle;
    private String webLink;
    private Bitmap image;

    public WebLink(int id, Web webTitle, String webLink, Bitmap image) {
        this.id = id;
        this.webTitle = webTitle;
        this.webLink = webLink;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public Web getWebTitle() {
        return webTitle;
    }

    public String getWebLink() {
        return webLink;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWebTitle(Web webTitle) {
        this.webTitle = webTitle;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
