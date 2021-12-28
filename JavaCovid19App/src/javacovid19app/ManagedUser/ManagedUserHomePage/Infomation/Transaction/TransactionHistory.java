package javacovid19app.ManagedUser.ManagedUserHomePage.Infomation.Transaction;

import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class TransactionHistory extends javax.swing.JFrame {
    String userID="";
    
    private ArrayList<Transaction> transactionList=new ArrayList<>();
    
    public TransactionHistory() {
        initComponents();
        this.setResizable(false); // can not fix size of a Frame;
        this.setTitle("Transaction History");
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
        transIDTextField.setEditable(false);
        accountIDTextField.setEditable(false);
        timeTextField.setEditable(false);
        totalTextField.setEditable(false);
        balanceTextField.setEditable(false);
    }
    
    public TransactionHistory(String username) {
        initComponents();
        this.setResizable(false); // can not fix size of a Frame;
        this.setTitle("Transaction History");
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.userID=username;
        
        transIDTextField.setEditable(false);
        accountIDTextField.setEditable(false);
        timeTextField.setEditable(false);
        totalTextField.setEditable(false);
        balanceTextField.setEditable(false);
        
        getTransactionList();
        show_historyTransaction();
        
        //edit size of column
        transHistoryTable.getTableHeader().setFont(new Font("Fredoka One", Font.PLAIN, 14));
        final TableColumnModel columnModel = transHistoryTable.getColumnModel();
        for (int column = 0; column < transHistoryTable.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < transHistoryTable.getRowCount(); row++) {
                TableCellRenderer renderer = transHistoryTable.getCellRenderer(row, column);
                Component comp = transHistoryTable.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
        
        transHistoryTable.setDefaultEditor(Object.class, null);
    }
    
    
     // get Treatement History data
    public void getTransactionList(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = true", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select TransactionID,UserID,TransactionTime,Transaction.AccountID,Total,Balance from Transaction,MainAccount where Transaction.UserID= '"+userID+"' and Transaction.AccountID=MainAccount.AccountID";
            System.out.println(sql);
            ResultSet res = state.executeQuery(sql);
            
            Transaction tmp;
            while(res.next()){
                tmp = new Transaction(res.getString("TransactionID"), res.getString("UserID"),res.getTimestamp("TransactionTime"),res.getString("AccountID"),res.getInt("Total"),res.getInt("Balance")); 
                this.transactionList.add(tmp);
            }
            connect.close();
           }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
    
    
    public void show_historyTransaction(){
        DefaultTableModel model=(DefaultTableModel)transHistoryTable.getModel();
        Object row[]=new Object[5];
        
        for (int i=0;i<transactionList.size();i++){
            row[0]=transactionList.get(i).getTransactionID();
            row[1]=transactionList.get(i).getAccountID();
            
            String timeTrans=transactionList.get(i).getTransactionTime().toString();
            timeTrans = timeTrans.substring(0, timeTrans.indexOf('.'));
            row[2]=timeTrans;
            
            row[3]=transactionList.get(i).getTotal();
            row[4]=transactionList.get(i).getBalance();
            model.addRow(row);
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

        transIDTextField = new javax.swing.JTextField();
        accountIDTextField = new javax.swing.JTextField();
        timeTextField = new javax.swing.JTextField();
        totalTextField = new javax.swing.JTextField();
        balanceTextField = new javax.swing.JTextField();
        backLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        transHistoryTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        transIDTextField.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        transIDTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        transIDTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        transIDTextField.setOpaque(false);
        getContentPane().add(transIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 190, 40));

        accountIDTextField.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        accountIDTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        accountIDTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        accountIDTextField.setOpaque(false);
        getContentPane().add(accountIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 190, 40));

        timeTextField.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        timeTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timeTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        timeTextField.setOpaque(false);
        getContentPane().add(timeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 310, 40));

        totalTextField.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        totalTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        totalTextField.setOpaque(false);
        getContentPane().add(totalTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 290, 40));

        balanceTextField.setFont(new java.awt.Font("Fredoka One", 0, 24)); // NOI18N
        balanceTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        balanceTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        balanceTextField.setOpaque(false);
        getContentPane().add(balanceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 600, 240, 40));

        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backLabelMouseClicked(evt);
            }
        });
        getContentPane().add(backLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 70, 70));

        transHistoryTable.setBackground(new java.awt.Color(102, 255, 102));
        transHistoryTable.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        transHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TRANSACTION ID", "ACCOUNT ID", "TIME", "TOTAL", "BALANCE"
            }
        ));
        transHistoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transHistoryTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(transHistoryTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 156, 690, 430));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/ManagedUser/ManagedUserHomePage/Infomation/Transaction/TransactionHistoryBackground.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLabelMouseClicked
        dispose();
    }//GEN-LAST:event_backLabelMouseClicked

    private void transHistoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transHistoryTableMouseClicked
        int i = transHistoryTable.getSelectedRow();
        TableModel model=transHistoryTable.getModel();
        transIDTextField.setText(model.getValueAt(i, 0).toString());
        accountIDTextField.setText(model.getValueAt(i, 1).toString());
        timeTextField.setText(model.getValueAt(i, 2).toString());
        totalTextField.setText(model.getValueAt(i, 3).toString());
        balanceTextField.setText(model.getValueAt(i, 4).toString());
    }//GEN-LAST:event_transHistoryTableMouseClicked

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
            java.util.logging.Logger.getLogger(TransactionHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountIDTextField;
    private javax.swing.JLabel backLabel;
    private javax.swing.JTextField balanceTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField timeTextField;
    private javax.swing.JTextField totalTextField;
    private javax.swing.JTable transHistoryTable;
    private javax.swing.JTextField transIDTextField;
    // End of variables declaration//GEN-END:variables
}
