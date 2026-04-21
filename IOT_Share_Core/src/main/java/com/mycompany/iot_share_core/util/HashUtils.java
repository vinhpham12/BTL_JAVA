/*
 * Lớp tiện ích dùng chung cho việc mã hóa / băm dữ liệu.
 */
package com.mycompany.iot_share_core.util;

import java.security.MessageDigest;

/**
 * @author ADMIN
 */
public class HashUtils {

    /**
     * Băm chuỗi đầu vào thành MD5 hex string.
     * Dùng để mã hóa mật khẩu trước khi lưu hoặc so sánh.
     */
    public static String hashMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
