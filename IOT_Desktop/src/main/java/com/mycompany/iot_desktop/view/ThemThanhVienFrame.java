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
import com.mycompany.iot_desktop.DAO.NguoiDungDAO;
import com.mycompany.iot_desktop.DAO.NhatKiHoatDongDAO;
import javax.swing.*;
import java.awt.*;

public class ThemThanhVienFrame extends JFrame {
    
    public ThemThanhVienFrame(NguoiDung currentUser) {
        // --- CHỐT CHẶN BẢO MẬT LỚP 2 ---
        // Nếu người mở cửa sổ này KHÔNG CÓ quyền ADMIN, đuổi ra ngoài ngay lập tức
        if (!currentUser.getRole().equals("ADMIN")) {
            JOptionPane.showMessageDialog(null, 
                "CẢNH BÁO: BẠN KHÔNG CÓ QUYỀN TRUY CẬP CHỨC NĂNG NÀY!", 
                "Vi phạm an ninh", 
                JOptionPane.ERROR_MESSAGE);
            this.dispose(); // Đóng cửa sổ lập tức
            return; // Dừng toàn bộ code phía dưới
        }

        // --- NẾU LÀ ADMIN THÌ MỚI VẼ GIAO DIỆN ---
        setTitle("Thêm Thành Viên Mới - Khu vực Admin");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        JTextField txtUser = new JTextField();
        JPasswordField txtPass = new JPasswordField();
        JTextField txtHoTen = new JTextField();
        
        // ComboBox chọn quyền cho tài khoản sắp tạo
        String[] quyen = {"USER", "ADMIN"};
        JComboBox<String> cbRole = new JComboBox<>(quyen);

        add(new JLabel(" Tên đăng nhập:")); add(txtUser);
        add(new JLabel(" Mật khẩu:"));      add(txtPass);
        add(new JLabel(" Họ và tên:"));     add(txtHoTen);
        add(new JLabel(" Cấp quyền:"));     add(cbRole);

        JButton btnLuu = new JButton("Lưu Tài Khoản");
        add(new JLabel("")); add(btnLuu);

        btnLuu.addActionListener(e -> {
            String u = txtUser.getText();
            String p = new String(txtPass.getPassword());
            String h = txtHoTen.getText();
            String r = cbRole.getSelectedItem().toString();

            if (u.isEmpty() || p.isEmpty() || h.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin!");
                return;
            }

            NguoiDungDAO dao = new NguoiDungDAO();
            if (dao.themTaiKhoan(u, p, h, r)) {
                JOptionPane.showMessageDialog(this, "Tạo tài khoản thành công!");
                // Ghi nhật ký: Admin nào đã tạo tài khoản nào
                NhatKiHoatDongDAO.ghiLog(currentUser.getUsername(), "Đã cấp tài khoản mới: " + u + " (Quyền: " + r + ")");
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi! Tên đăng nhập có thể đã tồn tại.");
            }
        });
    }
}
