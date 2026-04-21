/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_desktop.view;

import com.mycompany.iot_desktop.DAO.NhatKiCamBienDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author ADMIN
 */

public class XemDuLieuCamBienFrame extends JFrame {
    private DefaultTableModel tableModel;

    public XemDuLieuCamBienFrame() {
        setTitle("Dữ Liệu Cảm Biến Real-time");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] cols = {"Tên Mạch Phát", "Dữ Liệu JSON (Nhiệt độ, Độ ẩm...)", "Thời Gian Nhận"};
        tableModel = new DefaultTableModel(cols, 0);
        JTable table = new JTable(tableModel);
        
        // Chỉnh cột JSON rộng ra để dễ nhìn
        table.getColumnModel().getColumn(1).setPreferredWidth(300);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnTaiLai = new JButton("Cập Nhật Dữ Liệu Mới Nhất");
        btnTaiLai.addActionListener(e -> loadData());
        add(btnTaiLai, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        NhatKiCamBienDAO dao = new NhatKiCamBienDAO();
        ArrayList<Object[]> data = dao.layDuLieuCamBien();
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }
}
