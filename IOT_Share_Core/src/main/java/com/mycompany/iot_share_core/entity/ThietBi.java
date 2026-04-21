/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_share_core.entity;

/**
 *
 * @author ADMIN
 */
public class ThietBi {
    private int id;
    private String tenThietBi;
    private boolean trangThai;

    public ThietBi() {}

    public ThietBi(int id, String tenThietBi, boolean trangThai) {
        this.id = id;
        this.tenThietBi = tenThietBi;
        this.trangThai = trangThai;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTenThietBi() { return tenThietBi; }
    public void setTenThietBi(String tenThietBi) { this.tenThietBi = tenThietBi; }

    public boolean isTrangThai() { return trangThai; }
    public void setTrangThai(boolean trangThai) { this.trangThai = trangThai; }
}
