package model;

import entity.AccessLogs;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object (DAO) for accessing AccessLogs data.
 */
public class DAOAccessLogs extends DBConnect {

    /**
     * Retrieves all access logs from the database.
     *
     * @return List of AccessLogs objects representing access logs.
     */
    public List<AccessLogs> getAllAccessLogs() {
        List<AccessLogs> accessLogs = new ArrayList<>();
        String sql = "SELECT * FROM Access_Logs";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                AccessLogs accessLog = new AccessLogs();
                accessLog.setId(rs.getInt("ID"));
                accessLog.setUserId(rs.getInt("UserID"));
                accessLog.setLoginTime(rs.getTimestamp("LoginTime"));
                accessLog.setIpAddress(rs.getString("IPAddress"));
                accessLog.setUserAgent(rs.getString("UserAgent"));
                accessLogs.add(accessLog);
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOAccessLogs.class.getName()).log(Level.SEVERE, null, e);
        }
        return accessLogs;
    }

    /**
     * Adds a new access log to the database.
     *
     * @param accessLog The AccessLogs object representing the new access log.
     */
    public void addAccessLog(AccessLogs accessLog) {
        String sql = "INSERT INTO Access_Logs (UserID, LoginTime, IPAddress, UserAgent) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accessLog.getUserId());
            stmt.setTimestamp(2, new Timestamp(accessLog.getLoginTime().getTime()));
            stmt.setString(3, accessLog.getIpAddress());
            stmt.setString(4, accessLog.getUserAgent());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAOAccessLogs.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Updates an existing access log in the database.
     *
     * @param accessLog The AccessLogs object representing the updated access
     * log.
     */
    public void updateAccessLog(AccessLogs accessLog) {
        String sql = "UPDATE Access_Logs SET UserID = ?, LoginTime = ?, IPAddress = ?, UserAgent = ? WHERE ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accessLog.getUserId());
            stmt.setTimestamp(2, new Timestamp(accessLog.getLoginTime().getTime()));
            stmt.setString(3, accessLog.getIpAddress());
            stmt.setString(4, accessLog.getUserAgent());
            stmt.setInt(5, accessLog.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAOAccessLogs.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Deletes an access log from the database.
     *
     * @param id The ID of the access log to delete.
     */
    public void deleteAccessLog(int id) {
        String sql = "DELETE FROM Access_Logs WHERE ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAOAccessLogs.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Retrieves an access log by its ID from the database.
     *
     * @param id The ID of the access log to retrieve.
     * @return The AccessLogs object representing the retrieved access log.
     */
    public AccessLogs getAccessLogById(int id) {
        AccessLogs accessLog = null;
        String sql = "SELECT * FROM Access_Logs WHERE ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    accessLog = new AccessLogs();
                    accessLog.setId(rs.getInt("ID"));
                    accessLog.setUserId(rs.getInt("UserID"));
                    accessLog.setLoginTime(rs.getTimestamp("LoginTime"));
                    accessLog.setIpAddress(rs.getString("IPAddress"));
                    accessLog.setUserAgent(rs.getString("UserAgent"));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOAccessLogs.class.getName()).log(Level.SEVERE, null, e);
        }
        return accessLog;
    }

    /**
     * Logs an access event for a user.
     *
     * @param userId The ID of the user.
     * @param ipAddress The IP address of the user.
     * @param userAgent The user agent of the user.
     */
    public void logAccess(int userId, String ipAddress, String userAgent) {
        String sql = "INSERT INTO Access_Logs (UserID, LoginTime, IPAddress, UserAgent) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.setString(3, ipAddress);
            stmt.setString(4, userAgent);
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAOAccessLogs.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Retrieves the count of access logs per user.
     *
     * @return A map of user IDs to their respective access log counts.
     */
    public Map<Integer, Integer> getAccessCountPerUser() {
        Map<Integer, Integer> accessCountPerUser = new HashMap<>();
        String sql = "SELECT UserID, COUNT(*) AS AccessCount FROM Access_Logs GROUP BY UserID";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                accessCountPerUser.put(rs.getInt("UserID"), rs.getInt("AccessCount"));
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOAccessLogs.class.getName()).log(Level.SEVERE, null, e);
        }
        return accessCountPerUser;
    }

    /**
     * Retrieves the count of access logs per day of the week.
     *
     * @return A map of days of the week to their respective access log counts.
     */
    public Map<String, Integer> getAccessCountPerDay() {
        Map<String, Integer> accessCountPerDay = new HashMap<>();
        String sql = "SELECT DATENAME(dw, LoginTime) AS DayOfWeek, COUNT(*) AS AccessCount FROM Access_Logs GROUP BY DATENAME(dw, LoginTime)";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                accessCountPerDay.put(rs.getString("DayOfWeek"), rs.getInt("AccessCount"));
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOAccessLogs.class.getName()).log(Level.SEVERE, null, e);
        }
        return accessCountPerDay;
    }
}