/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.grammar;

import entity.Essay;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import model.DAOEssay;

/**
 *
 * @author congkhoa2723
 */
public class updatetext extends HttpServlet {

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
            out.println("<title>Servlet updatetext</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updatetext at " + request.getContextPath() + "</h1>");
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
       int essayId = Integer.parseInt(request.getParameter("essayId"));
       DAOEssay essayDAO = new DAOEssay();
       Essay newEssay =  essayDAO.getEssayById(essayId);
       request.setAttribute("contentE", newEssay.getContent());
       request.setAttribute("CategoryE", newEssay.getCategoriesId());
       request.setAttribute("essayId", newEssay.getId());
       request.getRequestDispatcher("edittext.jsp").forward(request, response);
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
    int essayId = Integer.parseInt(request.getParameter("essayId"));
    
    DAOEssay essayDAO = new DAOEssay();
       Essay newEssay =  essayDAO.getEssayById(essayId);
       request.setAttribute("contentE", newEssay.getContent());
       request.setAttribute("CategoryE", newEssay.getCategoriesId());
       request.setAttribute("essayId", newEssay.getId());

    if (user == null) {
        response.sendRedirect("login");
        return;
    }
       String content = request.getParameter("content");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        
        
        if (categoryId == 0) {
            session.setAttribute("inputContent", content);
            request.setAttribute("error", "Please select a valid category.");
            request.getRequestDispatcher("edittext.jsp").forward(request, response);
            return;
        }

       
        essayDAO.updateEssay( essayId,user.getId(),categoryId,content);
        
        session.setAttribute("succ", "Your post has been updated.");
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(() -> {
                session.removeAttribute("succ");
            }, 1, TimeUnit.SECONDS);
       response.sendRedirect("history");
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
