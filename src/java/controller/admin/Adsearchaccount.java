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
import java.util.ArrayList;
import java.util.Date;
import model.DAOUsers;

/**
 *
 * @author pgb31
 */
public class Adsearchaccount extends HttpServlet {

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
            out.println("<title>Servlet Adsearchaccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Adsearchaccount at " + request.getContextPath() + "</h1>");
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

        String name = request.getParameter("rname");
        String gmail = request.getParameter("gmail");
        String type = request.getParameter("userType"); 
        
        if(name==""&&gmail==""&&type.equals("User's type")) {
            request.setAttribute("er","Please enter at least 1 information to search");
             request.getRequestDispatcher("admin.jsp").forward(request, response); 
        }
        

// 
//         request.setAttribute("er", type);
//            request.getRequestDispatcher("admin.jsp").forward(request, response); 

        if (gmail != "") {

            DAOUsers DU = new DAOUsers();
            ArrayList<Users> list = DU.searchByGmail(gmail);
            request.setAttribute("list", list);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } else {
            if (name != "" && type.equals("User's type") ) {

                DAOUsers DU = new DAOUsers();
                ArrayList<Users> list = DU.searchByName(name);
                request.setAttribute("list", list);
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            } 
            
         if(name !="" && type != "User's type"){ 
          name = name.trim();
          int utype = Integer.parseInt(type);      
         DAOUsers DU = new DAOUsers();
          ArrayList<Users> list = DU.searchBynameandtype(name,utype);
          request.setAttribute("list", list);
          request.getRequestDispatcher("admin.jsp").forward(request, response);
        }  
         if(name=="" && type != "User's type") { 
             
                int roleid = Integer.parseInt(type);             
                DAOUsers DU = new DAOUsers();
                ArrayList<Users> list = DU.searchBytype(roleid);
                request.setAttribute("list", list);
                request.getRequestDispatcher("admin.jsp").forward(request, response);
             
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
