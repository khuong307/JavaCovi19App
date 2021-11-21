/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacovid19app.Manager.ManagerHomePage.DataClasses;

/**
 *
 * @author IVS-P0005
 */
public class TreatmentFacility {
    private String ID;
    private String FacilityName;
    private String Quantity;
    private String PresentQuantity;

    public TreatmentFacility(String ID, String Name, String quantity, String present){
        this.ID = ID;
        this.FacilityName = Name;
        this.Quantity = quantity;
        this.PresentQuantity = present;
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
    public String getFacilityName() {
        return FacilityName;
    }

    /**
     * @param Name the Name to set
     */
    public void setFacilityName(String Name) {
        this.FacilityName = Name;
    }

    /**
     * @return the Quantity
     */
    public String getQuantity() {
        return Quantity;
    }

    /**
     * @param Quantity the Quantity to set
     */
    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    /**
     * @return the PresentQuantity
     */
    public String getPresentQuantity() {
        return PresentQuantity;
    }

    /**
     * @param PresentQuantity the PresentQuantity to set
     */
    public void setPresentQuantity(String PresentQuantity) {
        this.PresentQuantity = PresentQuantity;
    }
    
}
