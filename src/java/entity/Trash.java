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
public class Trash {
    private int id;
    private int userId;
    private int essayId;
    private Date deletedDate;
    private String content;

    public Trash(int id, int userId, int essayId, Date deletedDate, String content) {
        this.id = id;
        this.userId = userId;
        this.essayId = essayId;
        this.deletedDate = deletedDate;
        this.content = content;
    }

    // Getters and setters
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

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Trash{" + "id=" + id + ", userId=" + userId + ",content=" + content + ", essayId=" + essayId + ", deletedDate=" + deletedDate + '}';
    }
}