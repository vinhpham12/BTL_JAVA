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
import java.sql.*;
import java.util.ArrayList;
public class NhatKiHoatDongDAO {
    /**
     * Ghi lại một hành động của người dùng vào cơ sở dữ liệu.
     * Phương thức này là static để bạn có thể gọi ở bất cứ đâu (ví dụ: trong Frame) 
     * mà không cần khởi tạo đối tượng.
     */
    public static void ghiLog(String username, String hanhDong) {
        String sql = "INSERT INTO NhatKyHoatDong (Username, HanhDong) VALUES (?, ?)";
        
        try (Connection conn = DBConnetion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username);
            ps.setString(2, hanhDong);
            
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi ghi nhật ký hoạt động: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Lấy toàn bộ danh sách nhật ký để hiển thị lên bảng JTable cho Admin.
     * Dữ liệu được sắp xếp theo thời gian mới nhất lên đầu.
     */
    public ArrayList<Object[]> layTatCaLog() {
        ArrayList<Object[]> danhSachLog = new ArrayList<>();
        String sql = "SELECT Username, HanhDong, ThoiGian FROM NhatKyHoatDong ORDER BY ThoiGian DESC";
        
        try (Connection conn = DBConnetion.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                // Đóng gói từng dòng thành mảng Object để dễ dàng đưa vào JTable
                danhSachLog.add(new Object[]{
                    rs.getString("Username"),
                    rs.getString("HanhDong"),
                    rs.getTimestamp("ThoiGian") // Lấy cả ngày và giờ
                });
            }
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi tải danh sách nhật ký: " + e.getMessage());
            e.printStackTrace();
        }
        return danhSachLog;
    }
}
