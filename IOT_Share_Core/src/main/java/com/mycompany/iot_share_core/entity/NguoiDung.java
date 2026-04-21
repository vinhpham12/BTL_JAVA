/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_share_core.entity;

/**
 *
 * @author ADMIN
 */
public class NguoiDung {
    private String username;
    private String password; // Ở backend đôi khi không cần pass, nhưng cứ tạo đủ cấu trúc
    private String hoTen;
    private String role;

    public NguoiDung() {}

    public NguoiDung(String username, String password, String hoTen, String role) {
        this.username = username;
        this.password = password;
        this.hoTen = hoTen;
        this.role = role;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
