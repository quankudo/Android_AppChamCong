package com.example.appchamcong.domain;


import java.util.Date;

public class SalaryAdvance {
    private double sotienung;
    private Date ngayung;
    private String note;
    private String hinhanh;
    private int idungluong;
    private int idctcv;

    public SalaryAdvance(double price, Date ngayung, String note, String hinhanh, int idungluong, int idctcv) {
        this.sotienung = price;
        this.ngayung = ngayung;
        this.note = note;
        this.hinhanh = hinhanh;
        this.idungluong = idungluong;
        this.idctcv = idctcv;
    }

    public double getPrice() {
        return sotienung;
    }

    public void setPrice(double price) {
        this.sotienung = price;
    }

    public Date getNgayung() {
        return ngayung;
    }

    public void setNgayung(Date ngayung) {
        this.ngayung = ngayung;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getIdungluong() {
        return idungluong;
    }

    public void setIdungluong(int idungluong) {
        this.idungluong = idungluong;
    }

    public int getIdctcv() {
        return idctcv;
    }

    public void setIdctcv(int idctcv) {
        this.idctcv = idctcv;
    }
}
