package entity;

import java.util.Date;

public class FeedbackForSystem {
    private int id;
    private int userId;
    private int feedbackTypeId;
    private String FeedbackName;
    private String content;
    private Date createdDate;

    public FeedbackForSystem() {
    }

    public FeedbackForSystem(int id, int userId, int feedbackTypeId, String FeedbackName, String content, Date createdDate) {
        this.id = id;
        this.userId = userId;
        this.feedbackTypeId = feedbackTypeId;
        this.FeedbackName = FeedbackName;
        this.content = content;
        this.createdDate = createdDate;
    }

    public FeedbackForSystem(int id, int userId, int feedbackTypeId, String content, Date createdDate) {
        this.id = id;
        this.userId = userId;
        this.feedbackTypeId = feedbackTypeId;
        this.content = content;
        this.createdDate = createdDate;
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

    public int getFeedbackTypeId() {
        return feedbackTypeId;
    }

    public void setFeedbackTypeId(int feedbackTypeId) {
        this.feedbackTypeId = feedbackTypeId;
    }

    public String getFeedbackName() {
        return FeedbackName;
    }

    public void setFeedbackName(String FeedbackName) {
        this.FeedbackName = FeedbackName;
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
        return "FeedbackForSystem{" + "id=" + id + ", userId=" + userId + ", feedbackTypeId=" + feedbackTypeId + ", FeedbackName=" + FeedbackName + ", content=" + content + ", createdDate=" + createdDate + '}';
    }


}
