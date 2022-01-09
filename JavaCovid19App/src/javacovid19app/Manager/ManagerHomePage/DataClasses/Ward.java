/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacovid19app.Manager.ManagerHomePage.DataClasses;

/**
 *
 * @author IVS-P0005
 */
public class Ward {
    private String WardID;
    private String Name;
    private String DistrictID;
    
    public Ward(String WardID, String Name, String DistrictID){
        this.WardID = WardID;
        this.Name = Name;
        this.DistrictID = DistrictID;
    }

    /**
     * @return the WardID
     */
    public String getWardID() {
        return WardID;
    }

    /**
     * @param WardID the WardID to set
     */
    public void setWardID(String WardID) {
        this.WardID = WardID;
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
     * @return the DistrictID
     */
    public String getDistrictID() {
        return DistrictID;
    }

    /**
     * @param DistrictID the DistrictID to set
     */
    public void setDistrictID(String DistrictID) {
        this.DistrictID = DistrictID;
    }
}
