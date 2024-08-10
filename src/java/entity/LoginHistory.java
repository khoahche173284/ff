/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.util.Date;

/**
 *
 * @author congkhoa2723
 */
public class LoginHistory {
    private Date loginTime;
    private int activityTypeID;
    private int userID;
    private String typeName;
    private int id;

    public LoginHistory() {
    }

    public LoginHistory(Date loginTime, int userID) {
        this.loginTime = loginTime;
        this.userID = userID;
    }

    public LoginHistory(Date loginTime, int activityTypeID, int userID, String typeName, int id) {
        this.loginTime = loginTime;
        this.activityTypeID = activityTypeID;
        this.userID = userID;
        this.typeName = typeName;
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    

    public LoginHistory(Date loginTime, int activityTypeID, int userID, int id) {
        this.loginTime = loginTime;
        this.activityTypeID = activityTypeID;
        this.userID = userID;
        this.id = id;
    }

    public int getActivityTypeID() {
        return activityTypeID;
    }

    public void setActivityTypeID(int activityTypeID) {
        this.activityTypeID = activityTypeID;
    }
    

    public LoginHistory(Date loginTime, int userID, int id) {
        this.loginTime = loginTime;
        this.userID = userID;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
     @Override
    public String toString() {
        return "LoginHistory{" + "loginTime=" + loginTime + ", userID=" + userID + ", typeName=" + typeName   + ", activityTypeID=" + activityTypeID + '}';
    }

    
}
