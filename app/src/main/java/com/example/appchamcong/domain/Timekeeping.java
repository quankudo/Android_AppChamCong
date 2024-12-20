package com.example.appchamcong.domain;

public class Timekeeping {
    private int id;
    private String ten;
    private int ngayDiLam;
    private int ngayNghi;
    private double daUng;
    private double tienCong;
    private boolean trangThai;

    public Timekeeping() {
    }

    public Timekeeping(int id, String ten, int ngayDiLam, int ngayNghi, double daUng, double tienCong, boolean trangThai) {
        this.id = id;
        this.ten = ten;
        this.ngayDiLam = ngayDiLam;
        this.ngayNghi = ngayNghi;
        this.daUng = daUng;
        this.tienCong = tienCong;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getNgayDiLam() {
        return ngayDiLam;
    }

    public void setNgayDiLam(int ngayDiLam) {
        this.ngayDiLam = ngayDiLam;
    }

    public int getNgayNghi() {
        return ngayNghi;
    }

    public void setNgayNghi(int ngayNghi) {
        this.ngayNghi = ngayNghi;
    }

    public double getDaUng() {
        return daUng;
    }

    public void setDaUng(double daUng) {
        this.daUng = daUng;
    }

    public double getTienCong() {
        return tienCong;
    }

    public void setTienCong(double tienCong) {
        this.tienCong = tienCong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
