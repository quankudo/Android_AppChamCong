package com.example.appchamcong.domain;

import java.util.ArrayList;

public class SalaryDetails {
    private String date;
    private double total;
    private String type;
    private ArrayList<SalaryDetailsChild> list;

    public SalaryDetails() {
    }

    public SalaryDetails(String date, double total, String type, ArrayList<SalaryDetailsChild> list) {
        this.date = date;
        this.total = total;
        this.type = type;
        this.list = list;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<SalaryDetailsChild> getList() {
        return list;
    }

    public void setList(ArrayList<SalaryDetailsChild> list) {
        this.list = list;
    }
}
