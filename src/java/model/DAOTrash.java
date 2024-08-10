package model;

import entity.Trash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOTrash extends DBConnect {

    // Method to move a record to Trash
    public void createTrash(int userId, int essayId) {
        String sql = "INSERT INTO Trash (UserID, EssayID, Deleted_Date) VALUES (?, ?, GETDATE())";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, essayId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


   public void deleteTrash(int id) {
  
    String selectSql = "SELECT EssayID FROM Trash WHERE ID = ?";
    String deleteTrashSql = "DELETE FROM Trash WHERE ID = ?";
    String deleteEssaySql = "DELETE FROM Essay WHERE ID = ?";
    
    try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
        selectStmt.setInt(1, id);
        ResultSet rs = selectStmt.executeQuery();
        
        if (rs.next()) {
            int essayId = rs.getInt("EssayID");
            
       
            try (PreparedStatement deleteTrashStmt = conn.prepareStatement(deleteTrashSql)) {
                deleteTrashStmt.setInt(1, id);
                deleteTrashStmt.executeUpdate();
            }
            
       
            try (PreparedStatement deleteEssayStmt = conn.prepareStatement(deleteEssaySql)) {
                deleteEssayStmt.setInt(1, essayId);
                deleteEssayStmt.executeUpdate();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // Method to restore a record from Trash
    public void restoreTrash(int id) {
        String moveToHistorySQL = "INSERT INTO Draft (UserID, EssayID, LastTimeChange) "
                + "SELECT UserID, EssayID, GETDATE() FROM Trash WHERE ID = ?";
        String deleteFromTrashSQL = "DELETE FROM Trash WHERE ID = ?";

        try (PreparedStatement moveToHistoryStmt = conn.prepareStatement(moveToHistorySQL); PreparedStatement deleteFromTrashStmt = conn.prepareStatement(deleteFromTrashSQL)) {

            moveToHistoryStmt.setInt(1, id);
            moveToHistoryStmt.executeUpdate();

            deleteFromTrashStmt.setInt(1, id);
            deleteFromTrashStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all trash records
    public List<Trash> getAllTrash(int userId) {
        List<Trash> trashList = new ArrayList<>();

        String sql = "SELECT t.ID, t.UserID, t.EssayID, t.Deleted_Date, e.Content "
                + "FROM Trash t "
                + "JOIN Essay e ON t.EssayID = e.ID "
                + "WHERE t.UserID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    int userID = rs.getInt("UserID");
                    int essayID = rs.getInt("EssayID");
                    java.util.Date deletedDate = new java.util.Date(rs.getTimestamp("Deleted_Date").getTime());
                    String content = rs.getString("Content");
                    Trash trash = new Trash(id, userID, essayID, deletedDate, content);
                    trashList.add(trash);
                }
            } // Đưa phần catch ra khỏi try-with-resources
        } catch (SQLException ex) {
            Logger.getLogger(DAOHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trashList;
    }


    public List<Trash> searchTrash(int userId, String keyword) {
        List<Trash> listTrash = new ArrayList<>();
        String sql = "SELECT t.ID, t.UserID, t.EssayID, t.Deleted_Date, e.Content "
                + "FROM Trash t "
                + "JOIN Essay e ON t.EssayID = e.ID "
                + "WHERE t.UserID =? AND e.Content LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    int userID = rs.getInt("UserID");
                    int essayID = rs.getInt("EssayID");
                    java.util.Date deletedDate = new java.util.Date(rs.getTimestamp("Deleted_Date").getTime());
                    String content = rs.getString("Content");
                    Trash trash = new Trash(id, userID, essayID, deletedDate, content);
                    listTrash.add(trash);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTrash;
    }

    // Helper method to extract trash data from ResultSet
    private Trash extractTrashFromResultSet(ResultSet rs) throws SQLException {

        int id = rs.getInt("ID");
        int userId = rs.getInt("UserID");
        int essayId = rs.getInt("EssayID");
        java.util.Date deletedDate = new java.util.Date(rs.getTimestamp("Deleted_Date").getTime());
        String content = rs.getString("Content"); // Lấy thêm thuộc tính content

        return new Trash(id, userId, essayId, deletedDate, content); // Bao gồm content trong constructor

    }

}
