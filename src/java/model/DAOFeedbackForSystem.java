package model;

import entity.FeedbackForSystem;
import entity.FeedbackType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for Feedback_For_System
 */
public class DAOFeedbackForSystem extends DBConnect {

    public boolean deleteFeedbackForSystem(int id) {
        String sql = "DELETE FROM Feedback_For_System WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to create a new feedback for system
    public void createFeedbackForSystem(int userId, int feedbackTypeId, String content) {
        String sql = "INSERT INTO Feedback_For_System (UserID, Feedback_TypeID, Content, CreatedDate) VALUES (?, ?, ?, GETDATE())";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, feedbackTypeId);
            stmt.setString(3, content);
            int rowsAffected = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Method to get feedback for system by ID
    public FeedbackForSystem getFeedbackForSystemById(int id) {
        String sql = "SELECT * FROM Feedback_For_System WHERE ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractFeedbackForSystemFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to get all feedback for the system
    public List<FeedbackForSystem> getAllFeedbackForSystem() {
        List<FeedbackForSystem> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM Feedback_For_System";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                feedbacks.add(extractFeedbackForSystemFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }

    // Method to update feedback for system
    public boolean updateFeedbackForSystem(int id, int userId, int feedbackTypeId, String content) {
        String sql = "UPDATE Feedback_For_System SET UserID = ?, Feedback_TypeID = ?, Content = ?, CreatedDate = GETDATE() WHERE ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, feedbackTypeId);
            stmt.setString(3, content);
            stmt.setInt(4, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete feedback for system
//    public boolean deleteFeedbackForSystem(int id) {
//        String sql = "DELETE FROM Feedback_For_System WHERE ID = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            int rowsAffected = stmt.executeUpdate();
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    // Helper method to extract feedback for system data from ResultSet
    private FeedbackForSystem extractFeedbackForSystemFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        int userId = rs.getInt("UserID");
        int feedbackTypeId = rs.getInt("Feedback_TypeID");
        String content = rs.getString("Content");
        java.util.Date createdDate = new java.util.Date(rs.getTimestamp("CreatedDate").getTime());
        return new FeedbackForSystem(id, userId, feedbackTypeId, content, createdDate);
    }

    private Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<FeedbackForSystem> getAllFeedback() {
        ArrayList<FeedbackForSystem> feedbackList = new ArrayList<>();
        String sql = "SELECT ID, UserID, Feedback_TypeID, Content, CreatedDate FROM Feedback_For_System";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                int userId = rs.getInt("UserID");
                int feedbackTypeId = rs.getInt("Feedback_TypeID");
                String content = rs.getString("Content");
                Date createdDate = rs.getTimestamp("CreatedDate");

                FeedbackForSystem feedback = new FeedbackForSystem(id, userId, feedbackTypeId, content, createdDate);
                feedbackList.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedbackForSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedbackList;
    }

    public ArrayList<FeedbackType> getAllFeedbackTypes() {
        ArrayList<FeedbackType> feedbackTypeList = new ArrayList<>();
        String sql = "SELECT ID, Feedback_Name FROM Feedback_Type";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String feedbackName = rs.getString("Feedback_Name");

                FeedbackType feedbackType = new FeedbackType(id, feedbackName);
                feedbackTypeList.add(feedbackType);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedbackForSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedbackTypeList;
    }

    public ArrayList<FeedbackForSystem> getFeedback(int i) {
        ArrayList<FeedbackForSystem> feedbackList = new ArrayList<>();
        String sql = "SELECT ID, UserID, Feedback_TypeID, Content, CreatedDate FROM Feedback_For_System where Feedback_TypeID =" + i;

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                int userId = rs.getInt("UserID");
                int feedbackTypeId = rs.getInt("Feedback_TypeID");
                String content = rs.getString("Content");
                Date createdDate = rs.getTimestamp("CreatedDate");

                FeedbackForSystem feedback = new FeedbackForSystem(id, userId, feedbackTypeId, content, createdDate);
                feedbackList.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedbackForSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedbackList;
    }

}
