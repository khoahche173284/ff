/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.FeedbackType;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.DAOFeedbackForSystem;

/**
 *
 * @author pgb31
 */
public class feedback extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet feedback</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet feedback at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFeedbackForSystem DF = new DAOFeedbackForSystem();
        ArrayList<FeedbackType> FT = DF.getAllFeedbackTypes();

        String fbt = request.getParameter("FeedbackType");
        request.setAttribute("feedbackName", fbt);
        request.setAttribute("fbt", FT);
        request.getRequestDispatcher("userfeedback.jsp").forward(request, response);
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
        DAOFeedbackForSystem DF = new DAOFeedbackForSystem(); 
        String content = request.getParameter("content");
        String fbt = request.getParameter("FeedbackType"); 
        ArrayList<FeedbackType> FT = DF.getAllFeedbackTypes();
        request.setAttribute("feedbackName", fbt);
        request.setAttribute("fbt", FT);
        
        if(content.equals("")) {
         request.setAttribute("er", "Please enter your feedback"); 
         request.getRequestDispatcher("userfeedback.jsp").forward(request, response);
        }
        
        int fbid = 0;
        for (FeedbackType f : FT) {
            if (f.getFeedbackName().equalsIgnoreCase(fbt)) {
                fbid = f.getId();
            }
        } 
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        int uid = user.getId(); 
        if(fbid!=0){
        DF.createFeedbackForSystem(uid, fbid, content);
        } 
           request.setAttribute("tb", "Successfully !"); 
          request.getRequestDispatcher("userfeedback.jsp").forward(request, response);
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