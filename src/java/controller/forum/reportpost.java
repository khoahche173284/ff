/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.forum;

import entity.Post;
import entity.ReportPost;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.DAOPost;
import model.DAOReportPost;

/**
 *
 * @author pgb31
 */
public class reportpost extends HttpServlet {

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
            out.println("<title>Servlet reportpost</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet reportpost at " + request.getContextPath() + "</h1>");
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
        DAOPost postDAO = new DAOPost();

        String sorp_str = request.getParameter("reportnum");
        String postidstr = request.getParameter("postId");
        String reason = request.getParameter("reportReason");
        
        int sorp = Integer.parseInt(sorp_str);
        int postid = Integer.parseInt(postidstr);
        DAOPost DP = new DAOPost();
        HttpSession session = request.getSession();
        Users UT = (Users) session.getAttribute("acc");
        DAOReportPost DRP = new DAOReportPost();
        ArrayList<ReportPost> rplist = DRP.getReportsbypostid(postid);
        int k = 0;
        for (ReportPost c : rplist) {
            if (c.getUserId() == UT.getId()) {
                k = 1;
            }

        }
        if (k == 1) {
            request.setAttribute("message", "You have reported this post"); 
            List<Post> listPost = postDAO.selectAllPosts();
            request.setAttribute("listPost", listPost);
            request.getRequestDispatcher("forum.jsp").forward(request, response);
        } else {

            DP.increaserp(sorp, postid);
            request.setAttribute("message", "Reported successful!");
            DRP.createreport(postid, UT.getId(),reason);
            List<Post> listPost = postDAO.selectAllPosts();
            request.setAttribute("listPost", listPost);
            request.getRequestDispatcher("forum.jsp").forward(request, response);

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
