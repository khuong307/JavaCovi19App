/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javacovid19app.Manager.ManagerHomePage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import javacovid19app.Manager.ManagerHomePage.DataClasses.Necessary;
import javacovid19app.Manager.ManagerHomePage.DataClasses.TreatmentFacility;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.apache.log4j.Logger;

/**
 *
 * @author IVS-P0005
 */
public class ManagerSupplies extends javax.swing.JFrame {

    /**
     * Creates new form ManagerSupplies
     */
    private final Logger logger = Logger.getLogger(ManagerSupplies.class);
    private ArrayList <Necessary> necessaryList = new ArrayList<>();
    private ArrayList <Necessary> searchResult = new ArrayList<>();
    private int type = 1;
    public ManagerSupplies() {
        initComponents();
        TableList.setDefaultEditor(Object.class, null);
        this.setResizable(false);
        this.setTitle("Supplies Management");
        getNecessariesData();
        
        Name.setText("");
        Limit.setText("");
        Expired.setText("");
        Price.setText("");
    
        PanelDetailFormList.setBackground(new Color (221, 174, 11));
        PanelSupliesList.setBackground(new Color (221, 174, 11));
        TableList.setBackground(new Color (221, 174, 11));
        refreshJTable();
        ShowInstantFoodsList();
        EditTableHeightWidth(TableList);
    }
    
    private void refreshJTable(){
        DefaultTableModel  model = (DefaultTableModel)TableList.getModel();
        while (model.getRowCount()>0){
            model.removeRow(0);
        }
    }
    
    public void getNecessariesData(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select * from Necessaries ";
            ResultSet res = state.executeQuery(sql);
            
            Necessary tmp;
            while(res.next()){
                tmp = new Necessary (res.getString("NecessariesID"), res.getString("Name"),res.getString("TimeLimited"), res.getString("DateLimited"), res.getString("Price"), res.getString("Type")); 
                this.necessaryList.add(tmp);
            }
            connect.close();
            logger.debug("Debug in getNecessariesData()");
           }catch(Exception e){
              logger.error("Error when get Necessaries in database.");
           System.out.println(e.getMessage());
        }
    }
    
    public void ShowSearchFoodsList(){
        DefaultTableModel model = (DefaultTableModel)TableList.getModel();
        Object[] row = new Object [4];
        
        for (int i = 0; i < this.necessaryList.size(); i++){
            for (int j = 0; j < this.searchResult.size(); j++){
                if (this.searchResult.get(j).getID().compareTo(this.necessaryList.get(i).getID())==0){
                    row[0] = necessaryList.get(i).getName();
                    row[1] = necessaryList.get(i).getLimitation();
                    if (Integer.valueOf(necessaryList.get(i).getDateLimit()) >= 30){
                        row[2] = Integer.valueOf(necessaryList.get(i).getDateLimit()) / 30 + " month(s)";
                    }
                    else if (Integer.valueOf(necessaryList.get(i).getDateLimit()) >= 7 ){
                        row[2] = Integer.valueOf(necessaryList.get(i).getDateLimit()) / 7 + " week(s)";
                    }
                    else{
                        row[2] = Integer.valueOf(necessaryList.get(i).getDateLimit()) + " days";
                    }
                    row [3] = necessaryList.get(i).getPrice();
                    model.addRow(row);
                }
            }
        }
        logger.debug("Info in ShowInstantFoodsList()");
    }
    
    public void ShowInstantFoodsList(){
        DefaultTableModel model = (DefaultTableModel)TableList.getModel();
        Object[] row = new Object [4];
        
        for (int i = 0; i < this.necessaryList.size(); i++){
            if (this.necessaryList.get(i).getType().compareTo("1") == 0){
                row[0] = necessaryList.get(i).getName();
                row[1] = necessaryList.get(i).getLimitation();
                if (Integer.valueOf(necessaryList.get(i).getDateLimit()) >= 30){
                    row[2] = Integer.valueOf(necessaryList.get(i).getDateLimit()) / 30 + " month(s)";
                }
                else if (Integer.valueOf(necessaryList.get(i).getDateLimit()) >= 7 ){
                    row[2] = Integer.valueOf(necessaryList.get(i).getDateLimit()) / 7 + " week(s)";
                }
                else{
                    row[2] = Integer.valueOf(necessaryList.get(i).getDateLimit()) + " days";
                }
                
                row [3] = necessaryList.get(i).getPrice();
                model.addRow(row);
            }
        }
        logger.debug("Info in ShowInstantFoodsList()");
    }
