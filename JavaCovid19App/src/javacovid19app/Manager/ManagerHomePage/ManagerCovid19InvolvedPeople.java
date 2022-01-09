/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javacovid19app.Manager.ManagerHomePage;

import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javacovid19app.Manager.ManagerHomePage.DataClasses.Account;
import javacovid19app.Manager.ManagerHomePage.DataClasses.City;
import javacovid19app.Manager.ManagerHomePage.DataClasses.District;
import javacovid19app.Manager.ManagerHomePage.DataClasses.Location;
import javacovid19app.Manager.ManagerHomePage.DataClasses.ManagedUser;
import javacovid19app.Manager.ManagerHomePage.DataClasses.TreatmentFacility;
import javacovid19app.Manager.ManagerHomePage.DataClasses.Ward;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;


/**
 *
 * @author IVS-P0005
 */
public class ManagerCovid19InvolvedPeople extends javax.swing.JFrame {

    /**
     * Creates new form ManagerCovid19InvolvedPeople
     */
    private final Logger logger = Logger.getLogger(ManagerCovid19InvolvedPeople.class);
    private ArrayList <Ward> wardList = new ArrayList<>(); 
    private ArrayList <City> cityList = new ArrayList<>(); 
    private ArrayList <District> districtList = new ArrayList<>(); 
    private ArrayList <Location> locateList = new ArrayList<>(); 
    private ArrayList <ManagedUser> managedUserList = new ArrayList<>(); 
    private ArrayList <ManagedUser> searchResult = new ArrayList<>(); 
    private ArrayList <TreatmentFacility> treatmentFacilityList = new ArrayList<>(); 
    
