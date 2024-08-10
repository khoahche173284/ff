/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.OrderDetail;
import entity.Product;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAOOrderDetail;

/**
 *
 * @author Quang
 */
public class SaveOrderController extends HttpServlet {

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
            out.println("<title>Servlet SaveOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaveOrderController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        request.getRequestDispatcher("vnpay_return.jsp").forward(request, response);
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

        HttpSession session = request.getSession();

        Users ua = (Users) session.getAttribute("acc");

        Product product = (Product) session.getAttribute("product");

        int userId = ua.getId();
        String email = ua.getEmail();

        int productId = product.getId();

        String vnpTxnRef = request.getParameter("vnp_TxnRef");
        int vnpAmount = Integer.parseInt(request.getParameter("vnp_Amount"));
        String vnpOrderInfo = request.getParameter("vnp_OrderInfo");
        String vnpResponseCode = request.getParameter("vnp_ResponseCode");
        String vnpTransactionNo = request.getParameter("vnp_TransactionNo");
        String vnpBankCode = request.getParameter("vnp_BankCode");
        String vnpPayDateStr = request.getParameter("vnp_PayDate");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date vnpPayDate = null;
        try {
            vnpPayDate = formatter.parse(vnpPayDateStr);
        } catch (ParseException ex) {
            Logger.getLogger(SaveOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

        OrderDetail orderDetail = new OrderDetail(
                new Date(),
                userId,
                email,
                productId,
                true,
                vnpAmount,
                vnpTxnRef,
                vnpOrderInfo,
                vnpResponseCode,
                vnpTransactionNo,
                vnpBankCode,
                vnpPayDate,
                vnpTransactionNo
        );

        DAOOrderDetail daoOrderDetail = new DAOOrderDetail();
        daoOrderDetail.createOrderDetail(orderDetail);

        if ("00".equals(vnpResponseCode)) {
            // Payment successful
            try {
                daoOrderDetail.updateUserAndOrder(userId, vnpPayDate, productId);
            } catch (SQLException ex) {
                Logger.getLogger(SaveOrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Users ut = (Users) session.getAttribute("acc");
            ut.setRoleId(3);
            session.setAttribute("acc", ut);
        }

        request.getRequestDispatcher("home.jsp").forward(request, response);
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
