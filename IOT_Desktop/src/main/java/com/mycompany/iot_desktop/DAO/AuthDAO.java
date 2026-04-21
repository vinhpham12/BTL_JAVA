/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_desktop.DAO;

import com.mycompany.iot_share_core.entity.NguoiDung;
import com.mycompany.iot_share_core.util.HashUtils;
import com.mycompany.iot_desktop.config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author ADMIN
 */
public class AuthDAO {
    // Nhiệm vụ: Nhận username/password từ màn hình đăng nhập, mã hóa, gửi xuống Database kiểm tra và trả về thông tin người dùng.

    // Hàm xác thực
    public NguoiDung login(String username, String password) {
        String sql = "SELECT * FROM nguoidung WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Không thể kết nối đến cơ sở dữ liệu!");
                return null;
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, HashUtils.hashMD5(password)); // Mã hóa pass người dùng nhập rồi mới so sánh

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    // Tạo đối tượng từ Shared_Core và trả về
                    return new NguoiDung(
                        rs.getString("username"),
                        null, // Không cần lưu lại password vào RAM
                        rs.getString("hoTen"),
                        rs.getString("role")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Đăng nhập thất bại
    }
}
