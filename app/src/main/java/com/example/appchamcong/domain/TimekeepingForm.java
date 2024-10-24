package com.example.appchamcong.domain;

import androidx.recyclerview.widget.RecyclerView;

public class TimekeepingForm {
    private String name;
    private String describe;

    public TimekeepingForm() {
    }

    public TimekeepingForm(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
