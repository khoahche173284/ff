package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOFeedbackForUser;
//20
public class DeleteFeedbackByUser extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("id"));
        DAOFeedbackForUser dao = new DAOFeedbackForUser();
        boolean success = dao.deleteFeedback(feedbackId);

        if (success) {
            request.getSession().setAttribute("succ", "Feedback deleted successfully");
        } else {
            request.getSession().setAttribute("err", "Failed to delete feedback");
        }

        String currentPage = request.getParameter("currentPage");
        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");
        response.sendRedirect("userfeedbacklist?page=" + currentPage + "&keyword=" + keyword + "&category=" + category);
    }
}
