/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacovid19app.Manager.ManagerHomePage.DataClasses;

/**
 *
 * @author IVS-P0005
 */
public class Necessary {
    private String ID;
    private String Name;
    private String Limitation;
    private String Price;
    private String Type;
    private String DateLimit;
    
    public Necessary(){
        this.ID = "";
        this.Name = "";
        this.Limitation = "";
        this.Price = "";
        this.DateLimit = "";
    }
    
    public Necessary(String id, String name, String limit, String dateLimited, String price, String type){
        this.ID = id;
        this.Name = name;
        this.Limitation = limit;
        this.Price = price;
        this.Type = type;
        this.DateLimit = dateLimited;
    }

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Limitation
     */
    public String getLimitation() {
        return Limitation;
    }

    /**
     * @param Limitation the Limitation to set
     */
    public void setLimitation(String Limitation) {
        this.Limitation = Limitation;
    }

    /**
     * @return the Price
     */
    public String getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(String Price) {
        this.Price = Price;
    }
    
     public String getType() {
        return Type;
    }

    /**
     * @param Type the ID to set
     */
    public void setType(String Type) {
        this.ID = Type;
    }

    /**
     * @return the DateLimit
     */
    public String getDateLimit() {
        return DateLimit;
    }

    /**
     * @param DateLimit the DateLimit to set
     */
    public void setDateLimit(String DateLimit) {
        this.DateLimit = DateLimit;
    }
}
