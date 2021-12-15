package javacovid19app.ManagedUser.ManagedUserHomePage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javacovid19app.HomePage.*;
import javacovid19app.ManagedUser.ManagedUserSupplies.*;
import javacovid19app.ManagedUser.ManagedUserTransaction.*;

public class ManagedUserHomePage extends javax.swing.JFrame {

    String userID = "";

    public ManagedUserHomePage() {
        initComponents();
        this.setResizable(false); // can not fix size of a Frame;
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }
    
    public ManagedUserHomePage(String username) {
        this.userID=username;
        System.out.println(userID);
        initComponents();
        this.setResizable(false); // can not fix size of a Frame;
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnInfo = new javax.swing.JLabel();
        BtnSupplies = new javax.swing.JLabel();
        BtnTransaction = new javax.swing.JLabel();
        BtnBackSignIn = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnInfoMouseClicked(evt);
            }
        });
        getContentPane().add(BtnInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 240, 50));

        BtnSupplies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSuppliesMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSupplies, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 224, 160, 60));

        BtnTransaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnTransactionMouseClicked(evt);
            }
        });
        getContentPane().add(BtnTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 234, 220, 50));

        BtnBackSignIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackSignInMouseClicked(evt);
            }
        });
        getContentPane().add(BtnBackSignIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 14, 60, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\DELL\\Documents\\GitHub\\JavaCovi19App\\JavaCovid19App\\src\\javacovid19app\\ManagedUser\\ManagedUserHomePage\\ManagedUserHomePage.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnInfoMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Continue...");
    }//GEN-LAST:event_BtnInfoMouseClicked

    private void BtnSuppliesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSuppliesMouseClicked
        // TODO add your handling code here:
        ManagedUserSupplies sup = new ManagedUserSupplies(userID);
        sup.show();
        dispose();
    }//GEN-LAST:event_BtnSuppliesMouseClicked

    private void BtnTransactionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnTransactionMouseClicked
        // TODO add your handling code here:
        ManagedUserTransaction trans = new ManagedUserTransaction(userID);
        trans.show();
        dispose();
    }//GEN-LAST:event_BtnTransactionMouseClicked

    private void BtnBackSignInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackSignInMouseClicked
        try {
            // TODO add your handling code here:
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String logoutTime = formatter.format(date).toString();
            String loginHis = "update LoginHistory set LogoutTime = '" + logoutTime + "' where LogoutTime is null";
            state.executeUpdate(loginHis);
            connect.close();
            
            HomePage homepage = new HomePage();
            JOptionPane.showMessageDialog(this, "Log out the system!");
            homepage.show();
            dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagedUserHomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManagedUserHomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnBackSignInMouseClicked

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
            java.util.logging.Logger.getLogger(ManagedUserHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagedUserHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagedUserHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagedUserHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagedUserHomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnBackSignIn;
    private javax.swing.JLabel BtnInfo;
    private javax.swing.JLabel BtnSupplies;
    private javax.swing.JLabel BtnTransaction;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
