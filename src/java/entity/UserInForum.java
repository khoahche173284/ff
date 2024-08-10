/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author pgb31
 */
public class UserInForum {

    private int userId;
    private String userRealName;
    private int totalPost;
    private int totalComment;
    private int totalLike;
    private int totalReport;
    private String status; // New field for status

    public UserInForum() {
    }

    public UserInForum(int userId, String userRealName, int totalPost, int totalComment, int totalLike, int totalReport, String status) {
        this.userId = userId;
        this.userRealName = userRealName;
        this.totalPost = totalPost;
        this.totalComment = totalComment;
        this.totalLike = totalLike;
        this.totalReport = totalReport;
        this.status = status;
    }

   
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public int getTotalPost() {
        return totalPost;
    }

    public void setTotalPost(int totalPost) {
        this.totalPost = totalPost;
    }

    public int getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(int totalComment) {
        this.totalComment = totalComment;
    }

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

    public int getTotalReport() {
        return totalReport;
    }

    public void setTotalReport(int totalReport) {
        this.totalReport = totalReport;
    }
}
