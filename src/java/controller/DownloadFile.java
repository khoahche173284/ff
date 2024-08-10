package controller;

import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOLoginHistory;

public class DownloadFile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileContent = request.getParameter("fileContent");
        String fileName = "document.txt";
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        DAOLoginHistory loginHistoryDAO =  new DAOLoginHistory();
            loginHistoryDAO.insertRecentActivity(user.getId(),20);

        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try (PrintWriter out = response.getWriter()) {
            out.write(fileContent);
        }
    }
}