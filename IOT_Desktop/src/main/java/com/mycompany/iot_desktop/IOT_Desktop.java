/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.iot_desktop;



/**
 *
 * @author ADMIN
 */
import com.mycompany.iot_desktop.view.DangNhapFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class IOT_Desktop {
    public static void main(String[] args) {
        // Ép Java sử dụng giao diện (Look and Feel) gốc của Windows
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Khởi chạy GUI an toàn
        SwingUtilities.invokeLater(() -> {
            new DangNhapFrame().setVisible(true);
        });
    }
}
