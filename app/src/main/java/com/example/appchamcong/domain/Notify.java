package com.example.appchamcong.domain;

import android.widget.ImageButton;

public class Notify {
    int icon;
    String date, title;
    String time;

    public Notify(int icon, String title, String date ) {
        this.title = title;
        this.date = date;
        this.icon = icon;
    }

    public Notify(String title, String time ) {
        this.title = title;
        this.time = time;
    }

    public  Notify(){

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
