/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.LoginDAO;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Manh
 */
public class LoginFrm extends JFrame implements ActionListener {
    private JTextField txtUserName;
    private JTextField txtPassword;
    
    private JButton btnLogin;
    
    public LoginFrm(){
        JPanel panel = new JPanel();
        panel.setSize(200, 200);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Login"));

        JPanel pn1 = new JPanel();
        pn1.setLayout(new BoxLayout(pn1, BoxLayout.X_AXIS));
        pn1.add(new JLabel("Username: "));
        txtUserName = new JTextField();
        txtUserName.setMaximumSize(new Dimension(300, txtUserName.getMinimumSize().height + 10));
        pn1.add(txtUserName);
        panel.add(pn1);
        
        JPanel pn2 = new JPanel();
        pn2.setLayout(new BoxLayout(pn2, BoxLayout.X_AXIS));
        pn2.add(new JLabel("Password: "));
        txtPassword = new JPasswordField();
        txtPassword.setMaximumSize(new Dimension(300, txtPassword.getMinimumSize().height + 10));
        pn2.add(txtPassword);
        panel.add(pn2);
        
        btnLogin = new JButton("Đăng nhập");
        btnLogin.addActionListener(this);

        panel.add(btnLogin);
        
        this.add(panel);
        panel.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
            
    public static void main(String[] args) {
        LoginFrm frm = new LoginFrm();
        frm.setSize(800, 300);
        frm.setVisible(true);
        frm.setLocation(200, 10);
    }
        
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("ae");
        JButton btnClicked = (JButton) ae.getSource();
        
        if(btnClicked.equals(btnLogin)) {
            try {
                btnLoginClicked();
            } catch (Exception e){
                e.printStackTrace();
            }
            return;
        }           
    }
    
    public void btnLoginClicked() throws SQLException{
        System.out.println("321");
        LoginDAO loginDao = new LoginDAO();
        boolean success = loginDao.login(txtUserName.getText(), txtPassword.getText());
        
        if(success){
            this.dispose();
            QuanLiHomeFrm view = new QuanLiHomeFrm();
//            ThongKeKHFrm view = new ThongKeKHFrm();
            view.setSize(300, 300);
            view.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu sai");
        }
    }
    
}
