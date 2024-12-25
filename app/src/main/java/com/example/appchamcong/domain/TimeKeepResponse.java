package com.example.appchamcong.domain;

public class TimeKeepResponse {
    private int idChamCong;
    private int ngay;
    private String loaiChamCong;

    public TimeKeepResponse() {
    }

    public TimeKeepResponse(int idChamCong, int ngay, String loaiChamCong) {
        this.idChamCong = idChamCong;
        this.ngay = ngay;
        this.loaiChamCong = loaiChamCong;
    }

    public int getIdChamCong() {
        return idChamCong;
    }

    public void setIdChamCong(int idChamCong) {
        this.idChamCong = idChamCong;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public String getLoaiChamCong() {
        return loaiChamCong;
    }

    public void setLoaiChamCong(String loaiChamCong) {
        this.loaiChamCong = loaiChamCong;
    }
}
