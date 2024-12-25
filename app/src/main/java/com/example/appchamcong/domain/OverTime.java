package com.example.appchamcong.domain;

import java.util.Date;

public class OverTime {
    private int idtangca;
    private  double heso;
    private double sotien;
    private int sophut;
    private Date ngaytangca;
    private String hinhanh;
    private String ghichu;
    private int idctcv;

    public OverTime(int idtangca, double heso, double sotien, int sophut, Date ngaytangca, String hinhanh, String ghichu, int idctcv) {
        this.idtangca = idtangca;
        this.heso = heso;
        this.sotien = sotien;
        this.sophut = sophut;
        this.ngaytangca = ngaytangca;
        this.hinhanh = hinhanh;
        this.ghichu = ghichu;
        this.idctcv = idctcv;
    }

    public OverTime() {
    }

    public int getIdtangca() {
        return idtangca;
    }

    public void setIdtangca(int idtangca) {
        this.idtangca = idtangca;
    }

    public double getHeso() {
        return heso;
    }

    public void setHeso(double heso) {
        this.heso = heso;
    }

    public double getSotien() {
        return sotien;
    }

    public void setSotien(double sotien) {
        this.sotien = sotien;
    }

    public int getSophut() {
        return sophut;
    }

    public void setSophut(int sophut) {
        this.sophut = sophut;
    }

    public Date getNgaytangca() {
        return ngaytangca;
    }

    public void setNgaytangca(Date ngaytangca) {
        this.ngaytangca = ngaytangca;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getIdctcv() {
        return idctcv;
    }

    public void setIdctcv(int idctcv) {
        this.idctcv = idctcv;
    }
}
