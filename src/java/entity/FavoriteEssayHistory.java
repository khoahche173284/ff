package entity;

import java.util.Date;

public class FavoriteEssayHistory {

    private int id;
    private int userId;
    private int essayId;
    private Date addedDate;
    private String content;
    private Date createdDate; 
    private String category;

    public FavoriteEssayHistory() {
    }

    public FavoriteEssayHistory(int id, int userId, int essayId, Date addedDate, String content, Date createdDate, String category) {
        this.id = id;
        this.userId = userId;
        this.essayId = essayId;
        this.addedDate = addedDate;
        this.content = content;
        this.createdDate = createdDate;
        this.category = category;
    }

    public FavoriteEssayHistory(int userId, int essayId) {
        this.userId = userId;
        this.essayId = essayId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
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

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "FavoriteEssayHistory{" + "id=" + id + ", userId=" + userId + ", essayId=" + essayId + ", addedDate=" + addedDate + ", content=" + content + ", createdDate=" + createdDate + ", category=" + category + '}';
    }
}