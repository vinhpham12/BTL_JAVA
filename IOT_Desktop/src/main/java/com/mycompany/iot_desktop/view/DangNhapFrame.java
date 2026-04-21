/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_desktop.view;

import com.mycompany.iot_share_core.entity.NguoiDung;
import com.mycompany.iot_desktop.DAO.AuthDAO;
import com.mycompany.iot_desktop.DAO.NhatKiHoatDongDAO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author ADMIN
 */

public class DangNhapFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public DangNhapFrame() {
        setTitle("Đăng nhập Hệ thống IoT");
        setSize(400, 280); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE); // Phủ nền trắng toàn bộ

        // --- 1. HEADER (TIÊU ĐỀ) ---
        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÍ THIẾT BỊ IOT ", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(41, 128, 185)); // Màu xanh dương
        lblTitle.setBorder(new EmptyBorder(20, 0, 10, 0)); // Tạo khoảng trống trên dưới
        add(lblTitle, BorderLayout.NORTH);

        // --- 2. FORM NHẬP LIỆU (CENTER) ---
        JPanel panelForm = new JPanel(new GridLayout(2, 2, 10, 15));
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(new EmptyBorder(10, 40, 10, 40)); // Ép vào giữa

        JLabel lblUser = new JLabel("Tài khoản:");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblPass = new JLabel("Mật khẩu:");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelForm.add(lblUser); panelForm.add(txtUsername);
        panelForm.add(lblPass); panelForm.add(txtPassword);
        add(panelForm, BorderLayout.CENTER);

        // --- 3. NÚT ĐĂNG NHẬP (SOUTH) ---
        JPanel panelBot = new JPanel();
        panelBot.setBackground(Color.WHITE);
        panelBot.setBorder(new EmptyBorder(10, 0, 20, 0));

        btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setBackground(new Color(46, 204, 113)); // Nền màu xanh lá
        btnLogin.setForeground(Color.WHITE); // Chữ màu trắng
        btnLogin.setContentAreaFilled(false);
        btnLogin.setOpaque(true);
        btnLogin.setBorder(BorderFactory.createLineBorder(new Color(39, 174, 96), 2));
        btnLogin.setFocusPainted(false); // Bỏ viền chấm khi click
        btnLogin.setPreferredSize(new Dimension(200, 40)); // Chỉnh kích thước nút to ra
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đổi thành bàn tay khi trỏ vào

        panelBot.add(btnLogin);
        add(panelBot, BorderLayout.SOUTH);

        // --- BẮT SỰ KIỆN ---
        btnLogin.addActionListener(e -> xuLyDangNhap());
    }

    private void xuLyDangNhap() {
        String user = txtUsername.getText();
        String pass = new String(txtPassword.getPassword());

        AuthDAO authDAO = new AuthDAO();
        NguoiDung loggedInUser = authDAO.login(user, pass);

        if (loggedInUser != null) {
            NhatKiHoatDongDAO.ghiLog(loggedInUser.getUsername(), "Đăng nhập hệ thống");
            this.dispose();
            new TrangChuFrame(loggedInUser).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
