 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.forum;

import entity.Comment;
import entity.Post;
import entity.ReportPost;
import entity.listlike;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.DAOComment;
import model.DAOListlike;
import model.DAOPost;
import model.DAOReportPost;

/**
 *
 * @author pgb31
 */
public class ViewPostDetail extends HttpServlet {

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
            out.println("<title>Servlet ViewPostDetail</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewPostDetail at " + request.getContextPath() + "</h1>");
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
        DAOPost DP =new DAOPost();
        DAOComment DC = new DAOComment();
        DAOListlike DLL = new DAOListlike(); 
        DAOReportPost DRP = new DAOReportPost();
       String postid_str = request.getParameter("postid"); 
       if(postid_str!=null) {
       int postid = Integer.parseInt(postid_str);   
       
       Post pt = DP.selectPost(postid);  
       
       ArrayList<listlike> listlike = DLL.getlistlikebypostid(postid); 
       ArrayList<ReportPost> listrp = DRP.getReportsbypostid(postid); 
       List<Comment> listcomment = DC.selectAllCommentsByPostId(postid);
        request.setAttribute("listcomment", listcomment); 
       request.setAttribute("Post", pt); 
       request.setAttribute("listlike", listlike); 
       request.setAttribute("listrp", listrp); 
       request.getRequestDispatcher("ViewPostDetail.jsp").forward(request, response);
       
       }
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
        processRequest(request, response);
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
