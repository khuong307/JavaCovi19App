/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacovid19app.Manager.ManagerHomePage.DataClasses;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class CovidHistory {
    private String ID;
    private Date BeginTime;
    private Date EndTime;
    private String Status;
    private String InvolvedPerson;

    public CovidHistory(String ID, Date BeginTime, Date EndTime, String Status, String InvolvedPerson) {
        this.ID = ID;
        this.BeginTime = BeginTime;
        this.EndTime = EndTime;
        this.Status = Status;
        this.InvolvedPerson = InvolvedPerson;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getBeginTime() {
        return BeginTime;
    }

    public void setBeginTime(Date BeginTime) {
        this.BeginTime = BeginTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date EndTime) {
        this.EndTime = EndTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getInvolvedPerson() {
        return InvolvedPerson;
    }

    public void setInvolvedPerson(String InvolvedPerson) {
        this.InvolvedPerson = InvolvedPerson;
    }
}