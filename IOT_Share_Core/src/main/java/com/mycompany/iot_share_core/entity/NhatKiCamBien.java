/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iot_share_core.entity;

/**
 *
 * @author ADMIN
 */
import java.util.Date;

public class NhatKiCamBien {
    private long id;
    private int thietBiId; // Mạch nào gửi?
    private String duLieuJson; // Chứa dữ liệu gì?
    private Date thoiGianGhi; // Gửi lúc nào?

    public NhatKiCamBien() {}

    public NhatKiCamBien(int thietBiId, String duLieuJson) {
        this.thietBiId = thietBiId;
        this.duLieuJson = duLieuJson;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public int getThietBiId() { return thietBiId; }
    public void setThietBiId(int thietBiId) { this.thietBiId = thietBiId; }

    public String getDuLieuJson() { return duLieuJson; }
    public void setDuLieuJson(String duLieuJson) { this.duLieuJson = duLieuJson; }

    public Date getThoiGianGhi() { return thoiGianGhi; }
    public void setThoiGianGhi(Date thoiGianGhi) { this.thoiGianGhi = thoiGianGhi; }
}