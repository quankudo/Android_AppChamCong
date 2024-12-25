package com.example.appchamcong.domain;

public class TimeKeepingDetails {
    private String tenCongViec;
    private String loaiChamCong;
    private double tienCong;

    public TimeKeepingDetails() {
    }

    public TimeKeepingDetails(String tenCongViec, String loaiChamCong, double tienCong) {
        this.tenCongViec = tenCongViec;
        this.loaiChamCong = loaiChamCong;
        this.tienCong = tienCong;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public String getLoaiChamCong() {
        return loaiChamCong;
    }

    public void setLoaiChamCong(String loaiChamCong) {
        this.loaiChamCong = loaiChamCong;
    }

    public double getTienCong() {
        return tienCong;
    }

    public void setTienCong(double tienCong) {
        this.tienCong = tienCong;
    }
}
