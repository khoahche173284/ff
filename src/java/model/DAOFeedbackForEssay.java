package model;

import entity.FeedbackForEssay;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Feedback_For_Essay
 */
public class DAOFeedbackForEssay extends DBConnect {

    // Method to create a new feedback for essay
    public boolean createFeedbackForEssay(int essayId, String content) {
        String sql = "INSERT INTO Feedback_For_Essay (EssayID, Content, CreatedDate) VALUES (?, ?, GETDATE())";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, essayId);
            stmt.setString(2, content);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to get feedback for essay by ID
    public FeedbackForEssay getFeedbackForEssayById(int id) {
        String sql = "SELECT * FROM Feedback_For_Essay WHERE ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractFeedbackForEssayFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to get all feedback for essays
    public List<FeedbackForEssay> getAllFeedbackForEssays() {
        List<FeedbackForEssay> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM Feedback_For_Essay";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                feedbacks.add(extractFeedbackForEssayFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }

    // Method to update feedback for essay
    public boolean updateFeedbackForEssay(int id, int essayId, String content) {
        String sql = "UPDATE Feedback_For_Essay SET EssayID = ?, Content = ?, CreatedDate = GETDATE() WHERE ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, essayId);
            stmt.setString(2, content);
            stmt.setInt(3, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete feedback for essay
    public boolean deleteFeedbackForEssay(int id) {
        String sql = "DELETE FROM Feedback_For_Essay WHERE ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to extract feedback for essay data from ResultSet
    private FeedbackForEssay extractFeedbackForEssayFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        int essayId = rs.getInt("EssayID");
        String content = rs.getString("Content");
        java.util.Date createdDate = new java.util.Date(rs.getTimestamp("CreatedDate").getTime());
        return new FeedbackForEssay(id, essayId, content, createdDate);
    }

    private Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
