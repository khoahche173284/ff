package authen;

import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.DAOUsers;

public class verifyOtp extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String otp = (String) session.getAttribute("otp");
        String enteredOtp = request.getParameter("otp");

        if (otp.equals(enteredOtp)) {
            Users user = (Users) session.getAttribute("tempUser");
            DAOUsers DU = new DAOUsers();
            DU.insert(user.getUsername(), user.getRealname(), user.getEmail(), user.getPassword());
            session.removeAttribute("otp");
            session.removeAttribute("tempUser");
            request.setAttribute("successMessage", "Signup is successfully! Please login.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("er", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("enterOtp.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}