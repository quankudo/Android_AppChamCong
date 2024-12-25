package com.example.appchamcong.domain;

import java.util.Date;

public class TimekeepingReq {
    private int timeStart;
    private int ngayChamCong;
    private int idcv;
    private String tinhCong;
    private boolean nghiPhep;
    private String lyDo;
    private String ghiChu;
    private double salary;
    private int idNhanVien;
    private String qrCode;

    public TimekeepingReq() {
    }

    public TimekeepingReq(int timeStart, int ngayChamCong, int idCtcv, String tinhCong, boolean nghiPhep, String lyDo, String ghiChu, double salary) {
        this.timeStart = timeStart;
        this.ngayChamCong = ngayChamCong;
        this.idcv = idCtcv;
        this.tinhCong = tinhCong;
        this.nghiPhep = nghiPhep;
        this.lyDo = lyDo;
        this.ghiChu = ghiChu;
        this.salary = salary;
    }

    public int getIdcv() {
        return idcv;
    }

    public void setIdcv(int idcv) {
        this.idcv = idcv;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }

    public int getNgayChamCong() {
        return ngayChamCong;
    }

    public void setNgayChamCong(int ngayChamCong) {
        this.ngayChamCong = ngayChamCong;
    }

    public int getIdCv() {
        return idcv;
    }

    public void setIdCv(int idCtcv) {
        this.idcv = idCtcv;
    }

    public String getTinhCong() {
        return tinhCong;
    }

    public void setTinhCong(String tinhCong) {
        this.tinhCong = tinhCong;
    }

    public boolean isNghiPhep() {
        return nghiPhep;
    }

    public void setNghiPhep(boolean nghiPhep) {
        this.nghiPhep = nghiPhep;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
