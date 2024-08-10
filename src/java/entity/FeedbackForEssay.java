/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Quang
 */
public class FeedbackForEssay {
    private int id;
    private int essayId;
    private String content;
    private java.util.Date createdDate;

    public FeedbackForEssay() {
    }

    public FeedbackForEssay(int id, int essayId, String content, java.util.Date createdDate) {
        this.id = id;
        this.essayId = essayId;
        this.content = content;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEssayId() {
        return essayId;
    }

    public void setEssayId(int essayId) {
        this.essayId = essayId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.util.Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "FeedbackForEssay{" + "id=" + id + ", essayId=" + essayId + ", content=" + content + ", createdDate=" + createdDate + '}';
    }

}