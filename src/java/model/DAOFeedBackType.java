package model;

import entity.FeedbackType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Feedback_Type
 */
public class DAOFeedBackType extends DBConnect {

    // Method to create a new feedback type
    public boolean createFeedbackType(String feedbackName) {
        String sql = "INSERT INTO Feedback_Type (Feedback_Name) VALUES (?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, feedbackName);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to get a feedback type by ID
    public FeedbackType getFeedbackTypeById(int id) {
        String sql = "SELECT * FROM Feedback_Type WHERE ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractFeedbackTypeFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to get all feedback types
    public List<FeedbackType> getAllFeedbackTypes() {
        List<FeedbackType> feedbackTypes = new ArrayList<>();
        String sql = "SELECT * FROM Feedback_Type";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                feedbackTypes.add(extractFeedbackTypeFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackTypes;
    }

    // Method to update a feedback type
    public boolean updateFeedbackType(int id, String feedbackName) {
        String sql = "UPDATE Feedback_Type SET Feedback_Name = ? WHERE ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, feedbackName);
            stmt.setInt(2, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete a feedback type
    public boolean deleteFeedbackType(int id) {
        String sql = "DELETE FROM Feedback_Type WHERE ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to extract feedback type data from ResultSet
    private FeedbackType extractFeedbackTypeFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        String feedbackName = rs.getString("Feedback_Name");
        return new FeedbackType(id, feedbackName);
    }

    private Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
