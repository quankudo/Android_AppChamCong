package com.example.appchamcong.domain;

public class Wifi {
    private String name;
    private String MAC;

    public Wifi(String name, String MAC) {
        this.name = name;
        this.MAC = MAC;
    }

    public Wifi() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }
}
