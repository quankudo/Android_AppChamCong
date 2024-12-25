package com.example.appchamcong.domain;

import android.widget.Button;
import android.widget.TextView;

public class JoinTeam {
    private int idCongViec;
    private int idNguoiDung;
    private String ten;
    private String sdt;

    public JoinTeam() {
    }

    public JoinTeam(String sdt, String ten, int idNguoiDung, int idCongViec) {
        this.sdt = sdt;
        this.ten = ten;
        this.idNguoiDung = idNguoiDung;
        this.idCongViec = idCongViec;
    }

    public int getIdCongViec() {
        return idCongViec;
    }

    public void setIdCongViec(int idCongViec) {
        this.idCongViec = idCongViec;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
