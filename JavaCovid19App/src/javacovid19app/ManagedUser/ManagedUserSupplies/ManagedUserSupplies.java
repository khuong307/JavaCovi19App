/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javacovid19app.ManagedUser.ManagedUserSupplies;

import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javacovid19app.ManagedUser.ManagedUserHomePage.*;
import javacovid19app.Manager.ManagerHomePage.DataClasses.Necessary;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author DELL
 */
public class ManagedUserSupplies extends javax.swing.JFrame {

    private ArrayList<Necessary> lst = new ArrayList<Necessary>(); // danh sách tất cả nhu yếu phẩm
    private ArrayList<Necessary> temp = new ArrayList<Necessary>(); // danh sách đang hiển thị
    
    public ManagedUserSupplies() {
        initComponents();
        TextSearch.setOpaque(false);
        TextSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setData();
        refreshJTable();
        showData(this.lst);
        EditTableHeightWidth(this.TabSupplies);
        temp = lst;
    }
    
    public void refreshJTable(){
        DefaultTableModel model = (DefaultTableModel)TabSupplies.getModel();
        while (model.getRowCount()>0){
            model.removeRow(0);
        }
    }
    
    public void setData(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select * from Necessaries ";
            ResultSet res = state.executeQuery(sql);
            
            while (res.next()){
                Necessary tmp = new Necessary (res.getString("NecessariesID"), res.getString("Name"),res.getString("TimeLimited"), res.getString("DateLimited"), res.getString("Price"), res.getString("Type")); 
                this.lst.add(tmp);
            }
            
            connect.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagedUserSupplies.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManagedUserSupplies.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showData(ArrayList<Necessary> lst){
        DefaultTableModel model = (DefaultTableModel)TabSupplies.getModel();
        Object[] row = new Object[4];
        
        for (int i = 0; i < lst.size(); i++){
            row[0] = i+1;
            row[1] = lst.get(i).getID();
            row[2] = lst.get(i).getName();
            row[3] = lst.get(i).getPrice();
            model.addRow(row);
        }
    }
    
    public void EditTableHeightWidth(JTable tmp){
        //edit size of column
        tmp.getTableHeader().setFont(new Font("Fredoka One", Font.PLAIN, 16));
        //tmp.getTableHeader().setForeground(jScrollPane2.getBackground());
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
    
    public void sortById(boolean type){
        if (type){
            for (int i = 0; i < temp.size()-1; i++){
                for (int j = i+1; j < temp.size(); j++){
                    if (temp.get(i).getID().compareTo(temp.get(j).getID()) > 0)
                        Collections.swap(temp, i, j);
                }
            }
        }
        else{
            for (int i = 0; i < temp.size()-1; i++){
                for (int j = i+1; j < temp.size(); j++){
                    if (temp.get(i).getID().compareTo(temp.get(j).getID()) < 0)
                        Collections.swap(temp, i, j);
                }
            }
        }
    }
    
    public void sortByName(boolean type){
        if (type){
            for (int i = 0; i < temp.size()-1; i++){
                for (int j = i+1; j < temp.size(); j++){
                    if (temp.get(i).getName().compareTo(temp.get(j).getName()) > 0)
                        Collections.swap(temp, i, j);
                }
            }
        }
        else{
            for (int i = 0; i < temp.size()-1; i++){
                for (int j = i+1; j < temp.size(); j++){
                    if (temp.get(i).getName().compareTo(temp.get(j).getName()) < 0)
                        Collections.swap(temp, i, j);
                }
            }
        }
    }
    
    public void sortByPrice(boolean type){
        if (type){
            for (int i = 0; i < temp.size()-1; i++){
                for (int j = i+1; j < temp.size(); j++){
                    if (Integer.valueOf(temp.get(i).getPrice()) > Integer.valueOf(temp.get(j).getPrice()))
                        Collections.swap(temp, i, j);
                }
            }
        }
        else{
            for (int i = 0; i < temp.size()-1; i++){
                for (int j = i+1; j < temp.size(); j++){
                    if (Integer.valueOf(temp.get(i).getPrice()) < Integer.valueOf(temp.get(j).getPrice()))
                        Collections.swap(temp, i, j);
                }
            }
        }
    }
    
    public ArrayList<Necessary> filterType(String type){
        ArrayList<Necessary> res = new ArrayList<Necessary>();
        for (int i = 0; i < temp.size(); i++){
            if (temp.get(i).getType().equals(type)){
                res.add(temp.get(i));
            }
        }
        return res;
    }
    
    public ArrayList<Necessary> filterPrice(int min, int max){
        ArrayList<Necessary> res = new ArrayList<Necessary>();
        for (int i = 0; i < temp.size(); i++){
            int price = Integer.valueOf(temp.get(i).getPrice());
            if (price >= min && price <= max){
                res.add(temp.get(i));
            }
        }
        return res;
    }
    
    public int getMaxPrice(){
        int max = 0;
        for (int i = 0; i < lst.size(); i++){
            if (max < Integer.valueOf(lst.get(i).getPrice()))
                max = Integer.valueOf(lst.get(i).getPrice());
        }
        return max;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        TabSupplies = new javax.swing.JTable();
        TextSearch = new javax.swing.JTextField();
        BtnSearch = new javax.swing.JLabel();
        BtnSort = new javax.swing.JLabel();
        SortComboBox = new CustomComboBox.ComboBox();
        FilterComboBox = new CustomComboBox.ComboBox();
        BtnFilter = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackMouseClicked(evt);
            }
        });
        getContentPane().add(BtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 60, 60));

        TabSupplies.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        TabSupplies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "ID", "Name", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TabSupplies);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 180, 470, 480));

        TextSearch.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        getContentPane().add(TextSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 310, 40));

        BtnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSearchMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 90, 50, 50));

        BtnSort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSortMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 50, 50));

        SortComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID Ascending", "ID Descending", "Name Ascending", "Name Descending", "Price Ascending", "Price Descending" }));
        SortComboBox.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        SortComboBox.setOpaque(false);
        getContentPane().add(SortComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 140, -1));

        FilterComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Instant Food", "Fruit", "Essential", "Low Price", "Medium Price", "High Price" }));
        FilterComboBox.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        FilterComboBox.setOpaque(false);
        getContentPane().add(FilterComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 140, -1));

        BtnFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnFilterMouseClicked(evt);
            }
        });
        getContentPane().add(BtnFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 450, 40, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\DELL\\Documents\\GitHub\\JavaCovi19App\\JavaCovid19App\\src\\javacovid19app\\ManagedUser\\ManagedUserSupplies\\SuppliesManagedUserBackground.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackMouseClicked
        // TODO add your handling code here:
        ManagedUserHomePage homepage = new ManagedUserHomePage();
        homepage.show();
        dispose();
    }//GEN-LAST:event_BtnBackMouseClicked

    private void BtnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSearchMouseClicked
        // TODO add your handling code here:
        String name = TextSearch.getText();
        ArrayList<Necessary> res = new ArrayList<Necessary>();
        if (name.isEmpty()){
            refreshJTable();
            showData(temp);
            EditTableHeightWidth(this.TabSupplies);
            temp = lst;
        }
        else{
            for (int i = 0; i < this.temp.size(); i++){
                Necessary tmp = this.temp.get(i);
                String necName = tmp.getName().split(" \\(")[0];
                if (necName.toLowerCase().contains(name.toLowerCase()))
                    res.add(tmp);
            }
            refreshJTable();
            showData(res);
            EditTableHeightWidth(this.TabSupplies);
            temp = res;
        }
    }//GEN-LAST:event_BtnSearchMouseClicked

    private void BtnSortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSortMouseClicked
        // TODO add your handling code here:
        String typeSort = SortComboBox.getSelectedItem().toString();
        if (typeSort.equals("ID Ascending"))
            sortById(true);
        
        else if (typeSort.equals("ID Descending"))
            sortById(false);

        else if (typeSort.equals("Name Ascending"))
            sortByName(true);
        
        else if (typeSort.equals("Name Descending"))
            sortByName(false);
        
        else if (typeSort.equals("Price Ascending"))
            sortByPrice(true);
        
        else if (typeSort.equals("Price Descending"))
            sortByPrice(false);
        
        refreshJTable();
        showData(temp);
        EditTableHeightWidth(this.TabSupplies);
    }//GEN-LAST:event_BtnSortMouseClicked

    private void BtnFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnFilterMouseClicked
        // TODO add your handling code here:
        String typeFilter = FilterComboBox.getSelectedItem().toString();
        if (typeFilter.equals("Instant Food"))
            temp = filterType("1");
        
        else if (typeFilter.equals("Fruit"))
            temp = filterType("2");
        
        else if (typeFilter.equals("Essential"))
            temp = filterType("3");
        
        else if (typeFilter.equals("Low Price"))
            temp = filterPrice(0, 9999);
        
        else if (typeFilter.equals("Medium Price"))
            temp = filterPrice(10000, 50000);
        
        else if (typeFilter.equals("High Price")){
            int max = getMaxPrice();
            temp = filterPrice(50001, max);
        }
        
        refreshJTable();
        showData(temp);
        EditTableHeightWidth(this.TabSupplies);
    }//GEN-LAST:event_BtnFilterMouseClicked

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
            java.util.logging.Logger.getLogger(ManagedUserSupplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagedUserSupplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagedUserSupplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagedUserSupplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagedUserSupplies().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnBack;
    private javax.swing.JLabel BtnFilter;
    private javax.swing.JLabel BtnSearch;
    private javax.swing.JLabel BtnSort;
    private CustomComboBox.ComboBox FilterComboBox;
    private CustomComboBox.ComboBox SortComboBox;
    private javax.swing.JTable TabSupplies;
    private javax.swing.JTextField TextSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
