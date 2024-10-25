package com.example.appchamcong.domain;

public class TimekeepingOptions {
    int img;
    private String name;
    private String describe;

    public TimekeepingOptions(int img, String name, String describe) {
        this.img = img;
        this.name = name;
        this.describe = describe;
    }

    public TimekeepingOptions() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
