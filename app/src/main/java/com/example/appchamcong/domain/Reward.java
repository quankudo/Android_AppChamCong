package com.example.appchamcong.domain;


public class Reward {
    private long price;
    private String time;
    private String note;
    private String reason;

    public Reward(long price, String time, String note, String reason) {
        this.price = price;
        this.time = time;
        this.note = note;
        this.reason = reason;
    }

    public Reward() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
