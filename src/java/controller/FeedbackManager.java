/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.FeedbackForSystem;
import entity.FeedbackType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.DAOFeedbackForSystem;

/**
 *
 * @author pgb31
 */
public class FeedbackManager extends HttpServlet {

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
            out.println("<title>Servlet FeedbackManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackManager at " + request.getContextPath() + "</h1>");
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

        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                DF.deleteFeedbackForSystem(id);
            }
        }

        String fbt = request.getParameter("FeedbackType");
        request.setAttribute("feedbackName", fbt);

        ArrayList<FeedbackForSystem> list = DF.getAllFeedback();
        ArrayList<FeedbackType> FT = DF.getAllFeedbackTypes();
        request.setAttribute("fbt", FT);
        request.setAttribute("list", list);
        request.getRequestDispatcher("feedbackmanager.jsp").forward(request, response);
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
        String fbt = request.getParameter("FeedbackType");
        request.setAttribute("feedbackName", fbt);

        DAOFeedbackForSystem DF = new DAOFeedbackForSystem();
        if (fbt.equals("FeedBack Type")) {
            ArrayList<FeedbackForSystem> list = DF.getAllFeedback();

            request.setAttribute("list", list);

        } else {
            if (fbt.equalsIgnoreCase("Bug Report")) {
                ArrayList<FeedbackForSystem> list = DF.getFeedback(1);
                request.setAttribute("list", list);

            }
            if (fbt.equalsIgnoreCase("Feature Request")) {
                ArrayList<FeedbackForSystem> list = DF.getFeedback(2);
                request.setAttribute("list", list);

            }
            if (fbt.equalsIgnoreCase("General Feedback")) {
                ArrayList<FeedbackForSystem> list = DF.getFeedback(3);
                request.setAttribute("list", list);

            }

        }
        ArrayList<FeedbackType> FT = DF.getAllFeedbackTypes();
        request.setAttribute("fbt", FT);

        request.getRequestDispatcher("feedbackmanager.jsp").forward(request, response);

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
