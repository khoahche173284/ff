/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.History;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for History
 */
public class DAOHistory extends DBConnect {

    public History getHistoryById(int id) {
        String sql = "SELECT h.ID, h.UserID, h.EssayID,c.CategoriesName, h.LastTimeChange, e.Content "
                + "FROM Draft h "
                + "JOIN Essay e ON h.EssayID = e.ID "
                + "JOIN Categories c ON e.CategoriesID = c.ID "
                + "WHERE h.ID = ?";
        try (
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractHistoryFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<History> searchHistory(int userId, String keyword) {
        List<History> histories = new ArrayList<>();
        String sql = "SELECT h.ID, h.UserID, h.EssayID, e.Content, h.LastTimeChange "
                + "FROM Draft h "
                + "JOIN Essay e ON h.EssayID = e.ID "
                + "WHERE h.UserID = ? AND e.Content LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    int userID = rs.getInt("UserID");
                    int essayID = rs.getInt("EssayID");
                    String content = rs.getString("Content");
                    String date = rs.getString("LastTimeChange");
                    History history = new History(id, userID, essayID, date, content);
                    histories.add(history);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return histories;
    }

    // Method to get all history records
    public List<History> getAllHistory(int userId) {
        List<History> listHistory = new ArrayList<>();

        String sql = "SELECT h.ID, h.UserID, h.EssayID,c.CategoriesName, h.LastTimeChange, e.Content "
                + "FROM Draft h "
                + "JOIN Essay e ON h.EssayID = e.ID "
                + "JOIN Categories c ON e.CategoriesID = c.ID "
                + "WHERE h.UserID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    int userID = rs.getInt("UserID");
                    int essayID = rs.getInt("EssayID");
                    String date = rs.getString("LastTimeChange");
                    String content = rs.getString("Content");
                    String categoriesName = rs.getString("CategoriesName");
                    History history = new History(id, userID, essayID, date, content, categoriesName);
                    listHistory.add(history);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHistory;
    }

    public void deleteHistory(int id) {
        String moveToTrashSQL = "INSERT INTO Trash (UserID, EssayID) "
                + "SELECT UserID, EssayID FROM Draft WHERE ID = ?";
        String deleteFromHistorySQL = "DELETE FROM Draft WHERE ID = ?";

        try (PreparedStatement moveToTrashStmt = conn.prepareStatement(moveToTrashSQL); PreparedStatement deleteFromHistoryStmt = conn.prepareStatement(deleteFromHistorySQL)) {

            moveToTrashStmt.setInt(1, id);
            moveToTrashStmt.executeUpdate();

            deleteFromHistoryStmt.setInt(1, id);
            deleteFromHistoryStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void duplicateHistory(int historyId) {
        String selectSQL = "SELECT UserID, EssayID FROM Draft WHERE ID = ?";
        String selectEssaySQL = "SELECT Content,CategoriesID FROM Essay WHERE ID = ?";
        String insertEssaySQL = "INSERT INTO Essay (UserID, CategoriesID, Content) VALUES (?, ?, ?)";
        String insertHistorySQL = "INSERT INTO Draft (UserID, EssayID, LastTimeChange) VALUES (?, ?,GETDATE())";

        try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL); PreparedStatement selectEssayStmt = conn.prepareStatement(selectEssaySQL); PreparedStatement insertEssayStmt = conn.prepareStatement(insertEssaySQL, Statement.RETURN_GENERATED_KEYS); PreparedStatement insertHistoryStmt = conn.prepareStatement(insertHistorySQL)) {

            selectStmt.setInt(1, historyId);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("UserID");
                    int oldEssayId = rs.getInt("EssayID");
                    System.out.println("UserID: " + userId + ", EssayID: " + oldEssayId);

                    selectEssayStmt.setInt(1, oldEssayId);
                    try (ResultSet essayRs = selectEssayStmt.executeQuery()) {
                        if (essayRs.next()) {
                            String content = essayRs.getString("Content");
                            int categoriesID = Integer.parseInt(essayRs.getString("CategoriesID"));

                            insertEssayStmt.setInt(1, userId);
                            insertEssayStmt.setInt(2, categoriesID);
                            insertEssayStmt.setString(3, content);
                            insertEssayStmt.executeUpdate();

                            try (ResultSet generatedKeys = insertEssayStmt.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    int newEssayId = generatedKeys.getInt(1);
                                    System.out.println("New EssayID: " + newEssayId);

                                    insertHistoryStmt.setInt(1, userId);
                                    insertHistoryStmt.setInt(2, newEssayId);

                                    insertHistoryStmt.executeUpdate();
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOHistory.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Helper method to extract history data from ResultSet
    private History extractHistoryFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
                    int userID = rs.getInt("UserID");
                    int essayID = rs.getInt("EssayID");
                    String date = rs.getString("LastTimeChange");
                    String content = rs.getString("Content");
                    String categoriesName = rs.getString("CategoriesName");
                  History history = new History(id, userID, essayID, date, content, categoriesName);
return history;
    }

    public boolean isEssayFavorited(int userId, int essayId) {
        String sql = "SELECT COUNT(*) FROM Favorite_Essay_Draft WHERE UserID = ? AND EssayID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, essayId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT CategoriesName FROM Categories";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categories.add(rs.getString("CategoriesName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<History> getHistoryByCategory(int userId, String category) {
        List<History> histories = new ArrayList<>();
        String sql = "SELECT h.ID, h.UserID, h.EssayID, e.Content, h.LastTimeChange, c.CategoriesName "
                + "FROM Draft h "
                + "JOIN Essay e ON h.EssayID = e.ID "
                + "JOIN Categories c ON e.CategoriesID = c.ID "
                + "WHERE h.UserID = ? AND c.CategoriesName = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, category);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    int userID = rs.getInt("UserID");
                    int essayID = rs.getInt("EssayID");
                    String content = rs.getString("Content");
                    String date = rs.getString("LastTimeChange");
                    String categoriesName = rs.getString("CategoriesName");
                    History history = new History(id, userID, essayID, date, content, categoriesName);
                    histories.add(history);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return histories;
    }

    public List<History> searchHistory(int userId, String keyword, String category) {
    List<History> histories = new ArrayList<>();
    String sql = "SELECT h.ID, h.UserID, h.EssayID, e.Content, h.LastTimeChange, c.CategoriesName "
            + "FROM Draft h "
            + "JOIN Essay e ON h.EssayID = e.ID "
            + "JOIN Categories c ON e.CategoriesID = c.ID "
            + "WHERE h.UserID = ? AND e.Content LIKE ? AND c.CategoriesName LIKE ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, userId);
        stmt.setString(2, "%" + keyword + "%");
        stmt.setString(3, "%" + category + "%");
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                int userID = rs.getInt("UserID");
                int essayID = rs.getInt("EssayID");
                String content = rs.getString("Content");
                String date = rs.getString("LastTimeChange");
                String categoriesName = rs.getString("CategoriesName");
                History history = new History(id, userID, essayID, date, content, categoriesName);
                histories.add(history);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return histories;
}

}