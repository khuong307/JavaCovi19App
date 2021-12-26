package javacovid19app.ManagedUser.ManagedUserHomePage.Infomation.Consume;

import java.util.Date;


public class ConsumeHistory {
    private String consumeID;
    private String UserID;
    private String NecessaryID;
    private Date BuyTime;

    public ConsumeHistory(String consumeID, String UserID, String NecessaryID, Date BuyTime) {
        this.consumeID = consumeID;
        this.UserID = UserID;
        this.NecessaryID = NecessaryID;
        this.BuyTime = BuyTime;
    }

    
    public String getConsumeID() {
        return consumeID;
    }

    public void setConsumeID(String consumeID) {
        this.consumeID = consumeID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getNecessaryID() {
        return NecessaryID;
    }

    public void setNecessaryID(String NecessaryID) {
        this.NecessaryID = NecessaryID;
    }

    public Date getBuyTime() {
        return BuyTime;
    }

    public void setBuyTime(Date BuyTime) {
        this.BuyTime = BuyTime;
    }
}
