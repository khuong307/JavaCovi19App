package javacovid19app.ManagedUser.ManagedUserHomePage.Infomation.Transaction;

import java.util.Date;


public class Transaction {
    private String TransactionID;
    private String UserID;
    private Date TransactionTime;
    private String AccountID;
    private int balance;
    private int total;
    
    public Transaction(String TransactionID,String UserID,Date TransactionTime,String AccountID,int total,int balance){
        this.TransactionID=TransactionID;
        this.UserID=UserID;
        this.TransactionTime=TransactionTime;
        this.AccountID=AccountID;
        this.total=total;
        this.balance=balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String TransactionID) {
        this.TransactionID = TransactionID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public Date getTransactionTime() {
        return TransactionTime;
    }

    public void setTransactionTime(Date TransactionTime) {
        this.TransactionTime = TransactionTime;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String AccountID) {
        this.AccountID = AccountID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
