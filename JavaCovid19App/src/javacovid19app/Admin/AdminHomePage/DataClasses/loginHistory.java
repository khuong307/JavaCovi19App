/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacovid19app.Admin.AdminHomePage.DataClasses;

/**
 *
 * @author tongt
 */
public class loginHistory {
    public String loginTime;
    public String logoutTime;
    public loginHistory(String loginTime,String logoutTime){
        this.loginTime=loginTime;
        this.logoutTime=logoutTime;
    }
    public String getLoginTime(){
        return this.loginTime;
    }
    public String getLogoutTime(){
        return this.logoutTime;
    }
}
