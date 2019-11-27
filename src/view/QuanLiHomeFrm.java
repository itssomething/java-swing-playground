/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Manh
 */
public class QuanLiHomeFrm extends JFrame implements ActionListener {
    private JButton customerStats;

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("ae");
        JButton btnClicked = (JButton) ae.getSource();
        
        if(btnClicked.equals(customerStats)) {
            try {
                customerStatsClicked();
            } catch (Exception e){
                e.printStackTrace();
            }
            return;
        }           
    }
    
    public QuanLiHomeFrm(){
        JPanel panel = new JPanel();
        panel.setSize(100, 100);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        customerStats = new JButton("Thống kê KH theo doanh thu");
        customerStats.addActionListener(this);
        
        panel.add(customerStats);
        this.add(panel);
    }
    
    public void customerStatsClicked(){
        ThongKeKHFrm view = new ThongKeKHFrm();
        view.setSize(1000, 500);
        view.setVisible(true);
    }
}
