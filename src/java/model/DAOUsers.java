/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Users;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Quang
 */
public class DAOUsers extends DBConnect {

    public ArrayList<Users> listAllUsers() {
        ArrayList<Users> list = new ArrayList<>();
        String sql = "select * from Users where RoleID != 1";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String realname = rs.getString(3);
                String email = rs.getString(4);
                String password = rs.getString(5);
                String img = rs.getString(6);
                Date date = rs.getDate(7);
                int roleId = rs.getInt(8);
                Users e = new Users(id, name, realname, email, password, img, date, roleId);
                list.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Users normallogin(String username, String password) {
        String sql = "select * from Users\n"
                + "WHERE [Username] =?\n"
                + "and Password =?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getInt(8)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void UpdatePassAccount(Users acc, String newPass) {
        int userID = acc.getId();

        String query = "UPDATE Users SET [Password] = ? WHERE ID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newPass);
            ps.setInt(2, userID);
            ps.executeUpdate();
            System.out.println("Password updated successfully for user with ID: " + userID);
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert(String name, String realname, String gmail, String pass) {

        DBConnect db = new DBConnect();
        ArrayList<Users> list = listAllUsers();
        String img = "";
        String sql = "INSERT INTO Users (Username,Realname,Email, [Password], Img, RoleID) VALUES (?,?,?,?,?,?)";

        int i = 2;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, realname);
            ps.setString(3, gmail);
            ps.setString(4, pass);
            ps.setString(5, img);
            ps.setInt(6, i);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Users getAccountByEmail(String email) {
        String query = "SELECT * FROM Users WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getInt(8)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteUsers(int id) {
        String[] sqlStatements = {
            "DELETE FROM Trash WHERE UserID = ?",
            "DELETE FROM History WHERE UserID = ?",
            "DELETE FROM OrderDetail WHERE UserID = ?",
            "DELETE FROM Essay WHERE UserID = ?",
            "DELETE FROM Feedback_For_System WHERE UserID = ?",
            "DELETE FROM RecentActivity WHERE UserID = ?",
            "DELETE FROM remember_me_tokens WHERE user_id = ?",
            "DELETE FROM library WHERE UserID = ?",
            "DELETE FROM FavoriteEssayHistory WHERE UserID = ?",
            "DELETE FROM post WHERE userid = ?",
            "DELETE FROM listuserslikepost WHERE userid = ?",
            "DELETE FROM reports WHERE userid = ?",
            "DELETE FROM comment WHERE user_id = ?",
            "DELETE FROM favorite_posts WHERE user_id = ?",
            "DELETE FROM savepost WHERE user_id = ?",
            "DELETE FROM AccessLogs WHERE UserID = ?",
            "DELETE FROM userinforum WHERE userid = ?",
            "DELETE FROM Users WHERE ID = ?"
        };

        try {
            conn.setAutoCommit(false);

            for (String sql : sqlStatements) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }
            }

            conn.commit();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, rollbackEx);
            }
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateAvatar(Users user) {
        String sql = "UPDATE Users SET Img = ? WHERE ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getImg());
            stmt.setInt(2, user.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void UsersupdateUser(Users user) {
        String sql = "UPDATE Users SET Username = ?, Email = ? ,Realname =,Img = ? ? WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getRealname());
            stmt.setString(4, user.getImg());
            stmt.setInt(5, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Users user) {
        String sql = "UPDATE Users SET Username = ?,Realname=?,Email = ?,[Password]=?,Img=?, RoleID=? WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getRealname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getImg());
            stmt.setInt(6, user.getRoleId());
            stmt.setInt(7, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Users getUserById(int userId) {

        PreparedStatement stmt = null;

        Users user = null;

        try {
            DBConnect db = new DBConnect();
            String query = "SELECT * FROM Users WHERE ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new Users();
                user.setId(rs.getInt("ID"));
                user.setUsername(rs.getString("Username"));
                user.setRealname(rs.getString("Realname"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setCreatedDate(rs.getDate(7));
                user.setImg(rs.getString("Img"));
                user.setRoleId(rs.getInt("RoleID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Users getAccountByUserAndPass(String username, String password) {
        String query = "SELECT * FROM Users WHERE Username = ? AND [Password] = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Users users = new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getInt(8)
                );
                return users;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate 6-digit OTP
        return String.valueOf(otp);
    }

    public void sendVerificationEmail(String sentTo, String otp) {
        final String username = "hungtuan2406@gmail.com"; // Update with your email address
        final String password = "fdyq cduo gmrb tzrj"; // Update with your email password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sentTo));
            message.setSubject("Email Verification");
            message.setContent("<h1>Email verification</h1><p>Your OTP is: " + otp + "</p>", "text/html");

            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public ArrayList<Users> searchByGmail(String gmail) {
        ArrayList<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE Email = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, gmail);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String realname = rs.getString(3);
                    String email = rs.getString(4);
                    String password = rs.getString(5);
                    String img = rs.getString(6);
                    Date date = rs.getDate(7);
                    int roleId = rs.getInt(8);
                    Users user = new Users(id, name, realname, email, password, img, date, roleId);
                    list.add(user);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ArrayList<Users> searchByName(String name) {
        ArrayList<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE Realname LIKE ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "%" + name + "%");
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String username = rs.getString(2);
                    String realname = rs.getString(3);
                    String email = rs.getString(4);
                    String password = rs.getString(5);
                    String img = rs.getString(6);
                    Date date = rs.getDate(7);
                    int roleId = rs.getInt(8);
                    Users user = new Users(id, username, realname, email, password, img, date, roleId);
                    list.add(user);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ArrayList<Users> searchBynameandtype(String name, int type) {
        ArrayList<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE Realname LIKE ? and RoleID = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "%" + name + "%");
            st.setInt(2, type);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String username = rs.getString(2);
                    String realname = rs.getString(3);
                    String email = rs.getString(4);
                    String password = rs.getString(5);
                    String img = rs.getString(6);
                    Date date = rs.getDate(7);
                    int roleId = rs.getInt(8);
                    Users user = new Users(id, username, realname, email, password, img, date, roleId);
                    list.add(user);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ArrayList<Users> searchBytype(int type) {
        ArrayList<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE RoleID = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, type);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String username = rs.getString(2);
                    String realname = rs.getString(3);
                    String email = rs.getString(4);
                    String password = rs.getString(5);
                    String img = rs.getString(6);
                    Date date = rs.getDate(7);
                    int roleId = rs.getInt(8);
                    Users user = new Users(id, username, realname, email, password, img, date, roleId);
                    list.add(user);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ArrayList<Users> findUsersByDateRange(LocalDate date1, LocalDate date2) {
        ArrayList<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE CAST(CreatedDate AS DATE) BETWEEN ? AND ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDate(1, java.sql.Date.valueOf(date1));
            st.setDate(2, java.sql.Date.valueOf(date2));
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String username = rs.getString("Username");
                    String realname = rs.getString("Realname");
                    String email = rs.getString("Email");
                    String password = rs.getString("Password");
                    String img = rs.getString("Img");
                    Date createdDate = rs.getDate("CreatedDate");
                    int roleId = rs.getInt("RoleID");
                    Users user = new Users(id, username, realname, email, password, img, createdDate, roleId);
                    list.add(user);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Users> findUsersByExactDate(LocalDate date) {
        ArrayList<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE CAST(CreatedDate AS DATE) = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDate(1, java.sql.Date.valueOf(date));

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String username = rs.getString("Username");
                    String realname = rs.getString("Realname");
                    String email = rs.getString("Email");
                    String password = rs.getString("Password");
                    String img = rs.getString("Img");
                    Date createdDate = rs.getDate("CreatedDate");
                    int roleId = rs.getInt("RoleID");
                    Users user = new Users(id, username, realname, email, password, img, createdDate, roleId);
                    list.add(user);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void createGoogleUser(String email) {
        String sql = "INSERT INTO Users ([Username], [Realname], [Email], [Password], [Img], [CreatedDate], [RoleID]) VALUES (?, ?, ?, '', '', GETDATE(), 2)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = this.conn;  // Sử dụng kết nối từ DBConnect
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, email);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public void saveRememberMeToken(int userId, String rememberMeToken) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = this.conn; // Lấy kết nối tới SQL Server
            if (conn != null) {
                System.out.println("Connection to database established.");
            } else {
                System.out.println("Failed to make connection!");
                return;
            }

            // Kiểm tra xem user_id đã tồn tại trong bảng remember_me_tokens hay chưa
            String checkSql = "SELECT COUNT(*) FROM remember_me_tokens WHERE user_id = ?";
            ps = conn.prepareStatement(checkSql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            ps.close();

            if (count > 0) {
                // Hàng tồn tại, thực hiện cập nhật
                String updateSql = "UPDATE remember_me_tokens SET token = ? WHERE user_id = ?";
                ps = conn.prepareStatement(updateSql);
                ps.setString(1, rememberMeToken);
                ps.setInt(2, userId);
                ps.executeUpdate();
                System.out.println("Token updated successfully.");
            } else {
                // Hàng không tồn tại, thực hiện chèn mới
                String insertSql = "INSERT INTO remember_me_tokens (user_id, token) VALUES (?, ?)";
                ps = conn.prepareStatement(insertSql);
                ps.setInt(1, userId);
                ps.setString(2, rememberMeToken);
                ps.executeUpdate();
                System.out.println("Token inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error: " + e.getMessage());
        } finally {
            // Đóng kết nối và các tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Hàm tìm người dùng bằng mã thông báo Remember Me
    public Users getUserByRememberMeToken(String token) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users user = null;
        try {
            conn = this.conn; // Lấy kết nối tới SQL Server
            if (conn != null) {
                System.out.println("Connection to database established.");
            } else {
                System.out.println("Failed to make connection!");
                return null;
            }

            String sql = "SELECT u.* FROM Users u JOIN remember_me_tokens rmt ON u.ID = rmt.user_id WHERE rmt.token = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, token);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("User found with token: " + token);
                user = new Users();
                user.setId(rs.getInt("ID"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setRoleId(rs.getInt("RoleID"));
                // Thêm các thuộc tính khác nếu cần
            } else {
                System.out.println("No user found with the given token: " + token);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    public boolean isUsernameTaken(String username) {
        String sql = "SELECT COUNT(*) FROM Users WHERE Username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // If count > 0, username is taken
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isEmailTaken(String email) {
        String sql = "SELECT COUNT(*) FROM Users WHERE Email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void updatetovip(int id) {

        String sql = "Update Users set RoleID = 3 where ID = " + id;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void changeUser(Users user) {
        String sql = "UPDATE Users SET Realname = ?, Username = ?, Email = ? WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getRealname());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incrementFailedLoginAttempts(int userId) {
        String sql = "UPDATE Users SET FailedLoginAttempts = FailedLoginAttempts + 1 WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetFailedLoginAttempts(int userId) {
        String sql = "UPDATE Users SET FailedLoginAttempts = 0, IsLocked = 0, LockTime = NULL WHERE ID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lockUser(int userId) {
        String sql = "UPDATE Users SET IsLocked = 1, LockTime = ? WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis())); // Lưu lại thời gian hiện tại khi khóa tài khoản
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unlockUserIfTimeElapsed(int userId) {
        String sql = "SELECT LockTime FROM Users WHERE ID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Timestamp lockTime = rs.getTimestamp("LockTime");
                if (lockTime != null) {
                    long currentTime = new Date().getTime();
                    long lockTimeMillis = lockTime.getTime();
                    // Kiểm tra nếu đã qua 5 phút (300000 milliseconds)
                    if (currentTime - lockTimeMillis >= 30000) {
                        resetFailedLoginAttempts(userId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Users getUserByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE Username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("ID"));
                user.setUsername(rs.getString("Username"));
                user.setRealname(rs.getString("Realname"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setImg(rs.getString("Img"));
                user.setRoleId(rs.getInt("RoleID"));
                user.setFailedLoginAttempts(rs.getInt("FailedLoginAttempts"));
                user.setIsLocked(rs.getBoolean("IsLocked"));
                user.setLockTime(rs.getTimestamp("LockTime"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Users> listAllLockedUsers() {
        List<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE IsLocked = 1";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("ID"));
                user.setUsername(rs.getString("Username"));
                user.setRealname(rs.getString("Realname"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setImg(rs.getString("Img"));
                user.setCreatedDate(rs.getDate("CreatedDate"));
                user.setRoleId(rs.getInt("RoleID"));
                user.setFailedLoginAttempts(rs.getInt("FailedLoginAttempts"));
                user.setIsLocked(rs.getBoolean("IsLocked"));
                user.setLockTime(rs.getTimestamp("LockTime"));
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean verifyPassword(String username, String password) {
        String sql = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
