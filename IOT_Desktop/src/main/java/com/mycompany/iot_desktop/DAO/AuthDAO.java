/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_desktop.DAO;

/**
 *
 * @author ADMIN
 */
import com.mycompany.iot_share_core.entity.NguoiDung;
import com.mycompany.iot_desktop.config.DBConnetion;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class AuthDAO {
    //Nhiệm vụ: Nhận username/password từ màn hình đăng nhập, mã hóa, gửi xuống Database kiểm tra và trả về thông tin người dùng.
    // Hàm băm mật khẩu thành chuỗi MD5 để bảo mật
    private String hashMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) { 
            return ""; 
        }
    }

    // Hàm xác thực
    public NguoiDung login(String username, String password) {
        String sql = "SELECT * FROM nguoidung WHERE username = ? AND password = ?";
        try (Connection conn = DBConnetion .getConnection();PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, username);
            ps.setString(2, hashMD5(password)); // Mã hóa pass người dùng nhập rồi mới so sánh
            
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
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        return null; // Đăng nhập thất bại
    }
}
