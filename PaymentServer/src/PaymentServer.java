package paymentserver;

import java.io.IOException;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.logging.*;
import paymentserver.HomePage.*;
import paymentserver.SignIn.SignIn;

public class PaymentServer {
    
    private static String ReceivedAccount = "6380220027134";
    
    public static String getCurrentTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        java.util.Date now = new java.util.Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
    
    public static int getLengthTransaction(){
        int length = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "Select * from Transaction";
            ResultSet res = state.executeQuery(sql);

            while (res.next()) {
                length++;
            }

            connect.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaymentServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return length;
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
            Statement state = connect.createStatement();

            String sql = "select * from MainAccount";
            ResultSet res = state.executeQuery(sql);
            
            int count = 0;
            while (res.next()){
                count++;
            }
            connect.close();
            
            if (count > 0){
                ServerHomePage hp = new ServerHomePage();
                hp.show();
            }
            else{
                SignIn sign = new SignIn();
                sign.show();
            }
 
            Thread receiveTransaction = new Thread(new Runnable() 
            {
                @Override
                public void run() {
                    try {
                        ServerSocket ss = new ServerSocket(1234);
                        Socket s;
                        while (true){
                            s = ss.accept();
                            DataInputStream dis = new DataInputStream(s.getInputStream());
                            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                            String received = dis.readUTF();
                            System.out.println(received);
                            String[] lst = received.split("-");
                            
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection connect = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6448649?useSSL = false", "sql6448649", "ygTCgTJZu6");
                            Statement state = connect.createStatement();
                            
                            if (lst[1].equals("Request")){
                                String sql = "update ManagedUser set isPay = 1, Balance = 1000000 where UserID = '" + lst[0] + "'";
                                state.executeUpdate(sql);
                                dos.writeUTF("This user can begin to transact.");
                            }
                            else{
                                int length = getLengthTransaction();
                                String num = String.format("%05d", length + 1);
                                String TransactionID = "TR" + num;

                                String sql = "insert into Transaction(TransactionID, UserID, TransactionTime, AccountID, Total) values"
                                        + "('" + TransactionID + "', '" + lst[0] + "', '" + getCurrentTime() + "', '"
                                        + ReceivedAccount + "', " + lst[1] + ")";
                                state.executeUpdate(sql);

                                sql = "update ManagedUser set Balance = Balance - Loan, Loan = 0 where UserID = " + lst[0];
                                state.executeUpdate(sql);

                                sql = "update MainAccount set Balance = Balance + " + lst[1] + " where AccountID = '" + ReceivedAccount + "'";
                                state.executeUpdate(sql);

                                dos.writeUTF("Transaction successful");
                            }
                            connect.close();
                            dos.close();
                            dis.close();
                            s.close();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(PaymentServer.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(PaymentServer.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(PaymentServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            receiveTransaction.start();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaymentServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
