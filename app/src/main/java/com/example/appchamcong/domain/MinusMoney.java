package com.example.appchamcong.domain;

public class MinusMoney {
    private long price;
    private String reason;
    private String date;
    private int minutes;
    private String notes;

    public MinusMoney(long price, String reason, String date, int minutes, String notes) {
        this.price = price;
        this.reason = reason;
        this.date = date;
        this.minutes = minutes;
        this.notes = notes;
    }

    public MinusMoney() {
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
