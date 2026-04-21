/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_desktop.view;

/**
 *
 * @author ADMIN
 */
import com.mycompany.iot_share_core.entity.ThietBi;
import com.mycompany.iot_desktop.DAO.ThietBiDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class QuanLiThietBiFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ThietBiDAO dao;

    public QuanLiThietBiFrame() {
        dao = new ThietBiDAO(); // Khởi tạo anh "thợ" DAO
        
        setTitle("Quản Lý Mạch IoT");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Chỉ đóng cửa sổ này, không tắt cả phần mềm
        setLayout(new BorderLayout());

        // 1. Tạo bảng hiển thị
        String[] columns = {"ID", "Tên Mạch", "Trạng Thái"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 2. Tạo thanh công cụ chứa các nút
        JPanel panelBot = new JPanel();
        JButton btnTaiLai = new JButton("Tải Lại Bảng");
        JButton btnThem = new JButton("Thêm Mạch");
        JButton btnXoa = new JButton("Xóa Mạch");
        
        panelBot.add(btnTaiLai);
        panelBot.add(btnThem);
        panelBot.add(btnXoa);
        add(panelBot, BorderLayout.SOUTH);

        // 3. Đổ dữ liệu ngay khi vừa mở cửa sổ lên
        loadDuLieuLenBang();

        // --- BẮT SỰ KIỆN CHO CÁC NÚT ---

        // Nút Tải Lại
        btnTaiLai.addActionListener(e -> loadDuLieuLenBang());

        // Nút Thêm Mạch (Dùng hộp thoại nhập liệu nhanh)
        btnThem.addActionListener(e -> {
            String tenMach = JOptionPane.showInputDialog(this, "Nhập tên mạch mới (VD: ESP32_Vuon):");
            if (tenMach != null && !tenMach.trim().isEmpty()) {
                // Mặc định mạch mới thêm vào sẽ ở trạng thái Bật (true)
                ThietBi tbMoi = new ThietBi(0, tenMach, true); 
                if (dao.addDevice(tbMoi)) {
                    JOptionPane.showMessageDialog(this, "Thêm mạch thành công!");
                    loadDuLieuLenBang(); // Cập nhật lại bảng
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm vào DB!");
                }
            }
        });

        // Nút Xóa Mạch
        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một mạch trên bảng để xóa!");
                return;
            }
            int id = (int) table.getValueAt(row, 0); // Lấy ID ở cột đầu tiên
            
            int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa mạch ID " + id + "?", "Cảnh báo", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                if (dao.deleteDevice(id)) {
                    JOptionPane.showMessageDialog(this, "Đã xóa!");
                    loadDuLieuLenBang();
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể xóa! (Có thể mạch này đang chứa dữ liệu cảm biến)");
                }
            }
        });
    }

    // Hàm hỗ trợ: Gọi DAO lấy dữ liệu và quét vào bảng
    private void loadDuLieuLenBang() {
        tableModel.setRowCount(0); // Xóa sạch dữ liệu cũ trên bảng
        ArrayList<ThietBi> danhSach = dao.getAllDevices(); // Nhờ DAO lấy dữ liệu mới
        
        for (ThietBi tb : danhSach) {
            String trangThaiChu = tb.isTrangThai() ? "Đang Bật (Online)" : "Đã Tắt (Offline)";
            tableModel.addRow(new Object[]{tb.getId(), tb.getTenThietBi(), trangThaiChu});
        }
    }
}
