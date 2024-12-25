package com.example.appchamcong.domain;

public class WorkDetails {
    private int id;
    private String workName;
    private String qrCode;
    private double chuaThanhToan;
    private double tienCong;
    private double tongTangCa;
    private double tongThuong;
    private double tongNghiCoLuong;
    private double tongDaUng;
    private double truTien;

    public WorkDetails() {
    }

    public WorkDetails(int id, String workName, String qrCode, double chuaThanhToan, double tienCong, double tongTangCa, double tongThuong, double tongNghiCoLuong, double tongDaUng, double truTien) {
        this.id = id;
        this.workName = workName;
        this.qrCode = qrCode;
        this.chuaThanhToan = chuaThanhToan;
        this.tienCong = tienCong;
        this.tongTangCa = tongTangCa;
        this.tongThuong = tongThuong;
        this.tongNghiCoLuong = tongNghiCoLuong;
        this.tongDaUng = tongDaUng;
        this.truTien = truTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public double getChuaThanhToan() {
        return chuaThanhToan;
    }

    public void setChuaThanhToan(double chuaThanhToan) {
        this.chuaThanhToan = chuaThanhToan;
    }

    public double getTienCong() {
        return tienCong;
    }

    public void setTienCong(double tienCong) {
        this.tienCong = tienCong;
    }

    public double getTongTangCa() {
        return tongTangCa;
    }

    public void setTongTangCa(double tongTangCa) {
        this.tongTangCa = tongTangCa;
    }

    public double getTongThuong() {
        return tongThuong;
    }

    public void setTongThuong(double tongThuong) {
        this.tongThuong = tongThuong;
    }

    public double getTongNghiCoLuong() {
        return tongNghiCoLuong;
    }

    public void setTongNghiCoLuong(double tongNghiCoLuong) {
        this.tongNghiCoLuong = tongNghiCoLuong;
    }

    public double getTongDaUng() {
        return tongDaUng;
    }

    public void setTongDaUng(double tongDaUng) {
        this.tongDaUng = tongDaUng;
    }

    public double getTruTien() {
        return truTien;
    }

    public void setTruTien(double truTien) {
        this.truTien = truTien;
    }
}
