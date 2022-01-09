/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacovid19app.Manager.ManagerHomePage.DataClasses;

/**
 *
 * @author IVS-P0005
 */
public class District {
    private String DistrictID;
    private String Name;
    private String CityID;
    
    public District(String DistrictID, String Name, String CityID){
        this.DistrictID = DistrictID;
        this.Name = Name;
        this.CityID = CityID;
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
     * @return the CityID
     */
    public String getCityID() {
        return CityID;
    }

    /**
     * @param CityID the CityID to set
     */
    public void setCityID(String CityID) {
        this.CityID = CityID;
    }
}
