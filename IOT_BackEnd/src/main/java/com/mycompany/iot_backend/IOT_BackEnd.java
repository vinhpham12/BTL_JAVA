/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.iot_backend;

import com.mycompany.iot_backend.config.DBConnection;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author ADMIN
 */
public class IOT_BackEnd {

    public static void main(String[] args) {
        try {
            // Giúp Console hiện tiếng Việt chuẩn
                System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
            // Xin hệ điều hành mở cổng 82
            int port=8082;
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            
            // Định tuyến đường dẫn API
            server.createContext("/api/data", new DataHandler());
            server.setExecutor(null); 
            server.start();
            
            System.out.println("==========================================");
            System.out.println("🚀 IOT BACKEND ĐANG LẮNG NGHE TẠI CỔNG "+port);
            System.out.println("==========================================");
        } catch (Exception e) {
            System.out.println("❌ Lỗi khởi động Server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Lớp xử lý luồng dữ liệu đến
    static class DataHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {
            try {
                
                // Chỉ nhận lệnh POST từ mạch ESP32
                if ("POST".equals(exchange.getRequestMethod())) {
                    InputStream is = exchange.getRequestBody();
                    String jsonPayload = new String(is.readAllBytes(),"UTF-8");
                    
                    // Ghi thẳng vào DB (Giả định thiết bị ID = 1)
                    try (Connection conn = DBConnection.getConnection()) {
                        String sql = "INSERT INTO NhatKyCamBien (ThietBiId, DuLieuJSON) VALUES (1, ?)";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, jsonPayload);
                        ps.executeUpdate();
                        System.out.println("✅ Đã nhận và lưu: " + jsonPayload);
                    } catch (Exception ex) {
                        System.out.println("❌ Lỗi ghi DB: " + ex.getMessage());
                    }

                    // Phản hồi lại cho mạch biết đã nhận xong
                    String response = "Backend OK";
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } else {
                    exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
