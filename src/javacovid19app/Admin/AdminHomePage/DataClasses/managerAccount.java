/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacovid19app.Admin.AdminHomePage.DataClasses;

/**
 *
 * @author tongt
 */
public class managerAccount {
    private String ID;
    private String password;
    public String getID(){
        return this.ID;
    }
    public String getPassword(){
        return this.password;
    }
    public managerAccount(String id,String password){
        this.ID=id;
        this.password=password;
    }


}
