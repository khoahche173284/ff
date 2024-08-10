package controller;

import entity.Users;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.http.HttpSession;
import model.DAOLoginHistory;
import model.DAOUsers;

@MultipartConfig
public class ChangeAvatar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String type = request.getParameter("message");
        if ("succ".equals(type)) {
            session.removeAttribute("succ");
        } else if ("err".equals(type)) {
            session.removeAttribute("err");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("acc");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String imageUrl = null;
        Part filePart = request.getPart("avatarFile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        if (filePart != null && filePart.getSize() > 0) {
            String contentType = filePart.getContentType();
            if (!contentType.startsWith("image/")) {
                session.setAttribute("message", "Only image files are allowed");
                response.sendRedirect("account.jsp");
                return;
            }

            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
            filePart.write(uploadPath + File.separator + uniqueFileName);
            imageUrl = "uploads/" + uniqueFileName;
        } else {
            imageUrl = request.getParameter("avatarLink");
            if (imageUrl != null && !imageUrl.isEmpty() && !imageUrl.startsWith("http")) {
                session.setAttribute("message", "Invalid URL");
                response.sendRedirect("account.jsp");
                return;
            }
        }

        if (imageUrl != null && !imageUrl.isEmpty()) {
            DAOUsers userDAO = new DAOUsers();
            user.setImg(imageUrl);
            try {

                userDAO.updateAvatar(user);

                DAOLoginHistory loginHistoryDAO = new DAOLoginHistory();
                loginHistoryDAO.insertRecentActivity(user.getId(), 7);
                session.setAttribute("acc", user);
                session.setAttribute("message", "Avatar updated successfully");
            } catch (Exception e) {
                session.setAttribute("message", "Failed to update avatar");
                e.printStackTrace();
            }
        } else {
            session.setAttribute("message", "No image provided");
        }

        response.sendRedirect("account.jsp");
    }

    @Override
    public String getServletInfo() {
        return "";
    }
}
