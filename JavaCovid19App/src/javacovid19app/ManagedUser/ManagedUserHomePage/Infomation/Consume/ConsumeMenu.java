package javacovid19app.ManagedUser.ManagedUserHomePage.Infomation.Consume;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.Connection;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class ConsumeMenu extends javax.swing.JFrame {
    private ArrayList <ConsumeHistory> consumeHistoryList=new ArrayList<> ();
    private ArrayList <Necessary>necessaryList = new ArrayList<> ();
    
    String userID="";
    
    /**
     * Creates new form ConsumeMenu
     */
    public ConsumeMenu() {
        initComponents();
        this.setResizable(false); // can not fix size of a Frame;
        this.setTitle("Consume History");
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
        consumeIDTextField.setEditable(false);
        supplyNameTextField.setEditable(false);
        typeSupTextField.setEditable(false);
        buyTimeTextField.setEditable(false);
        priceTextField.setEditable(false);
        limitedTextField.setEditable(false);
        timeLimitedTextField.setEditable(false);
    }
    
    public ConsumeMenu(String username) {
        initComponents();
        this.setResizable(false); // can not fix size of a Frame;
        this.setTitle("Consume History");
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
        this.userID=username;
        consumeIDTextField.setEditable(false);
        supplyNameTextField.setEditable(false);
        typeSupTextField.setEditable(false);
        buyTimeTextField.setEditable(false);
        priceTextField.setEditable(false);
        limitedTextField.setEditable(false);
        timeLimitedTextField.setEditable(false);
        
        getConsumeHistoryList();
        getNecessaryList();
        show_historyConsume();
        //edit size of column
        consumeHistoryTable.getTableHeader().setFont(new Font("Fredoka One", Font.PLAIN, 14));
        final TableColumnModel columnModel = consumeHistoryTable.getColumnModel();
        for (int column = 0; column < consumeHistoryTable.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < consumeHistoryTable.getRowCount(); row++) {
                TableCellRenderer renderer = consumeHistoryTable.getCellRenderer(row, column);
                Component comp = consumeHistoryTable.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    
    
    public void show_historyConsume(){
        DefaultTableModel model=(DefaultTableModel)consumeHistoryTable.getModel();
        Object row[]=new Object[7];
        
        for (int i=0;i<consumeHistoryList.size();i++){
            row[0]=consumeHistoryList.get(i).getConsumeID();
            row[3]=consumeHistoryList.get(i).getBuyTime();
            for(int j=0;j<necessaryList.size();j++){
                if(consumeHistoryList.get(i).getNecessaryID().compareTo(necessaryList.get(j).getNecessaryID())==0){
                    row[1]=necessaryList.get(j).getName();
                    row[2]=necessaryList.get(j).getType();
                    row[4]=necessaryList.get(j).getPrice();
                    row[5]=necessaryList.get(j).getLimited();
                    row[6]=necessaryList.get(j).getTimeLimited();
                    break;
                }
            }
            model.addRow(row);
        }
    }
    
    // get consume history list
    public void getConsumeHistoryList(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = true", "sql6448649", "ygTCgTJZu6");
            Statement state = (Statement) connect.createStatement();

            String sql = "Select * from ConsumeHistory where ConsumeHistory.UserID= '"+userID+"'";
            ResultSet res = state.executeQuery(sql);
            
            ConsumeHistory tmp;
            while(res.next()){
                tmp = new ConsumeHistory(res.getString("ConsumeID"), res.getString("UserID"),res.getString("NecessariesID"), res.getDate("BuyTime")); 
                this.consumeHistoryList.add(tmp);
            }
            connect.close();
           }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
    
    
    // get necessary list
    public void getNecessaryList(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = true", "sql6448649", "ygTCgTJZu6");
            Statement state = (Statement) connect.createStatement();

            String sql = "Select * from Necessaries";
            ResultSet res = state.executeQuery(sql);
            
            Necessary tmp;
            while(res.next()){
                tmp = new Necessary(res.getString("NecessariesID"), res.getString("Name"),res.getInt("TimeLimited"),res.getInt("DateLimited") ,res.getDouble("Price"),res.getInt("Type")); 
                this.necessaryList.add(tmp);
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
        consumeIDTextField = new javax.swing.JTextField();
        supplyNameTextField = new javax.swing.JTextField();
        typeSupTextField = new javax.swing.JTextField();
        buyTimeTextField = new javax.swing.JTextField();
        priceTextField = new javax.swing.JTextField();
        limitedTextField = new javax.swing.JTextField();
        timeLimitedTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        consumeHistoryTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backLabelMouseClicked(evt);
            }
        });
        getContentPane().add(backLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 80, 70));

        consumeIDTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        consumeIDTextField.setForeground(new java.awt.Color(255, 255, 255));
        consumeIDTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        consumeIDTextField.setOpaque(false);
        getContentPane().add(consumeIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 200, 30));

        supplyNameTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        supplyNameTextField.setForeground(new java.awt.Color(255, 255, 255));
        supplyNameTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        supplyNameTextField.setOpaque(false);
        getContentPane().add(supplyNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 200, 30));

        typeSupTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        typeSupTextField.setForeground(new java.awt.Color(255, 255, 255));
        typeSupTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        typeSupTextField.setOpaque(false);
        getContentPane().add(typeSupTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 200, 30));

        buyTimeTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        buyTimeTextField.setForeground(new java.awt.Color(255, 255, 255));
        buyTimeTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        buyTimeTextField.setOpaque(false);
        buyTimeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyTimeTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(buyTimeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 200, 40));

        priceTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        priceTextField.setForeground(new java.awt.Color(255, 255, 255));
        priceTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        priceTextField.setOpaque(false);
        getContentPane().add(priceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, 200, 40));

        limitedTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        limitedTextField.setForeground(new java.awt.Color(255, 255, 255));
        limitedTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        limitedTextField.setOpaque(false);
        getContentPane().add(limitedTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, 200, 30));

        timeLimitedTextField.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        timeLimitedTextField.setForeground(new java.awt.Color(255, 255, 255));
        timeLimitedTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        timeLimitedTextField.setOpaque(false);
        getContentPane().add(timeLimitedTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 590, 170, 30));

        consumeHistoryTable.setBackground(new java.awt.Color(0, 255, 51));
        consumeHistoryTable.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        consumeHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CONSUME ID", "NAME", "TYPE", "BUY TIME", "PRICE", "LIMITED", "TIME LIMITED"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        consumeHistoryTable.setOpaque(false);
        consumeHistoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                consumeHistoryTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(consumeHistoryTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 150, 750, 440));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/ManagedUser/ManagedUserHomePage/Infomation/Consume/ConsumeHistoryBackground.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLabelMouseClicked
        dispose();
    }//GEN-LAST:event_backLabelMouseClicked

    private void buyTimeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyTimeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buyTimeTextFieldActionPerformed

    private void consumeHistoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consumeHistoryTableMouseClicked
        int i = consumeHistoryTable.getSelectedRow();
        TableModel model=consumeHistoryTable.getModel();
        consumeIDTextField.setText(model.getValueAt(i, 0).toString());
        supplyNameTextField.setText(model.getValueAt(i, 1).toString());
        typeSupTextField.setText(model.getValueAt(i, 2).toString());
        buyTimeTextField.setText(model.getValueAt(i, 3).toString());
        priceTextField.setText(model.getValueAt(i, 4).toString());
        limitedTextField.setText(model.getValueAt(i, 5).toString());
        timeLimitedTextField.setText(model.getValueAt(i, 6).toString());
    }//GEN-LAST:event_consumeHistoryTableMouseClicked

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
            java.util.logging.Logger.getLogger(ConsumeMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsumeMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsumeMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsumeMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsumeMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backLabel;
    private javax.swing.JTextField buyTimeTextField;
    private javax.swing.JTable consumeHistoryTable;
    private javax.swing.JTextField consumeIDTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField limitedTextField;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JTextField supplyNameTextField;
    private javax.swing.JTextField timeLimitedTextField;
    private javax.swing.JTextField typeSupTextField;
    // End of variables declaration//GEN-END:variables
}
