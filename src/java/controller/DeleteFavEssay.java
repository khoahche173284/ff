package controller;

import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOFavoriteEssay;
//25 + dao 20
public class DeleteFavEssay extends HttpServlet {

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
        String page = request.getParameter("page");
        String keyword = request.getParameter("keyword");

        DAOFavoriteEssay daoFavoriteEssay = new DAOFavoriteEssay();
        boolean addedToTrash = daoFavoriteEssay.addEssayToTrash(userId, essayId);
        boolean deletedFromFavorite = daoFavoriteEssay.deleteFavoriteEssay(userId, essayId);
        boolean deletedFromHistory = daoFavoriteEssay.deleteHistoryEssay(userId, essayId);

        if (deletedFromFavorite && deletedFromHistory && addedToTrash) {
            session.setAttribute("succ", "Essay moved to trash successfully.");
        } else {
            session.setAttribute("err", "Failed to move essay to trash.");
        }

        String redirectURL = "favorite?page=" + page;
        if (keyword != null && !keyword.isEmpty()) {
            redirectURL += "&keyword=" + keyword;
        }
        response.sendRedirect(redirectURL);
    }
}