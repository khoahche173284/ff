package controller;

import entity.Trash;
import entity.Users;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOTrash;

public class trash extends HttpServlet {

    private static final int ITEMS_PER_PAGE = 5;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        DAOTrash trashDAO = new DAOTrash();
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String keyword = request.getParameter("keyword");
        List<Trash> trashList;
        if (keyword != null && !keyword.isEmpty()) {
            trashList = trashDAO.searchTrash(user.getId(), keyword);
        } else {
            trashList = trashDAO.getAllTrash(user.getId());
        }

        handlePagination(request, trashList, keyword);
        request.getRequestDispatcher("trash.jsp").forward(request, response);
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

        String action = request.getParameter("action");
        DAOTrash trashDAO = new DAOTrash();
        int currentPage = request.getParameter("currentPage") != null
                ? Integer.parseInt(request.getParameter("currentPage"))
                : 1;

        String keyword = request.getParameter("keyword");

        if ("search".equals(action)) {
            keyword = request.getParameter("keyword");
            List<Trash> trashList = trashDAO.searchTrash(user.getId(), keyword);

            if (trashList != null && !trashList.isEmpty()) {
                handlePagination(request, trashList, keyword);
            } else {
                request.setAttribute("err", "No trash items match the search.");
            }
            request.getRequestDispatcher("trash.jsp").forward(request, response);
        } else if ("restore".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                trashDAO.restoreTrash(id);
                session.setAttribute("message", "Restore successful!");
            } catch (Exception e) {
                session.setAttribute("message", "Restore failed!");
            }
            response.sendRedirect("trash?page=" + currentPage + "&keyword=" + keyword);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                trashDAO.deleteTrash(id);
                session.setAttribute("message", "Delete successful!");
            } catch (Exception e) {
                session.setAttribute("message", "Delete failed!");
            }
            response.sendRedirect("trash?page=" + currentPage + "&keyword=" + keyword);
        }
//        else if ("restore".equals(action)) {
//            int id = Integer.parseInt(request.getParameter("id"));
//            trashDAO.restoreTrash(id);
//            response.sendRedirect("trash?page=" + currentPage + "&keyword=" + keyword);
//        } else if ("delete".equals(action)) {
//            int id = Integer.parseInt(request.getParameter("id"));
//            trashDAO.deleteTrash(id);
//            response.sendRedirect("trash?page=" + currentPage + "&keyword=" + keyword);
//        }
    }

    private void handlePagination(HttpServletRequest request, List<Trash> trashList, String keyword) {
        int currentPage = request.getParameter("page") != null
                ? Integer.parseInt(request.getParameter("page"))
                : 1;
        int totalItems = trashList.size();
        int totalPages = (int) Math.ceil((double) totalItems / ITEMS_PER_PAGE);
        int start = (currentPage - 1) * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, totalItems);
        List<Trash> paginatedTrash = trashList.subList(start, end);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("trashList", paginatedTrash);
        request.setAttribute("keyword", keyword);
    }

    @Override
    public String getServletInfo() {
        return "Trash management servlet";
    }
}
