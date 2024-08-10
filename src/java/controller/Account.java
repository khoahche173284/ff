package controller;

import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOLoginHistory;
import model.DAOUsers;

public class Account extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Account</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Account at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        session.setAttribute("message", "");
        if (user == null) {
            response.sendRedirect("login");
        } else {
            DAOUsers userDao = new DAOUsers();
            Users currentUser = userDao.getUserById(user.getId());
            request.setAttribute("acc", currentUser);
            request.getRequestDispatcher("account.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("message", "");

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        DAOUsers userDao = new DAOUsers();
        String newRealname = request.getParameter("realname");
        String newUsername = request.getParameter("username");
        String newEmail = request.getParameter("email");

        if (newUsername == null || newUsername.isEmpty() || newEmail == null || newEmail.isEmpty()) {
            request.setAttribute("error", "Username and email cannot be blank.");
            request.getRequestDispatcher("account.jsp").forward(request, response);
            return;
        }

        if (userDao.isUsernameTaken(newUsername) && !newUsername.equals(user.getUsername())) {
            request.setAttribute("error", "Username is already taken.");
            request.getRequestDispatcher("account.jsp").forward(request, response);
            return;
        }

        if (newUsername.equals(user.getUsername()) && newEmail.equals(user.getEmail()) && newRealname.equals(user.getRealname())) {
            request.setAttribute("error", "Nothing has changed.");
            request.getRequestDispatcher("account.jsp").forward(request, response);
            return;
        }

        // Check if email has changed
        if (!newEmail.equals(user.getEmail())) {
            // Generate OTP and send to user's current email
            String otp = userDao.generateOTP();
            userDao.sendVerificationEmail(user.getEmail(), otp);

            // Save new details and OTP in session
            session.setAttribute("newRealname", newRealname);
            session.setAttribute("newUsername", newUsername);
            session.setAttribute("newEmail", newEmail);
            session.setAttribute("otp", otp);

            // Redirect to OTP verification page
            response.sendRedirect("UpdateEmailOTP.jsp");
        } else {

            user.setRealname(newRealname);
            user.setUsername(newUsername);
            user.setEmail(newEmail);
            userDao.updateUser(user);
            session.setAttribute("acc", user);
            request.setAttribute("success", "Account updated successfully.");
            DAOLoginHistory loginHistoryDAO = new DAOLoginHistory();

            loginHistoryDAO.insertRecentActivity(user.getId(), 16);

            request.getRequestDispatcher("account.jsp").forward(request, response);
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
    }
}
