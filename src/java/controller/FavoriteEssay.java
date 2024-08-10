package controller;

import entity.FavoriteEssayHistory;
import entity.Users;
import model.DAOFavoriteEssay;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FavoriteEssay extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int userId = user.getId();
        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");
        
        int currentPage = 1;
        if (request.getParameter("page") != null) {
            try {
                currentPage = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        int recordsPerPage = 5;
        int offset = (currentPage - 1) * recordsPerPage;

        DAOFavoriteEssay dao = new DAOFavoriteEssay();
        List<FavoriteEssayHistory> favoriteEssays;
        int totalRecords = 0;

        if ((category != null && !category.isEmpty()) || (keyword != null && !keyword.isEmpty())) {
            favoriteEssays = dao.searchFavoriteEssays(userId, keyword, category, offset, recordsPerPage);
            totalRecords = dao.getTotalFavoriteEssays(userId, keyword, category);
        } else {
            favoriteEssays = dao.getFavoriteEssaysByUserId(userId, offset, recordsPerPage);
            totalRecords = dao.getTotalFavoriteEssaysByUserId(userId);
        }

        int noOfPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

        List<String> categories = dao.getAllCategories();

        request.setAttribute("favoriteEssays", favoriteEssays);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("keyword", keyword);
        request.setAttribute("category", category);
        request.setAttribute("categories", categories);
        
        request.getRequestDispatcher("favorite.jsp").forward(request, response);
    }
}//35 + dao 45