/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ThongKeKHChiTiet;

/**
 *
 * @author Manh
 */
public class ThongKeKHChiTietDAO {
    private static Connection con;
    
    public ThongKeKHChiTietDAO(){
        if(con == null){
            String url = "jdbc:mysql://localhost:3306/car_rental?user=root&password=123456";
            String dbClass = "com.mysql.cj.jdbc.Driver";
            
            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
    }
    
    public ArrayList<ThongKeKHChiTiet> xemChiTiet(int id, String start, String end) throws SQLException {
        ArrayList<ThongKeKHChiTiet> result = new ArrayList<>();
        String sql = "select * from tblkhachhang \n" +
                    "inner join tblhopdongthue\n" +
                    "on tblkhachhang.id = tblhopdongthue.khachHangID\n" +
                    "inner join tblhopdong\n" +
                    "on tblhopdong.id = tblhopdongthue.hopDongID\n" +
                    "inner join tblhoadonthue \n" +
                    "on tblhoadonthue.hopDongThueID = tblhopdongthue.id\n" +
                    "inner join tblhonghoc\n" +
                    "on tblhonghoc.hopDongThueID = tblhopdongthue.id\n" +
                    "where tblhopdongthue.khachHangID = ?";
        if(!"".equals(start))
            sql += "and ngayKetThuc >= ?";
        if(!"".equals(end))
            sql += "and ngayKetThuc <= ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Integer.toString(id));
            if(!"".equals(start))
                ps.setString(2, start);
            if(!"".equals(end))
                ps.setString(2, end);
            
            System.out.println(ps.toString());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ThongKeKHChiTiet tkkhc = new ThongKeKHChiTiet();
                tkkhc.setID(rs.getString("id"));
                tkkhc.setTenKH(rs.getString("ten"));
                tkkhc.setNgayThue(rs.getString("ngayBatDau"));
                tkkhc.setNgayTra(rs.getString("ngayKetThuc"));
                tkkhc.setSoXe("0");
                tkkhc.setTienThue(rs.getString("gia"));
                tkkhc.setTienPhat(rs.getString("giaTri"));
                tkkhc.setTongTien(rs.getString("thanhTien"));
                result.add(tkkhc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
