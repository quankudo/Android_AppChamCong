package com.example.appchamcong.domain;

public class SalaryDetailsChild {
    private int icon;
    private String title;
    private String desc;
    private double price;
    private String time;
    private String reason;
    private String shift;
    private String type;
    public SalaryDetailsChild() {
    }


    public SalaryDetailsChild(int icon, String title, String shift, double price, String time, String type) {
        this.icon = icon;
        this.title = title;
        this.shift = shift;
        this.price = price;
        this.time = time;
        this.type = type;
    }

    public SalaryDetailsChild(int icon, String title, double price, String time, String reason, String type) {
        this.icon = icon;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.time = time;
        this.reason = reason;
        this.shift = shift;
        this.type = type;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
