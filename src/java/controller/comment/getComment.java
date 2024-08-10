/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.comment;

import entity.Comment;
import entity.Post;
import entity.UserInForum;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.DAOComment;
import model.DAOPost;
import model.DAOUserinforum;

/**
 *
 * @author congkhoa2723
 */
public class getComment extends HttpServlet {

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
            out.println("<title>Servlet getComment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet getComment at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int postId = Integer.parseInt(request.getParameter("postId"));
        DAOPost postDAO = new DAOPost();
        Post postt = postDAO.selectPost(postId);
        System.out.println("ádfasdf" + postId);

        DAOComment commentDAO = new DAOComment();
        List<Comment> listComments = commentDAO.selectAllCommentsByPostId(postId);

        request.setAttribute("postt", postt);

        request.setAttribute("listComments", listComments);
        request.setAttribute("bat", "bat");
        request.setAttribute("type", "1");
        //
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        DAOUserinforum DUF = new DAOUserinforum();
        UserInForum UIF = DUF.getUserById(user.getId());
        if (UIF == null) {
            request.setAttribute("UserStatus", "Normal");
        } else {
            request.setAttribute("UserStatus", UIF.getStatus());
        }

        // 
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
