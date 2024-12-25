package com.example.appchamcong.domain;

import java.util.Date;

public class Deduct {
    private  String lyDo;
    private  double soTien;
    private Date ngayTao;
    private  String ghiChu;

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Deduct() {

    }

    public Deduct(String lyDo, double soTien, Date ngayTao, String ghiChu) {
        this.lyDo = lyDo;
        this.soTien = soTien;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
    }
}
