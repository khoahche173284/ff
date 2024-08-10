/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.DAOUsers;

/**
 *
 * @author pgb31
 */
public class searchbydate extends HttpServlet {

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
            out.println("<title>Servlet searchbydate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet searchbydate at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String date1Str = request.getParameter("date1");
        String date2Str = request.getParameter("date2");

        if (date1Str == null || date2Str == null || date1Str.isEmpty() || date2Str.isEmpty()) {
            request.setAttribute("er", "Both dates are required.");
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }
        LocalDate date1 = LocalDate.parse(date1Str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse(date2Str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (date1.isBefore(date2)) {
            DAOUsers DU = new DAOUsers();
            ArrayList<Users> list = DU.findUsersByDateRange(date1, date2);
            request.setAttribute("list", list);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } else if (date2.isBefore(date1)) {
            DAOUsers DU = new DAOUsers();
            ArrayList<Users> list = DU.findUsersByDateRange(date2, date1);
            request.setAttribute("list", list);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } else {
            DAOUsers DU = new DAOUsers();
            ArrayList<Users> list = DU.findUsersByExactDate(date1);
            request.setAttribute("list", list);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }

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
