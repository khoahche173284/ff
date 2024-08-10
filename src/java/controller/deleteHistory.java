/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.History;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOFavoriteEssay;
import model.DAOHistory;
import model.DAOLoginHistory;

/**
 *
 * @author congkhoa2723
 */
public class deleteHistory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         int id = Integer.parseInt(request.getParameter("id"));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        String keyword = request.getParameter("keyword");
        DAOHistory historyDAO = new DAOHistory();
        DAOLoginHistory loginHistoryDAO =  new DAOLoginHistory();
         HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        History hit = historyDAO.getHistoryById(id);  
          
          
          
        DAOFavoriteEssay DFE = new DAOFavoriteEssay();  
        DFE.deleteFavoriteEssay(user.getId(), hit.getEssayId());

        loginHistoryDAO.insertRecentActivity(user.getId(),21); 
        historyDAO.deleteHistory(id); 
        
      
        response.sendRedirect("history?page=" + currentPage + "&keyword=" + keyword);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}