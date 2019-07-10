package com.example.abdullahi.weblinksaver.model;

import pl.droidsonroids.jspoon.annotation.Selector;

public class Web {
    @Selector("title")private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Web(String title) {

        this.title = title;
    }
}
