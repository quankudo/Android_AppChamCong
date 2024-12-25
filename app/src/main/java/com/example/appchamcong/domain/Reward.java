package com.example.appchamcong.domain;


import java.util.Date;

public class Reward {
    private int idpc;
    private int idctcv;
    private int idloaipc;
    private double sotien;
    private Date ngaytao;
    private String ghichu;
    private String loai;

    public Reward(int idpc, int idctcv, int idloaipc, double sotien, Date ngaytao, String ghichu, String loai) {
        this.idpc = idpc;
        this.idctcv = idctcv;
        this.idloaipc = idloaipc;
        this.sotien = sotien;
        this.ngaytao = ngaytao;
        this.ghichu = ghichu;
        this.loai = loai;
    }

    public int getIdpc() {
        return idpc;
    }

    public void setIdpc(int idpc) {
        this.idpc = idpc;
    }

    public int getIdctcv() {
        return idctcv;
    }

    public void setIdctcv(int idctcv) {
        this.idctcv = idctcv;
    }

    public int getIdloaipc() {
        return idloaipc;
    }

    public void setIdloaipc(int idloaipc) {
        this.idloaipc = idloaipc;
    }

    public double getSotien() {
        return sotien;
    }

    public void setSotien(double sotien) {
        this.sotien = sotien;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
}
