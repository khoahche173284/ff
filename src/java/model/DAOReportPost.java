/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.ReportPost;
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
public class DAOReportPost extends DBConnect {
//
//    public ArrayList<ReportPost> getAllReports() {
//        ArrayList<ReportPost> reports = new ArrayList<>();
//
//        String sql = "SELECT * FROM reports";
//
//        try {
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                int postId = rs.getInt("postid");
//                int userId = rs.getInt("userid");
//                Date reportDate = rs.getTimestamp("report_date");
//
//                ReportPost report = new ReportPost(postId, userId, reportDate);
//                report.setId(id);
//
//                reports.add(report);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOReportPost.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return reports;
//    }

    public ArrayList<ReportPost> getReportsbypostid(int postid) {
        ArrayList<ReportPost> reports = new ArrayList<>();

        String sql = "SELECT Reports.id,postid,userid,report_date,Realname,content FROM Reports LEFT JOIN Users on Users.ID=Reports.userid where Reports.postid =" + postid;

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int postId = rs.getInt("postid");
                int userId = rs.getInt("userid");
                Date reportDate = rs.getTimestamp("report_date");
                String name = rs.getString("Realname");
                String reason = rs.getString("content");
                ReportPost report = new ReportPost(id, postId, userId, reportDate, reason, name);

                reports.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOReportPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reports;
    }

    public void createreport(int postid, int uid, String reason ) {
        String sql = "insert into Reports(postid,userid,content) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, postid);
             preparedStatement.setInt(2, uid);
             preparedStatement.setString(3, reason);
             preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOReportPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