//    
    public void ShowFruitList(){
        DefaultTableModel model = (DefaultTableModel)TableList.getModel();
        Object[] row = new Object [4];
        
        for (int i = 0; i < this.necessaryList.size(); i++){
            if (this.necessaryList.get(i).getType().compareTo("2") == 0){
                row[0] = necessaryList.get(i).getName();
                row[1] = necessaryList.get(i).getLimitation();
                if (Integer.valueOf(necessaryList.get(i).getDateLimit()) >= 30){
                    row[2] = Integer.valueOf(necessaryList.get(i).getDateLimit()) / 30 + " month(s)";
                }
                else if (Integer.valueOf(necessaryList.get(i).getDateLimit()) >= 7 ){
                    row[2] = Integer.valueOf(necessaryList.get(i).getDateLimit()) / 7 + " week(s)";
                }
                else{
                    row[2] = Integer.valueOf(necessaryList.get(i).getDateLimit()) + " days";
                }
                
                row [3] = necessaryList.get(i).getPrice();
                model.addRow(row);
            }
        }
    }
//    
    public void ShowEssentialList(){
        DefaultTableModel model = (DefaultTableModel)TableList.getModel();
        Object[] row = new Object [4];
        
        for (int i = 0; i < this.necessaryList.size(); i++){
            if (this.necessaryList.get(i).getType().compareTo("3") == 0){
                row[0] = necessaryList.get(i).getName();
                row[1] = necessaryList.get(i).getLimitation();
                
                if (Integer.valueOf(necessaryList.get(i).getDateLimit()) >= 30){
                    row[2] = Integer.valueOf(necessaryList.get(i).getDateLimit()) / 30 + " month(s)";
                }
                else if (Integer.valueOf(necessaryList.get(i).getDateLimit()) >= 7 ){
                    row[2] = Integer.valueOf(necessaryList.get(i).getDateLimit()) / 7 + " week(s)";
                }
                else{
                    row[2] = Integer.valueOf(necessaryList.get(i).getDateLimit()) + " days";
                }
                
                row [3] = necessaryList.get(i).getPrice();
                model.addRow(row);
            }
        }
    }

    
    public void EditTableHeightWidth(JTable tmp){
        //edit size of column
        tmp.getTableHeader().setFont(new Font("Fredoka One", Font.PLAIN, 16));
        tmp.getTableHeader().setForeground(PanelDetailFormList.getBackground());
        final TableColumnModel columnModel = tmp.getColumnModel();
        for (int column = 0; column < tmp.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < tmp.getRowCount(); row++) {
                TableCellRenderer renderer = tmp.getCellRenderer(row, column);
                Component comp = tmp.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
        
        for (int row = 0; row < tmp.getRowCount(); row++) {
            int rowHeight = tmp.getRowHeight();
            for (int column = 0; column < tmp.getColumnCount(); column++){
                Component comp = tmp.prepareRenderer(tmp.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
            tmp.setRowHeight(row, rowHeight);
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

        PanelSupliesList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableList = new javax.swing.JTable();
        BtnInstantFood = new javax.swing.JLabel();
        PanelDetailFormList = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        Limit = new javax.swing.JTextField();
        Expired = new javax.swing.JTextField();
        Price = new javax.swing.JTextField();
        BtnEssenials = new javax.swing.JLabel();
        BtnFruits = new javax.swing.JLabel();
        SortComboBox = new CustomComboBox.ComboBox();
        BtnSort = new javax.swing.JLabel();
        BtnAdd = new javax.swing.JLabel();
        BtnSave = new javax.swing.JLabel();
        BtnBack = new javax.swing.JLabel();
        BtnRemove = new javax.swing.JLabel();
        BtnRefresh = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        BtnSearch = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelSupliesList.setBackground(new java.awt.Color(173, 0, 0));
        PanelSupliesList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableList.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        TableList.setForeground(new java.awt.Color(255, 255, 255));
        TableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Limit", "Expired", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableList.setGridColor(new java.awt.Color(255, 255, 255));
        TableList.setShowGrid(true);
        TableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableList);

        PanelSupliesList.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 510, 410));

        getContentPane().add(PanelSupliesList, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 220, 530, 430));

        BtnInstantFood.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnInstantFoodMouseClicked(evt);
            }
        });
        getContentPane().add(BtnInstantFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 180, 40));

        PanelDetailFormList.setBackground(new java.awt.Color(173, 0, 0));
        PanelDetailFormList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NAME:");
        PanelDetailFormList.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 30));

        jLabel3.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("LIMIT:");
        PanelDetailFormList.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel4.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("EXPIRED:");
        PanelDetailFormList.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel5.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("PRiCE:");
        PanelDetailFormList.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        Name.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        Name.setForeground(new java.awt.Color(255, 255, 255));
        Name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        Name.setOpaque(false);
        PanelDetailFormList.add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 270, -1));

        Limit.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        Limit.setForeground(new java.awt.Color(255, 255, 255));
        Limit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Limit.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        Limit.setOpaque(false);
        PanelDetailFormList.add(Limit, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 270, -1));

        Expired.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        Expired.setForeground(new java.awt.Color(255, 255, 255));
        Expired.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Expired.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        Expired.setOpaque(false);
        Expired.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExpiredActionPerformed(evt);
            }
        });
        PanelDetailFormList.add(Expired, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 270, -1));

        Price.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        Price.setForeground(new java.awt.Color(255, 255, 255));
        Price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Price.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        Price.setOpaque(false);
        PanelDetailFormList.add(Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 270, -1));

        getContentPane().add(PanelDetailFormList, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 410, 370));

        BtnEssenials.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnEssenialsMouseClicked(evt);
            }
        });
        getContentPane().add(BtnEssenials, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 650, 160, 30));

        BtnFruits.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnFruitsMouseClicked(evt);
            }
        });
        getContentPane().add(BtnFruits, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 100, 30));

        SortComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High - Low", "Low - High", "Name", "Limit", "Expired" }));
        SortComboBox.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        SortComboBox.setOpaque(false);
        SortComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SortComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(SortComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 160, 140, -1));

        BtnSort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSortMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 160, 50, 50));

        BtnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAddMouseClicked(evt);
            }
        });
        getContentPane().add(BtnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 530, 60, 50));

        BtnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSaveMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 530, 50, 50));

        BtnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackMouseClicked(evt);
            }
        });
        getContentPane().add(BtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 50));

        BtnRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRemoveMouseClicked(evt);
            }
        });
        getContentPane().add(BtnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 530, 60, 50));

        BtnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRefreshMouseClicked(evt);
            }
        });
        getContentPane().add(BtnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 30, 40));

        txtSearch.setFont(new java.awt.Font("Fredoka One", 0, 18)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(0, 51, 51));
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtSearch.setOpaque(false);
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 610, 210, 40));

        BtnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSearchMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 610, 50, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/Manager/ManagerHomePage/ManagerSupliesBackGround.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnInstantFoodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnInstantFoodMouseClicked
        // TODO add your handling code here:
        Name.setText("");
        Limit.setText("");
        Expired.setText("");
        Price.setText("");
        
        type =1;
        
        //
        PanelDetailFormList.setBackground(new Color (221, 174, 11));
        PanelSupliesList.setBackground(new Color (221, 174, 11));
        TableList.setBackground(new Color (221, 174, 11));
        refreshJTable();
        ShowInstantFoodsList();
        EditTableHeightWidth(TableList);
        
    }//GEN-LAST:event_BtnInstantFoodMouseClicked

    private void BtnEssenialsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnEssenialsMouseClicked
        // TODO add your handling code here:
        Name.setText("");
        Limit.setText("");
        Expired.setText("");
        Price.setText("");
        
        type = 3;
        //
        PanelDetailFormList.setBackground(new Color (22, 41, 66));
        PanelSupliesList.setBackground(new Color (22, 41, 66));
        TableList.setBackground(new Color (22, 41, 66));
        refreshJTable();
        ShowEssentialList();
        EditTableHeightWidth(TableList);
    }//GEN-LAST:event_BtnEssenialsMouseClicked

    private void BtnFruitsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnFruitsMouseClicked
        // TODO add your handling code here:
        Name.setText("");
        Limit.setText("");
        Expired.setText("");
        Price.setText("");
        
        type = 2;
        //
        PanelDetailFormList.setBackground(new Color (173, 0, 0));
        PanelSupliesList.setBackground(new Color (173, 0, 0));
        TableList.setBackground(new Color (173, 0, 0));
        refreshJTable();
        ShowFruitList();
        EditTableHeightWidth(TableList);
    }//GEN-LAST:event_BtnFruitsMouseClicked

    private void TableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableListMouseClicked
        // TODO add your handling code here:
        int index = TableList.getSelectedRow(); // row that manager choose.
        TableModel model = TableList.getModel();
