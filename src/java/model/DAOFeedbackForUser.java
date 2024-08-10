package model;

import entity.FeedbackForSystem;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBConnect;
import java.sql.PreparedStatement;

public class DAOFeedbackForUser extends DBConnect {

    public ArrayList<FeedbackForSystem> getAllFeedbackForUser(int userID) {
        ArrayList<FeedbackForSystem> feedbackList = new ArrayList<>();
        String query = "SELECT fs.ID, fs.UserID, fs.Feedback_TypeID, ft.Feedback_Name, fs.Content, fs.CreatedDate "
                + "FROM Feedback_For_System fs "
                + "JOIN Feedback_Type ft ON fs.Feedback_TypeID = ft.ID "
                + "WHERE fs.UserID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                int userId = rs.getInt("UserID");
                int feedbackTypeId = rs.getInt("Feedback_TypeID");
                String feedbackName = rs.getString("Feedback_Name");
                String content = rs.getString("Content");
                Date createdDate = rs.getDate("CreatedDate");

                FeedbackForSystem feedback = new FeedbackForSystem();
                feedback.setId(id);
                feedback.setUserId(userId);
                feedback.setFeedbackTypeId(feedbackTypeId);
                feedback.setFeedbackName(feedbackName);
                feedback.setContent(content);
                feedback.setCreatedDate(createdDate);

                feedbackList.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedbackForUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return feedbackList;
    }

    public boolean deleteFeedback(int feedbackId) {
        String query = "DELETE FROM Feedback_For_System WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, feedbackId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedbackForUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<FeedbackForSystem> getFeedbackForUser(int userID, int offset, int limit) {
        ArrayList<FeedbackForSystem> feedbackList = new ArrayList<>();
        String query = "SELECT fs.ID, fs.UserID, fs.Feedback_TypeID, ft.Feedback_Name, fs.Content, fs.CreatedDate "
                + "FROM Feedback_For_System fs "
                + "JOIN Feedback_Type ft ON fs.Feedback_TypeID = ft.ID "
                + "WHERE fs.UserID = ? "
                + "ORDER BY fs.ID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, offset);
            stmt.setInt(3, limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                int userId = rs.getInt("UserID");
                int feedbackTypeId = rs.getInt("Feedback_TypeID");
                String feedbackName = rs.getString("Feedback_Name");
                String content = rs.getString("Content");
                Date createdDate = rs.getDate("CreatedDate");

                FeedbackForSystem feedback = new FeedbackForSystem();
                feedback.setId(id);
                feedback.setUserId(userId);
                feedback.setFeedbackTypeId(feedbackTypeId);
                feedback.setFeedbackName(feedbackName);
                feedback.setContent(content);
                feedback.setCreatedDate(createdDate);

                feedbackList.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedbackForUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return feedbackList;
    }

    public int getTotalFeedbackCount(int userID) {
        String query = "SELECT COUNT(*) FROM Feedback_For_System WHERE UserID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedbackForUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<String> getAllFeedbackTypes() {
        ArrayList<String> feedbackTypes = new ArrayList<>();
        String query = "SELECT Feedback_Name FROM Feedback_Type";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                feedbackTypes.add(rs.getString("Feedback_Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedbackForUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return feedbackTypes;
    }

    public int getTotalFeedbackCount(int userID, String keyword, String category) {
        String query = "SELECT COUNT(*) FROM Feedback_For_System fs "
                + "JOIN Feedback_Type ft ON fs.Feedback_TypeID = ft.ID "
                + "WHERE fs.UserID = ? AND fs.Content LIKE ? AND (? = '' OR ft.Feedback_Name LIKE ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, category);
            stmt.setString(4, "%" + category + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedbackForUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<FeedbackForSystem> getFeedbackForUser(int userID, String keyword, String category, int offset, int limit) {
        ArrayList<FeedbackForSystem> feedbackList = new ArrayList<>();
        String query = "SELECT fs.ID, fs.UserID, fs.Feedback_TypeID, ft.Feedback_Name, fs.Content, fs.CreatedDate "
                + "FROM Feedback_For_System fs "
                + "JOIN Feedback_Type ft ON fs.Feedback_TypeID = ft.ID "
                + "WHERE fs.UserID = ? AND fs.Content LIKE ? AND (? = '' OR ft.Feedback_Name LIKE ?) "
                + "ORDER BY fs.ID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, category);
            stmt.setString(4, "%" + category + "%");
            stmt.setInt(5, offset);
            stmt.setInt(6, limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                int userId = rs.getInt("UserID");
                int feedbackTypeId = rs.getInt("Feedback_TypeID");
                String feedbackName = rs.getString("Feedback_Name");
                String content = rs.getString("Content");
                Date createdDate = rs.getDate("CreatedDate");

                FeedbackForSystem feedback = new FeedbackForSystem();
                feedback.setId(id);
                feedback.setUserId(userId);
                feedback.setFeedbackTypeId(feedbackTypeId);
                feedback.setFeedbackName(feedbackName);
                feedback.setContent(content);
                feedback.setCreatedDate(createdDate);

                feedbackList.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedbackForUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return feedbackList;
    }

    public static void main(String[] args) {
        // Tạo một đối tượng của lớp DAOFeedbackForUser
        DAOFeedbackForUser dao = new DAOFeedbackForUser();

        // Gọi phương thức getAllFeedbackTypes và lưu kết quả vào một danh sách
        ArrayList<String> feedbackTypes = dao.getAllFeedbackTypes();

        // Kiểm tra xem danh sách có dữ liệu hay không
        if (feedbackTypes != null && !feedbackTypes.isEmpty()) {
            // In ra các loại phản hồi
            System.out.println("Feedback Types:");
            for (String type : feedbackTypes) {
                System.out.println(type);
            }
        } else {
            // In ra thông báo nếu không có dữ liệu
            System.out.println("No feedback types found.");
        }
    }
}
