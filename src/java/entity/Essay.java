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
public class Essay {
    private int id;
    private int userId;
    private int categoriesId;
    private String content;
    private Date createdDate;

    public Essay(int id, int userId, int categoriesId, String content, Date createdDate) {
        this.id = id;
        this.userId = userId;
        this.categoriesId = categoriesId;
        this.content = content;
        this.createdDate = createdDate;
    }
    public Essay(int id, int userId, int categoriesId, String content) {
        this.id = id;
        this.userId = userId;
        this.categoriesId = categoriesId;
        this.content = content;
     
    }

    public Essay() {
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Essay{" + "id=" + id + ", userId=" + userId + ", categoriesId=" + categoriesId + ", content=" + content + ", createdDate=" + createdDate + '}';
    }
    
}