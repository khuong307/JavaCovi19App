package javacovid19app.ManagedUser.ManagedUserHomePage.Infomation.Treatment;

import java.util.ArrayList;
import javacovid19app.Manager.ManagerHomePage.DataClasses.TreatmentFacility;
import javacovid19app.Manager.ManagerHomePage.DataClasses.TreatmentHistory;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Date;    


public class UserTreatmentMenu extends javax.swing.JFrame {
    String userID="";
    
    private ArrayList<TreatmentFacility> treatmentFacilityList=new ArrayList<>();
    private ArrayList<TreatmentHistory> treatmentHistoryList=new ArrayList<>();
    
    public UserTreatmentMenu() {
        initComponents();
        this.setResizable(false); // can not fix size of a Frame;
        this.setTitle("Covid 19 Treatment History");
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
        // Set all textfield can not be edited
        facilityNameTextField.setEditable(false);
        arriveTimeTextField.setEditable(false);
        leaveTimeTextField.setEditable(false);
        presentQuantityTextField.setEditable(false);
        quantityMaxTextField.setEditable(false);
    }
    
    
    public UserTreatmentMenu(String username) {
        // take the userID from another frame
        this.userID=username;
        
        initComponents();
        this.setResizable(false); // can not fix size of a Frame;
        this.setTitle("Covid 19 Treatment History");
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
        // Set all textfield can not be edited
        facilityNameTextField.setEditable(false);
        arriveTimeTextField.setEditable(false);
        leaveTimeTextField.setEditable(false);
        presentQuantityTextField.setEditable(false);
        quantityMaxTextField.setEditable(false);
        
        getTreatementHistoryList();
        getTreatementFacilityList();
        show_TreatmentCovid();
        
        
        
    }
    
    
    public void show_TreatmentCovid(){
        DefaultTableModel model=(DefaultTableModel)treatmentTable.getModel();
        Object row[]=new Object[5];
      
        for(int i=0;i<treatmentHistoryList.size();i++){
            for(int j=0;j<treatmentFacilityList.size();j++){
                if(treatmentHistoryList.get(i).getFacilityID().compareTo(treatmentFacilityList.get(j).getID())==0){
                    row[0]=treatmentFacilityList.get(j).getFacilityName();
                    row[1]=treatmentHistoryList.get(i).getArriveTime();
                    row[2]=treatmentHistoryList.get(i).getLeaveTime();
                    row[3]=treatmentFacilityList.get(j).getPresentQuantity();
                    row[4]=treatmentFacilityList.get(j).getQuantity();
                    model.addRow(row);
                }
            }
        }
    }
    
    
    
    // get Treatement History data
    public void getTreatementHistoryList(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select * from TreatmentHistory where TreatmentHistory.UserID= '"+userID+"'";
            System.out.println(sql);
            ResultSet res = state.executeQuery(sql);
            
            TreatmentHistory tmp;
            while(res.next()){
                tmp = new TreatmentHistory(res.getString("UserID"), res.getTimestamp("ArriveTime"),res.getString("FacilityID"),res.getTimestamp("LeaveTime")); 
                this.treatmentHistoryList.add(tmp);
            }
            connect.close();
           }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
    
    
    // get Treatement History data
    public void getTreatementFacilityList(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select TreatmentFacility.FacilityID,Name,Quantity,PresentQuantity from TreatmentFacility,TreatmentHistory where TreatmentHistory.UserID= '"+userID+"' and TreatmentFacility.FacilityID=TreatmentHistory.FacilityID";
            System.out.println(sql);
            ResultSet res = state.executeQuery(sql);
            
            TreatmentFacility tmp;
            while(res.next()){
                tmp = new TreatmentFacility(res.getString("FacilityID"), res.getString("Name"),res.getString("Quantity"),res.getString("PresentQuantity")); 
                this.treatmentFacilityList.add(tmp);
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

        backLabel = new javax.swing.JLabel();
        facilityNameTextField = new javax.swing.JTextField();
        arriveTimeTextField = new javax.swing.JTextField();
        leaveTimeTextField = new javax.swing.JTextField();
        presentQuantityTextField = new javax.swing.JTextField();
        quantityMaxTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        treatmentTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backLabelMouseClicked(evt);
            }
        });
        getContentPane().add(backLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 60));

        facilityNameTextField.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        getContentPane().add(facilityNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 170, 30));

        arriveTimeTextField.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        getContentPane().add(arriveTimeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 170, 30));

        leaveTimeTextField.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        getContentPane().add(leaveTimeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 170, 30));

        presentQuantityTextField.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        getContentPane().add(presentQuantityTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 399, 120, 30));

        quantityMaxTextField.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        getContentPane().add(quantityMaxTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 469, 100, 30));

        treatmentTable.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        treatmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FacilityName", "Arrive Time", "Leave Time", "Present Quantity", "Max Quantity"
            }
        ));
        treatmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treatmentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(treatmentTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 430, 280));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ASUS\\Desktop\\JavaCovi19App-Khang\\JavaCovid19App\\src\\javacovid19app\\ManagedUser\\ManagedUserHomePage\\Infomation\\Treatment\\TreatmentHistoryBackground.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLabelMouseClicked
       dispose();
    }//GEN-LAST:event_backLabelMouseClicked

    private void treatmentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treatmentTableMouseClicked
        int i = treatmentTable.getSelectedRow();
        TableModel model=treatmentTable.getModel();
        facilityNameTextField.setText(model.getValueAt(i, 0).toString());
        arriveTimeTextField.setText(model.getValueAt(i, 1).toString());
        if(model.getValueAt(i,2)!=null && model.getValueAt(i,2).toString().trim().length()!=0){
            leaveTimeTextField.setText(model.getValueAt(i, 2).toString());
        }
        else{
            leaveTimeTextField.setText("");
        }
        presentQuantityTextField.setText(model.getValueAt(i, 3).toString());
        quantityMaxTextField.setText(model.getValueAt(i, 4).toString());
    }//GEN-LAST:event_treatmentTableMouseClicked

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
            java.util.logging.Logger.getLogger(UserTreatmentMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserTreatmentMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserTreatmentMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserTreatmentMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserTreatmentMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField arriveTimeTextField;
    private javax.swing.JLabel backLabel;
    private javax.swing.JTextField facilityNameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField leaveTimeTextField;
    private javax.swing.JTextField presentQuantityTextField;
    private javax.swing.JTextField quantityMaxTextField;
    private javax.swing.JTable treatmentTable;
    // End of variables declaration//GEN-END:variables
}
