/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javacovid19app.Admin.AdminHomePage.ManagerAccountsManagement;

import com.mysql.jdbc.PreparedStatement;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import javacovid19app.Admin.AdminHomePage.AdminHomePage;
import javacovid19app.Admin.AdminHomePage.DataClasses.loginHistory;
import javacovid19app.Admin.AdminHomePage.DataClasses.managerAccount;
import static javax.management.remote.JMXConnectorFactory.connect;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author tongt
 */
public class ManagerAccountManagement extends javax.swing.JFrame {

    /**
     * Creates new form ManagerAccountManagement
     */
    public int selectedIndex=0;
    public ArrayList<managerAccount>managerList=new ArrayList<>();
    public ArrayList<loginHistory>loginHistory=new ArrayList<>();
    public ManagerAccountManagement() {
        initComponents();
        TableList.setDefaultEditor(Object.class, null);
        LoginHistory.setDefaultEditor(Object.class, null);
        this.setTitle("Manager Accounts Management");
        this.setResizable(false); // can not fix size of a Frame;
        Username.setFont(new java.awt.Font("Fredoka One", 0, 18)); // NOI18N
        TableList.setFont(new java.awt.Font("Fredoka One", 0, 18)); // NOI18N
        LoginHistory.setFont(new java.awt.Font("Fredoka One", 0, 18)); // NOI18N
        getAccountData();
        TableList.setBackground(new Color (221, 174, 11));
        LoginHistory.setBackground(new Color (221, 174, 11));
        refreshJTable();
        refreshJTableLoginHistory();
        ShowInstantManager();
        TableList.getTableHeader().setFont(new Font("Fredoka One",Font.PLAIN,16));
        LoginHistory.getTableHeader().setFont(new Font("Fredoka One",Font.PLAIN,16));
        
        
        
    }
    public int checkExistUser(String username){
        for (int i = 0; i < this.managerList.size(); i++){
            
            if (this.managerList.get(i).getID().compareTo(username)==0){
                
                return i;
            }
        }
        return -1;
    }
    private void refreshJTable(){
        DefaultTableModel  model = (DefaultTableModel)TableList.getModel();
        while (model.getRowCount()>0){
            model.removeRow(0);
        }
    }
    private void refreshJTableLoginHistory(){
        DefaultTableModel  model = (DefaultTableModel)LoginHistory.getModel();
        while (model.getRowCount()>0){
            model.removeRow(0);
        }
    }
    public void getAccountData(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select UserID, Password from Account where Account.Type=2 ";
            ResultSet res = state.executeQuery(sql);
            
            managerAccount tmp;
            while(res.next()){
                tmp = new managerAccount(res.getString("UserID"),res.getString("Password")); 
                this.managerList.add(tmp);
            }
            connect.close();
            
           }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
    public void ShowInstantManager(){
        
        DefaultTableModel model = (DefaultTableModel)TableList.getModel();
        Object[] row = new Object [2];
        
        for (int i = 0; i < this.managerList.size(); i++){
            
                row[0] = managerList.get(i).getID();
                row[1] = managerList.get(i).getPassword();
                model.addRow(row);
            
        }
    }
    public void ShowLoginHistory(){
        refreshJTableLoginHistory();
        try{        
            String username = Username.getText();
            this.loginHistory=new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");             
            String sql="Select * from LoginHistory where LoginHistory.UserID='"+username+"'";

            Statement state = connection.createStatement();                       
            ResultSet res = state.executeQuery(sql);
            loginHistory tmp;

            while(res.next()){
                tmp=new loginHistory(res.getString("LoginTime"),res.getString("LogoutTime"));                       
                this.loginHistory.add(tmp);
            }         
            Collections.reverse(this.loginHistory);

            DefaultTableModel model = (DefaultTableModel)LoginHistory.getModel();
            Object[] row = new Object [2];

            for(int i = 0; i < loginHistory.size(); i++){   
                row[0] = loginHistory.get(i).getLoginTime();
                row[1] = loginHistory.get(i).getLogoutTime();
                model.addRow(row);                  
            }
            connection.close();              
        }catch(Exception e){
            
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

        Password = new javax.swing.JPasswordField();
        ConfirmedPassword = new javax.swing.JPasswordField();
        BtnSubmit = new javax.swing.JLabel();
        BtnLock = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableList = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        LoginHistory = new javax.swing.JTable();
        BtnLoginHistory = new javax.swing.JLabel();
        btnTest = new javax.swing.JLabel();
        Username = new javax.swing.JTextField();
        BtnBack = new javax.swing.JLabel();
        BtnRefresh = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Password.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        Password.setForeground(new java.awt.Color(255, 255, 255));
        Password.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        Password.setOpaque(false);
        getContentPane().add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 210, 50));

