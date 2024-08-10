package controller;

import entity.FavoriteEssayHistory;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOFavoriteEssay;

public class AddFavEssay extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int userId = user.getId();
        int essayId = Integer.parseInt(request.getParameter("essayId"));
        DAOFavoriteEssay daoFavoriteEssay = new DAOFavoriteEssay();

        String currentPage = request.getParameter("currentPage");
        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");

        if (daoFavoriteEssay.isFavoriteEssayExist(userId, essayId)) {
            session.setAttribute("err", "Essay already in favorites.");
//            session.removeAttribute("succ");
        } else {
            FavoriteEssayHistory favoriteEssay = new FavoriteEssayHistory(userId, essayId);
            int result = daoFavoriteEssay.addFavoriteEssay(favoriteEssay);

            if (result > 0) {
                session.setAttribute("succ", "Essay added to favorites successfully.");
//                session.removeAttribute("err");
            } else {
                session.setAttribute("err", "Failed to add essay to favorites.");
//                session.removeAttribute("succ");
            }
        }
        response.sendRedirect("history?page=" + currentPage + "&keyword=" + keyword + "&category=" + category);
    }
}//15 + 10 dao