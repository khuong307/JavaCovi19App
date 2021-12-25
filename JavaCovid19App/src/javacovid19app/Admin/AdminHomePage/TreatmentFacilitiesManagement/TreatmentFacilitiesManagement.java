/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javacovid19app.Admin.AdminHomePage.TreatmentFacilitiesManagement;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javacovid19app.Admin.AdminHomePage.AdminHomePage;
import javacovid19app.Admin.AdminHomePage.DataClasses.treatmentFacility;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tongt
 */
public class TreatmentFacilitiesManagement extends javax.swing.JFrame {
    private ArrayList<treatmentFacility> treatmentFacility= new ArrayList();
    private int selectedIndex;
    /**
     * Creates new form TreatmentFacilitiesManagement
     */
    public void getTreatmentFacility(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();
            String sql = "Select * from TreatmentFacility ";
            ResultSet res = state.executeQuery(sql);
            
            treatmentFacility tmp;
            while(res.next()){
                tmp = new treatmentFacility(res.getString("FacilityID"), res.getString("Name"),res.getInt("Quantity"), res.getInt("PresentQuantity")); 
                this.treatmentFacility.add(tmp);
            }
            connect.close();
            
           }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }   
    public TreatmentFacilitiesManagement() {
        initComponents();
        this.setResizable(false);
        this.setTitle("Treatment Facilites Management");
        getTreatmentFacility();
        TreatmentList.setFont(new java.awt.Font("Fredoka One", 0, 18));
        TreatmentList.setBackground(new Color (221, 174, 11));
        FacilityName.setFont(new java.awt.Font("Fredoka One", 0, 18));
        PresentQuantity.setFont(new java.awt.Font("Fredoka One", 0, 18));
        MaximumQuantity.setFont(new java.awt.Font("Fredoka One", 0, 18));
        TreatmentList.getTableHeader().setFont(new Font("Fredoka One",Font.PLAIN,16));
        ShowInstantTreatment();
    }
    public void ShowInstantTreatment(){
        
        DefaultTableModel model = (DefaultTableModel)TreatmentList.getModel();
        Object[] row = new Object [3];
        System.out.print(this.treatmentFacility.size());
        for (int i = 0; i < this.treatmentFacility.size(); i++){
            
                row[0] = treatmentFacility.get(i).getName();
                row[1] = treatmentFacility.get(i).getQuantity();
                row[2] = treatmentFacility.get(i).getPresentQuantity();
                model.addRow(row);
            
        }
    }
    private void refreshJTable(){
        DefaultTableModel  model = (DefaultTableModel)TreatmentList.getModel();
        while (model.getRowCount()>0){
            model.removeRow(0);
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

        PresentQuantity = new javax.swing.JTextField();
        MaximumQuantity = new javax.swing.JTextField();
        BtnAdd = new javax.swing.JLabel();
        BtnSave = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TreatmentList = new javax.swing.JTable();
        FacilityName = new javax.swing.JTextField();
        BtnBack = new javax.swing.JLabel();
        BtnRefresh = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PresentQuantity.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        PresentQuantity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PresentQuantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        PresentQuantity.setOpaque(false);
        getContentPane().add(PresentQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 160, 40));

        MaximumQuantity.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        MaximumQuantity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        MaximumQuantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        MaximumQuantity.setOpaque(false);
        getContentPane().add(MaximumQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 160, 40));

        BtnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAddMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnAddMouseEntered(evt);
            }
        });
        getContentPane().add(BtnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 424, 120, 40));

        BtnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSaveMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 120, 40));

        TreatmentList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Facilities's name", "Maximum quantity", "Present Quantity"
            }
        ));
        TreatmentList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TreatmentListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TreatmentList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 480, 340));

        FacilityName.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        FacilityName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FacilityName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        FacilityName.setOpaque(false);
        getContentPane().add(FacilityName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 160, 40));

        BtnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBackMouseClicked(evt);
            }
        });
        getContentPane().add(BtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 70, 60));

        BtnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRefreshMouseClicked(evt);
            }
        });
        getContentPane().add(BtnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 50, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/Admin/AdminHomePage/TreatmentFacilitiesManagement/TreatMentManagementBackground(960x540).png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private int checkExistFacility(String name){
        for (int i = 0; i < this.treatmentFacility.size(); i++){
            if (this.treatmentFacility.get(i).getName().compareTo(name)==0){
                return i;
            }
        }
        return -1;
    }
    private void BtnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddMouseClicked
        String name = FacilityName.getText();
        String maximumQuantityText = MaximumQuantity.getText();           
        String presentQuantityText = PresentQuantity.getText();       
        if (name.isEmpty() || maximumQuantityText.isEmpty() || presentQuantityText.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide full information!");
            return;
        }
        
        if (checkExistFacility(name) != -1){
            JOptionPane.showMessageDialog(this, " Facility existed!");
            return;
        }
        int maximumQuantity=Integer.parseInt(maximumQuantityText);
        int presentQuantity= Integer.parseInt(presentQuantityText);
        int size = this.treatmentFacility.size() +1;
        String ID = "F00"+String.valueOf(size);
        treatmentFacility tmp = new treatmentFacility (ID, name, maximumQuantity, presentQuantity);
        this.treatmentFacility.add(tmp); 
        for(int i=0;i<treatmentFacility.size();i++){
            System.out.print(treatmentFacility.get(i).getName());
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();
           
            String necesscary = " insert into TreatmentFacility (FacilityID, Name, Quantity, PresentQuantity)"
                                                + " values ('"+ID+"', '"+name+"', '"+maximumQuantity+"','"+presentQuantity+"')";
            state.execute(necesscary);
            connect.close();
            JOptionPane.showMessageDialog(this, "Add new Facility successful!");
        }catch(Exception e){
           System.out.println(e.getMessage());
        }
        refreshJTable();
        ShowInstantTreatment();
        
        
    }//GEN-LAST:event_BtnAddMouseClicked

    private void BtnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSaveMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        
        String name = FacilityName.getText();
        String maximumQuantityText = MaximumQuantity.getText();
        
        
        String presentQuantityText = PresentQuantity.getText();
        
        System.out.println(name);
        if (name.isEmpty() || maximumQuantityText.isEmpty() || presentQuantityText.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide full information!");
            return;
        }
        else{
            int maximumQuantity=Integer.parseInt(maximumQuantityText);
            int presentQuantity= Integer.parseInt(presentQuantityText);
            int index=checkExistFacility(this.treatmentFacility.get(selectedIndex).getName());
            System.out.print(this.treatmentFacility.get(index).getName());
            this.treatmentFacility.get(index).setName(name);
            this.treatmentFacility.get(index).setQuantity(maximumQuantity);
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state = connect.createStatement();

                String necesscary = "Update TreatmentFacility set Name = '"+name+"', Quantity = '"+maximumQuantity+"' where Name = '"+this.treatmentFacility.get(index).getName()+"'";
                state.execute(necesscary);
                connect.close();
                JOptionPane.showMessageDialog(this, "Save successfully!");
                refreshJTable();
                ShowInstantTreatment();
            }catch(Exception e){
               System.out.println(e.getMessage());
            }
        }  
    }//GEN-LAST:event_BtnSaveMouseClicked

    private void BtnAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAddMouseEntered

    private void TreatmentListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TreatmentListMouseClicked
        // TODO add your handling code here:
        int index=TreatmentList.getSelectedRow();
        this.selectedIndex=index;     
        System.out.print(this.selectedIndex);
        FacilityName.setText(treatmentFacility.get(index).getName());
        String PresentQuantityString = String.valueOf(treatmentFacility.get(index).getPresentQuantity());
        PresentQuantity.setText(PresentQuantityString);
        String MaximumQuantityString = String.valueOf(treatmentFacility.get(index).getQuantity());
        MaximumQuantity.setText(MaximumQuantityString);
    }//GEN-LAST:event_TreatmentListMouseClicked

    private void BtnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackMouseClicked
        // TODO add your handling code here:
        AdminHomePage homepage= new AdminHomePage();
        homepage.show();
        dispose();
    }//GEN-LAST:event_BtnBackMouseClicked

    private void BtnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRefreshMouseClicked
        // TODO add your handling code here:
        FacilityName.setText("");       
        PresentQuantity.setText("");       
        MaximumQuantity.setText("");
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
            java.util.logging.Logger.getLogger(TreatmentFacilitiesManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TreatmentFacilitiesManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TreatmentFacilitiesManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TreatmentFacilitiesManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TreatmentFacilitiesManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnAdd;
    private javax.swing.JLabel BtnBack;
    private javax.swing.JLabel BtnRefresh;
    private javax.swing.JLabel BtnSave;
    private javax.swing.JTextField FacilityName;
    private javax.swing.JTextField MaximumQuantity;
    private javax.swing.JTextField PresentQuantity;
    private javax.swing.JTable TreatmentList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}