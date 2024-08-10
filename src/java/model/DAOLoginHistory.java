/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.ActivityType;
import entity.Categories;
import entity.LoginHistory;
import entity.Users;
import jakarta.mail.Session;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author congkhoa2723
 */
public class DAOLoginHistory extends DBConnect {
//
//    public void insertLoginHistory(int userid) {
//        String sql = "INSERT INTO LoginHistory (UserID) VALUES (?)";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, userid);
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            java.util.logging.Logger.getLogger(DAOUsers.class.getName()
//            ).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//    }

    public void insertRecentActivity(int userId, int activityTypeID) {
        String sql = "INSERT INTO Recent_Activity "
                + "(UserID, ActivityTypeID) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, activityTypeID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOUsers.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
    }

//     public List<LoginHistory> getLoginHistoryByUserId(int userId) {
//        List<LoginHistory> list = new ArrayList<>();
//        String sql = "SELECT ra.ID, ra.LoginTime, at.TypeName " +
//                     "FROM RecentActivity ra " +
//                     "JOIN ActivityType at ON ra.ActivityTypeID = at.ID " +
//                     "WHERE ra.UserID = ? AND ra.ActivityTypeID = ?";
//        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, userId);
//            ps.setInt(2, 1); // Assuming 1 is the ActivityTypeID for login
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    LoginHistory loginHistory = new LoginHistory();
//                    loginHistory.setId(rs.getInt("ID"));
//                    loginHistory.setLoginTime(rs.getTimestamp("LoginTime"));
//                    loginHistory.setTypeName(rs.getString("TypeName"));
//                    list.add(loginHistory);
//                }
//            }
//        } catch (SQLException ex) {
//            LOGGER.log(Level.SEVERE, "Error retrieving login history", ex);
//        }
//        return list;
//    }
    public List<LoginHistory> getHistoryActivityByUserId(int userId) {
        List<LoginHistory> list = new ArrayList<>();
        String sql = "SELECT r.ID, r.LoginTime,r.ActivityTypeID, a.ActivityName as TypeName "
                + "FROM Recent_Activity r "
                + "JOIN Activity_Type a ON r.ActivityTypeID = a.ID "
                + "WHERE r.UserID = ? ";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LoginHistory loginHistory = new LoginHistory();
                    loginHistory.setId(rs.getInt("ID"));
                    loginHistory.setTypeName(rs.getString("TypeName"));
                    loginHistory.setLoginTime(rs.getTimestamp("LoginTime"));
                    list.add(loginHistory);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(
                    DAOUsers.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);

        }
        return list;
    }

    public List<LoginHistory> getRecentActivitiesByActivityTypeID(int ID, int userID) {
        List<LoginHistory> activities = new ArrayList<>();
        String SQL_SELECT_BY_ACTIVITY_TYPE_ID = "SELECT r.ID, r.LoginTime, r.ActivityTypeID, a.ActivityName AS TypeName "
                + "FROM Recent_Activity r "
                + "JOIN Activity_Type a ON r.ActivityTypeID = a.ID "
                + "WHERE r.ActivityTypeID = ? AND r.UserID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ACTIVITY_TYPE_ID)) {
            stmt.setInt(1, ID);
            stmt.setInt(2, userID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LoginHistory loginHistory = new LoginHistory();
                loginHistory.setId(rs.getInt("ID"));
                loginHistory.setLoginTime(rs.getTimestamp("LoginTime"));
                loginHistory.setTypeName(rs.getString("TypeName")); // Set TypeName from ActivityType table
                activities.add(loginHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    public List<ActivityType> getActivityNamesByUserID(int userID) {
        List<ActivityType> activityNames = new ArrayList<>();
        String SQL_SELECT_ACTIVITY_NAMES = "SELECT a.ID, a.ActivityName as TypeName "
                + "FROM Recent_Activity r "
                + "JOIN Activity_Type a ON r.ActivityTypeID = a.ID "
                + "WHERE r.UserID = ? "
                + "GROUP BY a.ID, a.ActivityName";
        try (PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_ACTIVITY_NAMES)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ActivityType activityType = new ActivityType();
                activityType.setId(rs.getString("ID"));
                activityType.setActivityName(rs.getString("TypeName"));
                activityNames.add(activityType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityNames;
    }

//
//    public List<String> getActivityNamesByUserID(int userID) {
//        List<String> activityNames = new ArrayList<>();
//        String SQL_SELECT_ACTIVITY_NAMES = "SELECT a.ActivityName as TypeName FROM RecentActivity r JOIN ActivityType a ON r.ActivityTypeID = a.ID WHERE r.UserID = ?";
//        try (PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_ACTIVITY_NAMES)) {
//            stmt.setInt(1, userID);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                LoginHistory loginHistory = new LoginHistory();
//                 loginHistory.setTypeName(rs.getString("TypeName"));
//                
//                activityNames.add(loginHistory);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return activityNames;
//    }
//    public List<LoginHistory> getLoginHistoryByUserId(int userId) {
//        List<LoginHistory> list = new ArrayList<>();
//        String sql = "SELECT * FROM LoginHistory WHERE UserID = ?";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, userId);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                LoginHistory loginHistory = new LoginHistory();
//                loginHistory.setId(rs.getInt("ID"));
//                loginHistory.setLoginTime(rs.getTimestamp(
//                        "LoginTime"));
//                list.add(loginHistory);
//            }
//
//        } catch (SQLException ex) {
//            java.util.logging.Logger.getLogger(
//                    DAOUsers.class.getName()).log(
//           java.util.logging.Level.SEVERE, null, ex);
//
//        }
//        return list;
//    }
}
