/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacovid19app.Manager.ManagerHomePage.DataClasses;

import java.util.ArrayList;

/**
 *
 * @author IVS-P0005
 */
public class ManagedUser {
    private String ID;
    private String Fullname;
    private String YOB;
    private String CurrentStatus;
    private TreatmentFacility FacilityName = new TreatmentFacility("", "", "", "");
    private String InvolvedID;
    private City city = new City ("", "");
    private Ward ward = new Ward ("", "", "");
    private District district = new District ("", "", "");
    private int balance;
    private int loan;
    private boolean isPay;
    
    public ManagedUser(){
        ID = "";
        Fullname = "";
        YOB = "";
        CurrentStatus = "";
        InvolvedID = "";
        balance = 0;
        loan = 0;
    }
    public String getCityName(ArrayList<City> cityList, String CityID){
        for (int i  =0;i < cityList.size(); i++){
            if (cityList.get(i).getCityID().compareTo(CityID) == 0){
                return cityList.get(i).getName();
            }
        }
        return "";
    }
    
    public String getDistrictName(ArrayList<District> districtList, String districtID){
        for (int i  =0;i < districtList.size(); i++){
            if (districtList.get(i).getDistrictID().compareTo(districtID) == 0){
                return districtList.get(i).getName();
            }
        }
        return "";
    }
    
    public String getWardName(ArrayList<Ward> WardList, String WardID){
        for (int i  =0;i < WardList.size(); i++){
            if (WardList.get(i).getWardID().compareTo(WardID) == 0){
                return WardList.get(i).getName();
            }
        }
        return "";
    }
    
    public String getFacilityTreatmentName(ArrayList<TreatmentFacility> treatMentList, String FacilityID){
        for (int i  =0;i < treatMentList.size(); i++){
            if (treatMentList.get(i).getID().compareTo(FacilityID) == 0){
                return treatMentList.get(i).getFacilityName();
            }
        }
        return "";
    }
    
    public Location getLocation(ArrayList<Location> locateList, String LocationID){
        Location tmp = new Location("","","","");
        for (int i  =0;i < locateList.size(); i++){
            if (locateList.get(i).getLocationID().compareTo(LocationID) == 0){
                return locateList.get(i);
            }
        }
        return tmp;
    }

    public ManagedUser(String ID, String Fullname, String YOB,String CurrentStatus, String FacilityID, String InvolvedID, String LocationID, String Balance, ArrayList<City> cityList, ArrayList<Ward> wardList, ArrayList<District> districtList, ArrayList <Location> locateList, ArrayList<TreatmentFacility> treatMentList ){
        this.ID = ID;
        this.Fullname = Fullname;
        this.YOB = YOB;
        this.CurrentStatus = CurrentStatus;
        
        Location tmp = getLocation(locateList, LocationID);
        
        String DistricID = tmp.getDistrict();
        
        this.district.setDistrictID(DistricID);
        this.district.setName(getDistrictName(districtList, tmp.getDistrict()));
        
        this.ward.setWardID(tmp.getWard());
        this.ward.setName(getWardName(wardList,tmp.getWard()));
        
        
        this.city.setCityID(tmp.getCity());
        this.city.setName(getCityName(cityList, tmp.getCity()));
        
        this.FacilityName.setID(FacilityID);
        this.FacilityName.setFacilityName(getFacilityTreatmentName(treatMentList, FacilityID));
        
        if (InvolvedID != null){
            this.InvolvedID = InvolvedID;
        }else{
            this.InvolvedID = "null";
        }
        
        this.balance = Integer.valueOf(Balance);
        
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
     * @return the Fullname
     */
    public String getFullname() {
        return Fullname;
    }

    /**
     * @param Fullname the Fullname to set
     */
    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    /**
     * @return the DOB
     */
    public String getYOB() {
        return YOB;
    }

    /**
     * @param YOB the DOB to set
     */
    public void setYOB(String YOB) {
        this.YOB = YOB;
    }

    /**
     * @return the CurrentSatus
     */
    public String getCurrentStatus() {
        return CurrentStatus;
    }

    /**
     * @param CurrentStatus the CurrentSatus to set
     */
    public void setCurrentStatus(String CurrentStatus) {
        this.CurrentStatus = CurrentStatus;
    }

    /**
     * @return the FacilityName
     */
    public String getFacilityName() {
        return FacilityName.getFacilityName();
    }
    
    public String getFacilityID() {
        return FacilityName.getID();
    }

    /**
     * @param FacilityName the FacilityName to set
     */
    public void setFacilityName(String FacilityName) {
        this.FacilityName.setFacilityName(FacilityName);
    }

    /**
     * @return the InvolvedID
     */
    public String getInvolvedID() {
        return InvolvedID;
    }

    /**
     * @param InvolvedID the InvolvedID to set
     */
    public void setInvolvedID(String InvolvedID) {
        this.InvolvedID = InvolvedID;
    }

    /**
     * @return the City
     */
    public City getCity() {
        return city;
    }

    /**
     * @param City the City to set
     */
    public void setCity(String City) {
        this.city.setCityID(City);
    }

    /**
     * @return the Ward
     */
    public Ward getWard() {
        return this.ward;
    }

    /**
     * @param Ward the Ward to set
     */
    public void setWard(String Ward) {
        this.ward.setName(Ward);
    }

    /**
     * @return the District
     */
    public District getDistrict() {
        return this.district;
    }

    /**
     * @param District the District to set
     */
    public void setDistrict(String District) {
        this.district.setDistrictID(District);
    }

    /**
     * @return the balance
     */
    public int getBalance() {
        return balance;
    }
    
    /**
     * @return the loan
     */
    public int getLoan() {
        return loan;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public void setLoan(int loan){
        this.loan = loan;
    }
     
    public void setFacilityID(String ID){
        this.FacilityName.setID(ID);
    }
    
    public void setPresentQuantity(String present){
        this.FacilityName.setPresentQuantity(present);
    }
    
    public String getPresentQuantity(){
        return this.FacilityName.getPresentQuantity();
    }
    
    public void setQuantity(String quantity){
        this.FacilityName.setQuantity(quantity);
    }
    
    public void setIsPay(boolean status){
        this.isPay = status;
    }
    public boolean getIsPay(){
        return isPay;
    }
}
