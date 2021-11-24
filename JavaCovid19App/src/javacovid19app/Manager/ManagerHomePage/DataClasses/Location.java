/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacovid19app.Manager.ManagerHomePage.DataClasses;

/**
 *
 * @author IVS-P0005
 */
public class Location {

    public Location(String ID, String City, String Dist, String Ward){
        this.LocationID = ID;
        this.city = City;
        this.district = Dist;
        this.ward = Ward;
    }
    /**
     * @return the LocationID
     */
    public String getLocationID() {
        return LocationID;
    }

    /**
     * @param LocationID the LocationID to set
     */
    public void setLocationID(String LocationID) {
        this.LocationID = LocationID;
    }

    /**
     * @return the ward
     */
    public String getWard() {
        return ward;
    }

    /**
     * @param ward the ward to set
     */
    public void setWard(String ward) {
        this.ward = ward;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }
    private String LocationID;
    private String ward;
    private String city;
    private String district;
    
    
}
