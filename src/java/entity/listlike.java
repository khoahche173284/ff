
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author pgb31
 */
public class listlike {
     private int id;
    
 
    private int postId;
    
  
    private int userId;
 
    private Date likeDate; 
    private String UserName;

    public listlike() {
    }

    public listlike(int id, int postId, int userId, Date likeDate, String UserName) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.likeDate = likeDate;
        this.UserName = UserName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
   
    
    
    
}