        ConfirmedPassword.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        ConfirmedPassword.setForeground(new java.awt.Color(255, 255, 255));
        ConfirmedPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        ConfirmedPassword.setOpaque(false);
        getContentPane().add(ConfirmedPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 210, 50));

        BtnSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSubmitMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 560, 80, 70));

        BtnLock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnLockMouseClicked(evt);
            }
        });
        getContentPane().add(BtnLock, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 560, 70, 70));

        TableList.setFont(new java.awt.Font("Fredoka One", 0, 11)); // NOI18N
        TableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UserID", "Password"
            }
        ));
        TableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, 380, 200));

        LoginHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Login Time", "Logout Time"
            }
        ));
        jScrollPane2.setViewportView(LoginHistory);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, 690, 210));

        BtnLoginHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnLoginHistoryMouseClicked(evt);
            }
        });
        getContentPane().add(BtnLoginHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 560, 70, 70));

        btnTest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTestMouseClicked(evt);
            }
        });
        getContentPane().add(btnTest, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, 70, 70));

        Username.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        Username.setForeground(new java.awt.Color(255, 255, 255));
        Username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        Username.setOpaque(false);
        Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameActionPerformed(evt);
            }
        });
        getContentPane().add(Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 210, 50));

        BtnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackMouseClicked(evt);
            }
        });
        getContentPane().add(BtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 50, 60));

        BtnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRefreshMouseClicked(evt);
            }
        });
        getContentPane().add(BtnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 50, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/Admin/AdminHomePage/ManagerAccountsManagement/ManagerAccpuntManagentBackground(1280x720).png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSubmitMouseClicked
        // TODO add your handling code here:
        try{
             Class.forName("com.mysql.jdbc.Driver");
             Connection connection=DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
             String username=Username.getText();
             String password=Password.getText();
             String confirmPassword=ConfirmedPassword.getText();
             if(username.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()){
                 JOptionPane.showMessageDialog(this, "Please input full information !!");
             }
             else if(checkExistUser(username)!=-1){
                 JOptionPane.showMessageDialog(this, "User exist !! Please input again !!");
             }
             else if(!password.equals(confirmPassword)){
                JOptionPane.showMessageDialog(this, "Password does not match !! Please input again !!");
             }
             else{
                String sql="Insert into Account values(?,?,?,?)";
                PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
                String hashPassowrd= BCrypt.hashpw(password, BCrypt.gensalt(4));
                ps.setString(1, username);
                ps.setString(2, hashPassowrd);
                ps.setString(3, "2");
                ps.setString(4, "1");
                int i = ps.executeUpdate();
                if(i>0){
                    managerAccount tmp =new managerAccount(username,hashPassowrd);
                    managerList.add(tmp);
                    refreshJTable();
                    ShowInstantManager();
                    JOptionPane.showMessageDialog(this, "Add new manager account successfully");
                }
             }
                       
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_BtnSubmitMouseClicked

    private void BtnLockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnLockMouseClicked
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            String username=Username.getText();
            String password=Password.getText();
            String confirmPassword=ConfirmedPassword.getText();
            if(username.isEmpty() == true){
                JOptionPane.showMessageDialog(this, "Please input username to deactivate an manager account!");
                return;
            }
            else{
                if(checkExistUser(username)== -1){
                    JOptionPane.showMessageDialog(this, "Username does not exist!");
                    return;
                }
                String sql="Update Account set Status = 0 where UserID='"+this.managerList.get(this.selectedIndex).getID()+"'";
                Statement state = connection.createStatement();                       
                state.execute(sql);
                connection.close();
                JOptionPane.showMessageDialog(this, "Deactivated " + username);
            }
                       
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_BtnLockMouseClicked

    private void TableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableListMouseClicked
        // TODO add your handling code here:
        int index=TableList.getSelectedRow();
        this.selectedIndex=index;     
        Username.setText(managerList.get(index).getID());
    }//GEN-LAST:event_TableListMouseClicked

    private void BtnLoginHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnLoginHistoryMouseClicked
        // TODO add your handling code here:
        refreshJTableLoginHistory();
        ShowLoginHistory();
    }//GEN-LAST:event_BtnLoginHistoryMouseClicked

    private void btnTestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTestMouseClicked
        // TODO add your handling code here:
        String username = Username.getText();
       
        
        if (username.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide username!");
            return;
        }
        
        int index = checkExistUser(username);
        if ( index == -1){
            JOptionPane.showMessageDialog(this, "Not found!");
            return;
        }
        else{
            JOptionPane.showMessageDialog(this, "Found!");
           
            for (int i = 0; i < this.managerList.size(); i++){
                if (this.managerList.get(i).getID().contains(username) == true){
                    break;
                }             
            }
            TableList.setRowSelectionInterval(index, index);
            return;
        }
    }//GEN-LAST:event_btnTestMouseClicked

    private void UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameActionPerformed

    private void BtnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackMouseClicked
        // TODO add your handling code here:
        AdminHomePage homepage= new AdminHomePage();
        homepage.show();
        dispose();
    }//GEN-LAST:event_BtnBackMouseClicked

    private void BtnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRefreshMouseClicked
        // TODO add your handling code here:
        Username.setText("");
        Password.setText("");
        ConfirmedPassword.setText("");
    }//GEN-LAST:event_BtnRefreshMouseClicked

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
            java.util.logging.Logger.getLogger(ManagerAccountManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerAccountManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerAccountManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerAccountManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerAccountManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnBack;
    private javax.swing.JLabel BtnLock;
    private javax.swing.JLabel BtnLoginHistory;
    private javax.swing.JLabel BtnRefresh;
    private javax.swing.JLabel BtnSubmit;
    private javax.swing.JPasswordField ConfirmedPassword;
    private javax.swing.JTable LoginHistory;
    private javax.swing.JPasswordField Password;
    private javax.swing.JTable TableList;
    private javax.swing.JTextField Username;
    private javax.swing.JLabel btnTest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
