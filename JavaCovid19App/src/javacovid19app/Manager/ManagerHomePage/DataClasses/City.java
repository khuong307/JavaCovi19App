/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacovid19app.Manager.ManagerHomePage.DataClasses;

/**
 *
 * @author IVS-P0005
 */
public class City {
    private String CityID;
    private String Name;
    public City (String CityID, String Name){
        this.CityID = CityID;
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
}
