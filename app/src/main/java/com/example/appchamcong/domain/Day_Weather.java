package com.example.appchamcong.domain;

public class Day_Weather {
    private String day;
    private int icon;
    private int fromTemperature;
    private int toTemperature;

    public Day_Weather() {

    }

    public Day_Weather(String day, int icon, int fromTemperature, int toTemperature) {
        this.day = day;
        this.icon = icon;
        this.fromTemperature = fromTemperature;
        this.toTemperature = toTemperature;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getFromTemperature() {
        return fromTemperature;
    }

    public void setFromTemperature(int fromTemperature) {
        this.fromTemperature = fromTemperature;
    }

    public int getToTemperature() {
        return toTemperature;
    }

    public void setToTemperature(int toTemperature) {
        this.toTemperature = toTemperature;
    }
}
