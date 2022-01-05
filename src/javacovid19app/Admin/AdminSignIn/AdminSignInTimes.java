/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javacovid19app.Admin.AdminSignIn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javacovid19app.Admin.AdminHomePage.AdminHomePage;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCrypt;


/**
 *
 * @author tongt
 */
public class AdminSignInTimes extends javax.swing.JFrame {

    /**
     * Creates new form AdminSignInTimes
     */
    public AdminSignInTimes() {
        initComponents();
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
        BtnAdminSignIn = new javax.swing.JLabel();
        AdminID = new javax.swing.JTextField();
        AdminPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(BtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 440, 60, 60));

        BtnAdminSignIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAdminSignInMouseClicked(evt);
            }
        });
        getContentPane().add(BtnAdminSignIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 400, 150, 60));

        AdminID.setAutoscrolls(false);
        AdminID.setBorder(null);
        AdminID.setOpaque(false);
        AdminID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminIDActionPerformed(evt);
            }
        });
        getContentPane().add(AdminID, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 210, 350, 40));

        AdminPassword.setBorder(null);
        AdminPassword.setOpaque(false);
        AdminPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(AdminPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 280, 350, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/Admin/AdminSignIn/AdminSignInNTimesBackground.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AdminPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AdminPasswordActionPerformed

    private void BtnAdminSignInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAdminSignInMouseClicked
        // TODO add your handling code here:
        String username=AdminID.getText();
        String password=AdminPassword.getText();
        if(username.isEmpty()||password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide username and password");
        }
        else{
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect=DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state=connect.createStatement();
                String sql="Select Password from Account where UserID='"+username+"' and Type = 1 ";
                ResultSet res=state.executeQuery(sql);
                 if (res.next()){
                    String real_pass = res.getString(1);
                    System.out.println(real_pass);
                    if (BCrypt.checkpw(password, real_pass) == true){
                        JOptionPane.showMessageDialog(this, "Login successfully.");
                        AdminHomePage adminHomePage=new AdminHomePage();
                        adminHomePage.show();
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Wrong username or password!");
                        AdminID.setText("");
                        AdminPassword.setText("");
                    }
                }
                else{
                        JOptionPane.showMessageDialog(this, "Wrong username or password!");
                        AdminID.setText("");
                        AdminPassword.setText("");
                    }
                
                
            }catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_BtnAdminSignInMouseClicked

    private void AdminIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AdminIDActionPerformed

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
            java.util.logging.Logger.getLogger(AdminSignInTimes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminSignInTimes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminSignInTimes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminSignInTimes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminSignInTimes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AdminID;
    private javax.swing.JPasswordField AdminPassword;
    private javax.swing.JLabel BtnAdminSignIn;
    private javax.swing.JLabel BtnBack;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}