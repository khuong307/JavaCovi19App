package javacovid19app.ManagedUser.ManagedUserTransaction;

import java.awt.Component;
import java.awt.Font;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javacovid19app.ManagedUser.ManagedUserHomePage.*;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

class Transaction{
    private String TransactionID;
    private String TransactionTime;
    private String Total;
    
    Transaction(String TransactionID, String TransactionTime, String Total){
        this.TransactionID = TransactionID;
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
}

public class ManagedUserTransaction extends javax.swing.JFrame {
    private String userID = "";
    private int loan = 0;
    private int balance = 0;
    private String ReceivedAccount = "6380220027134";
    private ArrayList<Transaction> trans = new ArrayList<Transaction>();
    
    public ManagedUserTransaction() {
        initComponents();
    }
    
    public ManagedUserTransaction(String username) {
        this.userID = username;
        initComponents();
        TextBalance.setOpaque(false);
        TextBalance.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        TextBalance.setEditable(false);
        TextLoan.setOpaque(false);
        TextLoan.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        TextLoan.setEditable(false);
        setData();
        TextBalance.setText(String.valueOf(balance));
        TextLoan.setText(String.valueOf(loan));
        refreshJTable();
        showData();
        EditTableHeightWidth();
    }
    
    public void setData(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select * from Transaction where UserID = " + userID;
            ResultSet res = state.executeQuery(sql);

            while (res.next()) {
                Transaction tmp = new Transaction(res.getString("TransactionID"), res.getString("TransactionTime"), res.getString("Total"));
                trans.add(tmp);
            }
            
            sql = "Select * from ManagedUser where UserID = " + userID;
            res = state.executeQuery(sql);
            
            while (res.next()){
                loan = res.getInt("Loan");
                balance = res.getInt("Balance");
            }

            connect.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagedUserTransaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManagedUserTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refreshJTable() {
        DefaultTableModel model = (DefaultTableModel) TabTrans.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }
    
    public void showData() {
        DefaultTableModel model = (DefaultTableModel) TabTrans.getModel();
        Object[] row = new Object[5];

        for (int i = 0; i < trans.size(); i++) {
            row[0] = i + 1;
            row[1] = trans.get(i).getTransactionID();
            String time = trans.get(i).getTransactionTime();
            row[2] = time.substring(0, time.length()-2);
            row[3] = this.ReceivedAccount;
            row[4] = trans.get(i).getTotal();
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
        TextLoan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabTrans = new javax.swing.JTable();
        BtnConfirm = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackMouseClicked(evt);
            }
        });
        getContentPane().add(BtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 24, 70, 80));

        TextBalance.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        getContentPane().add(TextBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 390, 40));

        TextLoan.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        getContentPane().add(TextLoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 390, 40));

        TabTrans.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        TabTrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "ID", "Time", "Received Account", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TabTrans);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 210, 720, 380));

        BtnConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnConfirmMouseClicked(evt);
            }
        });
        getContentPane().add(BtnConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 640, 260, 60));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/ManagedUser/ManagedUserTransaction/ManagedUserTransaction.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackMouseClicked
        // TODO add your handling code here:
        ManagedUserHomePage home = new ManagedUserHomePage(userID);
        home.show();
        dispose();
    }//GEN-LAST:event_BtnBackMouseClicked

    private void BtnConfirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnConfirmMouseClicked
        try {                                        
            // TODO add your handling code here:
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();
            
            String sql = "select * from ManagedUser where UserID = '" + userID + "'";
            ResultSet res = state.executeQuery(sql);
            
            int val = 0;
            while (res.next()){
                val = res.getInt("isPay");
            }
            connect.close();
            
            if (val == 0){
                JOptionPane.showMessageDialog(this, "This account need to create an banking account");
            }
            
            else{
                InetAddress ip = InetAddress.getByName("localhost");
            
                Socket s = new Socket(ip, 1234);

                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                DataInputStream din = new DataInputStream(s.getInputStream());
                dos.writeUTF(userID + "-" + loan);
                
                String message = din.readUTF();
                System.out.println(message);
                JOptionPane.showMessageDialog(this, message);
                din.close();
                dos.close();
                s.close();
                
                ManagedUserHomePage hp = new ManagedUserHomePage(userID);
                hp.show();
                dispose();
            }

        } catch (UnknownHostException ex) {
            Logger.getLogger(ManagedUserTransaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagedUserTransaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagedUserTransaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManagedUserTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnConfirmMouseClicked

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
            java.util.logging.Logger.getLogger(ManagedUserTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagedUserTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagedUserTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagedUserTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagedUserTransaction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnBack;
    private javax.swing.JLabel BtnConfirm;
    private javax.swing.JTable TabTrans;
    private javax.swing.JTextField TextBalance;
    private javax.swing.JTextField TextLoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
