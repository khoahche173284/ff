/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.grammar;

import entity.Essay;
import entity.History;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOEssay;
import model.DAOLoginHistory;

/**
 *
 * @author congkhoa2723
 */
public class text extends HttpServlet {

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
            out.println("<title>Servlet text</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet text at " + request.getContextPath() + "</h1>");
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
        session.setAttribute("inputContent", null);
        request.getRequestDispatcher("text.jsp").forward(request, response);

        

    }
//     ${contentE}

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

    if (user == null) {
        response.sendRedirect("login");
        return;
    }

    // Lấy dữ liệu từ request
    int categoryId = Integer.parseInt(request.getParameter("category"));
    String content = request.getParameter("content");

    // Lưu dữ liệu vào session để hiển thị lại trong form
//    session.setAttribute("category", categoryId);
//    session.setAttribute("inputContent", content);
    if (categoryId == 0) {
        session.setAttribute("inputContent", content);
        request.setAttribute("nothing", "Please select a category.");
        request.getRequestDispatcher("text.jsp").forward(request, response);
        
    } else{  DAOEssay essayDAO = new DAOEssay();

    if (content != null && !content.isEmpty()) {
       
//        Essay existingEssay = essayDAO.getEssayByContent(user.getId(), categoryId, content);
//        if (existingEssay != null) {
//            
//            existingEssay.setContent(content);
//            essayDAO.updateEssay(existingEssay);
//            request.setAttribute("succ", "Your post has been updated");
//        } else {
            // neu không tồn tại, tạo bài luận mới
            Essay newEssay = essayDAO.createEssay(user.getId(), categoryId, content);
            if (newEssay != null) {
                 essayDAO.moveEssayToHis(user.getId(), newEssay.getId());
          
                    session.setAttribute("inputContent", null);
                     
                    request.setAttribute("succ", "Your post has been saved");
                DAOLoginHistory loginHistoryDAO =  new DAOLoginHistory();

        loginHistoryDAO.insertRecentActivity(user.getId(),11); 
            }
        
    } else {
        session.setAttribute("inputContent", null);
        request.setAttribute("nothing", "Please enter something...");
    }

    request.getRequestDispatcher("text.jsp").forward(request, response);
}}


    
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
