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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javacovid19app.HomePage.HomePage;
import javacovid19app.Manager.ManagerHomePage.DataClasses.Account;
import javacovid19app.Manager.ManagerHomePage.DataClasses.City;
import javacovid19app.Manager.ManagerHomePage.DataClasses.District;
import javacovid19app.Manager.ManagerHomePage.DataClasses.Location;
import javacovid19app.Manager.ManagerHomePage.DataClasses.ManagedUser;
import javacovid19app.Manager.ManagerHomePage.DataClasses.TreatmentFacility;
import javacovid19app.Manager.ManagerHomePage.DataClasses.TreatmentHistory;
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
    private ArrayList <TreatmentFacility> treatmentFacilityList = new ArrayList<>();
    private ArrayList <TreatmentHistory> treatmentHistotyList = new ArrayList<>(); 
    
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
        getTreatmentHistoryList();
        
        
        //chart statistic about disease, recover , treamenting.
        polarStatusChart();
       
        // bar chart with status city data.
        StatusCityBarChart();
        
        // polar chart consume nessaries.
        polarConsumeChart();
        //
        LoanBarChart();
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
        PolarFStatusChart.addItem(new ModelPolarAreaChart (new Color (245, 163, 39), "Involved", still_treament));
        PolarFStatusChart.addItem(new ModelPolarAreaChart (new Color (65, 181, 94), "Recover", recover));
        PolarFStatusChart.start();
    }
    
    public int getComsumeHistoryByType(int type){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select COUNT(ConsumeHistory.NecessariesID) AS NumberOfNess from ConsumeHistory, Necessaries where ConsumeHistory.NecessariesID = Necessaries.NecessariesID and Necessaries.Type = '"+type+"'";
            ResultSet res = state.executeQuery(sql);
            String num = "";
            while(res.next()){
                num = res.getString("NumberOfNess");
            }
            connect.close();
            return Integer.valueOf(num);
           }catch(Exception e){
           System.out.println(e.getMessage());
           return 0;
        }
    }
    
    public void polarConsumeChart(){
        int instant = getComsumeHistoryByType(1);
        int fruit = getComsumeHistoryByType(2);
        int essential = getComsumeHistoryByType(3);
        polarConsumeNess.addItem(new ModelPolarAreaChart (new Color (252, 186, 3), "Instant", instant));
        polarConsumeNess.addItem(new ModelPolarAreaChart (new Color (224, 99, 99), "Fruit", fruit));
        polarConsumeNess.addItem(new ModelPolarAreaChart (new Color (85, 109, 173), "Essential", essential)); 
        polarConsumeNess.start();
    }
  
    
    
    public String getStatus(String ID){
        for (int i = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(ID) == 0){
                return this.managedUserList.get(i).getCurrentStatus();
            }
        }
        return "";
    }
    //calculate number people in a city.
    public int numberPeopleStatusInQuarter(ArrayList <TreatmentHistory> treatmentHistotyList, int quarter, String status){
        int count = 0;
        for (int i = 0; i < treatmentHistotyList.size(); i++){
           String[] time = treatmentHistotyList.get(i).getArriveTime().split("-");
           String month = new String (time[1]);
           String currentStatus = getStatus(treatmentHistotyList.get(i).getUserID());
         
           if ((quarter == ((Integer.valueOf(month) - 1) / 3) + 1) && status.compareTo(currentStatus)==0){
               count++;
           }
           
        }
        return count;
    }
    
    public void StatusCityBarChart(){
        barChartCity.addLegend("F0", new Color(95, 199, 182));
        barChartCity.addLegend("F1", new Color(69, 106, 161));
        barChartCity.addLegend("F2", new Color(87, 69, 161));
        barChartCity.addLegend("F3", new Color(161, 69, 124));
        String quater [] = {"Quarter 1", "Quarter 2", "Quarter 3", "Quarter 4"};
        for (int i = 1; i < 5; i++){
            int f0 = numberPeopleStatusInQuarter(this.treatmentHistotyList, i, "F0");
            int f1 = numberPeopleStatusInQuarter(this.treatmentHistotyList, i, "F1");
            int f2 = numberPeopleStatusInQuarter(this.treatmentHistotyList, i, "F2");
            int f3 = numberPeopleStatusInQuarter(this.treatmentHistotyList, i, "F3");
            barChartCity.addData(new ModelChart(quater[i-1], new double[]{f0, f1, f2, f3}));
        }
        barChartCity.start();
    }
    
        public void LoanBarChart(){
        barChartLoan.addLegend("0", new Color(129, 204, 191));
        barChartLoan.addLegend("<100K", new Color(193, 129, 204));
        barChartLoan.addLegend("100K->200K", new Color(227, 70, 138));
        barChartLoan.addLegend("200K->300K", new Color(255, 15, 15));
        String quater [] = {"Loan Amount Bar Chart"};
      
        int noLoan = getLoanCountByRange(1);
        int loan100 = getLoanCountByRange(2);
        int loan200 = getLoanCountByRange(3);
        int loan300 = getLoanCountByRange(4);
        barChartLoan.addData(new ModelChart(quater[0], new double[]{noLoan, loan100, loan200, loan300}));
        barChartLoan.start();
    }
    
    public int getLoanCountByRange(int type){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();
            if(type == 1){
                String sql = "Select COUNT(ManagedUser.UserID) AS NumberOfUser from ManagedUser where ManagedUser.Loan = 0";
                ResultSet res = state.executeQuery(sql);
                String num = "";
                while(res.next()){
                    num = res.getString("NumberOfUser");
                }
                connect.close();
                return Integer.valueOf(num);
            }
            else if(type == 2){
                String sql = "Select COUNT(ManagedUser.UserID) AS NumberOfUser from ManagedUser where ManagedUser.Loan BETWEEN 1 AND 100000";
                ResultSet res = state.executeQuery(sql);
                String num = "";
                while(res.next()){
                    num = res.getString("NumberOfUser");
                }
                connect.close();
                return Integer.valueOf(num);
            }
            else if(type == 3){
                String sql = "Select COUNT(ManagedUser.UserID) AS NumberOfUser from ManagedUser where ManagedUser.Loan BETWEEN 100001 AND 200000";
                ResultSet res = state.executeQuery(sql);
                String num = "";
                while(res.next()){
                    num = res.getString("NumberOfUser");
                }
                connect.close();
                return Integer.valueOf(num);
            }
            else if(type == 4){
                String sql = "Select COUNT(ManagedUser.UserID) AS NumberOfUser from ManagedUser where ManagedUser.Loan BETWEEN 200000 AND 300000";
                ResultSet res = state.executeQuery(sql);
                String num = "";
                while(res.next()){
                    num = res.getString("NumberOfUser");
                }
                connect.close();
                return Integer.valueOf(num);
            }
           }catch(Exception e){
                System.out.println(e.getMessage());
                return 0;
            }
        return 0;
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
    
    //get TreatmentHistory List
    public void getTreatmentHistoryList(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select * from TreatmentHistory ";
            ResultSet res = state.executeQuery(sql);
            
            TreatmentHistory tmp;
            while(res.next()){
                tmp = new TreatmentHistory (res.getString("UserID"), res.getString("ArriveTime"),res.getString("FacilityID"), res.getString("LeaveTime")); 
                this.treatmentHistotyList.add(tmp);
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
        BtnLogout = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        polarConsumeNess = new PolarChart.PolarAreaChart();
        jLabel4 = new javax.swing.JLabel();
        barChartLoan = new BarChartAnimation.BarChart();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PolarFStatusChart.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        PolarFStatusChart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PolarFStatusChartMouseClicked(evt);
            }
        });
        getContentPane().add(PolarFStatusChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 270, 250));

        barChartCity.setOpaque(false);
        barChartCity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barChartCityMouseClicked(evt);
            }
        });
        getContentPane().add(barChartCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 100, 480, 260));

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

        BtnLogout.setText(" ");
        BtnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnLogoutMouseClicked(evt);
            }
        });
        getContentPane().add(BtnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, 60, 60));

        jLabel2.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("COVID STATUS POLAR CHART");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 220, 30));

        jLabel3.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("BAR CHART: COVID STATUS BY TIME PERIOD");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 360, 330, -1));

        polarConsumeNess.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        polarConsumeNess.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                polarConsumeNessMouseClicked(evt);
            }
        });
        getContentPane().add(polarConsumeNess, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, 240, 210));

        jLabel4.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NECESSARIES CONSUME POLAR CHART");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 640, -1, -1));

        barChartLoan.setOpaque(false);
        barChartLoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barChartLoanMouseClicked(evt);
            }
        });
        getContentPane().add(barChartLoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 410, 430, 260));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/Manager/ManagerHomePage/ManagerHomeBackground.png"))); // NOI18N
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

    private void BtnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnLogoutMouseClicked
        // TODO add your handling code here:
        try {
                //use SQL Query to update admin password.
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state = connect.createStatement();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String logoutTime = formatter.format(date).toString();
                String loginHis = "update LoginHistory set LogoutTime = '"+logoutTime+"' where LogoutTime is null";
                state.executeUpdate(loginHis);
                connect.close();
                JOptionPane.showMessageDialog(this, "Log out the system!");
                
                //back to homepage choose actor.
                HomePage home = new HomePage();
                home.show();
                dispose();
                
                
            }catch(Exception e){
            System.out.println(e.getMessage());
            }
    }//GEN-LAST:event_BtnLogoutMouseClicked

    private void polarConsumeNessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_polarConsumeNessMouseClicked
        // TODO add your handling code here:
        polarConsumeNess.start();
    }//GEN-LAST:event_polarConsumeNessMouseClicked

    private void barChartLoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barChartLoanMouseClicked
        // TODO add your handling code here:
        barChartLoan.start();
    }//GEN-LAST:event_barChartLoanMouseClicked

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
    private javax.swing.JLabel BtnLogout;
    private javax.swing.JLabel BtnSupplies;
    private PolarChart.PolarAreaChart PolarFStatusChart;
    private BarChartAnimation.BarChart barChartCity;
    private BarChartAnimation.BarChart barChartLoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private PolarChart.PolarAreaChart polarConsumeNess;
    // End of variables declaration//GEN-END:variables
}
