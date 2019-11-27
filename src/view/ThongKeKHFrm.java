/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ThongKeKHDAO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import model.ThongKeKH;

/**
 *
 * @author Manh
 */
public class ThongKeKHFrm extends JFrame implements ActionListener {

    private ArrayList<ThongKeKH> tkkhs;
    private JTextField txtName;
    private JTextField txtDateStart;
    private JTextField txtDateEnd;

    private JButton btnSearch;
    private JTable tblResult;

    public ThongKeKHFrm() {
        tkkhs = new ArrayList<ThongKeKH>();

        JPanel panel = new JPanel();
        panel.setSize(5, 5);
        panel.setMaximumSize(new Dimension(80,
                20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel pn1 = new JPanel();
        pn1.setLayout(new BoxLayout(pn1, BoxLayout.X_AXIS));
        pn1.setSize(5, 5);
        pn1.add(new JLabel("Ngày bắt đầu: "));
        txtDateStart = new JTextField();
        pn1.add(txtDateStart);

        pn1.add(new JLabel("Ngày kết thúc: "));
        txtDateEnd = new JTextField();
        
        pn1.add(txtDateEnd);
        txtDateStart.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtDateStart.getMinimumSize().height + 10));
        txtDateEnd.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtDateEnd.getMinimumSize().height + 10));

        btnSearch = new JButton("Lọc");
        btnSearch.addActionListener(this);
        pn1.add(btnSearch);
        panel.add(pn1);

        JPanel pn2 = new JPanel();
        pn2.setLayout(new BoxLayout(pn2, BoxLayout.Y_AXIS));
        tblResult = new JTable(new DefaultTableModel(new Object[]{"ID", "Tên", "Địa chỉ", "SĐT", "Tổng lượt thuê", "Tổng ngày thuê", "Tổng doanh thu"}, 0));
        DefaultTableModel model = (DefaultTableModel) tblResult.getModel();

        JScrollPane scrollPane = new JScrollPane(tblResult);
        tblResult.setFillsViewportHeight(false);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 250));
        
        tblResult.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("row clicked");
            }
        });
        
        tblResult.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent e) {
               try {
                   int row = tblResult.getSelectedRow();
                   Object o = tblResult.getValueAt(row, 0);
                   ThongKeKHChiTietFrm x = new ThongKeKHChiTietFrm(Integer.parseInt(o.toString()), txtDateStart.getText(), txtDateEnd.getText());
                   x.setSize(500,520);
                   x.setVisible(true);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
        });
        
        pn2.add(scrollPane);
        panel.add(pn2);

        this.add(panel);
        panel.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton btnClicked = (JButton) ae.getSource();

        if (btnClicked.equals(btnSearch)) {
            try {
                btnSearchClick();
            } catch (SQLException ex) {
                Logger.getLogger(ThongKeKHFrm.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
    }

    public void btnSearchClick() throws SQLException {
        ThongKeKHDAO tkkhDao = new ThongKeKHDAO();
        String start = txtDateStart.getText();
        String end = txtDateEnd.getText();
        ArrayList<ThongKeKH> tkkhs = tkkhDao.index(start, end);
        DefaultTableModel model = (DefaultTableModel) tblResult.getModel();
        
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
        for(ThongKeKH tkkh: tkkhs){
            model.addRow(new Object[]{tkkh.getId(), tkkh.getTen(), tkkh.getDiaChi(), tkkh.getSdt(), tkkh.getTongLuotThue(), tkkh.getTongNgayThue(), tkkh.getTongDoanhThu()});
        }
        
//        model.addRow(new Object[]{"1", "2", "3"});
        ((DefaultTableModel) tblResult.getModel()).fireTableDataChanged();
    }

    public static void main(String[] args) {
        ThongKeKHFrm frm = new ThongKeKHFrm();
        frm.setSize(800, 300);
        frm.setVisible(true);
        frm.setLocation(200, 10);
    }

    class UserTableModel extends DefaultTableModel {

        private String[] columnNames = {"ID", "Tên", "Địa chỉ", "SĐT", "Tổng lượt thuê", "Tổng ngày thuê", "Tổng doanh thu"};
        private final Class<?>[] columnTypes = new Class<?>[]{Integer.class, String.class, String.class, String.class, String.class, String.class, String.class};

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return tkkhs.size();
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnTypes[columnIndex];
        }

        @Override
        public Object getValueAt(final int rowIndex, final int columnIndex) {
            /*Adding components*/
            switch (columnIndex) {
                case 0:
                    return tkkhs.get(rowIndex).getId();
                case 1:
                    return tkkhs.get(rowIndex).getTen();
                case 2:
                    return tkkhs.get(rowIndex).getSdt();
                default:
                    return "Error";
            }
        }
    }

    class JTableButtonMouseListener extends MouseAdapter {

        private final JTable table;

        public JTableButtonMouseListener(JTable table) {
            this.table = table;
        }

        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
            int row = e.getY() / table.getRowHeight(); //get the row of the button

            //*Checking the row or column is valid or not
            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton) {
                    //perform a click event
                    ((JButton) value).doClick();
                }
            }
        }
    }

    class JTableButtonRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            JButton button = (JButton) value;
            return button;
        }
    }
}
