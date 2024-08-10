package entity;
import java.util.Date;


public class ReportPost {
    
  
    private int id;
    
 
    private int postId;
    
  
    private int userId;
 
    private Date reportDate; 
    private String reportreason;
    private String UserName;

    public ReportPost(int id, int postId, int userId, Date reportDate, String reportreason, String UserName) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.reportDate = reportDate;
        this.reportreason = reportreason;
        this.UserName = UserName;
    }

    public ReportPost() {
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

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportreason() {
        return reportreason;
    }

    public void setReportreason(String reportreason) {
        this.reportreason = reportreason;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    
    
}
