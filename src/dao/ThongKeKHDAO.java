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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ThongKeKH;

/**
 *
 * @author Manh
 */
public class ThongKeKHDAO {

    private static Connection con;

    public ThongKeKHDAO() {
        if (con == null) {
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

    public ArrayList<ThongKeKH> index(String start, String end) throws SQLException {
        ArrayList<ThongKeKH> result = new ArrayList<>();
//        String sql = "select * from tbluser where name like ?";
        String sql = "select *, sum(thanhTien) as tongDoanhThu from\n" +
                    "(select count(*) as tongLuotThue, tblkhachhang.ten, tblkhachhang.diaChi, tblkhachhang.dienThoai, tblkhachhang.id\n" +
                    "from tblkhachhang inner join tblhopdongthue \n" +
                    "on tblkhachhang.id = tblhopdongthue.khachHangID\n" +
                    "group by khachHangID ) as a\n" +
                    "inner join\n" +
                    "(select sum(tblhopdong.ngayKetThuc - tblhopdong.ngayBatDau+1) as tongNgayThue, tblhopdongthue.khachHangID\n" +
                    "from tblhopdong\n" +
                    "inner join tblhopdongthue \n" +
                    "on tblhopdong.id = tblhopdongthue.hopDongId\n" +
                    "where ngayKetThuc >= ? and ngayKetThuc <= ? \n" +
                    "group by tblhopdongthue.khachHangID ) as b\n" +
                    "on a.id = b.khachHangID\n" +
                    "inner join \n" +
                    "(select tblhopdongthue.khachHangID, tblhoadonthue.thanhTien from tblhopdong \n" +
                    "inner join tblhopdongthue\n" +
                    "on tblhopdong.id = tblhopdongthue.hopDongID\n" +
                    "inner join tblhoadonthue\n" +
                    "on tblhopdongthue.id = tblhoadonthue.hopDongThueID) as c\n" +
                    "on a.id = c.khachHangID\n" +
                    "group by c.khachHangID";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if(!"".equals(start)){
                ps.setString(1, start);
            } else {
                ps.setString(1, "1970-01-01");
            }
            if(!"".equals(end)){
                ps.setString(2, end);
            } else {
                ps.setString(2, "9999-12-31");
            }

            System.out.println(ps.toString());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ThongKeKH tkkh = new ThongKeKH();
                tkkh.setId(rs.getInt("id"));
                tkkh.setTongLuotThue(rs.getString("tongLuotThue"));

                tkkh.setTen(rs.getString("ten"));
                tkkh.setDiaChi(rs.getString("diaChi"));
                tkkh.setSdt(rs.getString("dienThoai"));
                tkkh.setTongNgayThue(rs.getString("tongNgayThue"));
                tkkh.setTongDoanhThu(rs.getString("tongDoanhThu"));
                
                result.add(tkkh);
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
