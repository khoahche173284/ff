/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author congkhoa2723
 */
import java.util.Date;

public class Comment {
    private int id;
    private String noiDung;
    private Date ngayDang;
    private int postId;
    private int userId;
    private String realName; 
    private String img; // Thêm thuộc tính realName để lưu tên thật của người viết comment
    private int cmt_id;

    public Comment(int id, String noiDung, Date ngayDang, int postId, int userId, String realName, String img, int cmt_id) {
        this.id = id;
        this.noiDung = noiDung;
        this.ngayDang = ngayDang;
        this.postId = postId;
        this.userId = userId;
        this.realName = realName;
        this.img = img;
        this.cmt_id = cmt_id;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCmt_id() {
        return cmt_id;
    }

    public void setCmt_id(int cmt_id) {
        this.cmt_id = cmt_id;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", noiDung=" + noiDung + ", ngayDang=" + ngayDang + ", postId=" + postId + ", userId=" + userId + ", realName=" + realName + ", img=" + img + ", cmt_id=" + cmt_id + '}';
    }

}