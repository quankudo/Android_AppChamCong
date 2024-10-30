package com.example.appchamcong.domain;


public class SalaryAdvance {
    private long price;
    private String time;
    private String note;

    public SalaryAdvance(long price, String time, String note) {
        this.price = price;
        this.time = time;
        this.note = note;
    }

    public SalaryAdvance() {
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
