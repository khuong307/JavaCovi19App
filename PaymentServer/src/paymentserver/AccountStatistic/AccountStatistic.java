/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package paymentserver.AccountStatistic;

import java.awt.Component;
import java.awt.Font;
import paymentserver.HomePage.*;
import java.sql.*;
import java.util.logging.*;
import java.util.*;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

class Transaction{
    private String TransactionID;
    private String UserID;
    private String TransactionTime;
    private String Total;
    
    Transaction(String TransactionID, String UserID, String TransactionTime, String Total){
        this.TransactionID = TransactionID;
        this.UserID = UserID;
        this.TransactionTime = TransactionTime;
        this.Total = Total;
    }
    
    public String getTransactionID(){
        return TransactionID;
    }
    
    public String getTransactionTime(){
        return TransactionTime;
    }
    
    public String getTotal(){
        return Total;
    }
    
    public String getUserID(){
        return UserID;
    }
}

public class AccountStatistic extends javax.swing.JFrame {
    private ArrayList<Transaction> trans = new ArrayList<Transaction>();
    
    public AccountStatistic() {
        initComponents();
        this.setResizable(false);
        TextAccountID.setEditable(false);
        TextAccountID.setOpaque(false);
        TextAccountID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        TextAccountID.setHorizontalAlignment(JTextField.CENTER);
        TextBalance.setEditable(false);
        TextBalance.setOpaque(false);
        TextBalance.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        TextBalance.setHorizontalAlignment(JTextField.CENTER);
        TabTrans.setDefaultEditor(Object.class, null);
        setData();
        refreshJTable();
        showData(trans);
        EditTableHeightWidth();
    }
    
    public int getBalance(ArrayList<Transaction> tmp){
        int balance = 0;
        for (int i = 0; i < tmp.size(); i++){
            balance += Integer.valueOf(tmp.get(i).getTotal());
        }
        return balance;
    }
    
    public void setData(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();
            
            String sql = "select * from MainAccount";
            ResultSet res = state.executeQuery(sql);
            
            while(res.next()){
                TextAccountID.setText(res.getString("AccountID"));
                TextBalance.setText(res.getString("Balance"));
            }
            
            sql = "select * from Transaction";
            res = state.executeQuery(sql);
            
            while (res.next()){
                Transaction tmp = new Transaction(res.getString("TransactionID"), res.getString("UserID"), res.getString("TransactionTime"), res.getString("Total"));
                trans.add(tmp);
            }

            connect.close();
            
            ArrayList<String> times = new ArrayList<String>();
            for (int i = 0; i < trans.size(); i++){
                String TransactionTime = trans.get(i).getTransactionTime().substring(0,7);
                if (!times.contains(TransactionTime))
                    times.add(TransactionTime);
            }
            ComboBoxFilter.addItem("All");
            for (int i = 0; i < times.size(); i++){
                ComboBoxFilter.addItem(times.get(i));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountStatistic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountStatistic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refreshJTable() {
        DefaultTableModel model = (DefaultTableModel) TabTrans.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }
    
    public void showData(ArrayList<Transaction> tmp) {
        DefaultTableModel model = (DefaultTableModel) TabTrans.getModel();
        Object[] row = new Object[5];

        for (int i = 0; i < tmp.size(); i++) {
            row[0] = i + 1;
            row[1] = tmp.get(i).getTransactionID();
            row[2] = tmp.get(i).getUserID();
            String time = tmp.get(i).getTransactionTime();
            row[3] = time.substring(0, time.length()-2);
            row[4] = tmp.get(i).getTotal();
            model.addRow(row);
        }
    }

    public void EditTableHeightWidth() {
        //edit size of column
        TabTrans.getTableHeader().setFont(new Font("Fredoka One", Font.PLAIN, 16));
        //tmp.getTableHeader().setForeground(jScrollPane2.getBackground());
        final TableColumnModel columnModel = TabTrans.getColumnModel();
        for (int column = 0; column < TabTrans.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < TabTrans.getRowCount(); row++) {
                TableCellRenderer renderer = TabTrans.getCellRenderer(row, column);
                Component comp = TabTrans.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }

        for (int row = 0; row < TabTrans.getRowCount(); row++) {
            int rowHeight = TabTrans.getRowHeight();
            for (int column = 0; column < TabTrans.getColumnCount(); column++) {
                Component comp = TabTrans.prepareRenderer(TabTrans.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
            TabTrans.setRowHeight(row, rowHeight);
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
        TextBalance = new javax.swing.JTextField();
        TextAccountID = new javax.swing.JTextField();
        BtnFilter = new javax.swing.JLabel();
        ComboBoxFilter = new paymentserver.CustomComboBox.ComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabTrans = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackMouseClicked(evt);
            }
        });
        getContentPane().add(BtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 70, 70));

        TextBalance.setFont(new java.awt.Font("Fredoka One", 0, 30)); // NOI18N
        getContentPane().add(TextBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 390, 40));

        TextAccountID.setFont(new java.awt.Font("Fredoka One", 0, 30)); // NOI18N
        getContentPane().add(TextAccountID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 390, 40));

        BtnFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnFilterMouseClicked(evt);
            }
        });
        getContentPane().add(BtnFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1185, 70, 50, 60));

        ComboBoxFilter.setFont(new java.awt.Font("Fredoka One", 0, 18)); // NOI18N
        getContentPane().add(ComboBoxFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, 190, 60));

        TabTrans.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        TabTrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Transaction ID", "User ID", "Transaction Time", "Total"
            }
        ));
        jScrollPane1.setViewportView(TabTrans);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 210, 740, 390));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paymentserver/AccountStatistic/StatisticBackground.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackMouseClicked
        // TODO add your handling code here:
        ServerHomePage hp = new ServerHomePage();
        hp.show();
        dispose();
    }//GEN-LAST:event_BtnBackMouseClicked

    private void BtnFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnFilterMouseClicked
        // TODO add your handling code here:
        String time = (String)ComboBoxFilter.getSelectedItem();
        if (time.equals("All")){
            refreshJTable();
            showData(trans);
            EditTableHeightWidth();
            TextBalance.setText(String.valueOf(getBalance(trans)));
        }
        else{
            ArrayList<Transaction> tmp = new ArrayList<Transaction>();
            for (int i = 0; i < trans.size(); i++){
                String TransactionTime = trans.get(i).getTransactionTime().substring(0,7);
                if (TransactionTime.equals(time)){
                    tmp.add(trans.get(i));
                }
            }
            refreshJTable();
            showData(tmp);
            EditTableHeightWidth();
            TextBalance.setText(String.valueOf(getBalance(tmp)));
        }
    }//GEN-LAST:event_BtnFilterMouseClicked

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
            java.util.logging.Logger.getLogger(AccountStatistic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountStatistic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountStatistic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountStatistic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccountStatistic().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnBack;
    private javax.swing.JLabel BtnFilter;
    private paymentserver.CustomComboBox.ComboBox ComboBoxFilter;
    private javax.swing.JTable TabTrans;
    private javax.swing.JTextField TextAccountID;
    private javax.swing.JTextField TextBalance;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
