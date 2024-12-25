package com.example.appchamcong.domain;

import java.util.Date;

public class AddDeduct {
    private  int idctcv;
    private  double soTien;
    private Date ngayTao;
    private  String ghiChu;
    private int loaiTT;

    public AddDeduct(int idctcv, double soTien, Date ngayTao, String ghiChu, int loaiTT) {
        this.idctcv = idctcv;
        this.soTien = soTien;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.loaiTT = loaiTT;
    }

    public AddDeduct() {

    }

    public int getIdctcv() {
        return idctcv;
    }

    public void setIdctcv(int idctcv) {
        this.idctcv = idctcv;
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

    public int getLoaiTT() {
        return loaiTT;
    }

    public void setLoaiTT(int loaiTT) {
        this.loaiTT = loaiTT;
    }
}
