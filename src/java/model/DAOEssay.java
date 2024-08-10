package model;

import entity.Essay;
import entity.History;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data Access Object for Essay
 */
public class DAOEssay extends DBConnect {

    public Essay createEssay(int userId, int categoryId, String content) {
        String sql = "INSERT INTO Essay (UserID, CategoriesID, Content, CreatedDate) VALUES (?, ?, ?, GETDATE())";
        Essay newEssay = null;
        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Chèn dữ liệu vào bảng Essay
            stmt.setInt(1, userId);
            stmt.setInt(2, categoryId);
            stmt.setString(3, content);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Lấy EssayID của bài luận mới được chèn vào bảng Essay
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int essayId = generatedKeys.getInt(1);
                    newEssay = new Essay(essayId, userId, categoryId, content, new Date());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newEssay;
    }

    public History moveEssayToHis(int userId, int essayId) {
        String historySql = "INSERT INTO Draft (UserID, EssayID, LastTimeChange) VALUES (?, ?, GETDATE())";
        History history = null;
        try (PreparedStatement historyStmt = conn.prepareStatement(historySql)) {

            historyStmt.setInt(1, userId);
            historyStmt.setInt(2, essayId);
            historyStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    // Method to get an essay by ID
    public Essay getEssayById(int id) {
        String sql = "SELECT * FROM Essay WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractEssayFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Essay getEssayByContent(int userId, int categoryId, String content) {
        String sql = "SELECT * FROM Essay WHERE UserID = ? AND CategoryID = ? AND Content = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, categoryId);
            stmt.setString(3, content);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("ID");
                    Essay essay = new Essay(id, userId, categoryId, content);
                    return essay;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Essay updateEssay(String content, int id) {
        String sql = "UPDATE Essay SET Content = ? WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, content);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Method to get all essays
    public List<Essay> getAllEssays() {
        List<Essay> essays = new ArrayList<>();
        String sql = "SELECT * FROM Essay";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                essays.add(extractEssayFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return essays;
    }

    // Method to update an essay
    public boolean updateEssay(int id, int userId, int categoryId, String content) {
        String sql = "UPDATE Essay SET UserID = ?, CategoriesID = ?, Content = ? WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, categoryId);
            stmt.setString(3, content);
            stmt.setInt(4, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete an essay
    public boolean deleteEssay(int id) {
        String sql = "DELETE FROM Essay WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to extract essay data from ResultSet
    private Essay extractEssayFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        int userId = rs.getInt("UserID");
        int categoryId = rs.getInt("CategoriesID");
        String content = rs.getString("Content");
        Date createdDate = new Date(rs.getTimestamp("CreatedDate").getTime());
        return new Essay(id, userId, categoryId, content, createdDate);
    }

    public List<String> getAllEssayContents() {
        List<String> contents = new ArrayList<>();
        String sql = "SELECT Content FROM Essay";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                contents.add(rs.getString("Content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contents;
    }
}
