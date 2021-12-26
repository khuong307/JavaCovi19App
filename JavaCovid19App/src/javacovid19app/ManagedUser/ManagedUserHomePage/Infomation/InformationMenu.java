package javacovid19app.ManagedUser.ManagedUserHomePage.Infomation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javacovid19app.HomePage.HomePage;
import javacovid19app.ManagedUser.ManagedUserHomePage.Infomation.Consume.ConsumeMenu;
import javacovid19app.ManagedUser.ManagedUserHomePage.Infomation.CovidHistory.CovidHistoryMenu;
import javacovid19app.ManagedUser.ManagedUserHomePage.Infomation.Transaction.TransactionHistory;
import javacovid19app.ManagedUser.ManagedUserHomePage.Infomation.Treatment.UserTreatmentMenu;
import javacovid19app.ManagedUser.ManagedUserHomePage.ManagedUserHomePage;
import javacovid19app.Manager.ManagerHomePage.DataClasses.City;
import javacovid19app.Manager.ManagerHomePage.DataClasses.District;
import javacovid19app.Manager.ManagerHomePage.DataClasses.Location;
import javacovid19app.Manager.ManagerHomePage.DataClasses.ManagedUser;
import javacovid19app.Manager.ManagerHomePage.DataClasses.TreatmentFacility;
import javacovid19app.Manager.ManagerHomePage.DataClasses.Ward;
import javax.swing.JOptionPane;


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
        loanTextField.setEditable(false);
        
        
        // Tạo truy vấn lấy loan riêng để ko ảnh hưởng đến class
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = true", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select Loan from ManagedUser ";
            ResultSet res = state.executeQuery(sql);
            
            if(res.next()){
                int loan=res.getInt(1);
                loanTextField.setText(String.valueOf(loan));
            }
            connect.close();
           }catch(Exception e){
           System.out.println(e.getMessage());
        }
        
        
        
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
                        break;
                    }
                    else{
                        relatedTextField.setText("NULL");
                    }
                }
            }
        }
        
        
    }
    
    
    // get Facility data
    public void getFacilityList(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = true", "sql6448649", "ygTCgTJZu6");
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
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = true", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select ManagedUser.* from ManagedUser, Account where ManagedUser.UserID = Account.UserID";
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
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = true", "sql6448649", "ygTCgTJZu6");
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
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = true", "sql6448649", "ygTCgTJZu6");
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
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = true", "sql6448649", "ygTCgTJZu6");
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
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = true", "sql6448649", "ygTCgTJZu6");
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
        loanTextField = new javax.swing.JTextField();
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
        personalIDTextField.setForeground(new java.awt.Color(255, 255, 255));
        personalIDTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        personalIDTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        personalIDTextField.setOpaque(false);
        getContentPane().add(personalIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 230, 30));

        yobTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        yobTextField.setForeground(new java.awt.Color(255, 255, 255));
        yobTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        yobTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        yobTextField.setOpaque(false);
        getContentPane().add(yobTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 230, 30));

        cityTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        cityTextField.setForeground(new java.awt.Color(255, 255, 255));
        cityTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cityTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        cityTextField.setOpaque(false);
        getContentPane().add(cityTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 230, 30));

        districtTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        districtTextField.setForeground(new java.awt.Color(255, 255, 255));
        districtTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        districtTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        districtTextField.setOpaque(false);
        getContentPane().add(districtTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 230, 40));

        wardTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        wardTextField.setForeground(new java.awt.Color(255, 255, 255));
        wardTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        wardTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        wardTextField.setOpaque(false);
        getContentPane().add(wardTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 230, 30));

        statusTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        statusTextField.setForeground(new java.awt.Color(255, 255, 255));
        statusTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        statusTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        statusTextField.setOpaque(false);
        statusTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(statusTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 230, 30));

        balanceTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        balanceTextField.setForeground(new java.awt.Color(255, 255, 255));
        balanceTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        balanceTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        balanceTextField.setOpaque(false);
        getContentPane().add(balanceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 590, 230, 30));

        relatedTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        relatedTextField.setForeground(new java.awt.Color(255, 255, 255));
        relatedTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        relatedTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        relatedTextField.setOpaque(false);
        getContentPane().add(relatedTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 540, 230, 30));

        fullNameTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        fullNameTextField.setForeground(new java.awt.Color(255, 255, 255));
        fullNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fullNameTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        fullNameTextField.setOpaque(false);
        getContentPane().add(fullNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 230, 30));

        loanTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        loanTextField.setForeground(new java.awt.Color(255, 255, 255));
        loanTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        loanTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        loanTextField.setOpaque(false);
        getContentPane().add(loanTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 640, 230, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/ManagedUser/ManagedUserHomePage/Infomation/InformationManagedUserBackground.png"))); // NOI18N
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
        ConsumeMenu consumeMenu=new ConsumeMenu(userID);
        consumeMenu.show();
    }//GEN-LAST:event_consumeLabelMouseClicked

    private void covidLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_covidLabelMouseClicked
        CovidHistoryMenu covidMenu=new CovidHistoryMenu(userID,managedUserList);
        covidMenu.show();
    }//GEN-LAST:event_covidLabelMouseClicked

    private void transactionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionLabelMouseClicked
        TransactionHistory transMenu=new TransactionHistory(userID);
        transMenu.show();
    }//GEN-LAST:event_transactionLabelMouseClicked

    private void statusTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusTextFieldActionPerformed

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
    private javax.swing.JTextField loanTextField;
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
