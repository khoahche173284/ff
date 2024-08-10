/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author Quang
 */
public class Users {

    private int id;
    private String username;
    private String realname;
    private String email;
    private String password;
    private String Img;
    private Date createdDate;
    private int roleId;
    private int FailedLoginAttempts;
    private boolean IsLocked;
    private Date lockTime;

    public Users() {
    }

    public Users(int id, String username, String realname, String email, String password, String Img, Date createdDate, int roleId) {
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.email = email;
        this.password = password;
        this.Img = Img;
        this.createdDate = createdDate;
        this.roleId = roleId;
    }

    public Users(int id, String username, String realname, String email, String password, String Img, Date createdDate, int roleId, int FailedLoginAttempts, boolean IsLocked, Date lockTime) {
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.email = email;
        this.password = password;
        this.Img = Img;
        this.createdDate = createdDate;
        this.roleId = roleId;
        this.FailedLoginAttempts = FailedLoginAttempts;
        this.IsLocked = IsLocked;
        this.lockTime = lockTime;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getFailedLoginAttempts() {
        return FailedLoginAttempts;
    }

    public void setFailedLoginAttempts(int FailedLoginAttempts) {
        this.FailedLoginAttempts = FailedLoginAttempts;
    }

    public boolean isIsLocked() {
        return IsLocked;
    }

    public void setIsLocked(boolean IsLocked) {
        this.IsLocked = IsLocked;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }
    
    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", username=" + username + ", realname=" + realname + ", email=" + email + ", password=" + password + ", Img=" + Img + ", createdDate=" + createdDate + ", roleId=" + roleId + ", FailedLoginAttempts=" + FailedLoginAttempts + ", IsLocked=" + IsLocked + '}';
    }

}
