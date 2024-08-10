/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import entity.listlike;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pgb31
 */
public class DAOListlike extends DBConnect{
    
//    public ArrayList<listlike> getalllistlike() {
//        ArrayList<listlike> list = new ArrayList<>();
//
//        String sql = "SELECT FROM listuserslikepost";
//
//        try {
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                int postId = rs.getInt("postid");
//                int userId = rs.getInt("userid");
//                Date like_date = rs.getTimestamp("like_date");
//
//                listlike like = new listlike(id, postId, userId, like_date);
//                like.setId(id);
//
//                list.add(like);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOReportPost.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }

    public ArrayList<listlike> getlistlikebypostid(int postid) {
        ArrayList<listlike> list = new ArrayList<>();

        String sql = "SELECT List_Users_Like_Post.id,postid,userid,like_date,Realname FROM List_Users_Like_Post LEFT JOIN Users on Users.ID=List_Users_Like_Post.userid where List_Users_Like_Post.postid =" + postid;

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int postId = rs.getInt("postid");
                int userId = rs.getInt("userid");
                Date like_date = rs.getTimestamp("like_date");
                String name = rs.getString("Realname");
               listlike like = new listlike(id, postId, userId, like_date, name);
                like.setId(id);

                list.add(like);
            }
        } catch (SQLException ex) {
  Logger.getLogger(DAOListlike.class.getName()).log(Level.SEVERE, null, ex);        }
        return list;
    }

    public void createlike(int postid, int uid) {
        String sql = "insert into List_Users_Like_Post(postid,userid) values(?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, postid);
             preparedStatement.setInt(2, uid);
             preparedStatement.executeUpdate();
        } catch (SQLException ex) {
  Logger.getLogger(DAOListlike.class.getName()).log(Level.SEVERE, null, ex);        }
    }
     public void deletelike(int postid, int uid) {
        try {
            String sql = "Delete List_Users_Like_Post where postid = ? and userid = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, postid);
            preparedStatement.setInt(2, uid);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOListlike.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}