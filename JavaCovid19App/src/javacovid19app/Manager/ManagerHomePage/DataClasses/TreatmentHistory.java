/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacovid19app.Manager.ManagerHomePage.DataClasses;

/**
 *
 * @author IVS-P0005
 */
public class TreatmentHistory {
    private String UserID;
    private String ArriveTime;
    private String FacilityID;
    private String LeaveTime;

    
    public TreatmentHistory (String id, String arrTime, String facility, String leaveTime){
        this.UserID = id;
        this.ArriveTime = arrTime;
        this.FacilityID = facility;
        this.LeaveTime = leaveTime;
    }
    /**
     * @return the UserID
     */
    public String getUserID() {
        return UserID;
    }

    /**
     * @param UserID the UserID to set
     */
    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    /**
     * @return the ArriveTime
     */
    public String getArriveTime() {
        return ArriveTime;
    }

    /**
     * @param ArriveTime the ArriveTime to set
     */
    public void setArriveTime(String ArriveTime) {
        this.ArriveTime = ArriveTime;
    }

    /**
     * @return the FacilityID
     */
    public String getFacilityID() {
        return FacilityID;
    }

    /**
     * @param FacilityID the FacilityID to set
     */
    public void setFacilityID(String FacilityID) {
        this.FacilityID = FacilityID;
    }

    /**
     * @return the LeaveTime
     */
    public String getLeaveTime() {
        return LeaveTime;
    }

    /**
     * @param LeaveTime the LeaveTime to set
     */
    public void setLeaveTime(String LeaveTime) {
        this.LeaveTime = LeaveTime;
    }
    
}
