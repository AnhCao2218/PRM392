package com.caodnhe150776.myapplication.slot6;

public class SanPham {
    public String maSp;
    private String tenSp;
    private  int soluong;

    public SanPham() {
    }

    public SanPham(String maSp, String tenSp, int soluong) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.soluong = soluong;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
