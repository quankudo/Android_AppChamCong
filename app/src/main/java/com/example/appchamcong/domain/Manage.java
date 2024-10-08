package com.example.appchamcong.domain;

import android.widget.TextView;

public class Manage {
    String valueName, valueNumber, valueDate;
    float valueSalary;

    public Manage(String valueName, String valueNumber, String valueDate, float valueSalary) {
        this.valueName = valueName;
        this.valueNumber = valueNumber;
        this.valueDate = valueDate;
        this.valueSalary = valueSalary;
    }

    public  Manage(){

    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getValueNumber() {
        return valueNumber;
    }

    public void setValueNumber(String valueNumber) {
        this.valueNumber = valueNumber;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public float getValueSalary() {
        return valueSalary;
    }

    public void setValueSalary(float valueSalary) {
        this.valueSalary = valueSalary;
    }
}
