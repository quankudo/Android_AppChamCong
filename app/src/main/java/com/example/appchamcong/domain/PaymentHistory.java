package com.example.appchamcong.domain;

public class PaymentHistory {
    private int index;
    private double totalPrice;
    private String date;

    public PaymentHistory(int index, double totalPrice, String date) {
        this.index = index;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
