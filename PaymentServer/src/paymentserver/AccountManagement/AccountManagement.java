/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package paymentserver.AccountManagement;

import java.awt.Component;
import java.awt.Font;
import paymentserver.HomePage.*;
import java.util.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author DELL
 */
class ManagedUser{
    private String UserID;
    private String UserName;
    private int loan = 0;
    private int balance = 0;

    ManagedUser(String id, String name, int loan, int balance){
        this.UserID = id;
        this.UserName = name;
        this.loan = loan;
        this.balance = balance;
    }
    
    public String getId(){
        return UserID;
    }
    
    public String getName(){
        return UserName;
    }
    
    public int getLoan(){
        return loan;
    }
    
    public int getBalance(){
        return balance;
    }
}

public class AccountManagement extends javax.swing.JFrame {
    private ArrayList<ManagedUser> lst = new ArrayList<ManagedUser>();
    private int row = -1;
    private String userid = "";
    
    public AccountManagement() {
        initComponents();
        this.setResizable(false);
        TextUserID.setOpaque(false);
        TextUserID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        TextUserID.setEditable(false);
        TextAccountID.setOpaque(false);
        TextAccountID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        TextAccountID.setEditable(false);
        TextBalance.setOpaque(false);
        TextBalance.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        TextBalance.setEditable(false);
        TabUser.setDefaultEditor(Object.class, null);
        setData();
        showData();
        EditTableHeightWidth();
    }
    
    public void setData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();
            
            String sql = "select * from ManagedUser where isPay = 1";
            ResultSet res = state.executeQuery(sql);
            
            while(res.next()){
                ManagedUser tmp = new ManagedUser(res.getString("UserID"), res.getString("Fullname"), res.getInt("Loan"), res.getInt("Balance"));
                lst.add(tmp);
            }

            connect.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refreshJTable() {
        DefaultTableModel model = (DefaultTableModel) TabUser.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }
    
    public void showData() {
        DefaultTableModel model = (DefaultTableModel) TabUser.getModel();
        Object[] row = new Object[4];

        for (int i = 0; i < lst.size(); i++) {
            row[0] = i + 1;
            row[1] = lst.get(i).getId();
            row[2] = lst.get(i).getName();
            row[3] = lst.get(i).getLoan();
            model.addRow(row);
        }
    }
    
    public void EditTableHeightWidth() {
        //edit size of column
        TabUser.getTableHeader().setFont(new Font("Fredoka One", Font.PLAIN, 16));
        //tmp.getTableHeader().setForeground(jScrollPane2.getBackground());
        final TableColumnModel columnModel = TabUser.getColumnModel();
        for (int column = 0; column < TabUser.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < TabUser.getRowCount(); row++) {
                TableCellRenderer renderer = TabUser.getCellRenderer(row, column);
                Component comp = TabUser.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }

        for (int row = 0; row < TabUser.getRowCount(); row++) {
            int rowHeight = TabUser.getRowHeight();
            for (int column = 0; column < TabUser.getColumnCount(); column++) {
                Component comp = TabUser.prepareRenderer(TabUser.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
            TabUser.setRowHeight(row, rowHeight);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnBack = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabUser = new javax.swing.JTable();
        TextUserID = new javax.swing.JTextField();
        TextAccountID = new javax.swing.JTextField();
        TextBalance = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackMouseClicked(evt);
            }
        });
        getContentPane().add(BtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 70, 70));

        TabUser.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        TabUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "UserID", "Name", "Loan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TabUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabUser);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 147, 580, 520));

        TextUserID.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        getContentPane().add(TextUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 210, 250, 40));

        TextAccountID.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        getContentPane().add(TextAccountID, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 310, 250, 40));

        TextBalance.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        getContentPane().add(TextBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 410, 220, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paymentserver/AccountManagement/AddPaymentAccount.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackMouseClicked
        // TODO add your handling code here:
        ServerHomePage hp = new ServerHomePage();
        hp.show();
        dispose();
    }//GEN-LAST:event_BtnBackMouseClicked

    private void TabUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabUserMouseClicked
        // TODO add your handling code here:
        int index = TabUser.getSelectedRow(); // row that manager choose.
        row = index;
        TableModel model = TabUser.getModel();
        
        TextUserID.setText(model.getValueAt(index, 1).toString());
        userid = TextUserID.getText();
        TextAccountID.setText(model.getValueAt(index, 1).toString());
        TextBalance.setText(Integer.toString(lst.get(index).getBalance()));        
    }//GEN-LAST:event_TabUserMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AccountManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccountManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnBack;
    private javax.swing.JTable TabUser;
    private javax.swing.JTextField TextAccountID;
    private javax.swing.JTextField TextBalance;
    private javax.swing.JTextField TextUserID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
