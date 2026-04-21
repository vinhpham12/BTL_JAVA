/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_desktop.DAO;

/**
 *
 * @author ADMIN
 */
import com.mycompany.iot_desktop.config.DBConnetion;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class NguoiDungDAO {
    // Phải băm mật khẩu trước khi lưu để bảo mật
    private String hashMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) { return ""; }
    }

    public boolean themTaiKhoan(String username, String password, String hoTen, String role) {
        String sql = "INSERT INTO nguoidung (username, password, hoTen, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnetion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, hashMD5(password)); // Băm pass
            ps.setString(3, hoTen);
            ps.setString(4, role);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
