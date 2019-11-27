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

/**
 *
 * @author Manh
 */
public class LoginDAO {
    private static Connection con;
    
    public LoginDAO() {
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
    
    public boolean login(String userName, String password) throws SQLException{
        String sql = "select * from tbluser where userName = ? and password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            System.out.println(ps.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
              return true;
            }
                
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
