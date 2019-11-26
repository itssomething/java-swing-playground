/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Manh
 */
public class ThongKeKH implements Serializable{
    private static final long serialVersionUID = 2018040801L;
    private int id;
    private String maKH;    
    private String ten;
    private String diaChi;
    private String sdt;
    private String tongLuotThue;
    private String tongNgayThue;
    private String tongDoanhThu;
    
    public ThongKeKH(){
        super();
    }

    public ThongKeKH(int id, String maKH, String ten, String diaChi, String sdt, String tongLuotThue, String tongNgayThue, String tongDoanhThu) {
        this.id = id;
        this.maKH = maKH;
        this.ten = ten;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.tongLuotThue = tongLuotThue;
        this.tongNgayThue = tongNgayThue;
        this.tongDoanhThu = tongDoanhThu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTongLuotThue() {
        return tongLuotThue;
    }

    public void setTongLuotThue(String tongLuotThue) {
        this.tongLuotThue = tongLuotThue;
    }

    public String getTongNgayThue() {
        return tongNgayThue;
    }

    public void setTongNgayThue(String tongNgayThue) {
        this.tongNgayThue = tongNgayThue;
    }

    public String getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(String tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }


    
}
