/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_share_core.entity;

import java.util.Date;

/**
 * @author ADMIN
 */

public class NhatKiHoatDong {
    private long id;
    private String username; // Ai làm?
    private String hanhDong; // Làm gì?
    private Date thoiGian;   // Lúc mấy giờ?

    public NhatKiHoatDong() {}

    public NhatKiHoatDong(String username, String hanhDong) {
        this.username = username;
        this.hanhDong = hanhDong;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getHanhDong() { return hanhDong; }
    public void setHanhDong(String hanhDong) { this.hanhDong = hanhDong; }

    public Date getThoiGian() { return thoiGian; }
    public void setThoiGian(Date thoiGian) { this.thoiGian = thoiGian; }
}
