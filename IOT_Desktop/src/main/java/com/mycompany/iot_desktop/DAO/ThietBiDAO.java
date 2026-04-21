/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_desktop.DAO;

import com.mycompany.iot_share_core.entity.ThietBi;
import com.mycompany.iot_desktop.config.DBConnection;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author ADMIN
 */
public class ThietBiDAO {
    
    public ArrayList<ThietBi> getAllDevices() {
        ArrayList<ThietBi> list = new ArrayList<>();
        String sql = "SELECT * FROM thietbi";
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Không thể kết nối đến cơ sở dữ liệu!");
                return list;
            }
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    list.add(new ThietBi(
                        rs.getInt("Id"), 
                        rs.getString("TenThietBi"), 
                        rs.getBoolean("TrangThai")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // 2. THÊM MẠCH MỚI
    public boolean addDevice(ThietBi device) {
        String sql = "INSERT INTO thietbi (TenThietBi, TrangThai) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, device.getTenThietBi());
                ps.setBoolean(2, device.isTrangThai());
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    // 3. SỬA TÊN/TRẠNG THÁI MẠCH
    public boolean updateDevice(ThietBi device) {
        String sql = "UPDATE thietbi SET TenThietBi = ?, TrangThai = ? WHERE Id = ?";
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, device.getTenThietBi());
                ps.setBoolean(2, device.isTrangThai());
                ps.setInt(3, device.getId());
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    // 4. XÓA MẠCH
    public boolean deleteDevice(int id) {
        String sql = "DELETE FROM thietbi WHERE Id = ?";
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) { e.printStackTrace(); return false; }
    }
}
