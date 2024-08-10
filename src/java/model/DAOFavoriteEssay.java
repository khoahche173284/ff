package model;

import entity.FavoriteEssayHistory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFavoriteEssay extends DBConnect {

    public int addFavoriteEssay(FavoriteEssayHistory favorite) {
        int result = 0;
        try {
            String sql = "INSERT INTO Favorite_Essay_Draft (UserID, EssayID) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, favorite.getUserId());
            stmt.setInt(2, favorite.getEssayId());
            result = stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    favorite.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFavoriteEssay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean deleteFavoriteEssay(int userId, int essayId) {
        String sql = "DELETE FROM Favorite_Essay_Draft WHERE UserID = ? AND EssayID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, essayId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteHistoryEssay(int userId, int essayId) {
        String sql = "DELETE FROM Draft WHERE UserID = ? AND EssayID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, essayId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<FavoriteEssayHistory> getFavoriteEssaysByUserId(int userId, int offset, int limit) {
        List<FavoriteEssayHistory> favoriteEssays = new ArrayList<>();
        String sql = "SELECT feh.ID, feh.UserID, feh.EssayID, feh.addedDate, e.Content, e.CreatedDate, c.CategoriesName "
                + "FROM Favorite_Essay_Draft feh "
                + "JOIN Essay e ON feh.EssayID = e.ID "
                + "JOIN Categories c ON e.CategoriesID = c.ID "
                + "WHERE feh.UserID = ? "
                + "ORDER BY feh.ID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, offset);
            stmt.setInt(3, limit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FavoriteEssayHistory favoriteEssay = new FavoriteEssayHistory();
                favoriteEssay.setId(rs.getInt("ID"));
                favoriteEssay.setUserId(rs.getInt("UserID"));
                favoriteEssay.setEssayId(rs.getInt("EssayID"));
                favoriteEssay.setAddedDate(rs.getTimestamp("addedDate"));
                favoriteEssay.setContent(rs.getString("Content"));
                favoriteEssay.setCreatedDate(rs.getTimestamp("CreatedDate"));
                favoriteEssay.setCategory(rs.getString("CategoriesName"));
                favoriteEssays.add(favoriteEssay);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return favoriteEssays;
    }

    public int getTotalFavoriteEssaysByUserId(int userId) {
        String sql = "SELECT COUNT(*) FROM Favorite_Essay_Draft WHERE UserID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public boolean addEssayToTrash(int userId, int essayId) {
        String sql = "INSERT INTO Trash (UserID, EssayID, Deleted_Date) VALUES (?, ?, GETDATE())";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, essayId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<FavoriteEssayHistory> searchFavoriteEssaysByContent(int userId, String keyword, int offset, int limit) {
        List<FavoriteEssayHistory> favoriteEssays = new ArrayList<>();
        String sql = "SELECT feh.ID, feh.UserID, feh.EssayID, feh.addedDate, e.Content, e.CreatedDate, c.CategoriesName "
                + "FROM Favorite_Essay_Draft feh "
                + "JOIN Essay e ON feh.EssayID = e.ID "
                + "JOIN Categories c ON e.CategoriesID = c.ID "
                + "WHERE feh.UserID = ? AND e.Content LIKE ? "
                + "ORDER BY feh.ID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, "%" + keyword + "%");
            stmt.setInt(3, offset);
            stmt.setInt(4, limit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FavoriteEssayHistory favoriteEssay = new FavoriteEssayHistory();
                favoriteEssay.setId(rs.getInt("ID"));
                favoriteEssay.setUserId(rs.getInt("UserID"));
                favoriteEssay.setEssayId(rs.getInt("EssayID"));
                favoriteEssay.setAddedDate(rs.getTimestamp("addedDate"));
                favoriteEssay.setContent(rs.getString("Content"));
                favoriteEssay.setCreatedDate(rs.getTimestamp("CreatedDate"));
                favoriteEssay.setCategory(rs.getString("CategoriesName"));
                favoriteEssays.add(favoriteEssay);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return favoriteEssays;
    }

    public int getTotalFavoriteEssaysByContent(int userId, String keyword) {
        String sql = "SELECT COUNT(*) FROM Favorite_Essay_Draft feh "
                + "JOIN Essay e ON feh.EssayID = e.ID "
                + "WHERE feh.UserID = ? AND e.Content LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public boolean isFavoriteEssayExist(int userId, int essayId) {
        String sql = "SELECT COUNT(*) FROM Favorite_Essay_Draft WHERE UserID = ? AND EssayID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, essayId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categories;
    }

    public int getTotalFavoriteEssaysByCategory(int userId, String category) {
        String sql = "SELECT COUNT(*) FROM Favorite_Essay_Draft feh "
                + "JOIN Essay e ON feh.EssayID = e.ID "
                + "JOIN Categories c ON e.CategoriesID = c.ID "
                + "WHERE feh.UserID = ? AND c.CategoriesName = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, category);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<FavoriteEssayHistory> getFavoriteEssaysByCategory(int userId, String category, int offset, int limit) {
        List<FavoriteEssayHistory> favoriteEssays = new ArrayList<>();
        String sql = "SELECT feh.ID, feh.UserID, feh.EssayID, feh.addedDate, e.Content, e.CreatedDate, c.CategoriesName "
                + "FROM Favorite_Essay_Draft feh "
                + "JOIN Essay e ON feh.EssayID = e.ID "
                + "JOIN Categories c ON e.CategoriesID = c.ID "
                + "WHERE feh.UserID = ? AND c.CategoriesName = ? "
                + "ORDER BY feh.ID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, category);
            stmt.setInt(3, offset);
            stmt.setInt(4, limit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FavoriteEssayHistory favoriteEssay = new FavoriteEssayHistory();
                favoriteEssay.setId(rs.getInt("ID"));
                favoriteEssay.setUserId(rs.getInt("UserID"));
                favoriteEssay.setEssayId(rs.getInt("EssayID"));
                favoriteEssay.setAddedDate(rs.getTimestamp("addedDate"));
                favoriteEssay.setContent(rs.getString("Content"));
                favoriteEssay.setCreatedDate(rs.getTimestamp("CreatedDate"));
                favoriteEssay.setCategory(rs.getString("CategoriesName"));
                favoriteEssays.add(favoriteEssay);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return favoriteEssays;
    }

    public List<FavoriteEssayHistory> searchFavoriteEssays(int userId, String keyword, String category, int offset, int limit) {
        List<FavoriteEssayHistory> favoriteEssays = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT feh.ID, feh.UserID, feh.EssayID, feh.addedDate, e.Content, e.CreatedDate, c.CategoriesName "
                + "FROM Favorite_Essay_Draft feh "
                + "JOIN Essay e ON feh.EssayID = e.ID "
                + "JOIN Categories c ON e.CategoriesID = c.ID "
                + "WHERE feh.UserID = ? "
        );

        if (keyword != null && !keyword.isEmpty()) {
            sql.append("AND e.Content LIKE ? ");
        }

        if (category != null && !category.isEmpty()) {
            sql.append("AND c.CategoriesName = ? ");
        }

        sql.append("ORDER BY feh.ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int paramIndex = 1;
            stmt.setInt(paramIndex++, userId);

            if (keyword != null && !keyword.isEmpty()) {
                stmt.setString(paramIndex++, "%" + keyword + "%");
            }

            if (category != null && !category.isEmpty()) {
                stmt.setString(paramIndex++, category);
            }

            stmt.setInt(paramIndex++, offset);
            stmt.setInt(paramIndex++, limit);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FavoriteEssayHistory favoriteEssay = new FavoriteEssayHistory();
                favoriteEssay.setId(rs.getInt("ID"));
                favoriteEssay.setUserId(rs.getInt("UserID"));
                favoriteEssay.setEssayId(rs.getInt("EssayID"));
                favoriteEssay.setAddedDate(rs.getTimestamp("addedDate"));
                favoriteEssay.setContent(rs.getString("Content"));
                favoriteEssay.setCreatedDate(rs.getTimestamp("CreatedDate"));
                favoriteEssay.setCategory(rs.getString("CategoriesName"));
                favoriteEssays.add(favoriteEssay);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return favoriteEssays;
    }

    public int getTotalFavoriteEssays(int userId, String keyword, String category) {
        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) "
                + "FROM Favorite_Essay_Draft feh "
                + "JOIN Essay e ON feh.EssayID = e.ID "
                + "JOIN Categories c ON e.CategoriesID = c.ID "
                + "WHERE feh.UserID = ? "
        );

        if (keyword != null && !keyword.isEmpty()) {
            sql.append("AND e.Content LIKE ? ");
        }

        if (category != null && !category.isEmpty()) {
            sql.append("AND c.CategoriesName = ? ");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int paramIndex = 1;
            stmt.setInt(paramIndex++, userId);

            if (keyword != null && !keyword.isEmpty()) {
                stmt.setString(paramIndex++, "%" + keyword + "%");
            }

            if (category != null && !category.isEmpty()) {
                stmt.setString(paramIndex++, category);
            }

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
