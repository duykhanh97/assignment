package com.example.phong.myapplication.model;

import java.io.Serializable;

public class hangsanxuatt implements Serializable {
    public int ID ;
    public String tenhangsanxuat ;
    public String hinhanh ;

    public hangsanxuatt(int ID, String tenhangsanxuat, String hinhanh) {
        this.ID = ID;
        this.tenhangsanxuat = tenhangsanxuat;
        this.hinhanh = hinhanh;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenhangsanxuat() {
        return tenhangsanxuat;
    }

    public void setTenhangsanxuat(String tenhangsanxuat) {
        this.tenhangsanxuat = tenhangsanxuat;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
