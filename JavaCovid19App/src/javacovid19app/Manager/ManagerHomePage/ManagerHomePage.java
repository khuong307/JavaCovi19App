/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javacovid19app.Manager.ManagerHomePage;

import BarChartAnimation.ModelChart;
import PolarChart.ModelPolarAreaChart;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javacovid19app.Manager.ManagerHomePage.DataClasses.Account;
import javacovid19app.Manager.ManagerHomePage.DataClasses.City;
import javacovid19app.Manager.ManagerHomePage.DataClasses.District;
import javacovid19app.Manager.ManagerHomePage.DataClasses.Location;
import javacovid19app.Manager.ManagerHomePage.DataClasses.ManagedUser;
import javacovid19app.Manager.ManagerHomePage.DataClasses.TreatmentFacility;
import javacovid19app.Manager.ManagerHomePage.DataClasses.Ward;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author IVS-P0005
 */
public class ManagerHomePage extends javax.swing.JFrame {

    /**
     * Creates new form ManagerHomePage
     */
    private ArrayList <Ward> wardList = new ArrayList<>(); 
    private ArrayList <City> cityList = new ArrayList<>(); 
    private ArrayList <District> districtList = new ArrayList<>(); 
    private ArrayList <Location> locateList = new ArrayList<>(); 
    private ArrayList <ManagedUser> managedUserList = new ArrayList<>(); 
    private ArrayList <Account> accountList = new ArrayList<>(); 
    ArrayList <TreatmentFacility> treatmentFacilityList = new ArrayList<>();
    public ManagerHomePage() {
        initComponents();
        this.setTitle("Manager HomePage");
        getArrayWardList();
        getArrayCityList();
        getArrayDistrictList();
        getArrayLocationList();
        getFacilityList();
        
        getManagedUserList();
        getAccountList();
        
        
        //chart statistic about disease, recover , treamenting.
        polarStatusChart();
       
        // bar chart with status city data.
        StatusCityBarChart();
    }
    
    //set up for Polar F status chart
     //calculate who reocver, who disease
    public int calcStatus(String status){
        int count = 0;
        for (int i = 0; i < this.accountList.size(); i++){
            if (this.accountList.get(i).getStatus().compareTo(status) == 0){
                count++;
            }
        }
        return count;
    }
    public void polarStatusChart(){
        int disease = calcStatus("0");
        int still_treament = calcStatus("1");
        int recover = calcStatus("2");
        PolarFStatusChart.addItem(new ModelPolarAreaChart (new Color (181, 14, 20), "Disease", disease));
        PolarFStatusChart.addItem(new ModelPolarAreaChart (new Color (240, 219, 84), "Treament", still_treament));
        PolarFStatusChart.addItem(new ModelPolarAreaChart (new Color (65, 181, 94), "Recover", recover));
        PolarFStatusChart.start();
    }
  
    
    
    //calculate number people in a city.
    public int numberPeopleStatusInCity(ArrayList <ManagedUser> managedUserList, String CityName, String status){
        int count = 0;
        for (int i = 0; i < managedUserList.size(); i++){
            if (managedUserList.get(i).getCity().getName().compareTo(CityName) == 0 && managedUserList.get(i).getCurrentStatus().compareTo(status) == 0){
                count++;
            }
        }
        return count;
    }
    
    public void StatusCityBarChart(){
        barChartCity.addLegend("F0", new Color(245, 189, 135));
        barChartCity.addLegend("F1", new Color(135, 189, 245));
        barChartCity.addLegend("F2", new Color(189, 135, 245));
        barChartCity.addLegend("F3", new Color(139, 229, 222));
        
        for (int i = 0; i < this.cityList.size(); i++){
            int f0 = numberPeopleStatusInCity(this.managedUserList, cityList.get(i).getName(), "F0");
            int f1 = numberPeopleStatusInCity(this.managedUserList, cityList.get(i).getName(), "F1");
            int f2 = numberPeopleStatusInCity(this.managedUserList, cityList.get(i).getName(), "F2");
            int f3 = numberPeopleStatusInCity(this.managedUserList, cityList.get(i).getName(), "F3");
            barChartCity.addData(new ModelChart(cityList.get(i).getName(), new double[]{f0, f1, f2, f3}));
        }
        barChartCity.start();
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
    public void getAccountList(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select UserID, Status from Account Where Type = 3 ";
            ResultSet res = state.executeQuery(sql);
            
            Account tmp;
            while(res.next()){
                tmp = new Account (res.getString("UserID"), res.getString("Status")); 
                this.accountList.add(tmp);
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

            String sql = "Select * from ManagedUser ";
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

        PolarFStatusChart = new PolarChart.PolarAreaChart();
        barChartCity = new BarChartAnimation.BarChart();
        BtnInvolvePeopleFeature = new javax.swing.JLabel();
        BtnSupplies = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PolarFStatusChart.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        PolarFStatusChart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PolarFStatusChartMouseClicked(evt);
            }
        });
        getContentPane().add(PolarFStatusChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 290, 270));

        barChartCity.setOpaque(false);
        barChartCity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barChartCityMouseClicked(evt);
            }
        });
        getContentPane().add(barChartCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 610, 290));

        BtnInvolvePeopleFeature.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnInvolvePeopleFeatureMouseClicked(evt);
            }
        });
        getContentPane().add(BtnInvolvePeopleFeature, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 220, 60));

        BtnSupplies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSuppliesMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSupplies, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 630, 140, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/Manager/ManagerHomePage/ManagerHomePageBackground.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PolarFStatusChartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PolarFStatusChartMouseClicked
        // TODO add your handling code here:
        PolarFStatusChart.start();
    }//GEN-LAST:event_PolarFStatusChartMouseClicked

    private void barChartCityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barChartCityMouseClicked
        // TODO add your handling code here:
        barChartCity.start();
    }//GEN-LAST:event_barChartCityMouseClicked

    private void BtnInvolvePeopleFeatureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnInvolvePeopleFeatureMouseClicked
        // TODO add your handling code here:
        ManagerCovid19InvolvedPeople involve = new ManagerCovid19InvolvedPeople();
        involve.show();
        dispose();
    }//GEN-LAST:event_BtnInvolvePeopleFeatureMouseClicked

    private void BtnSuppliesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSuppliesMouseClicked
        // TODO add your handling code here:
        ManagerSupplies supplies = new ManagerSupplies();
        supplies.show();
        dispose();
    }//GEN-LAST:event_BtnSuppliesMouseClicked

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
            java.util.logging.Logger.getLogger(ManagerHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerHomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnInvolvePeopleFeature;
    private javax.swing.JLabel BtnSupplies;
    private PolarChart.PolarAreaChart PolarFStatusChart;
    private BarChartAnimation.BarChart barChartCity;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
