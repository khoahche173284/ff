/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.forum;

import entity.Comment;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import model.DAOComment;
import model.DAOPost;

/**
 *
 * @author Quang
 */
public class ReplyCmtController extends HttpServlet {

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
            out.println("<title>Servlet ReplyCmtController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReplyCmtController at " + request.getContextPath() + "</h1>");
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
        String noiDung = request.getParameter("commentContent");
        int cmtId = Integer.parseInt(request.getParameter("commentId"));
        int postId = Integer.parseInt(request.getParameter("postId"));
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        } 
        
        
        DAOPost postDAO = new DAOPost();
        Comment newComment = new Comment();
        newComment.setNoiDung(noiDung);
        newComment.setCmt_id(cmtId);
        newComment.setUserId(user.getId());
        DAOComment commentDAO = new DAOComment();

        if (noiDung == null || noiDung.trim().isEmpty()) {
            request.setAttribute("vln", "Please enter something!");
            request.getRequestDispatcher("getComment?postId=" + postId).forward(request, response);
        } else {
            try {
                postDAO.tangComment(postId);
                commentDAO.insertReplyComment(newComment);
                response.sendRedirect("getComment?postId=" + postId);
            } catch (SQLException ex) {
                request.setAttribute("vln", "Error inserting comment. Please try again.");
                request.getRequestDispatcher("getComment?postId=" + postId).forward(request, response);
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

