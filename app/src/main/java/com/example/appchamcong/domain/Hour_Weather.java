package com.example.appchamcong.domain;

public class Hour_Weather {
    private int hour;
    private int icon;
    private int temperature;

    public Hour_Weather(int hour, int icon, int temperature) {
        this.hour = hour;
        this.icon = icon;
        this.temperature = temperature;
    }

    public Hour_Weather() {
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
