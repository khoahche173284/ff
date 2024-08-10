package authen;

import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.DAOUsers;

public class signup extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet signup</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet signup at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String name = request.getParameter("user");
    String pass = request.getParameter("pass");
    String realname = request.getParameter("realname");
    String repass = request.getParameter("repass");
    String email = request.getParameter("email");

    // Validate input
    if ( name.equals("") || pass.equals("") ||
         realname.equals("") || repass.equals("") || email.equals("")) {
        request.setAttribute("er", "Please input full information");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
        return;
    }

    if (!pass.equals(repass)) {
        request.setAttribute("er", "Passwords do not match. Please confirm password again.");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
        return;
    }

    // Email validation regex pattern
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pat = Pattern.compile(emailRegex);
    if (!pat.matcher(email).matches()) {
        request.setAttribute("er", "Invalid email format.");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
        return;
    }

    // Check username and email existence
    DAOUsers DU = new DAOUsers();
    ArrayList<Users> list = DU.listAllUsers();
    boolean usernameExists = false;
    boolean emailExists = false;

    for (Users c : list) {
        if (c.getUsername().equals(name)) {
            usernameExists = true;
        }
        if (c.getEmail().equals(email)) {
            emailExists = true;
        }
    }

    if (usernameExists && !emailExists) {
        request.setAttribute("er", "Username already exists.");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
        return;
    }

    if (emailExists && !usernameExists) {
        request.setAttribute("er", "Email already exists.");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
        return;
    }

    if (usernameExists && emailExists) {
        request.setAttribute("er", "Both username and email already exist.");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
        return;
    }

    // If validation passes
    if (!usernameExists && !emailExists) {
        String otp = DU.generateOTP();
        HttpSession session = request.getSession();
        session.setAttribute("otp", otp);
        session.setAttribute("tempUser", new Users(0, name, realname, email, pass, "", null, 2));
        DU.sendVerificationEmail(email, otp);
        request.getRequestDispatcher("SignupOTP.jsp").forward(request, response);
    }
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}