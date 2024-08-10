package controller.admin;

import com.google.gson.Gson;
import entity.AccessLogs;
import model.DAOAccessLogs;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DashBoardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOAccessLogs daoAccessLogs = new DAOAccessLogs();

        // Retrieve user information from the session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // If the user is logged in, log the access information
        if (userId != null) {
            String ipAddress = request.getRemoteAddr();
            String userAgent = request.getHeader("User-Agent");
            AccessLogs accessLog = new AccessLogs();
            accessLog.setUserId(userId);
            accessLog.setIpAddress(ipAddress);
            accessLog.setUserAgent(userAgent);
            accessLog.setLoginTime(new java.util.Date()); // set current time

            daoAccessLogs.addAccessLog(accessLog);
        }

        // Get access logs for display
        List<AccessLogs> accessLogsList = daoAccessLogs.getAllAccessLogs();

        // Get access count per user
        Map<Integer, Integer> accessCountPerUser = daoAccessLogs.getAccessCountPerUser();

        // Get access count per day
        Map<String, Integer> accessCountPerDay = daoAccessLogs.getAccessCountPerDay();

        // Convert data to JSON
        Gson gson = new Gson();
        String accessCountPerUserJson = gson.toJson(accessCountPerUser);
        String accessCountPerDayJson = gson.toJson(accessCountPerDay);

        // Set attributes to pass to JSP
        request.setAttribute("accessLogsList", accessLogsList);
        request.setAttribute("accessCountPerUserJson", accessCountPerUserJson);
        request.setAttribute("accessCountPerDayJson", accessCountPerDayJson);

        // Forward to JSP
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}