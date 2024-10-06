package com.example.appchamcong.domain;

import android.widget.ImageButton;

public class Notify {
    int icon;
    String date, title;

    public Notify(int icon, String title, String date ) {
        this.title = title;
        this.date = date;
        this.icon = icon;
    }
    public  Notify(){

    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
