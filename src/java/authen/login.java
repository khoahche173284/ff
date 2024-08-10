/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package authen;

import entity.AccessLogs;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import model.DAOAccessLogs;
import model.DAOLoginHistory;
import model.DAOUsers;

/**
 *
 * @author Quang
 */
public class login extends HttpServlet {

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
        
        DAOUsers DU = new DAOUsers();
        List<Users> lockedUsers = DU.listAllLockedUsers();
        for (Users user : lockedUsers) {
            DU.unlockUserIfTimeElapsed(user.getId());
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("rememberMe")) {
                    String token = cookie.getValue();
                    Users user = DU.getUserByRememberMeToken(token);
                    if (user != null) {
                        HttpSession session = request.getSession();
                        session.setMaxInactiveInterval(10 * 60);
                        session.setAttribute("acc", user);
                        if (user.getRoleId() == 2 || user.getRoleId() == 3) {
                            response.sendRedirect("home.jsp");
                        } else if (user.getRoleId() == 1) {
                            response.sendRedirect("adminhomejsp.jsp");
                        }
                        return;
                    }
                }
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        username = username.trim();
        password = password.trim();

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            String errLogin = "Username and password must not be empty";
            request.setAttribute("errLogin", errLogin);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        DAOUsers DU = new DAOUsers();
        Users user = DU.getUserByUsername(username);

        if (user != null) {
            DU.unlockUserIfTimeElapsed(user.getId()); // Kiểm tra và mở khóa tài khoản nếu cần thiết

            if (user.isIsLocked()) {
                String errLogin = "Your account is locked due to too many failed login attempts. Please try again after 30 seconds.";
                request.setAttribute("errLogin", errLogin);
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            if (DU.verifyPassword(username, password)) {
                DU.resetFailedLoginAttempts(user.getId());
                HttpSession session = request.getSession();
                session.setAttribute("acc", user);
                Cookie u = new Cookie("userC", username);
                Cookie p = new Cookie("passC", password);
                u.setMaxAge(60 * 60 * 24 * 7);

                if (remember != null) {
                    String token = UUID.randomUUID().toString();
                    DU.saveRememberMeToken(user.getId(), token);
                    Cookie rememberCookie = new Cookie("rememberMe", token);
                    rememberCookie.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(rememberCookie);
                } else {
                    Cookie rememberCookie = new Cookie("rememberMe", "");
                    rememberCookie.setMaxAge(0);
                    response.addCookie(rememberCookie);
                }
                DAOAccessLogs daoAccessLogs = new DAOAccessLogs();
                AccessLogs accessLog = new AccessLogs();
                accessLog.setUserId(user.getId());
                accessLog.setIpAddress(request.getRemoteAddr());
                accessLog.setUserAgent(request.getHeader("User-Agent"));
                accessLog.setLoginTime(new java.util.Date());
                daoAccessLogs.addAccessLog(accessLog);

                response.addCookie(u);
                if (user.getRoleId() == 2 || user.getRoleId() == 3) {
                    response.sendRedirect("home.jsp");
                } else if (user.getRoleId() == 1) {
                    response.sendRedirect("adminhomejsp.jsp");
                }
                DAOLoginHistory loginHistoryDAO = new DAOLoginHistory();
                loginHistoryDAO.insertRecentActivity(user.getId(), 1);
            } else {
                DU.incrementFailedLoginAttempts(user.getId());
                if (user.getFailedLoginAttempts() >= 5) {
                    DU.lockUser(user.getId());
                    String errLogin = "Your account is locked due to too many failed login attempts. Please try again later.";
                    request.setAttribute("errLogin", errLogin);
                } else {
                    String errLogin = "Username or password is incorrect";
                    request.setAttribute("errLogin", errLogin);
                }
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            String errLogin = "Username or password is incorrect";
            request.setAttribute("errLogin", errLogin);
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
