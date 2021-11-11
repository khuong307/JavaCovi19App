/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javacovid19app.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javacovid19app.HomePage.HomePage;
import javacovid19app.Manager.ManagerHomePage.ManagerHomePage;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author IVS-P0005
 */
public class ManagerSignIn extends javax.swing.JFrame {

    /**
     * Creates new form ManagerSignIn
     */
    public ManagerSignIn() {
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

        ManagerID = new javax.swing.JTextField();
        ManagerPassword = new javax.swing.JPasswordField();
        BtnManagerSignin = new javax.swing.JLabel();
        BtnBack = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manager Sign In");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ManagerID.setFont(new java.awt.Font("Fredoka One", 0, 18)); // NOI18N
        ManagerID.setForeground(new java.awt.Color(0, 0, 51));
        ManagerID.setBorder(null);
        ManagerID.setOpaque(false);
        getContentPane().add(ManagerID, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 350, 30));

        ManagerPassword.setFont(new java.awt.Font("Fredoka One", 0, 18)); // NOI18N
        ManagerPassword.setForeground(new java.awt.Color(0, 0, 51));
        ManagerPassword.setBorder(null);
        ManagerPassword.setOpaque(false);
        getContentPane().add(ManagerPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 350, 30));

        BtnManagerSignin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnManagerSigninMouseClicked(evt);
            }
        });
        getContentPane().add(BtnManagerSignin, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 380, 150, 60));

        BtnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackMouseClicked(evt);
            }
        });
        getContentPane().add(BtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 450, 60, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/Manager/ManagerSignInBackground.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnManagerSigninMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnManagerSigninMouseClicked
        // TODO add your handling code here:
        String username = ManagerID.getText();
        String password = ManagerPassword.getText();
        
        if (username.isEmpty() && password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide username and password!");
        }
        else{
            try {
                //use SQL Query to update admin password.
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state = connect.createStatement();

                String sql = "Select Password from Account where UserID = '"+username+"' and Type = 2";
                ResultSet res = state.executeQuery(sql);

                if (res.next()){
                    String real_pass = res.getString(1);
                    System.out.println(real_pass);
                    if (BCrypt.checkpw(password, real_pass) == true){
                        JOptionPane.showMessageDialog(this, "Login successfully.");
                        ManagerHomePage managerHomepage = new ManagerHomePage();
                        managerHomepage.show();
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Wrong username or password!");
                        ManagerID.setText("");
                        ManagerPassword.setText("");
                    }
                }
                else{
                        JOptionPane.showMessageDialog(this, "Wrong username or password!");
                        ManagerID.setText("");
                        ManagerPassword.setText("");
                    }
                connect.close();
            }catch(Exception e){
            System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_BtnManagerSigninMouseClicked

    private void BtnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackMouseClicked
        // TODO add your handling code here:
        dispose();
        HomePage home = new HomePage();
        home.show();
    }//GEN-LAST:event_BtnBackMouseClicked

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
            java.util.logging.Logger.getLogger(ManagerSignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerSignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerSignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerSignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerSignIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnBack;
    private javax.swing.JLabel BtnManagerSignin;
    private javax.swing.JTextField ManagerID;
    private javax.swing.JPasswordField ManagerPassword;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