    public ManagerCovid19InvolvedPeople() {
        this.setResizable(false);
        initComponents();
        jTable_Display_User.setDefaultEditor(Object.class, null);
        this.setTitle("Covid 19 Involved People Management");
        getArrayWardList();
        getArrayCityList();
        getArrayDistrictList();
        getArrayLocationList();
        getFacilityList();
        
        getManagedUserList();
       
        ShowUserList();
        //set combo box
        SetComboBoxCity();
        SetComboBoxTreament();
        
        
        //edit size of column
        jTable_Display_User.getTableHeader().setFont(new Font("Fredoka One", Font.PLAIN, 14));
        final TableColumnModel columnModel = jTable_Display_User.getColumnModel();
        for (int column = 0; column < jTable_Display_User.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < jTable_Display_User.getRowCount(); row++) {
                TableCellRenderer renderer = jTable_Display_User.getCellRenderer(row, column);
                Component comp = jTable_Display_User.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    
    
    
    public void SetComboBoxCity(){
            for(int i = 0; i < this.cityList.size(); i++){
                CityComboBox.addItem(this.cityList.get(i).getName());
            }
    }
    
    public void SetComboBoxTreament(){
            for(int i = 0; i < this.treatmentFacilityList.size(); i++){
                TreatmentFacilitiesComboBox.addItem(this.treatmentFacilityList.get(i).getFacilityName());
            }
    }
    
    public void ShowUserList(){
        DefaultTableModel model = (DefaultTableModel)jTable_Display_User.getModel();
        Object[] row = new Object [9];
        
        for (int i = 0; i < this.managedUserList.size(); i++){
            row[0] = managedUserList.get(i).getID();
            row[1] = managedUserList.get(i).getFullname();
            row[2] = managedUserList.get(i).getYOB();
            row[3] = managedUserList.get(i).getCity().getName();
            row[4] = managedUserList.get(i).getDistrict().getName();
            row[5] = managedUserList.get(i).getWard().getName();
            row[6] = managedUserList.get(i).getCurrentStatus();
            row[7] = managedUserList.get(i).getFacilityName();
            row[8] = managedUserList.get(i).getInvolvedID();
            model.addRow(row);
            
        }
    }
    public void ShowSearchList(){
        DefaultTableModel  model = (DefaultTableModel)jTable_Display_User.getModel();
        while (model.getRowCount()>0){
            model.removeRow(0);
        }
        Object[] row = new Object [9];
        
        for (int i = 0; i < this.searchResult.size(); i++){
            row[0] = searchResult.get(i).getID();
            row[1] = searchResult.get(i).getFullname();
            row[2] = searchResult.get(i).getYOB();
            row[3] = searchResult.get(i).getCity().getName();
            row[4] = searchResult.get(i).getDistrict().getName();
            row[5] = searchResult.get(i).getWard().getName();
            row[6] = searchResult.get(i).getCurrentStatus();
            row[7] = searchResult.get(i).getFacilityName();
            row[8] = searchResult.get(i).getInvolvedID();
            model.addRow(row);
        }
        searchResult.removeAll(searchResult);
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
    
    public int checkInvolved (String involved){
        for (int i = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(involved) == 0){
                return 1;
            }
        }
        return 0;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PersonalID = new javax.swing.JTextField();
        Fullname = new javax.swing.JTextField();
        YOB = new javax.swing.JTextField();
        InvolvedPerson = new javax.swing.JTextField();
        DistrictComboBox = new CustomComboBox.ComboBox();
        StatusComboBox = new CustomComboBox.ComboBox();
        CityComboBox = new CustomComboBox.ComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Display_User = new javax.swing.JTable();
        WardComboBox = new CustomComboBox.ComboBox();
        TreatmentFacilitiesComboBox = new CustomComboBox.ComboBox();
        BtnBackManageHomePage = new javax.swing.JLabel();
        BtnRefreshDetail = new javax.swing.JLabel();
        BtnSearchRelated = new javax.swing.JLabel();
        BtnAdd = new javax.swing.JLabel();
        RelatedComboBox = new CustomComboBox.ComboBox();
        ComboBoxSort = new CustomComboBox.ComboBox();
        BtnSortList = new javax.swing.JLabel();
        BtnSave = new javax.swing.JLabel();
        BtnCheck = new javax.swing.JLabel();
        BtnRemove = new javax.swing.JLabel();
        BtnSearchInvolved = new javax.swing.JLabel();
        ReFreshUserList = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        BtnCheck1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PersonalID.setBackground(new java.awt.Color(20, 31, 53));
        PersonalID.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        PersonalID.setForeground(new java.awt.Color(250, 179, 40));
        PersonalID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PersonalID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        PersonalID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PersonalIDActionPerformed(evt);
            }
        });
        getContentPane().add(PersonalID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 190, 30));

        Fullname.setBackground(new java.awt.Color(20, 31, 53));
        Fullname.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        Fullname.setForeground(new java.awt.Color(250, 179, 40));
        Fullname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Fullname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        getContentPane().add(Fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 190, 30));

        YOB.setBackground(new java.awt.Color(20, 31, 53));
        YOB.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        YOB.setForeground(new java.awt.Color(250, 179, 40));
        YOB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        YOB.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        getContentPane().add(YOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 70, 30));

        InvolvedPerson.setBackground(new java.awt.Color(20, 31, 53));
        InvolvedPerson.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        InvolvedPerson.setForeground(new java.awt.Color(250, 179, 40));
        InvolvedPerson.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        InvolvedPerson.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        InvolvedPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InvolvedPersonActionPerformed(evt);
            }
        });
        getContentPane().add(InvolvedPerson, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 530, 180, 30));

        DistrictComboBox.setBackground(new java.awt.Color(20, 31, 53));
        DistrictComboBox.setForeground(new java.awt.Color(250, 179, 40));
        DistrictComboBox.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        DistrictComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DistrictComboBoxMouseClicked(evt);
            }
        });
        DistrictComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DistrictComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(DistrictComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 180, 40));

        StatusComboBox.setBackground(new java.awt.Color(20, 31, 53));
        StatusComboBox.setForeground(new java.awt.Color(250, 179, 40));
        StatusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "F0", "F1", "F2", "F3" }));
        StatusComboBox.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        StatusComboBox.setLineColor(new java.awt.Color(240, 240, 240));
        StatusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(StatusComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 90, 40));

        CityComboBox.setBackground(new java.awt.Color(20, 31, 53));
        CityComboBox.setForeground(new java.awt.Color(250, 179, 40));
        CityComboBox.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        getContentPane().add(CityComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 180, -1));

        jTable_Display_User.setBackground(new java.awt.Color(20, 31, 53));
        jTable_Display_User.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jTable_Display_User.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        jTable_Display_User.setForeground(new java.awt.Color(255, 255, 255));
        jTable_Display_User.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PersonalID", "Fullname", "YOB", "City", "District", "Ward", "Status", "Treament Place", "Involve Person"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Display_User.setGridColor(new java.awt.Color(255, 255, 255));
        jTable_Display_User.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable_Display_User.setSelectionForeground(new java.awt.Color(250, 179, 40));
        jTable_Display_User.setShowGrid(true);
        jTable_Display_User.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_UserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Display_User);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 870, 360));

        WardComboBox.setBackground(new java.awt.Color(20, 31, 53));
        WardComboBox.setForeground(new java.awt.Color(250, 179, 40));
        WardComboBox.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        WardComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WardComboBoxMouseClicked(evt);
            }
        });
        WardComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WardComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(WardComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 180, 40));

        TreatmentFacilitiesComboBox.setBackground(new java.awt.Color(20, 31, 53));
        TreatmentFacilitiesComboBox.setForeground(new java.awt.Color(250, 179, 40));
        TreatmentFacilitiesComboBox.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        TreatmentFacilitiesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TreatmentFacilitiesComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(TreatmentFacilitiesComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, 200, 40));

        BtnBackManageHomePage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackManageHomePageMouseClicked(evt);
            }
        });
        getContentPane().add(BtnBackManageHomePage, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 40));

        BtnRefreshDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRefreshDetailMouseClicked(evt);
            }
        });
        getContentPane().add(BtnRefreshDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 30, 30));

        BtnSearchRelated.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSearchRelatedMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSearchRelated, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, 40, 30));

        BtnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAddMouseClicked(evt);
            }
        });
        getContentPane().add(BtnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 640, 90, 40));

        RelatedComboBox.setBackground(new java.awt.Color(20, 31, 53));
        RelatedComboBox.setForeground(new java.awt.Color(255, 255, 255));
        RelatedComboBox.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        RelatedComboBox.setOpaque(false);
        getContentPane().add(RelatedComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 560, 850, -1));

        ComboBoxSort.setBackground(new java.awt.Color(20, 31, 53));
        ComboBoxSort.setForeground(new java.awt.Color(255, 255, 255));
        ComboBoxSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fullname", "CurrentStatus", "City", "Treatment Place" }));
        ComboBoxSort.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        ComboBoxSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxSortActionPerformed(evt);
            }
        });
        getContentPane().add(ComboBoxSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 60, 200, -1));

        BtnSortList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSortListMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSortList, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 70, 40, 40));

        BtnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSaveMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, 80, 40));

        BtnCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCheckMouseClicked(evt);
            }
        });
        getContentPane().add(BtnCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 80, 30));

        BtnRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRemoveMouseClicked(evt);
            }
        });
        getContentPane().add(BtnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 640, 130, 40));

        BtnSearchInvolved.setText(" ");
        BtnSearchInvolved.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSearchInvolvedMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSearchInvolved, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 520, 40, 40));

        ReFreshUserList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReFreshUserListMouseClicked(evt);
            }
        });
        getContentPane().add(ReFreshUserList, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 60, 250, 60));

        txtSearch.setFont(new java.awt.Font("Fredoka One", 0, 18)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(196, 157, 0));
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 200, 30));

        BtnCheck1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCheck1MouseClicked(evt);
            }
        });
        getContentPane().add(BtnCheck1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 40, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/Manager/ManagerHomePage/ManagerCoviddInvolvedBackground.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_Display_UserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Display_UserMouseClicked
        // TODO add your handling code here:
        int index = jTable_Display_User.getSelectedRow(); // row that manager choose.
        TableModel model = jTable_Display_User.getModel();
//        
//        // set value in text filed.
        PersonalID.setText(model.getValueAt(index, 0).toString());
        Fullname.setText(model.getValueAt(index, 1).toString());
        YOB.setText(model.getValueAt(index, 2).toString());
        
        
        //combo box for CityComboBox.
        for (int i = 0; i < CityComboBox.getItemCount(); i ++){
            if (model.getValueAt(index, 3).toString().compareTo(CityComboBox.getItemAt(i).toString()) == 0){
                CityComboBox.setSelectedIndex(i);
                break;
            }
        }
//        
        //combo box for DistrictComboBox.
        String city = CityComboBox.getSelectedItem().toString();
        String cityID = "";
        for (int i = 0; i < this.cityList.size(); i++){
            if (this.cityList.get(i).getName().compareTo(city) == 0){
                cityID = this.cityList.get(i).getCityID();
            }
        }
        DistrictComboBox.removeAllItems();
        for (int i = 0;i < this.districtList.size(); i++){
            if (this.districtList.get(i).getCityID().compareTo(cityID) == 0){
                DistrictComboBox.addItem(this.districtList.get(i).getName());
            }
        }
        
        for (int i = 0; i < DistrictComboBox.getItemCount(); i ++){
            if (model.getValueAt(index, 4).toString().compareTo(DistrictComboBox.getItemAt(i).toString()) == 0){
                DistrictComboBox.setSelectedIndex(i);
                break;
            }
        }
        
        //combo box for WardComboBox.
        String district = DistrictComboBox.getSelectedItem().toString();
        String districtID = "";
        for (int i = 0; i < this.districtList.size(); i++){
            if (this.districtList.get(i).getName().compareTo(district) == 0){
                districtID = this.districtList.get(i).getDistrictID();
            }
        }
        WardComboBox.removeAllItems();
        for (int i = 0;i < this.wardList.size(); i++){
            if (this.wardList.get(i).getDistrictID().compareTo(districtID) == 0){
                WardComboBox.addItem(this.wardList.get(i).getName());
            }
        }
        for (int i = 0; i < WardComboBox.getItemCount(); i ++){
            if (model.getValueAt(index, 5).toString().compareTo(WardComboBox.getItemAt(i).toString()) == 0){
                WardComboBox.setSelectedIndex(i);
                break;
            }
        }
        
         //combo box for StatusComboBox
         for (int i = 0; i < StatusComboBox.getItemCount(); i ++){
            if (model.getValueAt(index, 6).toString().compareTo(StatusComboBox.getItemAt(i).toString()) == 0){
                StatusComboBox.setSelectedIndex(i);
                break;
            }
        }
         
        //combo box for Treatment Facility.
         for (int i = 0; i < TreatmentFacilitiesComboBox.getItemCount(); i ++){
            if (model.getValueAt(index, 7).toString().compareTo(TreatmentFacilitiesComboBox.getItemAt(i).toString()) == 0){
                TreatmentFacilitiesComboBox.setSelectedIndex(i);
                break;
            }
        }
        InvolvedPerson.setText(model.getValueAt(index, 8).toString());
    }//GEN-LAST:event_jTable_Display_UserMouseClicked

    private void DistrictComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DistrictComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DistrictComboBoxActionPerformed

    private void DistrictComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DistrictComboBoxMouseClicked
        // TODO add your handling code here:
        String city = CityComboBox.getSelectedItem().toString();
        String cityID = "";
        for (int i = 0; i < this.cityList.size(); i++){
            if (this.cityList.get(i).getName().compareTo(city) == 0){
                cityID = this.cityList.get(i).getCityID();
            }
        }
        DistrictComboBox.removeAllItems();
        for (int i = 0;i < this.districtList.size(); i++){
            if (this.districtList.get(i).getCityID().compareTo(cityID) == 0){
                DistrictComboBox.addItem(this.districtList.get(i).getName());
            }
        }
    }//GEN-LAST:event_DistrictComboBoxMouseClicked

    private void WardComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WardComboBoxMouseClicked
        // TODO add your handling code here:
        String district = DistrictComboBox.getSelectedItem().toString();
        String districtID = "";
        for (int i = 0; i < this.districtList.size(); i++){
            if (this.districtList.get(i).getName().compareTo(district) == 0){
                districtID = this.districtList.get(i).getDistrictID();
            }
        }
        WardComboBox.removeAllItems();
        for (int i = 0;i < this.wardList.size(); i++){
            if (this.wardList.get(i).getDistrictID().compareTo(districtID) == 0){
                WardComboBox.addItem(this.wardList.get(i).getName());
            }
        }
    }//GEN-LAST:event_WardComboBoxMouseClicked

    private void TreatmentFacilitiesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TreatmentFacilitiesComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TreatmentFacilitiesComboBoxActionPerformed

    private void BtnBackManageHomePageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackManageHomePageMouseClicked
        // TODO add your handling code here:
        ManagerHomePage managerHome = new ManagerHomePage();
        managerHome.show();
        
        dispose();
    }//GEN-LAST:event_BtnBackManageHomePageMouseClicked

    private void StatusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusComboBoxActionPerformed

    private void WardComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WardComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WardComboBoxActionPerformed

    private void BtnRefreshDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRefreshDetailMouseClicked
        // TODO add your handling code here:
        PersonalID.setText("");
        Fullname.setText("");
        YOB.setText("");
        CityComboBox.setSelectedIndex(-1);
        DistrictComboBox.setSelectedIndex(-1);
        WardComboBox.setSelectedIndex(-1);
        TreatmentFacilitiesComboBox.setSelectedIndex(-1);
        StatusComboBox.setSelectedIndex(-1);
        InvolvedPerson.setText("");
    }//GEN-LAST:event_BtnRefreshDetailMouseClicked

    private void InvolvedPersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InvolvedPersonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InvolvedPersonActionPerformed

    private void BtnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddMouseClicked
        // TODO add your handling code here:
        
        String ID = PersonalID.getText();
        String fullname = Fullname.getText();
        if (ID.isEmpty() && fullname.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide information!");
            return;
        }
        String year = YOB.getText();
        
        String city = CityComboBox.getSelectedItem().toString();
        String ward = WardComboBox.getSelectedItem().toString();
        String district = DistrictComboBox.getSelectedItem().toString();
        
        String status = StatusComboBox.getSelectedItem().toString();
       
        String place  = TreatmentFacilitiesComboBox.getSelectedItem().toString();
        
        String involved = InvolvedPerson.getText();
        
        if (ID.isEmpty() || fullname.isEmpty() || year.isEmpty() || city.isEmpty() || ward.isEmpty() || district.isEmpty() || place.isEmpty() || involved.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide full information!");
            return;
        }
        
        for (int i = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(ID) == 0){
                JOptionPane.showMessageDialog(this, "Personal's id already exist!");
                return;
            }
        }
        
        if (status.compareTo("F0") != 0 && (involved.compareTo("null") == 0 || involved.compareTo("NULL") == 0)){
            JOptionPane.showMessageDialog(this, "F1, F2, F3 must have invovled person!");
            return;

        }
        
        if (ID.length() > 14 || involved.length() > 14){
            JOptionPane.showMessageDialog(this, "Invalid personal's id!");
            return;
        }
        
        if (Integer.valueOf(year) > 2021){
            JOptionPane.showMessageDialog(this, "Invalid year birth!");
            return;
        }
        
        
        
        
        // f3 -> f2, f2 -> f1 check
        
        for (int i = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(involved) == 0){
                if (this.managedUserList.get(i).getCurrentStatus().compareTo(status) != -1){
                    JOptionPane.showMessageDialog(this, status + " can not involve with " + this.managedUserList.get(i).getCurrentStatus() + " !");
                    return;
                }
            }
        }
        
        

        if (checkInvolved(involved) == 0 && status.compareTo("F0") != 0){
            JOptionPane.showMessageDialog(this, "Involved Personal's id not exist!");
            return;
        }
        int index  = 0;
        for (int i = 0; i < this.treatmentFacilityList.size(); i++){
            if (this.treatmentFacilityList.get(i).getFacilityName().compareTo(place) == 0){
                if (Integer.valueOf(this.treatmentFacilityList.get(i).getPresentQuantity()) + 1 >= Integer.valueOf(this.treatmentFacilityList.get(i).getQuantity())){
                    JOptionPane.showMessageDialog(this, "Overload number of people!");
                    return;
                }
                index = i;
                break;
            }
        }
        
        String facilityID = this.treatmentFacilityList.get(index).getID();
        int present_quantity = Integer.valueOf(this.treatmentFacilityList.get(index).getPresentQuantity()) + 1;
        
        // get WardID;
        int wardIndex = 0;
        for (int i  = 0; i < this.wardList.size(); i++){
            if (this.wardList.get(i).getName().compareTo(ward) == 0){
                wardIndex = i;
                break;
            }
        }
        
        int cityIndex = 0;
        for (int i  = 0; i < this.cityList.size(); i++){
            if (this.cityList.get(i).getName().compareTo(city) == 0){
                cityIndex = i;
                break;
            }
        }
        
        int districtIndex = 0;
        for (int i  = 0; i < this.districtList.size(); i++){
            if (this.districtList.get(i).getName().compareTo(district) == 0){
                districtIndex = i;
                break;
            }
        }
        
        String locationID = this.locateList.get(wardIndex).getLocationID();
        
        ManagedUser tmp = new ManagedUser (ID, fullname, year ,status, facilityID, involved, locationID, "0", cityList, wardList, districtList, locateList, this.treatmentFacilityList );
        managedUserList.add(tmp);
        
        DefaultTableModel  model = (DefaultTableModel)jTable_Display_User.getModel();
        while (model.getRowCount()>0){
            model.removeRow(0);
        }
        this.ShowUserList();
        
        
        // update sql.
        try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state = connect.createStatement();
                
                String hashedPass =  BCrypt.hashpw("12345", BCrypt.gensalt(10));
                String account = " insert into Account (UserID, Password, Type, Status)"
                                                + " values ('"+ID+"', '"+hashedPass+"', '3', '1')";
                state.execute(account);
                
                if (status.compareTo("F0") != 0){
                    String manageTable = " insert into ManagedUser (UserID, Fullname, YOB, CurrentStatus, FacilityID, InvolvedPerson, LocationID, Balance)"
                                                + " values ('"+ID+"', '"+fullname+"', '"+year+"','"+status+"', '"+facilityID+"', '"+involved+"', '"+locationID+"', '0')";
                    state.execute(manageTable);
                }
                else{
                    String manageTable = " insert into ManagedUser (UserID, Fullname, YOB, CurrentStatus, FacilityID, LocationID, Balance)"
                                                + " values ('"+ID+"', '"+fullname+"', '"+year+"','"+status+"', '"+facilityID+"', '"+locationID+"', '0')";
                    state.execute(manageTable);
                }
                
                //update present quantity.
                
                String quantity = "Update TreatmentFacility set PresentQuantity = '"+present_quantity+"' where FacilityID = '"+facilityID+"'";
                state.executeUpdate(quantity);
                
                // add to Treatmenthistory table in database
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String arriveTime = formatter.format(date).toString();
                String treatmentHis = " insert into TreatmentHistory  (UserID, ArriveTime, FacilityID)"
                                + " values ('"+ID+"', '"+arriveTime+"', '"+facilityID+"')";
                state.execute(treatmentHis);
                
                connect.close();
                
                JOptionPane.showMessageDialog(this, "Add new user successful!");
                int size = this.managedUserList.size() - 1;
             
                jTable_Display_User.setRowSelectionInterval(size, size);
            }catch(Exception e){
            System.out.println(e.getMessage());
            }
    }//GEN-LAST:event_BtnAddMouseClicked

    private void searchAffectedBy(ArrayList<Integer> tmp, String ID){
        
        if (ID.compareTo("null")==0){
            return;
        }
        else{
            for (int i  = 0; i < this.managedUserList.size(); i++){
                if (this.managedUserList.get(i).getID().compareTo(ID) == 0){
                    tmp.add(i);
                    searchAffectedBy(tmp, this.managedUserList.get(i).getInvolvedID());
                }
            }
        }
    }
    
    private void searchAffectTo(ArrayList<Integer> tmp, String ID){
        for (int i  = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getInvolvedID().compareTo(ID) == 0){
                tmp.add(i);
            }
        }
    }
    
    private void BtnSearchRelatedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSearchRelatedMouseClicked
        // TODO add your handling code here:
        RelatedComboBox.removeAllItems();
        String ID = PersonalID.getText();
        
        ArrayList<Integer> affectedBy = new ArrayList<>();
        searchAffectedBy(affectedBy, ID);
        
        ArrayList<Integer> affecteTo = new ArrayList<>();
        searchAffectTo(affecteTo, ID);
        
        for(int i = affectedBy.size() - 1 ; i > 0  ; i--){
            RelatedComboBox.addItem(this.managedUserList.get(affectedBy.get(i)).getID()+ " - " + this.managedUserList.get(affectedBy.get(i)).getFullname() + " - " + this.managedUserList.get(affectedBy.get(i)).getYOB() + " - " + this.managedUserList.get(affectedBy.get(i)).getCity().getName() + " - " +this.managedUserList.get(affectedBy.get(i)).getDistrict().getName() + " - " + this.managedUserList.get(affectedBy.get(i)).getWard().getName() +" - "+ this.managedUserList.get(affectedBy.get(i)).getCurrentStatus() + " - " + this.managedUserList.get(affectedBy.get(i)).getFacilityName());
        }
        RelatedComboBox.addItem("-----------------");
        
        for(int i = 0 ; i < affecteTo.size() ; i++){
            RelatedComboBox.addItem(this.managedUserList.get(affecteTo.get(i)).getID()+ " - " + this.managedUserList.get(affecteTo.get(i)).getFullname() + " - " + this.managedUserList.get(affecteTo.get(i)).getYOB() + " - " + this.managedUserList.get(affecteTo.get(i)).getCity().getName() + " - " +this.managedUserList.get(affecteTo.get(i)).getDistrict().getName() + " - " + this.managedUserList.get(affecteTo.get(i)).getWard().getName() +" - "+ this.managedUserList.get(affecteTo.get(i)).getCurrentStatus() + " - " + this.managedUserList.get(affecteTo.get(i)).getFacilityName());
        }
        
    }//GEN-LAST:event_BtnSearchRelatedMouseClicked

    private void ComboBoxSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxSortActionPerformed

    private void refreshJTable(){
        DefaultTableModel  model = (DefaultTableModel)jTable_Display_User.getModel();
        while (model.getRowCount()>0){
            model.removeRow(0);
        }
        this.ShowUserList();
    }
    //Sort ManagedUSer List by Name.
    private void SortByName(){
        for (int i = 0; i < this.managedUserList.size() - 1; i++){
            for (int j = 0; j < this.managedUserList.size() - 1 - i; j++){
                if (this.managedUserList.get(j).getFullname().compareTo(this.managedUserList.get(j+1).getFullname()) > 0){
                    Collections.swap(this.managedUserList, j, j+1); 
                }
            }
        }
    }
    
    //Sort managed User list by Current Status
    private void SortByCurrentStatus(){
        for (int i = 0; i < this.managedUserList.size() - 1; i++){
            for (int j = 0; j < this.managedUserList.size() - 1 - i; j++){
                if (this.managedUserList.get(j).getCurrentStatus().compareTo(this.managedUserList.get(j+1).getCurrentStatus()) > 0){
                    Collections.swap(this.managedUserList, j, j+1); 
                }
            }
        }
    }
    
    //Sort managed User list by TreatMent Facility.
    private void SortByTreatmentPlace(){
        for (int i = 0; i < this.managedUserList.size() - 1; i++){
            for (int j = 0; j < this.managedUserList.size() - 1 - i; j++){
                if (this.managedUserList.get(j).getFacilityName().compareTo(this.managedUserList.get(j+1).getFacilityName()) > 0){
                    Collections.swap(this.managedUserList, j, j+1); 
                }
            }
        }
    }
    
     //Sort managed User list by TreatMent Facility.
    private void SortByCity(){
        for (int i = 0; i < this.managedUserList.size() - 1; i++){
            for (int j = 0; j < this.managedUserList.size() - 1 - i; j++){
                if (this.managedUserList.get(j).getCity().getName().compareTo(this.managedUserList.get(j+1).getCity().getName()) > 0){
                    Collections.swap(this.managedUserList, j, j+1); 
                }
            }
        }
    }
    
    private void BtnSortListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSortListMouseClicked
        // TODO add your handling code here:
        
        String value = ComboBoxSort.getSelectedItem().toString();
        
        if (value.compareTo("Fullname") == 0){
            SortByName();
        }
        else if (value.compareTo("CurrentStatus") == 0){
            SortByCurrentStatus();
        }
        
        else if (value.compareTo("Treatment Place") == 0){
            SortByTreatmentPlace();
        }
        else if (value.compareTo("City") == 0){
            SortByCity();
        }
        refreshJTable();
    }//GEN-LAST:event_BtnSortListMouseClicked

    private int getOldData(String changeID){
        for (int i = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(changeID) == 0){
                return i;
            }
        }
        return -1;
    }
    
    private void updateCurrentStatus(String ID, String newStatus){
        for (int i = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(ID) == 0){
                this.managedUserList.get(i).setCurrentStatus(newStatus);
                return;
            }
        }
    }
    
    private void editAffectTo(String ID, String status){
        for (int i  = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getInvolvedID().compareTo(ID) == 0){
                if (status.compareTo("F0") == 0){
                    this.managedUserList.get(i).setCurrentStatus("F1");
                }
                else if (status.compareTo("F1") == 0){
                    this.managedUserList.get(i).setCurrentStatus("F2");
                }
                else if (status.compareTo("F2") == 0){
                    this.managedUserList.get(i).setCurrentStatus("F3");
                }
            }
        }
    }
    
    private int checkUpdateStatus(String ID, String newStatus){ // only approve form f1 to f0.
        for (int i  = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(ID) == 0){
                if (newStatus.compareTo("F0") == 0 && this.managedUserList.get(i).getCurrentStatus().compareTo("F1") == 0){
                    return 1;
                }
            }
        }
        return 0;
    }
    
    private String getCurrentStatusFromData(String ID){
        for (int i  = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(ID) == 0){
                return this.managedUserList.get(i).getCurrentStatus();
            }
        }
        return "";
    }
    
    private String getStatusOfInvolvePerson(String invovled){
        for (int i = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(invovled) == 0){
                return this.managedUserList.get(i).getCurrentStatus();
            }
        }
        return "";
    }
    private void updateF3toF2(){
            for (int i = 0; i < this.managedUserList.size(); i++){
                if (this.managedUserList.get(i).getCurrentStatus().compareTo("F3") == 0 && getStatusOfInvolvePerson(this.managedUserList.get(i).getInvolvedID()).compareTo("F1") == 0){
                    this.managedUserList.get(i).setCurrentStatus("F2");
                }
            }
        }
    
    private int checkTreatment(String ID, String changeTreatment){
        for (int i  = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(ID) == 0){
                if (this.managedUserList.get(i).getFacilityName().compareTo(changeTreatment)== 0){
                    return 1;
                }
            }
        }
        return 0;
    }
    
    private int checkQuantity(String FacilityName){
        for (int i = 0; i < this.treatmentFacilityList.size(); i++){
            if (this.treatmentFacilityList.get(i).getFacilityName().compareTo(FacilityName) == 0){
                if (Integer.valueOf(this.treatmentFacilityList.get(i).getPresentQuantity()) + 1 <= Integer.valueOf(this.treatmentFacilityList.get(i).getQuantity())){
                    return 1;
                }
            }
        }
        return 0;
    }
    
    private TreatmentFacility getNewTreatment(String TreatmentName){
        TreatmentFacility treat = new TreatmentFacility("", "", "", "");
        for (int i = 0; i < this.treatmentFacilityList.size(); i++){
            if (this.treatmentFacilityList.get(i).getFacilityName().compareTo(TreatmentName) == 0){
                treat.setFacilityName(this.treatmentFacilityList.get(i).getFacilityName());
                treat.setID(this.treatmentFacilityList.get(i).getID());
                treat.setPresentQuantity(String.valueOf((Integer.valueOf(this.treatmentFacilityList.get(i).getPresentQuantity())+1)));
                treat.setQuantity(this.treatmentFacilityList.get(i).getQuantity());
            }
        }
        return treat;
    }
    
    private int getManagedUserIndex(String ID){
        
        for (int i = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(ID) == 0){
                return i;
            }
        }
        return -1;
    }
    
    private int getFacilityIndex(String id){
        for (int i =0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getFacilityID().compareTo(id) == 0){
                return i;
            }
        }
        return -1;
    }
    
    private void UpdateManagedUserTable(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            //update status in table account.
            for (int i = 0; i < this.managedUserList.size(); i++){
                String sql_mangedUser = "Update ManagedUser set CurrentStatus = '"+this.managedUserList.get(i).getCurrentStatus()+"', FacilityID = '"+this.managedUserList.get(i).getFacilityID()+"'where UserID = '"+this.managedUserList.get(i).getID()+"'";
                state.executeUpdate(sql_mangedUser);
            }connect.close();
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
    }
   
    
    private void BtnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSaveMouseClicked
        // TODO add your handling code here:
        String changeID = PersonalID.getText();
        String changeFullname = Fullname.getText();
        
        if (changeID.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide information!");
            return;
        }
        
        String changeYear = YOB.getText();
        
        String changeCity = CityComboBox.getSelectedItem().toString();
        String changeWard = WardComboBox.getSelectedItem().toString();
        String changeDistrict = DistrictComboBox.getSelectedItem().toString();
        String changeStatus = StatusComboBox.getSelectedItem().toString();
        String changeTreatment = TreatmentFacilitiesComboBox.getSelectedItem().toString();
        String changeInvolved = InvolvedPerson.getText();
        
        int index = getOldData(changeID);
        if (index == -1){
            JOptionPane.showMessageDialog(this, "Can not change Personal's ID!");
            return;
        }
        else{
            if (this.managedUserList.get(index).getCity().getName().compareTo(changeCity) != 0){
                JOptionPane.showMessageDialog(this, "Can not change City living!");
                return;
            }
            if (this.managedUserList.get(index).getDistrict().getName().compareTo(changeDistrict) != 0){
                JOptionPane.showMessageDialog(this, "Can not change District living!");
                return;
            }
            if (this.managedUserList.get(index).getWard().getName().compareTo(changeWard) != 0){
                JOptionPane.showMessageDialog(this, "Can not change Ward living!");
                return;
            }
            
            if (this.managedUserList.get(index).getFullname().compareTo(changeFullname) != 0){
                JOptionPane.showMessageDialog(this, "Can not change Fullname!");
                return;
            }
            
            if (this.managedUserList.get(index).getYOB().compareTo(changeYear) != 0){
                JOptionPane.showMessageDialog(this, "Can not change Year of Birth!");
                return;
            }
            
            
            if (this.managedUserList.get(index).getInvolvedID().compareTo(changeInvolved) != 0){
                JOptionPane.showMessageDialog(this, "Can not change Involved Person!");
                return;
            }
            
            if (getCurrentStatusFromData(changeID).compareTo(changeStatus) != 0){
                if (checkUpdateStatus(changeID, changeStatus) == 0){
                    String current = getCurrentStatusFromData(changeID);
                    JOptionPane.showMessageDialog(this, "Can not update " + current + " to " + changeStatus);
                    return;
                }
                else{
                    updateCurrentStatus(changeID, changeStatus);
                    editAffectTo(changeID, changeStatus); // similarly to F2 -> F1
                    updateF3toF2();
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                        Statement state = connect.createStatement();
                        
                        //insert to covid history
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date();
                        String arriveTime = formatter.format(date).toString();
                        String newCovidHistory = " insert into CovidHistory  (UserID, BeginTime, Status, InvolvedPerson)"
                                    + " values ('"+changeID+"', '"+arriveTime+"', '"+changeStatus+"', '"+changeInvolved+"' )";
                        state.execute(newCovidHistory);
                        connect.close();
                        
                    }catch(Exception e){
                         System.out.println(e.getMessage());
                    }
                }
            }
            
            if (checkTreatment(changeID, changeTreatment) == 0){
                if (checkQuantity(changeTreatment) == 0){
                    JOptionPane.showMessageDialog(this, "Onverload number of limit quanity!");
                }
                else{
                    TreatmentFacility newTreat = getNewTreatment(changeTreatment);
                    int indexUser = getManagedUserIndex(changeID);
                    String oldTreamentPlace = this.managedUserList.get(indexUser).getFacilityID();
                    
                    int oldQuantity = 0;
                    for (int i = 0; i < this.treatmentFacilityList.size(); i++){
                        if (this.treatmentFacilityList.get(i).getID().compareTo(oldTreamentPlace) == 0){
                            oldQuantity = Integer.valueOf(this.treatmentFacilityList.get(i).getPresentQuantity()) - 1;
                            break;
                        }
                    }
                    
                    this.managedUserList.get(indexUser).setFacilityName(newTreat.getFacilityName());
                    this.managedUserList.get(indexUser).setFacilityID(newTreat.getID());
                    this.managedUserList.get(indexUser).setPresentQuantity(newTreat.getPresentQuantity());
                    this.managedUserList.get(indexUser).setQuantity(newTreat.getQuantity());
                    
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                        Statement state = connect.createStatement();
                        String treatment = "Update TreatmentFacility set PresentQuantity = '"+newTreat.getPresentQuantity()+"' where FacilityID = '"+newTreat.getID()+"'";
                        state.executeUpdate(treatment);
                        
                        String oldTreatment = "Update TreatmentFacility set PresentQuantity = '"+oldQuantity+"' where FacilityID = '"+oldTreamentPlace+"'";
                        state.executeUpdate(oldTreatment);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date();
                        String arriveTime = formatter.format(date).toString();
                        
                        String leaveTime = "Update TreatmentHistory set LeaveTime = '"+arriveTime+"' where UserID = '"+changeID+"' and LeaveTime is null";
                        state.executeUpdate(leaveTime);
                        
                        String treatmentHis = " insert into TreatmentHistory  (UserID, ArriveTime, FacilityID)"
                                        + " values ('"+changeID+"', '"+arriveTime+"', '"+newTreat.getID()+"')";
                        state.execute(treatmentHis);
                        connect.close();
                    }catch(Exception e){
                         System.out.println(e.getMessage());
                    }
                }
            }
            UpdateManagedUserTable();
            JOptionPane.showMessageDialog(this, "Check your update!");
            refreshJTable();
        }
    }//GEN-LAST:event_BtnSaveMouseClicked

    private void BtnCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCheckMouseClicked
        // TODO add your handling code here:
        String ID = PersonalID.getText();
        String fullname = Fullname.getText();
        
        if (ID.isEmpty() && fullname.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide information!");
            return;
        }
        int index = -1;
        for (int i = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(ID) == 0 || this.managedUserList.get(i).getFullname().compareTo(fullname) == 0){
                index = i;
                break;
            }
        }
        
        if (index == -1){
            JOptionPane.showMessageDialog(this, "Not found!");
            return;
        }
        else{
            JOptionPane.showMessageDialog(this, "Found!");
            jTable_Display_User.setRowSelectionInterval(index, index);
            return;
        }
        
        
    }//GEN-LAST:event_BtnCheckMouseClicked

    private int checkExist(String ID){
        for (int i =0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(ID) ==0){
                return 1;
            }
        }
        return 0;
    }
    private void BtnRemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRemoveMouseClicked
        // TODO add your handling code here:
        
        String ID = PersonalID.getText();
        String fullname = Fullname.getText();
        
        String status = "";
        
        
        if (ID.isEmpty() && fullname.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide information!");
            return;
        }
        
        
        if (checkExist(ID) == 0){
            JOptionPane.showMessageDialog(this, "Personal ID does not exist!");
            return;
        }
        
        try{
            status = StatusComboBox.getSelectedItem().toString();
        }catch(Exception e){
            System.out.println(e.getMessage());
            logger.error("Error with remove an involved person!");
        }
        
        if (status.compareTo("F0") != 0){
            JOptionPane.showMessageDialog(this, "Can not remove "+status);
            return;
        }
        if (ID.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide ID to remove!");
            return;
        }
        
        int index = -1;
        for (int i = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(ID) == 0){
                index = i;
                break;
            }
        }
        
        String[] options = new String[3];
        options[0] = "Disease";
        options[1] = "Recover";
        options[2] = "Cancel";
        int choice = JOptionPane.showOptionDialog(null, "Reason to remove?", "Remove Confirm", 2, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        if (choice == 0){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state = connect.createStatement();

                //update status in table account.
                String sql_disease = "Update Account set Status = 0 where UserID = '"+ID+"'";
                state.executeUpdate(sql_disease);

                connect.close();
            }catch(Exception e){
            System.out.println(e.getMessage());
            }
            this.managedUserList.remove(index);
        }
        else if (choice == 1) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state = connect.createStatement();

                //update status in table account.
                String sql_recover = "Update Account set Status = 2 where UserID = '"+ID+"'";
                state.executeUpdate(sql_recover);

                connect.close();
                
            }catch(Exception e){
            System.out.println(e.getMessage());
            }
            this.managedUserList.remove(index);
        }
        refreshJTable();
        
    }//GEN-LAST:event_BtnRemoveMouseClicked

    private void BtnSearchInvolvedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSearchInvolvedMouseClicked
        // TODO add your handling code here:
        
        String info  = RelatedComboBox.getSelectedItem().toString();
        if (info.compareTo("-----------------") == 0){
            JOptionPane.showMessageDialog(this, "invalid Information!");
            return;
        }
        String tmp [] = info.split(" - ");
        
        String ID = new String (tmp[0]);
        
        if (ID.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide information!");
            return;
        }
        int index = -1;
        for (int i = 0; i < this.managedUserList.size(); i++){
            if (this.managedUserList.get(i).getID().compareTo(ID) == 0){
                index = i;
                break;
            }
        }
        
        if (index == -1){
            JOptionPane.showMessageDialog(this, "Not found!");
            logger.info("Search involved people function.");
            return;
        }
        else{
            JOptionPane.showMessageDialog(this, "Found!");
            jTable_Display_User.setRowSelectionInterval(index, index);
            logger.info("Search involved people function.");
            return;
        }
        
    }//GEN-LAST:event_BtnSearchInvolvedMouseClicked

    private void PersonalIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PersonalIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PersonalIDActionPerformed

    private void ReFreshUserListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReFreshUserListMouseClicked
        // TODO add your handling code here:
        refreshJTable();
        ShowUserList();
    }//GEN-LAST:event_ReFreshUserListMouseClicked

    private void BtnCheck1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCheck1MouseClicked
        String content = txtSearch.getText();
        if (content.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please input information you want to search!");
            return;
        }else{
            for(int i = 0; i < this.managedUserList.size(); i++){
                if (this.managedUserList.get(i).getID().contains(content)){
                    this.searchResult.add(this.managedUserList.get(i));
                }
                else if (this.managedUserList.get(i).getFullname().contains(content)){
                    this.searchResult.add(this.managedUserList.get(i));
                }
            }

            if (this.searchResult.size() == 0){
                JOptionPane.showMessageDialog(this, "No information found!");
            }
            else{
                ShowSearchList();
            }
        }
    }//GEN-LAST:event_BtnCheck1MouseClicked

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
            java.util.logging.Logger.getLogger(ManagerCovid19InvolvedPeople.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerCovid19InvolvedPeople.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerCovid19InvolvedPeople.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerCovid19InvolvedPeople.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerCovid19InvolvedPeople().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnAdd;
    private javax.swing.JLabel BtnBackManageHomePage;
    private javax.swing.JLabel BtnCheck;
    private javax.swing.JLabel BtnCheck1;
    private javax.swing.JLabel BtnRefreshDetail;
    private javax.swing.JLabel BtnRemove;
    private javax.swing.JLabel BtnSave;
    private javax.swing.JLabel BtnSearchInvolved;
    private javax.swing.JLabel BtnSearchRelated;
    private javax.swing.JLabel BtnSortList;
    private CustomComboBox.ComboBox CityComboBox;
    private CustomComboBox.ComboBox ComboBoxSort;
    private CustomComboBox.ComboBox DistrictComboBox;
    private javax.swing.JTextField Fullname;
    private javax.swing.JTextField InvolvedPerson;
    private javax.swing.JTextField PersonalID;
    private javax.swing.JLabel ReFreshUserList;
    private CustomComboBox.ComboBox RelatedComboBox;
    private CustomComboBox.ComboBox StatusComboBox;
    private CustomComboBox.ComboBox TreatmentFacilitiesComboBox;
    private CustomComboBox.ComboBox WardComboBox;
    private javax.swing.JTextField YOB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Display_User;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
