package controller;

import entity.History;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.DAOHistory;
//10 + dao 20
public class history extends HttpServlet {

    private static final int ITEMS_PER_PAGE = 5;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");
        DAOHistory historyDAO = new DAOHistory();
        List<History> listHistory;

        // Get all categories
        List<String> categories = historyDAO.getAllCategories();
        request.setAttribute("categories", categories);

//        if (keyword != null && !keyword.isEmpty()) {
//            listHistory = historyDAO.searchHistory(user.getId(), keyword);
//        } else if (category != null && !category.isEmpty()) {
//            listHistory = historyDAO.getHistoryByCategory(user.getId(), category);
//        } else {
//            listHistory = historyDAO.getAllHistory(user.getId());
//        }
        if ((keyword != null && !keyword.isEmpty()) || (category != null && !category.isEmpty())) {
            listHistory = historyDAO.searchHistory(user.getId(), keyword, category);
        } else {
            listHistory = historyDAO.getAllHistory(user.getId());
        }

        if (listHistory == null || listHistory.isEmpty()) {
            request.setAttribute("err", "Your History is Empty");
        } else {
            int currentPage = 1;
            if (request.getParameter("page") != null) {
                currentPage = Integer.parseInt(request.getParameter("page"));
            }

            int totalItems = listHistory.size();
            int totalPages = (int) Math.ceil((double) totalItems / ITEMS_PER_PAGE);

            int start = (currentPage - 1) * ITEMS_PER_PAGE;
            int end = Math.min(start + ITEMS_PER_PAGE, totalItems);

            List<History> paginatedHistory = listHistory.subList(start, end);

            Map<Integer, Boolean> favoritedMap = new HashMap<>();
            for (History history : paginatedHistory) {
                boolean isFavorited = historyDAO.isEssayFavorited(user.getId(), history.getEssayId());
                favoritedMap.put(history.getEssayId(), isFavorited);
            }

            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("listHistory", paginatedHistory);
            request.setAttribute("favoritedMap", favoritedMap);
            request.setAttribute("keyword", keyword);
            request.setAttribute("category", category); // Add this line
        }

        request.getRequestDispatcher("history.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String keyword = request.getParameter("keyword");
        DAOHistory daoHistory = new DAOHistory();
        List<History> histories = daoHistory.searchHistory(user.getId(), keyword);

        if (histories != null && !histories.isEmpty()) {
            int currentPage = 1;
            if (request.getParameter("page") != null) {
                currentPage = Integer.parseInt(request.getParameter("page"));
            }

            int totalItems = histories.size();
            int totalPages = (int) Math.ceil((double) totalItems / ITEMS_PER_PAGE);

            int start = (currentPage - 1) * ITEMS_PER_PAGE;
            int end = Math.min(start + ITEMS_PER_PAGE, totalItems);

            List<History> paginatedHistory = histories.subList(start, end);

            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("listHistory", paginatedHistory);
            request.setAttribute("keyword", keyword);
        } else {
            request.setAttribute("err", "No text matches the search");
        }

        request.getRequestDispatcher("history.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
