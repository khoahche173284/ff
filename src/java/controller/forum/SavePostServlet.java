package controller.forum;

import controller.*;
import entity.Post;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.DAOPost;

public class SavePostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        DAOPost daoPost = new DAOPost();
        String action = request.getParameter("action");
        String postIdParam = request.getParameter("postId");
        String keyword = request.getParameter("keyword");
        String startDate = request.getParameter("startDate");

        if ("delete".equals(action) && postIdParam != null) {
            int postId = Integer.parseInt(postIdParam);
            daoPost.deleteSavedPost(user.getId(), postId);
        }

        List<Post> savedPosts;

        if (keyword != null && !keyword.isEmpty()) {
            savedPosts = daoPost.searchSavedPostsByKeyword(user.getId(), keyword);
        } else if (startDate != null && !startDate.isEmpty()) {
            savedPosts = daoPost.searchSavedPostsByDate(user.getId(), startDate);
        } else {
            savedPosts = daoPost.selectSavedPostsByUser(user.getId());
        }

        request.setAttribute("savedPosts", savedPosts);
        request.getRequestDispatcher("savedposts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "SavePostServlet";
    }
}