/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_backend.DAO;

import com.mycompany.iot_backend.config.DBConnection;
import com.mycompany.iot_share_core.entity.NhatKiCamBien;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author ADMIN
 */
public class NhatKiCamBienDAO {
    // Hàm lưu dữ liệu cảm biến (Nhận đối tượng Entity từ Core)
    public boolean luuDuLieu(NhatKiCamBien log) {
        String sql = "INSERT INTO NhatKyCamBien (ThietBiId, DuLieuJSON) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Không thể kết nối DB để lưu dữ liệu cảm biến!");
                return false;
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, log.getThietBiId());
                ps.setString(2, log.getDuLieuJson());
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
            return false;
        }
    }
}
