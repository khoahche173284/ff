/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.forum;

import entity.Comment;
import entity.Post;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.DAOComment;
import model.DAOPost;

/**
 *
 * @author Quang
 */
public class ViewReply extends HttpServlet {

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
            out.println("<title>Servlet ViewReply</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewReply at " + request.getContextPath() + "</h1>");
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
        String cmt_id_str = request.getParameter("commentId");
        int postId = Integer.parseInt(request.getParameter("postId"));
        int cmt_id = Integer.parseInt(cmt_id_str);
        DAOComment DC = new DAOComment();
        List<Comment> listreply = DC.listAllCommentsReplyWithCmtID(cmt_id);

        DAOPost postDAO = new DAOPost();
        Post postt = postDAO.selectPost(postId);
  

        DAOComment commentDAO = new DAOComment();
        List<Comment> listComments = commentDAO.selectAllCommentsByPostId(postId);

        request.setAttribute("postt", postt);

        request.setAttribute("listComments", listComments);
        request.setAttribute("bat", "bat");
        request.setAttribute("rcmtid", cmt_id);
        request.setAttribute("listreply", listreply);
        request.setAttribute("type", "2");
        request.getRequestDispatcher("comments.jsp").forward(request, response);

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
