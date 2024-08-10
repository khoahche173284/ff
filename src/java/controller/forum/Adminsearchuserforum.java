/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.forum;

import entity.UserInForum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.DAOUserinforum;

/**
 * nnnnnnnnnn n
 *
 * @author pgb31
 */
public class Adminsearchuserforum extends HttpServlet {

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
            out.println("<title>Servlet Adminsearchuserforum</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Adminsearchuserforum at " + request.getContextPath() + "</h1>");
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
        String Tpost = request.getParameter("Total Post");
        String Tcomment = request.getParameter("Total comment");
        String Tlike = request.getParameter("Total likes");
        String Treport = request.getParameter("Total report");
        String Status = request.getParameter("Status");

        DAOUserinforum DUF = new DAOUserinforum();
        Status.trim();
        Treport.trim();
        Tlike.trim();
        Tcomment.trim();

        if (Tpost == "0" && Tcomment == "0" && Tlike == "0" && Treport == "0" && Status == "0") {
            request.setAttribute("ThongBao", "");

        }

        if (!Status.equals("0")) {
            if (Tpost.equals("0") && Tcomment.equals("0") && Tlike.equals("0") && Treport.equals("0")) {
                Status.trim();
                List<UserInForum> list = DUF.OnlyStatus(Status);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (Tpost.equals("0") && Tcomment.equals("0") && Tlike.equals("0") && !Treport.equals("0")) {

                List<UserInForum> list = DUF.StatusAndReport(Status, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);

            }
            if (Tpost.equals("0") && Tcomment.equals("0") && !Tlike.equals("0") && Treport.equals("0")) {

                List<UserInForum> list = DUF.StatusAndLike(Status, Tlike);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);

            }
            if (Tpost.equals("0") && Tcomment.equals("0") && !Tlike.equals("0") && !Treport.equals("0")) {

                List<UserInForum> list = DUF.StatusLikeReport(Status, Tlike, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);

            }
            if (Tpost.equals("0") && !Tcomment.equals("0") && Tlike.equals("0") && Treport.equals("0")) {
                List<UserInForum> list = DUF.StatusAndComment(Status, Tcomment);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);

            }
            if (Tpost.equals("0") && !Tcomment.equals("0") && Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.StatusCommentReport(Status, Tcomment, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);

            }
            if (Tpost.equals("0") && !Tcomment.equals("0") && !Tlike.equals("0") && Treport.equals("0")) {
                List<UserInForum> list = DUF.StatusCommentLike(Status, Tcomment, Tlike);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);

            }
            if (Tpost.equals("0") && !Tcomment.equals("0") && !Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.StatusCommnetLikeReport(Status, Tcomment, Tlike, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);

            }

            if (!Tpost.equals("0") && Tcomment.equals("0") && Tlike.equals("0") && Treport.equals("0")) {
                List<UserInForum> list = DUF.StatusAndPost(Status, Tpost);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);

            }
            if (!Tpost.equals("0") && Tcomment.equals("0") && Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.StatusPostReport(Status, Tpost, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);

            }
            if (!Tpost.equals("0") && Tcomment.equals("0") && !Tlike.equals("0") && Treport.equals("0")) {
                List<UserInForum> list = DUF.StatusPostLike(Status, Tpost, Tlike);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);

            }
            if (!Tpost.equals("0") && Tcomment.equals("0") && !Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.StatusPostLikeReport(Status, Tpost, Tlike, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (!Tpost.equals("0") && !Tcomment.equals("0") && Tlike.equals("0") && Treport.equals("0")) {
                List<UserInForum> list = DUF.StatusPostComment(Status, Tpost, Tcomment);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (!Tpost.equals("0") && !Tcomment.equals("0") && Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.StatusPostCommentReport(Status, Tpost, Tcomment, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (!Tpost.equals("0") && !Tcomment.equals("0") && !Tlike.equals("0") && Treport.equals("0")) {
                List<UserInForum> list = DUF.StatusPostCommentLike(Status, Tpost, Tcomment, Tlike);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (!Tpost.equals("0") && !Tcomment.equals("0") && !Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.SearchByAll(Status, Tpost, Tcomment, Tlike, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
        }

        if (Status.equals("0")) {

            if (Tpost.equals("0") && Tcomment.equals("0") && Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.OnlyReport(Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (Tpost.equals("0") && Tcomment.equals("0") && !Tlike.equals("0") && Treport.equals("0")) {
                List<UserInForum> list = DUF.OnlyLike(Tlike);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (Tpost.equals("0") && Tcomment.equals("0") && !Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.LikeReport(Tlike, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (Tpost.equals("0") && !Tcomment.equals("0") && Tlike.equals("0") && Treport.equals("0")) {
                List<UserInForum> list = DUF.OnlyComment(Tcomment);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (Tpost.equals("0") && !Tcomment.equals("0") && Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.CommentReport(Tcomment, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (Tpost.equals("0") && !Tcomment.equals("0") && !Tlike.equals("0") && Treport.equals("0")) {
                List<UserInForum> list = DUF.CommentLike(Tcomment, Tlike);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (Tpost.equals("0") && !Tcomment.equals("0") && !Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.CommentLikeReport(Tcomment, Tlike, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }

            if (!Tpost.equals("0") && Tcomment.equals("0") && Tlike.equals("0") && Treport.equals("0")) {
                List<UserInForum> list = DUF.OnlyPost(Tpost);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (!Tpost.equals("0") && Tcomment.equals("0") && Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.PostReport(Tpost, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (!Tpost.equals("0") && Tcomment.equals("0") && !Tlike.equals("0") && Treport.equals("0")) {
                List<UserInForum> list = DUF.PostLike(Tpost, Tlike);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (!Tpost.equals("0") && Tcomment.equals("0") && !Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.PostLikeReport(Tpost, Tlike, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (!Tpost.equals("0") && !Tcomment.equals("0") && Tlike.equals("0") && Treport.equals("0")) {
                List<UserInForum> list = DUF.PostComment(Tpost, Tcomment);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (!Tpost.equals("0") && !Tcomment.equals("0") && Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.PostCommentReport(Tpost, Tcomment, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (!Tpost.equals("0") && !Tcomment.equals("0") && !Tlike.equals("0") && Treport.equals("0")) {
                List<UserInForum> list = DUF.PostCommentLike(Tpost, Tcomment, Tlike);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
            }
            if (!Tpost.equals("0") && !Tcomment.equals("0") && !Tlike.equals("0") && !Treport.equals("0")) {
                List<UserInForum> list = DUF.PostCommentLikeReport(Tpost, Tcomment, Tlike, Treport);
                request.setAttribute("listu", list);
                request.setAttribute("type", "2");
                request.getRequestDispatcher("AdminManageForum.jsp").forward(request, response);
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
