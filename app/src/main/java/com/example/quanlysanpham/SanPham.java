package com.example.quanlysanpham;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class SanPham implements Serializable {
    private String danhMuc;
    private String maSP;
    private String tenSP;
    private int soLuong;

    public SanPham(){

    }

    public SanPham(String maSP, String tenSP, String danhMuc, int soLuong) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.danhMuc = danhMuc;
        this.soLuong = soLuong;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @NonNull
    @Override
    public String toString() {
        return getMaSP() + " , " + getTenSP() + " , " + getDanhMuc() + " , " + getSoLuong();
    }

    public static SanPham fromString(String str){
        String[] part = str.split(" , ");
        return new SanPham(part[0], part[1], part[2], Integer.parseInt(part[3]));
    }
}
