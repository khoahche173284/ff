/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.forum;

import entity.Post;
import entity.Users;
import entity.listlike;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.DAOListlike;
import model.DAOPost;

/**
 *
 * @author pgb31
 */
public class likepost extends HttpServlet {

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
            out.println("<title>Servlet likepost</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet likepost at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Users UT = (Users) session.getAttribute("acc");
        if(UT == null){
            response.sendRedirect("login");
        }else{
        DAOPost DP = new DAOPost();
        DAOListlike DLL = new DAOListlike();
        
        String pid_str = request.getParameter("postId");
       
        int pid = Integer.parseInt(pid_str);
         Post cpost = DP.getpost(pid);
        int luotthich = cpost.getLuotThich();
        ArrayList<listlike> listl = DLL.getlistlikebypostid(pid); 
        int k = 0; 
        for(listlike l : listl){
            if(l.getUserId()== UT.getId()) k =1;
        } 
        if(k==1&&cpost.getLuotThich()>0){
            DP.unlikepost(pid, luotthich); 
            DLL.deletelike(pid, UT.getId()); 
         
        }   
        if(k!=1){
            
            DP.likepost(pid, luotthich);
            DLL.createlike(pid, UT.getId());
           
        } 
            response.sendRedirect("getAllPost");      

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