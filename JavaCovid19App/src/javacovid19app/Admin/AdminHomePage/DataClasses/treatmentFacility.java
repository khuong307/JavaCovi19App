/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacovid19app.Admin.AdminHomePage.DataClasses;

/**
 *
 * @author tongt
 */
public class treatmentFacility {
    private String facilityID;
    private String name;
    private int quantity;
    private int presentQuantity;
    public treatmentFacility(){
        this.facilityID="";
        this.name="";
        this.quantity=0;
        this.presentQuantity=0;
    }
    public treatmentFacility(String facilityID,String name,int quantity,int presentQuantity){
        this.facilityID=facilityID;
        this.name=name;
        this.quantity=quantity;
        this.presentQuantity=presentQuantity;
    }
    public String getFacilityID(){
        return this.facilityID;
    }
    public String getName(){
        return this.name;
    }
     public int getQuantity(){
        return this.quantity;
    }
    public int getPresentQuantity(){
        return this.presentQuantity;
    }
    public void setFacilityID(String id){
         this.facilityID=id;
    }
    public void setName(String name){
         this.name=name;
    }
     public void setQuantity(int quantity){
         this.quantity=quantity;
    }
    public void setPresentQuantity(int presentQuantity){
         this.presentQuantity=presentQuantity;
    }
}
