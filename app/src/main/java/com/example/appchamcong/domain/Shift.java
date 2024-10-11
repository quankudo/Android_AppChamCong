package com.example.appchamcong.domain;

public class Shift {
    private int color;
    private int count;
    private String name;

    public Shift(int color, String name, int count) {
        this.color = color;
        this.count = count;
        this.name = name;
    }

    public Shift() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
