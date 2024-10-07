package com.example.appchamcong.domain;

public class Person_Timekeeping {
    private String name;
    private String date;

    public Person_Timekeeping(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public Person_Timekeeping() {
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
}
