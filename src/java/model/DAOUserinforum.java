/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.UserInForum;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pgb31
 */
public class DAOUserinforum extends DBConnect {

    public List<UserInForum> getAllUsersInForum() {
        List<UserInForum> userList = new ArrayList<>();
        String sql = "SELECT * FROM User_In_Forum";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            // Close resources in reverse order of creation
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log exception as necessary
        }
        return userList;
    }

    public void updateUserStatus(int userId, String newStatus) {
        String sql = "UPDATE User_In_Forum SET status = ? WHERE userid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, userId);
            int rowsAffected = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log exception as necessary

        }
    }

    public UserInForum getUserById(int userId) {
        UserInForum user = null;
        String sql = "SELECT * FROM User_In_Forum WHERE userid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String userRealName = rs.getString("user_realname");
                    int totalPost = rs.getInt("totalpost");
                    int totalComment = rs.getInt("totalcomment");
                    int totalLike = rs.getInt("totallike");
                    int totalReport = rs.getInt("totalreport");
                    String status = rs.getString("status");

                    user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log exception as necessary
        }
        return user;
    }

//        Searching 
    public List<UserInForum> OnlyStatus(String Status) {

        List<UserInForum> userList = new ArrayList<>();
        String sql = "SELECT * FROM User_In_Forum where status=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> StatusAndReport(String Status, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
        String sql = null;
        if(Treport.equalsIgnoreCase("ASC")) 
           sql  = "SELECT * FROM User_In_Forum where status=? "
                   + "order by totalreport ASC ";
         if(Treport.equalsIgnoreCase("DESC"))  
             sql = "SELECT * FROM User_In_Forum where status=? "
                     + "order by totalreport DESC ";
 
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return userList;
    }

    public List<UserInForum> StatusAndLike(String Status, String Tlike) {
        List<UserInForum> userList = new ArrayList<>();
        String sql = null;
        if(Tlike.equalsIgnoreCase("ASC")) 
           sql  = "SELECT * FROM User_In_Forum where status=? "
                   + "order by totallike ASC ";
         if(Tlike.equalsIgnoreCase("DESC"))  
             sql = "SELECT * FROM User_In_Forum where status=? "
                     + "order by totallike DESC ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
            
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> StatusLikeReport(String Status, String Tlike, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
 String sql = null;
        if(Tlike.equalsIgnoreCase("ASC")) 
        {
             if(Treport.equalsIgnoreCase("ASC"))
           sql  = "SELECT * FROM User_In_Forum where status=? "
                   + "order by totallike ASC,totalreport ASC ";  
             if(Treport.equalsIgnoreCase("DESC"))
           sql  = "SELECT * FROM User_In_Forum where status=? "
                   + "order by totallike ASC,totalreport DESC ";  
             
        }
        
         if(Tlike.equalsIgnoreCase("DESC"))  
         {if(Treport.equalsIgnoreCase("ASC"))
           sql  = "SELECT * FROM User_In_Forum where status=? "
                   + "order by totallike DESC,totalreport ASC ";  
             if(Treport.equalsIgnoreCase("DESC"))
           sql  = "SELECT * FROM User_In_Forum where status=? "
                   + "order by totallike DESC,totalreport DESC "; 

         }

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
           
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return userList;
    }

    public List<UserInForum> StatusAndComment(String Status, String Tcomment) {
       
         List<UserInForum> userList = new ArrayList<>();
        String sql = null;
        if(Tcomment.equalsIgnoreCase("ASC")) 
           sql  = "SELECT * FROM User_In_Forum where status=? "
                   + "order by totalcomment ASC ";
         if(Tcomment.equalsIgnoreCase("DESC"))  
             sql = "SELECT * FROM User_In_Forum where status=? "
                     + "order by totalcomment DESC ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
           
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
       
    }

    public List<UserInForum> StatusCommentReport(String Status, String Tcomment, String Treport) {
        List<UserInForum> userList = new ArrayList<>(); 
        String s1 = "totalcomment "+" " +Tcomment ;
        String s2 = "totalreport "+" " +Treport ;
          String sql = "SELECT * FROM User_In_Forum where status=? "
                  + "order by  " +s1 +", " +s2;
                  
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
      
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
        
    }

    public List<UserInForum> StatusCommentLike(String Status, String Tcomment, String Tlike) {
        List<UserInForum> userList = new ArrayList<>();
 String s1 = "totalcomment "+" " +Tcomment ;
        String s2 = "totallike "+" " +Tlike ;
          String sql = "SELECT * FROM User_In_Forum where status=? "
                  + "order by  " +s1 +", " +s2;
                  
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
      
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> StatusCommnetLikeReport(String Status, String Tcomment, String Tlike, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
        
        
        
        String s1 = "totalcomment "+" " +Tcomment ;
        String s2 = "totallike "+" " +Tlike ;
         String s3 = "totalreport "+" " +Treport ;
          String sql = "SELECT * FROM User_In_Forum where status=? "
                  + "order by  " +s1 +", " +s2+", "+s3;
                  
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
      
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
        
        
       
    }

    public List<UserInForum> StatusAndPost(String Status, String Tpost) {
        List<UserInForum> userList = new ArrayList<>();
        String sql = null;
        if(Tpost.equalsIgnoreCase("ASC")) 
           sql  = "SELECT * FROM User_In_Forum where status=? "
                   + "order by totalpost ASC ";
         if(Tpost.equalsIgnoreCase("DESC"))  
             sql = "SELECT * FROM User_In_Forum where status=? "
                     + "order by totalpost DESC ";
 
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return userList;
    }

    public List<UserInForum> StatusPostReport(String Status, String Tpost, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
       
        String s1 = "totalpost "+" " +Tpost ;
          String s2 = "totalreport "+" " +Treport ;
          String sql = "SELECT * FROM User_In_Forum where status=? "
                  + "order by  " +s1 +", " +s2;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
      
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;

    }

    public List<UserInForum> StatusPostLike(String Status, String Tpost, String Tlike) {
        List<UserInForum> userList = new ArrayList<>();
         String s1 = "totalpost "+" " +Tpost ;
          String s2 = "totallike "+" " +Tlike;
          String sql = "SELECT * FROM User_In_Forum where status=? "
                  + "order by  " +s1 +", " +s2;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
      
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> StatusPostLikeReport(String Status, String Tpost, String Tlike, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
        String s1 = "totalpost "+" " +Tpost ;
          String s2 = "totallike "+" " +Tlike;
            String s3 = "totalreport "+" " +Treport;
          String sql = "SELECT * FROM User_In_Forum where status=? "
                  + "order by  " +s1 +", " +s2+", " +s3;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
      
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return userList;
    }

    public List<UserInForum> StatusPostComment(String Status, String Tpost, String Tcomment) {
        List<UserInForum> userList = new ArrayList<>();
        String s1 = "totalpost "+" " +Tpost ;
          String s2 = "totalcomment "+" " +Tcomment;
          String sql = "SELECT * FROM User_In_Forum where status=? "
                  + "order by  " +s1 +", " +s2;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
      
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> StatusPostCommentReport(String Status, String Tpost, String Tcomment, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
         String s1 = "totalpost "+" " +Tpost ;
          String s2 = "totalcomment "+" " +Tcomment;
          String s3 = "totalreport "+" " +Treport;
          String sql = "SELECT * FROM User_In_Forum where status=? "
                  + "order by  " +s1 +", " +s2+", "+s3;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
      
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return userList;
    }

    public List<UserInForum> StatusPostCommentLike(String Status, String Tpost, String Tcomment, String Tlike) {
        List<UserInForum> userList = new ArrayList<>();
         String s1 = "totalpost "+" " +Tpost ;
          String s2 = "totalcomment "+" " +Tcomment;
          String s3 = "totallike "+" " +Tlike;
          String sql = "SELECT * FROM User_In_Forum where status=? "
                  + "order by  " +s1 +", " +s2+", "+s3;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
      
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> SearchByAll(String Status, String Tpost, String Tcomment, String Tlike, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
         String s1 = "totalpost "+" " +Tpost ;
          String s2 = "totalcomment "+" " +Tcomment;
          String s3 = "totallike "+" " +Tlike;
          String s4 = "totalreport "+" " +Treport;
          String sql = "SELECT * FROM User_In_Forum where status=? "
                  + "order by  " +s1 +", " +s2+", "+s3+", "+s4;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Status);
      
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> OnlyReport(String Treport) {
        List<UserInForum> userList = new ArrayList<>();
         String s1 = "totalreport "+" " +Treport;
         String sql = "SELECT * FROM User_In_Forum order by " + s1;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> OnlyLike(String Tlike) {
        List<UserInForum> userList = new ArrayList<>();
         String s1 = "totallike "+" " +Tlike;
         String sql = "SELECT * FROM User_In_Forum order by " + s1;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> LikeReport(String Tlike, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
        String s1 = "totallike "+" " +Tlike; 
        String s2 = "totalreport"+" " +Treport;
         String sql = "SELECT * FROM User_In_Forum order by " + s1 +", "+ s2;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> OnlyComment(String Tcomment) {
        List<UserInForum> userList = new ArrayList<>();
         String s1 = "totalcomment "+" " +Tcomment;
         String sql = "SELECT * FROM User_In_Forum order by " + s1;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> CommentReport(String Tcomment, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
         String s1 = "totalcomment "+" " +Tcomment;
         String s2 = "totalreport"+" " +Treport;
         String sql = "SELECT * FROM User_In_Forum order by " + s1+", "+s2;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> CommentLike(String Tcomment, String Tlike) {
        List<UserInForum> userList = new ArrayList<>();
            String s1 = "totalcomment "+" " +Tcomment;
         String s2 = "totallike"+" " +Tlike;
         String sql = "SELECT * FROM User_In_Forum order by " + s1+", "+s2;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> CommentLikeReport(String Tcomment, String Tlike, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
           String s1 = "totalcomment "+" " +Tcomment;
         String s2 = "totallike"+" " +Tlike;
          String s3 = "totalreport"+" " +Treport;
         String sql = "SELECT * FROM User_In_Forum order by " + s1+", "+s2+", "+s3;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> OnlyPost(String Tpost) {
        List<UserInForum> userList = new ArrayList<>();
        String s1 = "totalpost "+" " +Tpost;
       
         String sql = "SELECT * FROM User_In_Forum order by " + s1;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> PostReport(String Tpost, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
        String s1 = "totalpost "+" " +Tpost;
        String s2 = "totalreport "+" " +Treport;
         String sql = "SELECT * FROM User_In_Forum order by " + s1+", "+s2;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> PostLike(String Tpost, String Tlike) {
        List<UserInForum> userList = new ArrayList<>();
         String s1 = "totalpost "+" " +Tpost;
        String s2 = "totallike"+" " +Tlike;
         String sql = "SELECT * FROM User_In_Forum order by " + s1+", "+s2;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> PostLikeReport(String Tpost, String Tlike, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
        String s1 = "totalpost "+" " +Tpost;
        String s2 = "totallike"+" " +Tlike;
          String s3 = "totalreport "+" " +Treport;
         String sql = "SELECT * FROM User_In_Forum order by " + s1+", "+s2+", "+s3;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> PostComment(String Tpost, String Tcomment) {
        List<UserInForum> userList = new ArrayList<>();
String s1 = "totalpost "+" " +Tpost;
        String s2 = "totalcomment"+" " +Tcomment;
         
         String sql = "SELECT * FROM User_In_Forum order by " + s1+", "+s2;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> PostCommentReport(String Tpost, String Tcomment, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
          String s1 = "totalpost "+" " +Tpost;
       String s2 = "totalcomment"+" " +Tcomment;
          String s3 = "totalreport "+" " +Treport;
         String sql = "SELECT * FROM User_In_Forum order by " + s1+", "+s2+", "+s3;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> PostCommentLike(String Tpost, String Tcomment, String Tlike) {
        List<UserInForum> userList = new ArrayList<>();
          String s1 = "totalpost "+" " +Tpost;
       String s2 = "totalcomment"+" " +Tcomment;
          String s3 = "totallike "+" " +Tlike;
         String sql = "SELECT * FROM User_In_Forum order by " + s1+", "+s2+", "+s3;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public List<UserInForum> PostCommentLikeReport(String Tpost, String Tcomment, String Tlike, String Treport) {
        List<UserInForum> userList = new ArrayList<>();
        String s1 = "totalpost "+" " +Tpost;
       String s2 = "totalcomment"+" " +Tcomment;
          String s3 = "totallike "+" " +Tlike;
           String s4 = "totalreport "+" " +Treport;
         String sql = "SELECT * FROM User_In_Forum order by " + s1+", "+s2+", "+s3+", "+s4;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
         
              ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String userRealName = rs.getString("user_realname");
                int totalPost = rs.getInt("totalpost");
                int totalComment = rs.getInt("totalcomment");
                int totalLike = rs.getInt("totallike");
                int totalReport = rs.getInt("totalreport");
                String status = rs.getString("status");

                UserInForum user = new UserInForum(userId, userRealName, totalPost, totalComment, totalLike, totalReport, status);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserinforum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }
}
