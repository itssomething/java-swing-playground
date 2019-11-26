package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Manh
 */
public class ThongKeKHChiTiet {
    private static final long serialVersionUID = 2018040801L;
    private String ID;
    private String tenKH;
    private String ngayThue;
    private String ngayTra;
    private String soXe;
    private String tienThue;
    private String tienPhat;
    private String tongTien;

    public ThongKeKHChiTiet() {
        super();
    }

    public ThongKeKHChiTiet(String ID, String tenKH, String ngayThue, String ngayTra, String soXe, String tienThue, String tienPhat, String tongTien) {
        this.ID = ID;
        this.tenKH = tenKH;
        this.ngayThue = ngayThue;
        this.ngayTra = ngayTra;
        this.soXe = soXe;
        this.tienThue = tienThue;
        this.tienPhat = tienPhat;
        this.tongTien = tongTien;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getSoXe() {
        return soXe;
    }

    public void setSoXe(String soXe) {
        this.soXe = soXe;
    }

    public String getTienThue() {
        return tienThue;
    }

    public void setTienThue(String tienThue) {
        this.tienThue = tienThue;
    }

    public String getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(String tienPhat) {
        this.tienPhat = tienPhat;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    

}
