package com.example.phong.myapplication.model;

import java.io.Serializable;

public class sanpham implements Serializable {
    public int idsp;
    public String tensp;
    public String hinhanh;
    public Integer gia;
    public int idhangsx;

    public sanpham(int idsp, String tensp, String hinhanh, Integer gia, int idhangsx) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.hinhanh = hinhanh;
        this.gia = gia;
        this.idhangsx = idhangsx;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    public int getIdhangsx() {
        return idhangsx;
    }

    public void setIdhangsx(int idhangsx) {
        this.idhangsx = idhangsx;
    }
}