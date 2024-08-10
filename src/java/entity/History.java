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
public class History {
    private int id;
    private int userId;
    private int essayId;
    private String lastTimeChange;
    private String content;
    private String categoriesName;

    public History() {
    }

    public History(int id, int userId, int essayId, String lastTimeChange, String content, String categoriesName) {
        this.id = id;
        this.userId = userId;
        this.essayId = essayId;
        this.lastTimeChange = lastTimeChange;
        this.content = content;
        this.categoriesName = categoriesName;
    }
    public History(int id, int userId, int essayId, String lastTimeChange, String content) {
        this.id = id;
        this.userId = userId;
        this.essayId = essayId;
        this.lastTimeChange = lastTimeChange;
        this.content = content;
       
    }

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

    public int getEssayId() {
        return essayId;
    }

    public void setEssayId(int essayId) {
        this.essayId = essayId;
    }

    public String getLastTimeChange() {
        return lastTimeChange;
    }

    public void setLastTimeChange(String lastTimeChange) {
        this.lastTimeChange = lastTimeChange;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }
    

    @Override
    public String toString() {
        return "History{" + "id=" + id + ", userId=" + userId + ", essayId=" + essayId + ", lastTimeChange=" + lastTimeChange + ", content=" + content + ", categoriesName="+categoriesName +'}';
    }
    
}