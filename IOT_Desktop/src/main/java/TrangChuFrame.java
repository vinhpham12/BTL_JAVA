/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_desktop.view;

/**
 *
 * @author ADMIN
 */
import com.mycompany.iot_share_core.entity.NguoiDung;
import com.mycompany.iot_desktop.DAO.NhatKiHoatDongDAO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TrangChuFrame extends JFrame {
    private NguoiDung currentUser;

    public TrangChuFrame(NguoiDung user) {
        this.currentUser = user; 
        
        setTitle("Điều hành Trung tâm IoT");
        setSize(450, 450); // Cửa sổ cao hơn để chứa danh sách nút
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // --- 1. KHU VỰC HEADER BANNER ---
        JPanel panelHeader = new JPanel(new GridLayout(2, 1));
        panelHeader.setBackground(new Color(41, 128, 185)); // Nền banner xanh dương
        panelHeader.setBorder(new EmptyBorder(15, 0, 15, 0));
        
        JLabel lblTitle = new JLabel("TRUNG TÂM ĐIỀU KHIỂN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        
        JLabel lblXinChao = new JLabel("Xin chào: " + currentUser.getHoTen() + " (" + currentUser.getRole() + ")", SwingConstants.CENTER);
        lblXinChao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblXinChao.setForeground(Color.WHITE);
        
        panelHeader.add(lblTitle);
        panelHeader.add(lblXinChao);
        add(panelHeader, BorderLayout.NORTH);

        // --- 2. KHU VỰC MENU NÚT BẤM ---
        // Chia thành 5 dòng, 1 cột, khoảng cách các nút là 15px
        JPanel panelMenu = new JPanel(new GridLayout(5, 1, 15, 15));
        panelMenu.setBackground(Color.WHITE);
        panelMenu.setBorder(new EmptyBorder(25, 60, 25, 60)); // Lùi lề 2 bên vào 60px

        // Dùng hàm tiện ích bên dưới để tạo nút nhanh với màu sắc tùy chỉnh
        JButton btnThietBi = taoNutMenu("Quản lý Mạch IoT", new Color(52, 152, 219));
        JButton btnData = taoNutMenu("Xem Dữ Liệu Cảm Biến", new Color(46, 204, 113));
        JButton btnThemUser = taoNutMenu("Thêm Thành Viên", new Color(241, 196, 15));
        JButton btnNhatKy = taoNutMenu("Xem Nhật Ký Hệ Thống", new Color(155, 89, 182));
        JButton btnLogout = taoNutMenu("Đăng Xuất", new Color(231, 76, 60));

        // BẢO MẬT: Phân quyền làm mờ nút
        if (!currentUser.getRole().equals("ADMIN")) {
            btnNhatKy.setEnabled(false);
            btnNhatKy.setBackground(Color.LIGHT_GRAY);
            btnNhatKy.setText("Nhật Ký (Chỉ dành cho Admin)");
            
            btnThemUser.setEnabled(false);
            btnThemUser.setBackground(Color.LIGHT_GRAY);
            btnThemUser.setText("Thêm User (Chỉ dành cho Admin)");
        }

        panelMenu.add(btnThietBi);
        panelMenu.add(btnData);
        panelMenu.add(btnThemUser);
        panelMenu.add(btnNhatKy);
        panelMenu.add(btnLogout);
        add(panelMenu, BorderLayout.CENTER);

        // --- 3. BẮT SỰ KIỆN ---
        btnThietBi.addActionListener(e -> new QuanLiThietBiFrame().setVisible(true));
        btnData.addActionListener(e -> {
            NhatKiHoatDongDAO.ghiLog(currentUser.getUsername(), "Xem dữ liệu cảm biến");
            new XemDuLieuCamBienFrame().setVisible(true);
        });
        btnThemUser.addActionListener(e -> new ThemThanhVienFrame(currentUser).setVisible(true));
        btnNhatKy.addActionListener(e -> new XemNhatKiFrame().setVisible(true));
        btnLogout.addActionListener(e -> {
            NhatKiHoatDongDAO.ghiLog(currentUser.getUsername(), "Đăng xuất");
            this.dispose();
            new DangNhapFrame().setVisible(true);
        });
    }

    // Hàm hỗ trợ chuyên tạo nút để code không bị lặp lại dài dòng
    // Hàm hỗ trợ tạo nút chuẩn in ấn báo cáo
    private JButton taoNutMenu(String text, Color bgColor) {
        JButton btn = new JButton(text);
        
        // 1. Tăng cỡ chữ và độ đậm
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16)); 
        
        // 2. Ép đổ màu đặc, xóa hiệu ứng bóng mờ mặc định của hệ điều hành
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE); 
        btn.setContentAreaFilled(false); // Bắt buộc phải có
        btn.setOpaque(true);             // Bắt buộc phải có
        
        // 3. Kẻ viền (Border) dày 2 pixel, màu tối hơn nền một tông để tạo khối sắc nét
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker().darker(), 2), 
            BorderFactory.createEmptyBorder(12, 20, 12, 20) // Căn lề trong cho chữ không dính viền
        ));

        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return btn;
    }
}
