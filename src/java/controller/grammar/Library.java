/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.grammar;

import entity.Users;
import entity.library;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.DAOLibrary;

/**
 *
 * @author pgb31
 */
public class Library extends HttpServlet {

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
            out.println("<title>Servlet Library</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Library at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");
        int uid = user.getId();

        DAOLibrary DL = new DAOLibrary();
        ArrayList<library> list = DL.listbyid(uid);

        request.setAttribute("list", list);
        request.getRequestDispatcher("library.jsp").forward(request, response);
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
        Users user = (Users) session.getAttribute("acc");
        int uid = user.getId();
        DAOLibrary DL = new DAOLibrary();
        ArrayList<library> list = DL.listbyid(uid);
        int c = 0; 
        
        if(user.getRoleId()==2 && list.size()==8) {
           request.setAttribute("er", "You have reached the personal library word limit");
                    request.setAttribute("list", list);
                        request.getRequestDispatcher("library.jsp").forward(request, response);  
            
        } 
        if(user.getRoleId()==3 && list.size()==15) {
          request.setAttribute("er", "You have reached the personal library word limit");
                    request.setAttribute("list", list);
                        request.getRequestDispatcher("library.jsp").forward(request, response);    
            
        }
        
        String word = request.getParameter("key");
        String defi = request.getParameter("define"); 
        
        if(word.equals("") || defi.equals("")){ 
                        request.setAttribute("er", "Please enter word and difinition");
                    request.setAttribute("list", list);
                        request.getRequestDispatcher("library.jsp").forward(request, response);

        } 
        
        
        
        for (library li : list) {
            if (li.getKeyword().equalsIgnoreCase(word)) {
                c++;
            }

        }
        if (c == 0) {
            DL.addtolib(word, defi, uid);
            list = DL.listbyid(uid);
            request.setAttribute("list", list);
            request.getRequestDispatcher("library.jsp").forward(request, response);
        } else {
            request.setAttribute("er", "The keyword you want to add to the library already exists");
          
            request.setAttribute("list", list);
            request.getRequestDispatcher("library.jsp").forward(request, response);

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
