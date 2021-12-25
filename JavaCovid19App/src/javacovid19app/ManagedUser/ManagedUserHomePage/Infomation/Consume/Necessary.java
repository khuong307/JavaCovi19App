package javacovid19app.ManagedUser.ManagedUserHomePage.Infomation.Consume;


public class Necessary {
    private String NecessaryID;
    private String Name;
    private int Limited;
    private int TimeLimited;
    private double Price;
    private int Type;

    public Necessary(String NecessaryID, String Name, int Limited, int TimeLimited, double Price, int Type) {
        this.NecessaryID = NecessaryID;
        this.Name = Name;
        this.Limited = Limited;
        this.TimeLimited = TimeLimited;
        this.Price = Price;
        this.Type = Type;
    }
    
    public String getNecessaryID() {
        return NecessaryID;
    }

    public void setNecessaryID(String NecessaryID) {
        this.NecessaryID = NecessaryID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getLimited() {
        return Limited;
    }

    public void setLimited(int Limited) {
        this.Limited = Limited;
    }

    public int getTimeLimited() {
        return TimeLimited;
    }

    public void setTimeLimited(int TimeLimited) {
        this.TimeLimited = TimeLimited;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }
}
