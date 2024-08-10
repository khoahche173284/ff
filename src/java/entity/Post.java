package entity;

public class Post {
    private int id;
    private int typeId;
    private String ngayDang;
    private String noiDung;
    private int luotThich;
    private int userId;
    private int soLuongComment;
    private int numberReport;
    private String title;
    private String realName;



    public Post(int id, int typeId, String ngayDang, String noiDung, int luotThich, int userId, int soLuongComment, int numberReport, String title, String realName) {
        this.id = id;
        this.typeId = typeId;
        this.ngayDang = ngayDang;
        this.noiDung = noiDung;
        this.luotThich = luotThich;
        this.userId = userId;
        this.soLuongComment = soLuongComment;
        this.numberReport = numberReport;
        this.title = title;
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
    

    public Post() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(int luotThich) {
        this.luotThich = luotThich;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSoLuongComment() {
        return soLuongComment;
    }

    public void setSoLuongComment(int soLuongComment) {
        this.soLuongComment = soLuongComment;
    }

    public int getNumberReport() {
        return numberReport;
    }

    public void setNumberReport(int numberReport) {
        this.numberReport = numberReport;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", typeId=" + typeId + ", ngayDang=" + ngayDang + ", noiDung=" + noiDung + ", luotThich=" + luotThich + ", userId=" + userId + ", soLuongComment=" + soLuongComment + ", numberReport=" + numberReport + ", title=" + title + ", realName=" + realName + '}';
    }

   
    
}
