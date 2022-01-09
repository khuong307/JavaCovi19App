package javacovid19app.ManagedUser.ManagedUserSupplies;

import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
import javax.swing.table.TableModel;

class ConsumeHistory {

    private String ConsumeID;
    private String UserID;
    private String NecessaryID;
    private String BuyTime;

    ConsumeHistory(String ConsumeID, String UserID, String NecessaryID, String BuyTime) {
        this.ConsumeID = ConsumeID;
        this.UserID = UserID;
        this.NecessaryID = NecessaryID;
        this.BuyTime = BuyTime;
    }

    public String getConsumeID() {
        return ConsumeID;
    }

    public String getUserID() {
        return UserID;
    }

    public String getNecessaryID() {
        return NecessaryID;
    }

    public String getBuyTime() {
        return BuyTime;
    }
}

public class ManagedUserSupplies extends javax.swing.JFrame {

    private String userID = "";
    private ArrayList<Necessary> lst = new ArrayList<Necessary>(); // danh sách tất cả nhu yếu phẩm
    private ArrayList<Necessary> temp = new ArrayList<Necessary>(); // danh sách đang hiển thị
    private ArrayList<Necessary> cart = new ArrayList<Necessary>(); // danh sách các nhu yếu phẩm trong giỏ hàng
    private ArrayList<ConsumeHistory> ConHis = new ArrayList<ConsumeHistory>();
    private int size = 0;
    private int loan = 0;
    private int MAX = 300000;

    public ManagedUserSupplies() {
        initComponents();
        TextSearch.setOpaque(false);
        TextSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setData();
        setConsumeHistory();
        size = ConHis.size();
        refreshJTable(this.TabSupplies);
        showData(this.TabSupplies, this.lst);
        EditTableHeightWidth(this.TabSupplies);
    }

    public ManagedUserSupplies(String username) {
        this.userID = username;
        initComponents();
        TextSearch.setOpaque(false);
        TextSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setData();
        TabSupplies.setDefaultEditor(Object.class, null);
        TabCartList.setDefaultEditor(Object.class, null);
        setConsumeHistory();
        size = ConHis.size();
        refreshJTable(this.TabSupplies);
        showData(this.TabSupplies, this.lst);
        EditTableHeightWidth(this.TabSupplies);
        temp = lst;
    }

    public void setConsumeHistory() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select * from ConsumeHistory where UserID = " + userID;
            ResultSet res = state.executeQuery(sql);

            while (res.next()) {
                ConsumeHistory tmp = new ConsumeHistory(res.getString("ConsumeID"), res.getString("UserID"), res.getString("NecessariesID"), res.getString("BuyTime"));
                this.ConHis.add(tmp);
            }

