/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_desktop.view;

import com.mycompany.iot_desktop.DAO.NhatKiHoatDongDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author ADMIN
 */

public class XemNhatKiFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public XemNhatKiFrame() {
        setTitle("Nhật Ký Hoạt Động Hệ Thống");
        setSize(600, 400);
        setLocationRelativeTo(null);
        // Dùng DISPOSE_ON_CLOSE để khi tắt cửa sổ Log, phần mềm chính không bị tắt theo
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout());

        // 1. Tạo các cột cho bảng
        String[] columns = {"Tài Khoản", "Hành Động", "Thời Gian"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        // Nhét bảng vào thanh cuộn (JScrollPane) để nếu log dài quá có thể kéo xuống xem
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 2. Tạo nút Làm mới (Refresh) ở dưới cùng
        JPanel panelBottom = new JPanel();
        JButton btnRefresh = new JButton("Làm mới danh sách");
        panelBottom.add(btnRefresh);
        add(panelBottom, BorderLayout.SOUTH);

        // Bắt sự kiện cho nút Làm mới
        btnRefresh.addActionListener(e -> loadDuLieuLenBang());

        // 3. Đổ dữ liệu ngay khi vừa mở cửa sổ lên
        loadDuLieuLenBang();
    }

    // Hàm chuyên dụng để gọi DAO lấy dữ liệu và đưa lên bảng
    private void loadDuLieuLenBang() {
        tableModel.setRowCount(0); // Xóa sạch các dòng cũ trên giao diện
        
        NhatKiHoatDongDAO dao = new NhatKiHoatDongDAO();
        ArrayList<Object[]> danhSachLog = dao.layTatCaLog();
        
        // Quét từng dòng dữ liệu lấy từ DB và thêm vào JTable
        for (Object[] row : danhSachLog) {
            tableModel.addRow(row);
        }
    }
}
