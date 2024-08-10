///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package model;
//
//import entity.Comment;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DAOComment extends DBConnect  {
//   
//    private static final String INSERT_COMMENT_SQL = "INSERT INTO comment "
//            + "(noi_dung, ngay_dang, post_id, user_id) VALUES (?, GETDATE(), ?, ?)";
//    private static final String SELECT_COMMENT_BY_ID = "SELECT c.*,"
//            + " u.Realname FROM comment c JOIN Users u ON c.user_id = u.ID WHERE c.id = ?";
//    private static final String SELECT_COMMENTS_BY_POST_ID = "SELECT c.*, u.Realname "
//        + "FROM comment c JOIN Users u ON c.user_id = u.ID "
//        + "WHERE c.post_id = ? "
//        + "ORDER BY c.ngay_dang DESC;";
//
// 
//    private static final String DELETE_COMMENT_SQL = "DELETE FROM comment WHERE id = ?";
//
//  
//
//    public void insertComment(Comment comment) throws SQLException {
//        try (
//                PreparedStatement preparedStatement = conn.prepareStatement(INSERT_COMMENT_SQL)) {
//            preparedStatement.setString(1, comment.getNoiDung());
//            preparedStatement.setInt(2, comment.getPostId());
//            preparedStatement.setInt(3, comment.getUserId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Comment selectComment(int id) {
//        Comment comment = null;
//        try (
//                PreparedStatement preparedStatement = conn.prepareStatement(SELECT_COMMENT_BY_ID)) {
//            preparedStatement.setInt(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                String noiDung = rs.getString("noi_dung");
//                Date ngayDang = rs.getDate("ngay_dang");
//                int postId = rs.getInt("post_id");
//                int userId = rs.getInt("user_id");
//                String realName = rs.getString("Realname");
//                comment = new Comment();
//                comment.setId(id);
//                comment.setNoiDung(noiDung);
//                comment.setNgayDang(ngayDang);
//                comment.setPostId(postId);
//                comment.setUserId(userId);
//                comment.setRealName(realName);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return comment;
//    }
//
//    public List<Comment> selectAllCommentsByPostId(int postId) {
//        List<Comment> comments = new ArrayList<>();
//        try (
//                PreparedStatement preparedStatement = conn.prepareStatement(SELECT_COMMENTS_BY_POST_ID)) {
//            preparedStatement.setInt(1, postId);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String noiDung = rs.getString("noi_dung");
//                Date ngayDang = rs.getDate("ngay_dang");
//                int userId = rs.getInt("user_id");
//                String realName = rs.getString("Realname");
//                Comment comment = new Comment();
//                comment.setId(id);
//                comment.setNoiDung(noiDung);
//                comment.setNgayDang(ngayDang);
//                comment.setPostId(postId);
//                comment.setUserId(userId);
//                comment.setRealName(realName);
//                comments.add(comment);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return comments;
//    }
//    private static final String UPDATE_COMMENT_SQL = "UPDATE comment SET noi_dung = ? WHERE id = ?";
//    public void updateComment( String noiDung,int id) throws SQLException {
//        try (PreparedStatement statement = conn.prepareStatement(UPDATE_COMMENT_SQL)) {
//            statement.setString(1, noiDung);
//            statement.setInt(2, id);
//            statement.executeUpdate();
//        }
//    }
//    
//
//    public boolean deleteComment(int id) throws SQLException {
//        boolean rowDeleted;
//        try (
//                PreparedStatement statement = conn.prepareStatement(DELETE_COMMENT_SQL)) {
//            statement.setInt(1, id);
//            rowDeleted = statement.executeUpdate() > 0;
//        }
//        return rowDeleted;
//    }
//}
//
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Comment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOComment extends DBConnect {

    private static final String INSERT_COMMENT_SQL = "INSERT INTO Comment "
            + "(noi_dung, ngay_dang, post_id, user_id) VALUES (?, GETDATE(), ?, ?)";
    private static final String SELECT_COMMENT_BY_ID = "SELECT c.*,"
            + " u.Realname,u.Img FROM Comment c JOIN Users u ON c.user_id = u.ID WHERE c.id = ?";
    private static final String SELECT_COMMENTS_BY_POST_ID = "SELECT c.*, u.Realname,u.Img "
            + "FROM Comment c JOIN Users u ON c.user_id = u.ID "
            + "WHERE c.post_id = ? "
            + "ORDER BY c.ngay_dang DESC;";

    private static final String DELETE_COMMENT_SQL = "DELETE FROM Comment WHERE id = ?";

    public void insertComment(Comment comment) throws SQLException {
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(INSERT_COMMENT_SQL)) {
            preparedStatement.setString(1, comment.getNoiDung());
            preparedStatement.setInt(2, comment.getPostId());
            preparedStatement.setInt(3, comment.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Comment selectComment(int id) {
        Comment comment = null;
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(SELECT_COMMENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String noiDung = rs.getString("noi_dung");
                Date ngayDang = rs.getDate("ngay_dang");
                int postId = rs.getInt("post_id");
                int userId = rs.getInt("user_id");
                String img = rs.getString("Img");
                String realName = rs.getString("Realname");
                comment = new Comment();
                comment.setImg(img);
                comment.setId(id);
                comment.setNoiDung(noiDung);
                comment.setNgayDang(ngayDang);
                comment.setPostId(postId);
                comment.setUserId(userId);
                comment.setRealName(realName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }

    public List<Comment> selectAllCommentsByPostId(int postId) {
        List<Comment> comments = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(SELECT_COMMENTS_BY_POST_ID)) {
            preparedStatement.setInt(1, postId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String noiDung = rs.getString("noi_dung");
                Date ngayDang = rs.getDate("ngay_dang");
                String img = rs.getString("Img");
                int userId = rs.getInt("user_id");
                String realName = rs.getString("Realname");
                Comment comment = new Comment();
                comment.setId(id);
                comment.setImg(img);
                comment.setNoiDung(noiDung);
                comment.setNgayDang(ngayDang);
                comment.setPostId(postId);
                comment.setUserId(userId);
                comment.setRealName(realName);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    private static final String UPDATE_COMMENT_SQL = "UPDATE Comment SET noi_dung = ? WHERE id = ?";

    public void updateComment(String noiDung, int id) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(UPDATE_COMMENT_SQL)) {
            statement.setString(1, noiDung);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }

    public boolean deleteComment(int id) throws SQLException {
        boolean rowDeleted;
        try (
                PreparedStatement statement = conn.prepareStatement(DELETE_COMMENT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public List<Comment> listAllCommentsReplyWithCmtID(int cmt_id) {
        String sql = "  select Comment. *,Users.Realname,Users.Img from Comment left join Users on Comment.user_id = Users.ID where cmt_id = ?"; 
        
        List<Comment> comments = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, cmt_id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String noiDung = rs.getString("noi_dung");
                Date ngayDang = rs.getDate("ngay_dang");
                String img = rs.getString("Img");
                int userId = rs.getInt("user_id");
                String realName = rs.getString("Realname");
                
                Comment comment = new Comment();
                comment.setId(id);
                comment.setImg(img);
                comment.setNoiDung(noiDung);
                comment.setNgayDang(ngayDang);
                comment.setCmt_id(cmt_id);
                comment.setUserId(userId);
                comment.setRealName(realName);
                
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    } 
    
    
    public List<Comment> listAllCommentsReply() {
        String sql = "select * from Comment where cmt_id IS NOT NULL ";
        List<Comment> comments = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String noiDung = rs.getString("noi_dung");
                Date ngayDang = rs.getDate("ngay_dang");
                String img = rs.getString("Img");
                int userId = rs.getInt("user_id");
                String realName = rs.getString("Realname"); 
                int cmt_id = rs.getInt("cmt_id");
                Comment comment = new Comment();
                comment.setId(id);
                comment.setImg(img);
                comment.setNoiDung(noiDung);
                comment.setNgayDang(ngayDang);
                comment.setCmt_id(cmt_id);
                comment.setUserId(userId);
                comment.setRealName(realName);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    
    

    public void insertReplyComment(Comment comment) throws SQLException {
        String sql = "INSERT INTO Comment (noi_dung,ngay_dang ,user_id, cmt_id) VALUES (?,GETDATE(), ?, ?)";
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, comment.getNoiDung());
            preparedStatement.setInt(2, comment.getUserId());
            preparedStatement.setInt(3, comment.getCmt_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateReplyComment(String noiDung, int id) throws SQLException {
        String sql = "UPDATE Comment SET noi_dung = ? WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, noiDung);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }

    public boolean deleteReplyComment(int id) throws SQLException {
        boolean rowDeleted;
        String sql = "DELETE FROM Comment WHERE id = ?";
        try (
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

}
