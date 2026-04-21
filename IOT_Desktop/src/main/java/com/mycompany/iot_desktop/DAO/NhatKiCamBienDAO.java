/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_desktop.DAO;

import com.mycompany.iot_desktop.config.DBConnection;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author ADMIN
 */
public class NhatKiCamBienDAO {
    public ArrayList<Object[]> layDuLieuCamBien() {
        ArrayList<Object[]> list = new ArrayList<>();
        // Kết hợp 2 bảng để dịch từ ThietBiId sang TenThietBi
        String sql = "SELECT t.TenThietBi, n.DuLieuJSON, n.ThoiGianGhi " +
                     "FROM NhatKyCamBien n JOIN ThietBi t ON n.ThietBiId = t.Id " +
                     "ORDER BY n.ThoiGianGhi DESC";
                     
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Không thể kết nối đến cơ sở dữ liệu!");
                return list;
            }
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    list.add(new Object[]{
                        rs.getString("TenThietBi"),
                        rs.getString("DuLieuJSON"),
                        rs.getTimestamp("ThoiGianGhi")
                    });
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