            connect.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagedUserSupplies.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManagedUserSupplies.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getLengthConsumeHistory(){
        int length = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select * from ConsumeHistory";
            ResultSet res = state.executeQuery(sql);

            while (res.next()) {
                length++;
            }

            connect.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagedUserSupplies.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManagedUserSupplies.class.getName()).log(Level.SEVERE, null, ex);
        }
        return length;
    }

    public void refreshJTable(JTable tab) {
        DefaultTableModel model = (DefaultTableModel) tab.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }

    public void setData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();
            
            String sql = "select * from ManagedUser where UserID = '" + userID + "'";
            ResultSet res = state.executeQuery(sql);
            
            while(res.next()){
                loan = res.getInt("Loan");
            }

            sql = "Select * from Necessaries ";
            res = state.executeQuery(sql);

            while (res.next()) {
                Necessary tmp = new Necessary(res.getString("NecessariesID"), res.getString("Name"), res.getString("TimeLimited"), res.getString("DateLimited"), res.getString("Price"), res.getString("Type"));
                this.lst.add(tmp);
            }

            connect.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagedUserSupplies.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManagedUserSupplies.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showData(JTable tab, ArrayList<Necessary> lst) {
        DefaultTableModel model = (DefaultTableModel) tab.getModel();
        Object[] row = new Object[4];

        for (int i = 0; i < lst.size(); i++) {
            row[0] = i + 1;
            row[1] = lst.get(i).getID();
            row[2] = lst.get(i).getName();
            row[3] = lst.get(i).getPrice();
            model.addRow(row);
        }
    }

    public void EditTableHeightWidth(JTable tmp) {
        //edit size of column
        tmp.getTableHeader().setFont(new Font("Fredoka One", Font.PLAIN, 16));
        //tmp.getTableHeader().setForeground(jScrollPane2.getBackground());
        final TableColumnModel columnModel = tmp.getColumnModel();
        for (int column = 0; column < tmp.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < tmp.getRowCount(); row++) {
                TableCellRenderer renderer = tmp.getCellRenderer(row, column);
                Component comp = tmp.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }

        for (int row = 0; row < tmp.getRowCount(); row++) {
            int rowHeight = tmp.getRowHeight();
            for (int column = 0; column < tmp.getColumnCount(); column++) {
                Component comp = tmp.prepareRenderer(tmp.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
            tmp.setRowHeight(row, rowHeight);
        }
    }
    
    public void showWithCondition(){
        String name = TextSearch.getText();
        String typeSort = SortComboBox.getSelectedItem().toString();
        String typeFilter = FilterComboBox.getSelectedItem().toString();
        ArrayList<Necessary> currentList = new ArrayList<Necessary>(lst);
        currentList = search(name, currentList);
        currentList = sort(typeSort, currentList);
        currentList = filter(typeFilter, currentList);
        for (int i = 0; i < currentList.size(); i++){
            System.out.println(currentList.get(i).getID());
        }
        refreshJTable(this.TabSupplies);
        showData(this.TabSupplies, currentList);
        EditTableHeightWidth(this.TabSupplies);
    }
    
    public ArrayList<Necessary> search(String name, ArrayList<Necessary> currentList){
        if (!name.isEmpty()) {
            int i = 0;
            while (i < currentList.size()){
                Necessary tmp = currentList.get(i);
                String necName = tmp.getName().split(" \\(")[0];
                if (!necName.toLowerCase().contains(name.toLowerCase())) {
                    currentList.remove(tmp);
                }
                else
                    i++;
            }
        }
        return currentList;
    }
    
    public ArrayList<Necessary> sort(String typeSort, ArrayList<Necessary> currentList){
        if (typeSort.equals("ID Ascending")) {
            sortById(true, currentList);
        } else if (typeSort.equals("ID Descending")) {
            sortById(false, currentList);
        } else if (typeSort.equals("Name Ascending")) {
            sortByName(true, currentList);
        } else if (typeSort.equals("Name Descending")) {
            sortByName(false, currentList);
        } else if (typeSort.equals("Price Ascending")) {
            sortByPrice(true, currentList);
        } else if (typeSort.equals("Price Descending")) {
            sortByPrice(false, currentList);
        }
        return currentList;
    } 
    
    public ArrayList<Necessary> filter(String typeFilter, ArrayList<Necessary> currentList){
        if (typeFilter.equals("Instant Food")) {
            currentList = filterType("1", currentList);
        } else if (typeFilter.equals("Fruit")) {
            currentList = filterType("2", currentList);
        } else if (typeFilter.equals("Essential")) {
            currentList = filterType("3", currentList);
        } else if (typeFilter.equals("Low Price")) {
            currentList = filterPrice(0, 9999, currentList);
        } else if (typeFilter.equals("Medium Price")) {
            currentList = filterPrice(10000, 50000, currentList);
        } else if (typeFilter.equals("High Price")) {
            int max = getMaxPrice();
            currentList = filterPrice(50001, max, currentList);
        }
        return currentList;
    }

    public void sortById(boolean type, ArrayList<Necessary> temp) {
        if (type) {
            for (int i = 0; i < temp.size() - 1; i++) {
                for (int j = i + 1; j < temp.size(); j++) {
                    if (temp.get(i).getID().compareTo(temp.get(j).getID()) > 0) {
                        Collections.swap(temp, i, j);
                    }
                }
            }
        } else {
            for (int i = 0; i < temp.size() - 1; i++) {
                for (int j = i + 1; j < temp.size(); j++) {
                    if (temp.get(i).getID().compareTo(temp.get(j).getID()) < 0) {
                        Collections.swap(temp, i, j);
                    }
                }
            }
        }
    }

    public void sortByName(boolean type, ArrayList<Necessary> temp) {
        if (type) {
            for (int i = 0; i < temp.size() - 1; i++) {
                for (int j = i + 1; j < temp.size(); j++) {
                    if (temp.get(i).getName().compareTo(temp.get(j).getName()) > 0) {
                        Collections.swap(temp, i, j);
                    }
                }
            }
        } else {
            for (int i = 0; i < temp.size() - 1; i++) {
                for (int j = i + 1; j < temp.size(); j++) {
                    if (temp.get(i).getName().compareTo(temp.get(j).getName()) < 0) {
                        Collections.swap(temp, i, j);
                    }
                }
            }
        }
    }

    public void sortByPrice(boolean type, ArrayList<Necessary> temp) {
        if (type) {
            for (int i = 0; i < temp.size() - 1; i++) {
                for (int j = i + 1; j < temp.size(); j++) {
                    if (Integer.valueOf(temp.get(i).getPrice()) > Integer.valueOf(temp.get(j).getPrice())) {
                        Collections.swap(temp, i, j);
                    }
                }
            }
        } else {
            for (int i = 0; i < temp.size() - 1; i++) {
                for (int j = i + 1; j < temp.size(); j++) {
                    if (Integer.valueOf(temp.get(i).getPrice()) < Integer.valueOf(temp.get(j).getPrice())) {
                        Collections.swap(temp, i, j);
                    }
                }
            }
        }
    }

    public ArrayList<Necessary> filterType(String type, ArrayList<Necessary> currentList) {
        ArrayList<Necessary> res = new ArrayList<Necessary>();
        for (int i = 0; i < currentList.size(); i++) {
            if (currentList.get(i).getType().equals(type)) {
                res.add(currentList.get(i));
            }
        }
        return res;
    }

    public ArrayList<Necessary> filterPrice(int min, int max, ArrayList<Necessary> currentList) {
        ArrayList<Necessary> res = new ArrayList<Necessary>();
        for (int i = 0; i < currentList.size(); i++) {
            int price = Integer.valueOf(currentList.get(i).getPrice());
            if (price >= min && price <= max) {
                res.add(currentList.get(i));
            }
        }
        return res;
    }

    public int getMaxPrice() {
        int max = 0;
        for (int i = 0; i < lst.size(); i++) {
            if (max < Integer.valueOf(lst.get(i).getPrice())) {
                max = Integer.valueOf(lst.get(i).getPrice());
            }
        }
        return max;
    }

    public Necessary findById(String id) {
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getID().equals(id)) {
                return lst.get(i);
            }
        }
        return null;
    }

    public ArrayList<ConsumeHistory> findByNecessaryId(String id) {
        ArrayList<ConsumeHistory> res = new ArrayList<ConsumeHistory>();
        for (int i = 0; i < ConHis.size(); i++) {
            if (ConHis.get(i).getNecessaryID().equals(id)) {
                res.add(ConHis.get(i));
            }
        }
        return res;
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
        BtnAddToCart = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabCartList = new javax.swing.JTable();
        BtnBuy = new javax.swing.JLabel();
        BtnDel = new javax.swing.JLabel();
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

        SortComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No Sort", "ID Ascending", "ID Descending", "Name Ascending", "Name Descending", "Price Ascending", "Price Descending" }));
        SortComboBox.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        SortComboBox.setOpaque(false);
        getContentPane().add(SortComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 190, 60));

        FilterComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No Filter", "Instant Food", "Fruit", "Essential", "Low Price", "Medium Price", "High Price" }));
        FilterComboBox.setFont(new java.awt.Font("Fredoka One", 0, 14)); // NOI18N
        FilterComboBox.setOpaque(false);
        getContentPane().add(FilterComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 438, 190, 60));

        BtnFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnFilterMouseClicked(evt);
            }
        });
        getContentPane().add(BtnFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 450, 40, 50));

        BtnAddToCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAddToCartMouseClicked(evt);
            }
        });
        getContentPane().add(BtnAddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 90, 60, 70));

        TabCartList.setFont(new java.awt.Font("Fredoka One", 0, 16)); // NOI18N
        TabCartList.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TabCartList);
        if (TabCartList.getColumnModel().getColumnCount() > 0) {
            TabCartList.getColumnModel().getColumn(0).setHeaderValue("No");
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(802, 257, -1, 310));

        BtnBuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnBuyMouseClicked(evt);
            }
        });
        getContentPane().add(BtnBuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1165, 600, 90, 80));

        BtnDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnDelMouseClicked(evt);
            }
        });
        getContentPane().add(BtnDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1195, 194, 60, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javacovid19app/ManagedUser/ManagedUserSupplies/SuppliesManagedUserBackground.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBackMouseClicked
        // TODO add your handling code here:
        ManagedUserHomePage homepage = new ManagedUserHomePage(userID);
        homepage.show();
        dispose();
    }//GEN-LAST:event_BtnBackMouseClicked

    private void BtnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSearchMouseClicked
        // TODO add your handling code here:
        showWithCondition();
    }//GEN-LAST:event_BtnSearchMouseClicked

    private void BtnSortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSortMouseClicked
    // TODO add your handling code here:
        showWithCondition();
    }//GEN-LAST:event_BtnSortMouseClicked

    private void BtnFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnFilterMouseClicked
        // TODO add your handling code here:
        showWithCondition();
    }//GEN-LAST:event_BtnFilterMouseClicked

    private void BtnAddToCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAddToCartMouseClicked
        // TODO add your handling code here:
        int index = TabSupplies.getSelectedRow();
        TableModel model = TabSupplies.getModel();

        String id = model.getValueAt(index, 1).toString();
        Necessary tmp = findById(id);
        ArrayList<ConsumeHistory> ConHisById = findByNecessaryId(id);
        int limited = Integer.valueOf(tmp.getLimitation());
        int timeLimited = Integer.valueOf(tmp.getDateLimit());
        int price = Integer.valueOf(tmp.getPrice());
        if (limited > ConHisById.size()) {
            if (loan + price > MAX)
                JOptionPane.showMessageDialog(this, "Exceed the maximum loan");
            else{
                cart.add(tmp);
                String now = Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
                ConsumeHistory his = new ConsumeHistory(" ", userID, tmp.getID(), now);
                ConHis.add(his);
                loan += Integer.valueOf(tmp.getPrice());
            }
        } else {
            String firstDate = ConHisById.get(ConHisById.size() - limited).getBuyTime();
            String now = Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate dt1 = LocalDate.parse(firstDate);
            LocalDate dt2= LocalDate.parse(now);
            long diffDays = ChronoUnit.DAYS.between(dt1, dt2);

            if (Math.abs((int)diffDays + 1) > timeLimited){
                if (loan + price > MAX)
                    JOptionPane.showMessageDialog(this, "Exceed the maximum loan");
                else{
                    cart.add(tmp);
                    ConsumeHistory his = new ConsumeHistory(" ", userID, tmp.getID(), now);
                    ConHis.add(his);
                    loan += Integer.valueOf(tmp.getPrice());
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "You can only buy " + limited + " supply(ies)/" + timeLimited + " day(s)"
                        + "\n"
                + "From " + firstDate + " to now, you bought the maximum this supplies");
            }
        }
        
        System.out.println(loan);

        refreshJTable(this.TabCartList);
        showData(this.TabCartList, cart);
        EditTableHeightWidth(this.TabCartList);
    }//GEN-LAST:event_BtnAddToCartMouseClicked

    private void BtnBuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnBuyMouseClicked
        // TODO add your handling code here:
        int length = getLengthConsumeHistory();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            DefaultTableModel model = (DefaultTableModel) TabCartList.getModel();
            for (int i = 0; model.getRowCount() > i; i++){
                String NecessaryID = (String) model.getValueAt(i, 1);
                Necessary tmp = findById(NecessaryID);
                String price = tmp.getPrice();
                String num = String.format("%04d", length + i + 1);
                String ConsumeID = "CID" + num;
                String now = Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
                String sql = "insert into ConsumeHistory(ConsumeID, UserID, NecessariesID, BuyTime) values"
                        + "('" + ConsumeID + "', '" + userID + "', '" + NecessaryID + "', '" + now + "')";
                state.executeUpdate(sql);
                
                sql = "update ManagedUser set loan = loan + " + price + " where UserID = '" + userID + "'";
                state.executeUpdate(sql);
            }

            connect.close();
            
            JOptionPane.showMessageDialog(this, "Add loan successfully!!!");
            ManagedUserHomePage homepage = new ManagedUserHomePage(userID);
            homepage.show();
            dispose();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagedUserSupplies.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManagedUserSupplies.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnBuyMouseClicked

    private void BtnDelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDelMouseClicked
        // TODO add your handling code here:
        int index = TabCartList.getSelectedRow();
        TableModel model = TabCartList.getModel();
        
        cart.remove(index);
        ConHis.remove(size + index);
        loan -= Integer.valueOf(model.getValueAt(index, 3).toString());
        
        System.out.println(loan);
        
        refreshJTable(this.TabCartList);
        showData(this.TabCartList, cart);
        EditTableHeightWidth(this.TabCartList);
    }//GEN-LAST:event_BtnDelMouseClicked

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
    private javax.swing.JLabel BtnAddToCart;
    private javax.swing.JLabel BtnBack;
    private javax.swing.JLabel BtnBuy;
    private javax.swing.JLabel BtnDel;
    private javax.swing.JLabel BtnFilter;
    private javax.swing.JLabel BtnSearch;
    private javax.swing.JLabel BtnSort;
    private CustomComboBox.ComboBox FilterComboBox;
    private CustomComboBox.ComboBox SortComboBox;
    private javax.swing.JTable TabCartList;
    private javax.swing.JTable TabSupplies;
    private javax.swing.JTextField TextSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