//        
//        // set value in text filed.
        Name.setText(model.getValueAt(index, 0).toString());
        Limit.setText(model.getValueAt(index, 1).toString());
        Expired.setText(model.getValueAt(index, 2).toString());
        Price.setText(model.getValueAt(index, 3).toString());
    }//GEN-LAST:event_TableListMouseClicked

    private void ExpiredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExpiredActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ExpiredActionPerformed

    private void SortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SortComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SortComboBoxActionPerformed

    
    private void SortByName(){
        for (int i = 0; i < this.necessaryList.size() - 1; i++){
            for (int j = 0; j < this.necessaryList.size() - 1 - i; j++){
                if (this.necessaryList.get(j).getName().compareTo(this.necessaryList.get(j+1).getName()) > 0){
                    Collections.swap(this.necessaryList, j, j+1); 
                }
            }
        }
    }
    
    private void SortByLimit(){
        for (int i = 0; i < this.necessaryList.size() - 1; i++){
            for (int j = 0; j < this.necessaryList.size() - 1 - i; j++){
                if (this.necessaryList.get(j).getLimitation().compareTo(this.necessaryList.get(j+1).getLimitation()) > 0){
                    Collections.swap(this.necessaryList, j, j+1); 
                }
            }
        }
    }
    
    private void SortByExpired(){
        for (int i = 0; i < this.necessaryList.size() - 1; i++){
            for (int j = 0; j < this.necessaryList.size() - 1 - i; j++){
                if (Integer.valueOf(this.necessaryList.get(j).getDateLimit()) > Integer.valueOf(this.necessaryList.get(j+1).getDateLimit())){
                    Collections.swap(this.necessaryList, j, j+1); 
                }
            }
        }
    }
    
    private void SortByLowHigh(){
        for (int i = 0; i < this.necessaryList.size() - 1; i++){
            for (int j = 0; j < this.necessaryList.size() - 1 - i; j++){
                if (Integer.valueOf(this.necessaryList.get(j).getPrice()) > Integer.valueOf(this.necessaryList.get(j+1).getPrice())){
                    Collections.swap(this.necessaryList, j, j+1); 
                }
            }
        }
    }
    
    private void SortByHighLow(){
        for (int i = 0; i < this.necessaryList.size() - 1; i++){
            for (int j = 0; j < this.necessaryList.size() - 1 - i; j++){
                if (Integer.valueOf(this.necessaryList.get(j).getPrice()) < Integer.valueOf(this.necessaryList.get(j+1).getPrice())){
                    Collections.swap(this.necessaryList, j, j+1); 
                }
            }
        }
    }
    
    
    private void BtnSortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSortMouseClicked
        // TODO add your handling code here:
        String data = SortComboBox.getSelectedItem().toString();
        if (data.compareTo("Name") == 0){
            SortByName();
        }
        else if (data.compareTo("Limit") == 0){
            SortByLimit();
        }
        else if (data.compareTo("Expired") == 0){
            SortByExpired();
        }
        else if (data.compareTo("High - Low") == 0){
            SortByHighLow();
        }
        else if (data.compareTo("Low - High") == 0){
            SortByLowHigh();
        }
        
        
        refreshJTable();
        
        if (type == 1){
            ShowInstantFoodsList();
        }
        else if(type == 2){
            ShowFruitList();
        }
        else if (type == 3){
            ShowEssentialList();
        } 
        else if (type == 4){
            ShowSearchFoodsList();
        } 
        EditTableHeightWidth(TableList);
    }//GEN-LAST:event_BtnSortMouseClicked

    private int checkExistSupply(String name){
        for (int i = 0; i < this.necessaryList.size(); i++){
            if (this.necessaryList.get(i).getName().contains(name)== true){
                return i;
            }
        }
        return -1;
    }
    private void BtnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddMouseClicked
        // TODO add your handling code here:
        String name = Name.getText();
        String limit = Limit.getText();
        String expired = Expired.getText();
        String price = Price.getText();
        
        if (name.isEmpty() || limit.isEmpty() || expired.isEmpty() || price.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide full information!");
            return;
        }
        
        if (checkExistSupply(name) != -1){
            JOptionPane.showMessageDialog(this, "Supply already existed!");
            return;
        }
        
        int size = this.necessaryList.size() +1;
        String ID = "N0"+ size;
        Necessary tmp = new Necessary (ID, name, limit, expired, price, ""+type);
        this.necessaryList.add(tmp);
        
        refreshJTable();
        if (type == 1){
            ShowInstantFoodsList();
        }
        else if(type == 2){
            ShowFruitList();
        }
        else if (type == 3){
            ShowEssentialList();
        }    
        EditTableHeightWidth(TableList);
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();
           
            String necesscary = " insert into Necessaries (NecessariesID, Name, TimeLimited, DateLimited, Price, Type)"
                                                + " values ('"+ID+"', '"+name+"', '"+limit+"','"+expired+"', '"+price+"', '"+type+"')";
            state.execute(necesscary);
            connect.close();
            JOptionPane.showMessageDialog(this, "Add new supply successful!");
        }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_BtnAddMouseClicked

    private void BtnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSaveMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        String name = Name.getText();
        String limit = Limit.getText();
        String expired = Expired.getText();
        String price = Price.getText();
        
        if (name.isEmpty() || limit.isEmpty() || expired.isEmpty() || price.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide full information!");
            return;
        }
        
        int index = checkExistSupply(name);
        if ( index == -1){
            JOptionPane.showMessageDialog(this, "Supply does not existed!");
            return;
        }
        
        if ( expired.length() > 2){
            JOptionPane.showMessageDialog(this, "Please perform Expired in days!");
            return;
        }
        else{
            this.necessaryList.get(index).setName(name);
            this.necessaryList.get(index).setDateLimit(expired);
            this.necessaryList.get(index).setLimitation(limit);
            this.necessaryList.get(index).setPrice(price);
            String id = this.necessaryList.get(index).getID();
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state = connect.createStatement();

                String necesscary = "Update Necessaries set Name = '"+name+"', TimeLimited = '"+limit+"', DateLimited = '"+expired+"', Price = '"+price+"' where NecessariesID = '"+id+"'";
                state.execute(necesscary);
                connect.close();
                JOptionPane.showMessageDialog(this, "Save successfully!");
            }catch(Exception e){
               System.out.println(e.getMessage());
            }
            
        refreshJTable();
        if (type == 1){
            ShowInstantFoodsList();
        }
        else if(type == 2){
            ShowFruitList();
        }
        else if (type == 3){
            ShowEssentialList();
        }    
        else if (type == 4){
            ShowSearchFoodsList();
        }  
        EditTableHeightWidth(TableList);
        }
    }//GEN-LAST:event_BtnSaveMouseClicked

    private void BtnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackMouseClicked
        // TODO add your handling code here:
        ManagerHomePage home = new ManagerHomePage();
        home.show();
        dispose();
    }//GEN-LAST:event_BtnBackMouseClicked

    private void BtnRemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRemoveMouseClicked
        // TODO add your handling code here:
        String name = Name.getText();
        String limit = Limit.getText();
        String expired = Expired.getText();
        String price = Price.getText();
        
        if (name.isEmpty() || limit.isEmpty() || expired.isEmpty() || price.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide full information!");
            return;
        }
        
        int index = checkExistSupply(name);
        if ( index == -1){
            JOptionPane.showMessageDialog(this, "Supply does not exist!");
            return;
        }
        String[] options = new String[2];
        options[0] = "Yes";
        options[1] = "No";
        int choice = JOptionPane.showOptionDialog(null, "Do you want to remove this supply?", "Remove Confirm", 2, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        if (choice == 0){
            this.necessaryList.remove(index);
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state = connect.createStatement();

                String necesscary = "Update Necessaries set Type = '-1' where Name = '"+name+"'";
                state.execute(necesscary);
                connect.close();
                JOptionPane.showMessageDialog(this, "Remove successfully!");

            }catch(Exception e){
               System.out.println(e.getMessage());
            }

            refreshJTable();
            if (type == 1){
                ShowInstantFoodsList();
            }
            else if(type == 2){
                ShowFruitList();
            }
            else if (type == 3){
                ShowEssentialList();
            }    
            else if (type == 4){
                ShowSearchFoodsList();
            }  
            EditTableHeightWidth(TableList);
        }
        return;
    }//GEN-LAST:event_BtnRemoveMouseClicked

    private void BtnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRefreshMouseClicked
        // TODO add your handling code here:
        Name.setText("");
        Limit.setText("");
        Expired.setText("");
        Price.setText("");
    }//GEN-LAST:event_BtnRefreshMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void BtnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSearchMouseClicked
        // TODO add your handling code here:
        type = 4;
        this.searchResult.removeAll(searchResult);
        String content = txtSearch.getText();
        if (content.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please input information you want to search!");
            return;
        }else{
            
            for(int i = 0; i < this.necessaryList.size(); i++){
                if (this.necessaryList.get(i).getName().contains(content)){
                    this.searchResult.add(this.necessaryList.get(i));
                }
            }

            if (this.searchResult.size() == 0){
                JOptionPane.showMessageDialog(this, "No information found!");
            }
            else{
                refreshJTable();
                ShowSearchFoodsList();
            }
        }
    }//GEN-LAST:event_BtnSearchMouseClicked

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
            java.util.logging.Logger.getLogger(ManagerSupplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerSupplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerSupplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerSupplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerSupplies().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnAdd;
    private javax.swing.JLabel BtnBack;
    private javax.swing.JLabel BtnEssenials;
    private javax.swing.JLabel BtnFruits;
    private javax.swing.JLabel BtnInstantFood;
    private javax.swing.JLabel BtnRefresh;
    private javax.swing.JLabel BtnRemove;
    private javax.swing.JLabel BtnSave;
    private javax.swing.JLabel BtnSearch;
    private javax.swing.JLabel BtnSort;
    private javax.swing.JTextField Expired;
    private javax.swing.JTextField Limit;
    private javax.swing.JTextField Name;
    private javax.swing.JPanel PanelDetailFormList;
    private javax.swing.JPanel PanelSupliesList;
    private javax.swing.JTextField Price;
    private CustomComboBox.ComboBox SortComboBox;
    private javax.swing.JTable TableList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
