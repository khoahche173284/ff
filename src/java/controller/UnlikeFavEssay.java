package controller;

import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOFavoriteEssay;
//25 his + 25 fav + 10
public class UnlikeFavEssay extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int userId = user.getId();
        int essayId = Integer.parseInt(request.getParameter("essayId"));
        String page = request.getParameter("currentPage");
        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");

        DAOFavoriteEssay daoFavoriteEssay = new DAOFavoriteEssay();
        boolean success = daoFavoriteEssay.deleteFavoriteEssay(userId, essayId);

        if (success) {
            session.setAttribute("err", "Essay removed from favorites successfully.");
        } else {
            session.setAttribute("err", "Failed to remove essay from favorites.");
        }

        String redirectURL = "history?page=" + page;
        if (keyword != null && !keyword.isEmpty()) {
            redirectURL += "&keyword=" + keyword;
        }
        if (category != null && !category.isEmpty()) {
            redirectURL += "&category=" + category;
        }
        response.sendRedirect(redirectURL);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int userId = user.getId();
        int essayId = Integer.parseInt(request.getParameter("essayId"));
        String page = request.getParameter("page");
        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");

        DAOFavoriteEssay daoFavoriteEssay = new DAOFavoriteEssay();
        boolean success = daoFavoriteEssay.deleteFavoriteEssay(userId, essayId);

        if (success) {
            session.setAttribute("succ", "Essay removed from favorites successfully.");
        } else {
            session.setAttribute("err", "Failed to remove essay from favorites.");
        }

        String redirectURL = "favorite?page=" + page;
        if (keyword != null && !keyword.isEmpty()) {
            redirectURL += "&keyword=" + keyword;
        }
        if (category != null && !category.isEmpty()) {
            redirectURL += "&category=" + category;
        }
        response.sendRedirect(redirectURL);
    }
}//55