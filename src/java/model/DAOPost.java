package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.Post;
import java.util.ArrayList;
import java.util.List;

public class DAOPost extends DBConnect {

    private static final String SEARCH_HOT_POSTS_BY_KEYWORD_SQL
            = "SELECT p.*, u.Realname as realName "
            + "FROM Post p "
            + "JOIN Users u ON p.userid = u.ID "
            + "WHERE p.luot_thich > 5 AND (p.noi_dung LIKE ? OR p.title LIKE ?) "
            + "ORDER BY p.luot_thich DESC;";

    private static final String SEARCH_HOT_POSTS_BY_DATE_SQL
            = "SELECT p.*, u.Realname as realName "
            + "FROM Post p "
            + "JOIN Users u ON p.userid = u.ID "
            + "WHERE p.luot_thich > 5 AND CONVERT(DATE, p.ngay_dang) = ? "
            + "ORDER BY p.luot_thich DESC;";

    public List<Post> searchHotPostsByKeyword(String keyword) {
        List<Post> hotPosts = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SEARCH_HOT_POSTS_BY_KEYWORD_SQL)) {
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Post post = createPostFromResultSet(rs);
                hotPosts.add(post);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return hotPosts;
    }

    // Method to search hot posts by date
    public List<Post> searchHotPostsByDate(String date) {
        List<Post> hotPosts = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SEARCH_HOT_POSTS_BY_DATE_SQL)) {
            preparedStatement.setString(1, date);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Post post = createPostFromResultSet(rs);
                hotPosts.add(post);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return hotPosts;
    }

    public List<Post> getHotPosts() {
        List<Post> hotPosts = new ArrayList<>();
        String sql = "SELECT p.*, u.Realname as realName FROM Post p JOIN Users u ON p.userid = u.ID WHERE luot_thich > 5 ORDER BY luot_thich DESC";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Post post = createPostFromResultSet(rs);
                hotPosts.add(post);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return hotPosts;
    }

   private static final String INSERT_POST_SQL = "INSERT INTO Post "
            + "(typeid, ngay_dang, noi_dung, userid,title) VALUES (?,GETDATE(), ?, ?,?)";

    private static final String SELECT_ALL_POSTS = "SELECT p.*, u.Realname\n"
            + "FROM Post p\n"
            + "JOIN Users u ON p.userid = u.ID\n"
            + "ORDER BY p.ngay_dang DESC;";

    private static final String DELETE_POST_SQL = "DELETE FROM Post WHERE id = ?;";
    private static final String UPDATE_POST_SQL = "UPDATE Post SET "
            + " noi_dung = ? "
            + " WHERE id = ?;";

    private static final String SEARCH_POSTS_BY_KEYWORD_SQL = "SELECT * FROM Post WHERE noi_dung LIKE ? OR title LIKE ?";
    private static final String SEARCH_POSTS_BY_DATE_SQL = "SELECT * FROM Post WHERE CONVERT(DATE, ngay_dang) = ?";
    private static final String SELECT_SAVED_POSTS_BY_USER_SQL = "SELECT p.* FROM Post p INNER JOIN Save_Post s ON p.id = s.post_id WHERE s.user_id = ?";
    private static final String SAVE_POST_SQL = "INSERT INTO Save_Post (user_id, post_id) VALUES (?, ?)";
    private static final String DELETE_SAVED_POST_SQL = "DELETE FROM Save_Post WHERE user_id = ? AND post_id = ?";
    private static final String CHECK_POST_SAVED_SQL = "SELECT COUNT(*) FROM Save_Post WHERE user_id = ? AND post_id = ?";
    private static final String SEARCH_SAVED_POSTS_BY_KEYWORD_SQL = "SELECT p.* FROM post p INNER JOIN Save_Post s ON p.id = s.post_id WHERE s.user_id = ? AND (p.noi_dung LIKE ? OR p.title LIKE ?)";
    private static final String SEARCH_SAVED_POSTS_BY_DATE_SQL = "SELECT p.* FROM post p INNER JOIN Save_Post s ON p.id = s.post_id WHERE s.user_id = ? AND CONVERT(DATE, p.ngay_dang) = ?";

    public void insertPost(Post post) throws SQLException {
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(INSERT_POST_SQL)) {
            preparedStatement.setInt(1, post.getTypeId());

            preparedStatement.setString(2, post.getNoiDung());
            preparedStatement.setInt(3, post.getUserId());
            preparedStatement.setString(4, post.getTitle());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private static final String SELECT_POST_BY_ID = "SELECT * , u.Realname as realName"
            + "  FROM Post p "
            + "JOIN Users u ON p.userid = u.ID WHERE p.id = ?;";

    public Post selectPost(int id) {
        Post post = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_POST_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    post = createPostFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return post;
    }

    public void tangComment(int postId) {
        try (PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Post SET so_luong_comment = so_luong_comment + 1 WHERE id = ?")) {
            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void giamComment(int postId) {
        try (PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Post SET so_luong_comment = so_luong_comment - 1 WHERE id = ? AND so_luong_comment > 0")) {
            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private Post createPostFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int typeId = rs.getInt("typeid");
        String ngayDang = rs.getString("ngay_dang");
        String noiDung = rs.getString("noi_dung");
        String realName = rs.getString("realName");
        String title = rs.getString("title");
        int luotThich = rs.getInt("luot_thich");
        int userId = rs.getInt("userid");
        int soLuongComment = rs.getInt("so_luong_comment");
        int numberReport = rs.getInt("number_report");

        return new Post(id, typeId, ngayDang, noiDung, luotThich, userId, soLuongComment, numberReport, title, realName);
    }

    public void increaserp(int numrp, int postid) {

        numrp = numrp + 1;
        String sql = "update Post set number_report = ? where id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, numrp);
            preparedStatement.setInt(2, postid);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    public List<Post> selectAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_POSTS);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int typeId = rs.getInt("typeid");
                String title = rs.getString("title");
                String ngayDang = rs.getString("ngay_dang");
                String noiDung = rs.getString("noi_dung");
                String realName = rs.getString("Realname");
                int luotThich = rs.getInt("luot_thich");
                int userId = rs.getInt("userid");
                int soLuongComment = rs.getInt("so_luong_comment");
                int numberReport = rs.getInt("number_report");
                Post post = new Post();
                post.setId(id);
                post.setTitle(title);
                post.setTypeId(typeId);
                post.setNgayDang(ngayDang);
                post.setNoiDung(noiDung);
                post.setLuotThich(luotThich);
                post.setRealName(realName);
                post.setUserId(userId);
                post.setSoLuongComment(soLuongComment);
                post.setNumberReport(numberReport);
                posts.add(post);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return posts;
    }

    public void likepost(int postid, int luotlike) {
        luotlike = luotlike + 1;
        String sql = "update Post set luot_thich = ? where id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, luotlike);
            preparedStatement.setInt(2, postid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Post getpost(int pid) {
        Post post = null;
        String sql = "Select * from Post where id =" + pid;
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(sql);) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int typeId = rs.getInt("typeid");
                String ngayDang = rs.getString("ngay_dang");
                String noiDung = rs.getString("noi_dung");
                String title = rs.getString("title");
                int luotThich = rs.getInt("luot_thich");
                int userId = rs.getInt("userid");
                int soLuongComment = rs.getInt("so_luong_comment");
                int numberReport = rs.getInt("number_report");
                post = new Post();
                post.setId(pid);
                post.setTitle(title);
                post.setTypeId(typeId);
                post.setNgayDang(ngayDang);
                post.setNoiDung(noiDung);
                post.setLuotThich(luotThich);
                post.setUserId(userId);
                post.setSoLuongComment(soLuongComment);
                post.setNumberReport(numberReport);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return post;

    }

    public void deletelike(int postid, int uid) {
        try {
            String sql = "Delete List_Users_Like_Post where postid = ? and userid = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, postid);
            preparedStatement.setInt(2, uid);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            printSQLException(ex);
        }

    }

    public void unlikepost(int postid, int luotlike) {
        luotlike = luotlike - 1;
        String sql = "update Post set luot_thich = ? where id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, luotlike);
            preparedStatement.setInt(2, postid);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
    }

    public List<Post> searchPosts(String keyword) {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SEARCH_POSTS_BY_KEYWORD_SQL)) {
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int typeId = rs.getInt("typeid");
                String title = rs.getString("title");
                String ngayDang = rs.getString("ngay_dang");
                String noiDung = rs.getString("noi_dung");
                int luotThich = rs.getInt("luot_thich");
                int userId = rs.getInt("userid");
                int soLuongComment = rs.getInt("so_luong_comment");
                int numberReport = rs.getInt("number_report");
                Post post = new Post();
                post.setId(id);
                post.setTitle(title);
                post.setTypeId(typeId);
                post.setNgayDang(ngayDang);
                post.setNoiDung(noiDung);
                post.setLuotThich(luotThich);
                post.setUserId(userId);
                post.setSoLuongComment(soLuongComment);
                post.setNumberReport(numberReport);
                posts.add(post);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return posts;
    }

    public List<Post> searchPostsByDate(String startDate) {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SEARCH_POSTS_BY_DATE_SQL)) {
            preparedStatement.setString(1, startDate);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int typeId = rs.getInt("typeid");
                String title = rs.getString("title");
                String ngayDang = rs.getString("ngay_dang");
                String noiDung = rs.getString("noi_dung");
                int luotThich = rs.getInt("luot_thich");
                int userId = rs.getInt("userid");
                int soLuongComment = rs.getInt("so_luong_comment");
                int numberReport = rs.getInt("number_report");
                Post post = new Post();
                post.setId(id);
                post.setTitle(title);
                post.setTypeId(typeId);
                post.setNgayDang(ngayDang);
                post.setNoiDung(noiDung);
                post.setLuotThich(luotThich);
                post.setUserId(userId);
                post.setSoLuongComment(soLuongComment);
                post.setNumberReport(numberReport);
                posts.add(post);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return posts;
    }

    public void savePost(int userId, int postId) {
        try (PreparedStatement checkStatement = conn.prepareStatement(CHECK_POST_SAVED_SQL); PreparedStatement saveStatement = conn.prepareStatement(SAVE_POST_SQL)) {
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, postId);
            ResultSet rs = checkStatement.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                saveStatement.setInt(1, userId);
                saveStatement.setInt(2, postId);
                saveStatement.executeUpdate();
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteSavedPost(int userId, int postId) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_SAVED_POST_SQL)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, postId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deletepost(int postid) {

        String sql = "Delete Post where id=" + postid;
        String sql1 = "Delete Reports where postid=" + postid;
        String sql2 = "Delete List_Users_Like_Post where postid=" + postid;
        String sql3 = "Delete Favorite_Posts where post_id=" + postid;
        String sql4 = "Delete Comment where  post_id=" + postid;
        String sql5 = "Delete Save_Post where post_id=" + postid;

        try {
            PreparedStatement preparedStatement4 = conn.prepareStatement(sql1);
            preparedStatement4.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement preparedStatement5 = conn.prepareStatement(sql1);
            preparedStatement5.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);
            preparedStatement3.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    public void deleteByUser_id(int userid) {

        String sql = "Delete Post where userid=" + userid;
        String sql1 = "Delete Reports where userid=" + userid;
        String sql2 = "Delete  List_Users_Like_Post where userid=" + userid;
        String sql3 = "Delete Favorite_Posts where user_id=" + userid;
        String sql4 = "Delete Comment where  user_id=" + userid;
        String sql5 = "Delete Save_Post where user_id=" + userid;

        try {
            PreparedStatement preparedStatement4 = conn.prepareStatement(sql1);
            preparedStatement4.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement preparedStatement5 = conn.prepareStatement(sql1);
            preparedStatement5.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);
            preparedStatement3.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }
    
    
    
    public List<Post> selectSavedPostsByUser(int userId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT p.id, p.typeid, p.ngay_dang, p.noi_dung, p.luot_thich, p.userid, p.title, p.so_luong_comment, p.number_report "
                + "FROM Post p INNER JOIN Save_Post s ON p.id = s.post_id WHERE s.user_id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTypeId(rs.getInt("typeid"));
                post.setNgayDang(rs.getString("ngay_dang"));
                post.setNoiDung(rs.getString("noi_dung"));
                post.setLuotThich(rs.getInt("luot_thich"));
                post.setUserId(rs.getInt("userid"));
                post.setTitle(rs.getString("title"));
                post.setSoLuongComment(rs.getInt("so_luong_comment"));
                post.setNumberReport(rs.getInt("number_report"));
                posts.add(post);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return posts;
    }

    public boolean isPostSaved(int userId, int postId) {
        boolean isSaved = false;
        try (PreparedStatement checkStatement = conn.prepareStatement(CHECK_POST_SAVED_SQL); PreparedStatement saveStatement = conn.prepareStatement(SAVE_POST_SQL)) {
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, postId);
            ResultSet rs = checkStatement.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                saveStatement.setInt(1, userId);
                saveStatement.setInt(2, postId);
                saveStatement.executeUpdate();
                isSaved = true;  // Indicates post was saved
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return isSaved;
    }

    public List<Post> searchSavedPostsByKeyword(int userId, String keyword) {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SEARCH_SAVED_POSTS_BY_KEYWORD_SQL)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, "%" + keyword + "%");
            preparedStatement.setString(3, "%" + keyword + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTypeId(rs.getInt("typeid"));
                post.setTitle(rs.getString("title"));
                post.setNgayDang(rs.getString("ngay_dang"));
                post.setNoiDung(rs.getString("noi_dung"));
                post.setLuotThich(rs.getInt("luot_thich"));
                post.setUserId(rs.getInt("userid"));
                post.setSoLuongComment(rs.getInt("so_luong_comment"));
                post.setNumberReport(rs.getInt("number_report"));
                posts.add(post);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return posts;
    }

    public List<Post> searchSavedPostsByDate(int userId, String startDate) {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SEARCH_SAVED_POSTS_BY_DATE_SQL)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, startDate);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTypeId(rs.getInt("typeid"));
                post.setTitle(rs.getString("title"));
                post.setNgayDang(rs.getString("ngay_dang"));
                post.setNoiDung(rs.getString("noi_dung"));
                post.setLuotThich(rs.getInt("luot_thich"));
                post.setUserId(rs.getInt("userid"));
                post.setSoLuongComment(rs.getInt("so_luong_comment"));
                post.setNumberReport(rs.getInt("number_report"));
                posts.add(post);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return posts;
    }

    public void updatePost(int id, String noidung) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_POST_SQL)) {
            preparedStatement.setInt(2, id);
            preparedStatement.setString(1, noidung);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
