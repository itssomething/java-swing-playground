/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ThongKeKHChiTietDAO;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ThongKeKHChiTiet;

/**
 *
 * @author Manh
 */
public class ThongKeKHChiTietFrm extends JFrame implements ActionListener {
    private ArrayList<ThongKeKHChiTiet> tkkhcs;
    private JTable tblResult;
    
    public ThongKeKHChiTietFrm(int khachHangID, String start, String end) throws SQLException {
        
        JPanel panel = new JPanel();
        panel.setSize(10, 10);
        panel.setMaximumSize(new Dimension(80, 20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        
        JPanel pn1 = new JPanel();
        pn1.setLayout(new BoxLayout(pn1, BoxLayout.X_AXIS));
        pn1.setSize(5,5);
        pn1.add(new JLabel("Chi tiet: "));
        panel.add(pn1);
        
        JPanel pn2 = new JPanel();
        pn2.setLayout(new BoxLayout(pn2, BoxLayout.Y_AXIS));
        tblResult = new JTable(new DefaultTableModel(new Object[]{"ID", "Tên", "Ngày thuê", "Ngày trả", "Số lượng xe", "Số tiền thuê", "Số tiền phạt", "Tổng tiền"}, 0));

        JScrollPane scrollPane = new JScrollPane(tblResult);
        tblResult.setFillsViewportHeight(false);
        
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 250));
        
        pn2.add(scrollPane);
        panel.add(pn2);
        
        tkkhcs = new ArrayList<ThongKeKHChiTiet>();
        ThongKeKHChiTietDAO tkkcDao = new ThongKeKHChiTietDAO();
        tkkhcs = tkkcDao.xemChiTiet(khachHangID, start, end);
        DefaultTableModel model = (DefaultTableModel) tblResult.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        for(ThongKeKHChiTiet tkkhc: tkkhcs){
            model.addRow(new Object[]{tkkhc.getID(), tkkhc.getTenKH(), tkkhc.getNgayThue(), tkkhc.getNgayTra(), tkkhc.getSoXe(), tkkhc.getTienThue(), tkkhc.getTienPhat(), tkkhc.getTongTien()});
        }
        
        this.add(panel);
        panel.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
