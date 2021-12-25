package javacovid19app.Manager.ManagerHomePage.DataClasses.MinhDataClass;

import java.util.Date;


public class TreatmentHistory {
    private String UserID;
    private Date ArriveTime;
    private String FacilityID;
    private Date LeaveTime;

    public TreatmentHistory(String UserID, Date ArriveTime, String FacilityID, Date LeaveTime) {
        this.UserID = UserID;
        this.ArriveTime = ArriveTime;
        this.FacilityID = FacilityID;
        this.LeaveTime = LeaveTime;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public Date getArriveTime() {
        return ArriveTime;
    }

    public void setArriveTime(Date ArriveTime) {
        this.ArriveTime = ArriveTime;
    }

    public String getFacilityID() {
        return FacilityID;
    }

    public void setFacilityID(String FacilityID) {
        this.FacilityID = FacilityID;
    }

    public Date getLeaveTime() {
        return LeaveTime;
    }

    public void setLeaveTime(Date LeaveTime) {
        this.LeaveTime = LeaveTime;
    }
}