package com.example.appchamcong.domain;

import java.util.Date;

public class MyInfo {
    private int iduser;
    private String hovaten;
    private String email;
    private String sdt;
    private int trangthai;
    private Date ngayTao;

    public MyInfo() {
    }

    public MyInfo(int iduser, String hovaten, String email, String sdt, int trangthai) {
        this.iduser = iduser;
        this.hovaten = hovaten;
        this.email = email;
        this.sdt = sdt;
        this.trangthai = trangthai;
    }

    public MyInfo(int iduser, String hovaten, String email, String sdt, int trangthai, Date ngayTao) {
        this.iduser = iduser;
        this.hovaten = hovaten;
        this.email = email;
        this.sdt = sdt;
        this.trangthai = trangthai;
        this.ngayTao = ngayTao;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
