package com.example.appchamcong.domain;

public class AdvancedIntegration {
    private String name;
    private String describe;

    public AdvancedIntegration() {
    }

    public AdvancedIntegration(String name, String describe) {
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
