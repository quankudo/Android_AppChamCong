package com.example.appchamcong.domain;

public class GroupTimeKeeping {
    private String name;
    private String date;
    private String type;
    private int count;
    private int icon;

    public GroupTimeKeeping() {
    }

    public GroupTimeKeeping(String name, String date, String type, int count, int icon) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.count = count;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
