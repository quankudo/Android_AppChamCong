package com.example.appchamcong.domain;

public class Staff {
    String valueName;
    float valueWorkday, valuePaidLeave, valueSalary, valueReceived, valueNotReceived;

    public Staff(String valueName, float valueWorkday, float valuePaidLeave, float valueSalary, float valueReceived, float valueNotReceived) {
        this.valueName = valueName;
        this.valueWorkday = valueWorkday;
        this.valuePaidLeave = valuePaidLeave;
        this.valueSalary = valueSalary;
        this.valueReceived = valueReceived;
        this.valueNotReceived = valueNotReceived;
    }

    public Staff(){

    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public float getValueWorkday() {
        return valueWorkday;
    }

    public void setValueWorkday(float valueWorkday) {
        this.valueWorkday = valueWorkday;
    }

    public float getValuePaidLeave() {
        return valuePaidLeave;
    }

    public void setValuePaidLeave(float valuePaidLeave) {
        this.valuePaidLeave = valuePaidLeave;
    }

    public float getValueSalary() {
        return valueSalary;
    }

    public void setValueSalary(float valueSalary) {
        this.valueSalary = valueSalary;
    }

    public float getValueReceived() {
        return valueReceived;
    }

    public void setValueReceived(float valueReceived) {
        this.valueReceived = valueReceived;
    }

    public float getValueNotReceived() {
        return valueNotReceived;
    }

    public void setValueNotReceived(float valueNotReceived) {
        this.valueNotReceived = valueNotReceived;
    }
}
