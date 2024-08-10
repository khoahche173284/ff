package controller;

import entity.FeedbackForSystem;
import entity.Users;
import model.DAOFeedbackForUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
//40 + 30
public class FeedbackListForUser extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int userId = user.getId();

        DAOFeedbackForUser daoFeedback = new DAOFeedbackForUser();

        int page = 1;
        int pageSize = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        String keyword = "";
        if (request.getParameter("keyword") != null) {
            keyword = request.getParameter("keyword");
        }

        String category = "";
        if (request.getParameter("category") != null) {
            category = request.getParameter("category");
        }

        int totalFeedback = daoFeedback.getTotalFeedbackCount(userId, keyword, category);
        int totalPages = (int) Math.ceil((double) totalFeedback / pageSize);

        int offset = (page - 1) * pageSize;
        ArrayList<FeedbackForSystem> feedbackList = daoFeedback.getFeedbackForUser(userId, keyword, category, offset, pageSize);
        ArrayList<String> feedbackTypes = daoFeedback.getAllFeedbackTypes();

        request.setAttribute("feedbackList", feedbackList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("keyword", keyword);
        request.setAttribute("category", category);
        request.setAttribute("feedbackTypes", feedbackTypes);

        request.getRequestDispatcher("userfeedbacklist.jsp").forward(request, response);
    }
}
