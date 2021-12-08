/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javacovid19app.Admin.AdminHomePage.TreatmentFacilitiesManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javacovid19app.Admin.AdminHomePage.DataClasses.treatmentFacility;
import javax.swing.JOptionPane;

/**
 *
 * @author tongt
 */
public class TreatmentFacilitiesManagement extends javax.swing.JFrame {
    private ArrayList<treatmentFacility> treatmentFacility= new ArrayList();
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
        this.setTitle("Supplies Management");
        getTreatmentFacility();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Name = new javax.swing.JTextField();
        PresentQuantity = new javax.swing.JTextField();
        MaximumQuantity = new javax.swing.JTextField();
        BtnAdd = new javax.swing.JLabel();
        BtnSave = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameActionPerformed(evt);
            }
        });
        getContentPane().add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 160, 40));
        getContentPane().add(PresentQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 160, 40));
        getContentPane().add(MaximumQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 160, 40));

        BtnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAddMouseClicked(evt);
            }
        });
        getContentPane().add(BtnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 424, 120, 50));

        BtnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSaveMouseClicked(evt);
            }
        });
        getContentPane().add(BtnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 120, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/Admin/AdminHomePage/TreatmentFacilitiesManagement/TreatMentManagementBackground(960x540).png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameActionPerformed
    private int checkExistFacility(String name){
        for (int i = 0; i < this.treatmentFacility.size(); i++){
            if (this.treatmentFacility.get(i).getName().compareTo(name)==0){
                return i;
            }
        }
        return -1;
    }
    private void BtnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddMouseClicked
        String name = Name.getText();
        String maximumQuantityText = MaximumQuantity.getText();
        int maximumQuantity=Integer.parseInt(maximumQuantityText);
        
        String presentQuantityText = PresentQuantity.getText();
        int presentQuantity= Integer.parseInt(presentQuantityText);
        if (name.isEmpty() || maximumQuantityText.isEmpty() || presentQuantityText.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide full information!");
            return;
        }
        
        if (checkExistFacility(name) != -1){
            JOptionPane.showMessageDialog(this, " Facility existed!");
            return;
        }
        
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
        
        
        
        
    }//GEN-LAST:event_BtnAddMouseClicked

    private void BtnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSaveMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        String name = Name.getText();
        String maximumQuantityText = MaximumQuantity.getText();
        int maximumQuantity=Integer.parseInt(maximumQuantityText);
        
        String presentQuantityText = PresentQuantity.getText();
        int presentQuantity= Integer.parseInt(presentQuantityText);
        if (name.isEmpty() || maximumQuantityText.isEmpty() || presentQuantityText.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide full information!");
            return;
        }
        
        int index = checkExistFacility(name);
       if ( index == -1){
            JOptionPane.showMessageDialog(this, "Facility does not existed!");
            return;
        }
            
            this.treatmentFacility.get(index).setName(name);
            this.treatmentFacility.get(index).setQuantity(maximumQuantity);
            this.treatmentFacility.get(index).setPresentQuantity(presentQuantity);                     
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                Statement state = connect.createStatement();

                String facility = "Update TreatmentFacility set Name = '"+name+"', Quantity = '"+maximumQuantity+"', PresentQuantity = '"+presentQuantity+"'";
                state.execute(facility);
                connect.close();
                JOptionPane.showMessageDialog(this, "Save successfully!");
            }catch(Exception e){
               System.out.println(e.getMessage());
            }
            
        
        
    }//GEN-LAST:event_BtnSaveMouseClicked

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
    private javax.swing.JLabel BtnSave;
    private javax.swing.JTextField MaximumQuantity;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField PresentQuantity;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
