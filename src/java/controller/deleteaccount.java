/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import model.DAOPost;
import model.DAOUsers;

/**
 *
 * @author admin
 */
public class deleteaccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");

        if (user == null) {
            response.sendRedirect("login.jsp");
        } else {
            DAOLoginHistory loginHistoryDAO = new DAOLoginHistory();
            loginHistoryDAO.insertRecentActivity(user.getId(), 17);
            DAOUsers userDao = new DAOUsers();
          
            userDao.deleteUsers(user.getId());
            
            session.invalidate();
            response.sendRedirect("login.jsp");
        }
    }
}
