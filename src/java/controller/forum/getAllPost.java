package controller.forum;

import entity.Post;
import entity.UserInForum;
import entity.Users;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOPost;
import model.DAOUserinforum;

public class getAllPost extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        DAOPost postDAO = new DAOPost();
        List<Post> listPost;

        // Kiểm tra nếu có từ khóa tìm kiếm
        String keyword = request.getParameter("keyword");
        String startDate = request.getParameter("startDate");
        String action = request.getParameter("action");
        String postIdParam = request.getParameter("postId");
        String messaged = null;  // Biến để lưu thông báo
        String error = null;  // Biến để lưu thông báo
        messaged = (String) request.getAttribute("tb");

        if ("save".equals(action) && postIdParam != null) {
            int postId = Integer.parseInt(postIdParam);
            boolean isSaved = postDAO.isPostSaved(user.getId(), postId);
            if (isSaved) {
                messaged = "Post saved successfully!";
            } else {
                error = "This post has already been saved.";
            }
        }

        if (keyword != null && !keyword.isEmpty()) {
            listPost = postDAO.searchPosts(keyword);
        } else if (startDate != null && !startDate.isEmpty()) {
            listPost = postDAO.searchPostsByDate(startDate);
        } else {
            listPost = postDAO.selectAllPosts();
        }
        DAOUserinforum DUF = new DAOUserinforum(); 
        UserInForum UIF = DUF.getUserById(user.getId());  
        if(UIF == null) request.setAttribute("UserStatus", "Normal"); 
        else request.setAttribute("UserStatus", UIF.getStatus());
        
        
        request.setAttribute("listPost", listPost);
        request.setAttribute("keyword", keyword);
        request.setAttribute("startDate", startDate);
        request.setAttribute("messaged", messaged);  
        request.setAttribute("error", error);  
        
        request.getRequestDispatcher("forum.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}