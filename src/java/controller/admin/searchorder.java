/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.OrderDetail;
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
import model.DAOOrderDetail;
import model.DAOUsers;

/**
 *
 * @author pgb31
 */
public class searchorder extends HttpServlet {

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
            out.println("<title>Servlet searchorder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet searchorder at " + request.getContextPath() + "</h1>");
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
        String gmail = request.getParameter("gmail");
        String date1Str = request.getParameter("date1");
        String date2Str = request.getParameter("date2");

        if (gmail == "" && date1Str == "" && date2Str == "") {

            request.getRequestDispatcher("orderdetail.jsp").forward(request, response);
        }

        if (gmail != "") {
            DAOOrderDetail DO = new DAOOrderDetail();
            ArrayList<OrderDetail> list = DO.searchByGmail(gmail);
            request.setAttribute("list", list);
            request.getRequestDispatcher("orderdetail.jsp").forward(request, response);
        } else {
            if (date1Str == "" || date2Str == "") {
                request.getRequestDispatcher("orderdetail.jsp").forward(request, response);
            } else {
                LocalDate date1 = LocalDate.parse(date1Str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate date2 = LocalDate.parse(date2Str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if (date1.isBefore(date2)) {
                    DAOOrderDetail DO = new DAOOrderDetail();
                    ArrayList<OrderDetail> list = DO.findOrderByDateRange(date1, date2);
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("orderdetail.jsp").forward(request, response);
                } else if (date2.isBefore(date1)) {
                    DAOOrderDetail DO = new DAOOrderDetail();
                    ArrayList<OrderDetail> list = DO.findOrderByDateRange(date2, date1);
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("orderdetail.jsp").forward(request, response);
                } else {
                    DAOOrderDetail DO = new DAOOrderDetail();
                    ArrayList<OrderDetail> list = DO.findOrderByDate(date1);
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("orderdetail.jsp").forward(request, response);
                }

            }

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