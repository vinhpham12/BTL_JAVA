/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_desktop.DAO;

import com.mycompany.iot_share_core.util.HashUtils;
import com.mycompany.iot_desktop.config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author ADMIN
 */
public class NguoiDungDAO {

    public boolean themTaiKhoan(String username, String password, String hoTen, String role) {
        String sql = "INSERT INTO nguoidung (username, password, hoTen, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Không thể kết nối đến cơ sở dữ liệu!");
                return false;
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, HashUtils.hashMD5(password)); // Băm pass
                ps.setString(3, hoTen);
                ps.setString(4, role);
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
