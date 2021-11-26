/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacovid19app.ManagedUser.ManagedUserHomePage.Infomation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javacovid19app.HomePage.HomePage;
import javacovid19app.ManagedUser.ManagedUserHomePage.Infomation.CovidHistory.CovidHistoryMenu;
import javacovid19app.ManagedUser.ManagedUserHomePage.Infomation.Treatment.UserTreatmentMenu;
import javacovid19app.ManagedUser.ManagedUserHomePage.ManagedUserHomePage;
import javacovid19app.Manager.ManagerHomePage.DataClasses.City;
import javacovid19app.Manager.ManagerHomePage.DataClasses.District;
import javacovid19app.Manager.ManagerHomePage.DataClasses.Location;
import javacovid19app.Manager.ManagerHomePage.DataClasses.ManagedUser;
import javacovid19app.Manager.ManagerHomePage.DataClasses.TreatmentFacility;
import javacovid19app.Manager.ManagerHomePage.DataClasses.Ward;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class InformationMenu extends javax.swing.JFrame {

    private ArrayList <Ward> wardList = new ArrayList<>(); 
    private ArrayList <City> cityList = new ArrayList<>(); 
    private ArrayList <District> districtList = new ArrayList<>(); 
    private ArrayList <Location> locateList = new ArrayList<>(); 
    private ArrayList <ManagedUser> managedUserList = new ArrayList<>(); 
    private ArrayList <TreatmentFacility> treatmentFacilityList = new ArrayList<>();
    
    
    
    String userID="";
    public InformationMenu() {
        initComponents();
        this.setResizable(false); // can not fix size of a Frame;
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
        this.setTitle("Covid 19 User Information");
        getArrayWardList();
        getArrayCityList();
        getArrayDistrictList();
        getArrayLocationList();
        getFacilityList();
        
        getManagedUserList();
       
    }
    
    public InformationMenu(String username){
        this.userID=username;
        System.out.println(userID);
        initComponents();
        this.setResizable(false); // can not fix size of a Frame;
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
        personalIDTextField.setEditable(false);
        fullNameTextField.setEditable(false);
        yobTextField.setEditable(false);
        cityTextField.setEditable(false);
        districtTextField.setEditable(false);
        wardTextField.setEditable(false);
        statusTextField.setEditable(false);
        balanceTextField.setEditable(false);
        relatedTextField.setEditable(false);
        
        this.setTitle("Covid 19 User Information");
        getArrayWardList();
        getArrayCityList();
        getArrayDistrictList();
        getArrayLocationList();
        getFacilityList();
        
        getManagedUserList();
        
        for(int i=0;i<managedUserList.size();i++){
            if(managedUserList.get(i).getID().compareTo(userID)==0){
                personalIDTextField.setText(managedUserList.get(i).getID());
                fullNameTextField.setText(managedUserList.get(i).getFullname());
                yobTextField.setText(managedUserList.get(i).getYOB());
                cityTextField.setText(managedUserList.get(i).getCityName(cityList,managedUserList.get(i).getCity().getCityID()));
                districtTextField.setText(managedUserList.get(i).getDistrictName(districtList, managedUserList.get(i).getDistrict().getDistrictID()));
                wardTextField.setText(managedUserList.get(i).getWardName(wardList,managedUserList.get(i).getWard().getWardID()));
                statusTextField.setText(managedUserList.get(i).getCurrentStatus());
                int balanceValue=managedUserList.get(i).getBalance();
                balanceTextField.setText(String.valueOf(balanceValue));
                
                // Traverse all managedUSerList to find involved person
                for(int j=0;j<managedUserList.size();j++){
                    if(managedUserList.get(j).getID().compareTo(managedUserList.get(i).getInvolvedID())==0){
                        String involvedPerson=managedUserList.get(j).getFullname();
                        relatedTextField.setText(involvedPerson);
                    }
                }
            }
        }
        
        
    }
    
    
    // get Facility data
    public void getFacilityList(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select * from TreatmentFacility ";
            ResultSet res = state.executeQuery(sql);
            
            TreatmentFacility tmp;
            while(res.next()){
                tmp = new TreatmentFacility (res.getString("FacilityID"), res.getString("Name"),res.getString("Quantity"), res.getString("PresentQuantity")); 
                this.treatmentFacilityList.add(tmp);
            }
            connect.close();
           }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
    
    // get ManagedUser data
    public void getManagedUserList(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select ManagedUser.* from ManagedUser, Account where ManagedUser.UserID = Account.UserID and Account.Status = 1";
            ResultSet res = state.executeQuery(sql);
            
            ManagedUser tmp;
            while(res.next()){
                tmp = new ManagedUser (res.getString("UserID"), res.getString("Fullname"),res.getString("YOB"), res.getString("CurrentStatus"),  res.getString("FacilityID"), res.getString("InvolvedPerson"), res.getString("LocationID"), res.getString("Balance"),  this.cityList, this.wardList, this.districtList, this.locateList, this.treatmentFacilityList); 
                this.managedUserList.add(tmp);
            }
            connect.close();
           }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
    
    // get data from Ward table.
    public void getArrayLocationList(){
        try {
                //use SQL Query to update admin password.
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state = connect.createStatement();

                String sql = "Select * from Location";
                ResultSet res = state.executeQuery(sql);
                
                Location tmp;
                while (res.next()){
                    tmp = new Location (res.getString("LocationID"), res.getString("CityID"),res.getString("DistrictID"), res.getString("WardID"));
                    this.locateList.add(tmp);
                }
                connect.close();
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
    }
    
    // get data from Ward table.
    public void getArrayWardList(){
        try {
                //use SQL Query to update admin password.
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state = connect.createStatement();

                String sql = "Select * from Ward";
                ResultSet res = state.executeQuery(sql);
                
                Ward tmp;
                while (res.next()){
                    tmp = new Ward (res.getString("WardID"), res.getString("Name"),res.getString("DistrictID"));
                    this.wardList.add(tmp);
                }
                connect.close();
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
    }
    
    //get data from City table.
    public void getArrayCityList(){
        try {
                //use SQL Query to update admin password.
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state = connect.createStatement();

                String sql = "Select * from City";
                ResultSet res = state.executeQuery(sql);
                
                City tmp;
                while (res.next()){
                    tmp = new City (res.getString("CityID"), res.getString("Name"));
                    this.cityList.add(tmp);
                }
                connect.close();
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
    }
    //get data from City table.
    public void getArrayDistrictList(){
        try {
                //use SQL Query to update admin password.
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state = connect.createStatement();

                String sql = "Select * from District";
                ResultSet res = state.executeQuery(sql);
                
                District tmp;
                while (res.next()){
                    tmp = new District (res.getString("DistrictID"), res.getString("Name"), res.getString("CityID"));
                    this.districtList.add(tmp);
                }
                connect.close();
        }catch(Exception e){
        System.out.println(e.getMessage());
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

        consumeLabel = new javax.swing.JLabel();
        transactionLabel = new javax.swing.JLabel();
        treatmentLabel = new javax.swing.JLabel();
        covidLabel = new javax.swing.JLabel();
        logOutLabel = new javax.swing.JLabel();
        backLabel = new javax.swing.JLabel();
        personalIDTextField = new javax.swing.JTextField();
        yobTextField = new javax.swing.JTextField();
        cityTextField = new javax.swing.JTextField();
        districtTextField = new javax.swing.JTextField();
        wardTextField = new javax.swing.JTextField();
        statusTextField = new javax.swing.JTextField();
        balanceTextField = new javax.swing.JTextField();
        relatedTextField = new javax.swing.JTextField();
        fullNameTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        consumeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                consumeLabelMouseClicked(evt);
            }
        });
        getContentPane().add(consumeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 190, 190, 230));

        transactionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transactionLabelMouseClicked(evt);
            }
        });
        getContentPane().add(transactionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 190, 190, 240));

        treatmentLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treatmentLabelMouseClicked(evt);
            }
        });
        getContentPane().add(treatmentLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 450, 190, 240));

        covidLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                covidLabelMouseClicked(evt);
            }
        });
        getContentPane().add(covidLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 450, 190, 230));

        logOutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOutLabelMouseClicked(evt);
            }
        });
        getContentPane().add(logOutLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, 60, 60));

        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backLabelMouseClicked(evt);
            }
        });
        getContentPane().add(backLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 50, 40));

        personalIDTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        personalIDTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(personalIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 230, 30));

        yobTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        yobTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(yobTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 230, 30));

        cityTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        cityTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(cityTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 369, 230, 30));

        districtTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        districtTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(districtTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 230, 40));

        wardTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        wardTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(wardTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 480, 230, 30));

        statusTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        statusTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(statusTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 540, 230, 30));

        balanceTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        balanceTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(balanceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 589, 230, 30));

        relatedTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        relatedTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(relatedTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 639, 230, 30));

        fullNameTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        fullNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(fullNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 269, 230, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ASUS\\Desktop\\JavaCovi19App-Khang\\JavaCovid19App\\src\\javacovid19app\\ManagedUser\\ManagedUserHomePage\\Infomation\\InformationManagedUserBackground.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLabelMouseClicked
        ManagedUserHomePage homepage = new ManagedUserHomePage(userID);                
        homepage.show();
        dispose();
    }//GEN-LAST:event_backLabelMouseClicked

    private void logOutLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutLabelMouseClicked
        JOptionPane.showMessageDialog(this, "Log out the system!");
        HomePage homepage = new HomePage();
        homepage.show();
        dispose();
    }//GEN-LAST:event_logOutLabelMouseClicked

    private void treatmentLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treatmentLabelMouseClicked
        UserTreatmentMenu userTreatmentMenu=new UserTreatmentMenu(userID);
        userTreatmentMenu.show();
    }//GEN-LAST:event_treatmentLabelMouseClicked

    private void consumeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consumeLabelMouseClicked
        JOptionPane.showMessageDialog(this, "continue....");
    }//GEN-LAST:event_consumeLabelMouseClicked

    private void covidLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_covidLabelMouseClicked
        CovidHistoryMenu covidMenu=new CovidHistoryMenu(userID,managedUserList);
        covidMenu.show();
    }//GEN-LAST:event_covidLabelMouseClicked

    private void transactionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionLabelMouseClicked
        JOptionPane.showMessageDialog(this, "continue....");
    }//GEN-LAST:event_transactionLabelMouseClicked

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
            java.util.logging.Logger.getLogger(InformationMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformationMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformationMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformationMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformationMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backLabel;
    private javax.swing.JTextField balanceTextField;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JLabel consumeLabel;
    private javax.swing.JLabel covidLabel;
    private javax.swing.JTextField districtTextField;
    private javax.swing.JTextField fullNameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel logOutLabel;
    private javax.swing.JTextField personalIDTextField;
    private javax.swing.JTextField relatedTextField;
    private javax.swing.JTextField statusTextField;
    private javax.swing.JLabel transactionLabel;
    private javax.swing.JLabel treatmentLabel;
    private javax.swing.JTextField wardTextField;
    private javax.swing.JTextField yobTextField;
    // End of variables declaration//GEN-END:variables
}
