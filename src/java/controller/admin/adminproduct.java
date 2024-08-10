package controller.admin;

import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.DAOPremium_Package;

/**
 *
 * @author admin
 */
public class adminproduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOPremium_Package DP = new DAOPremium_Package();

        String searchKeyword = request.getParameter("searchKeyword");
        String sortType = request.getParameter("sortType");

        String sql = "SELECT * FROM Premium_Package";

        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            sql += " WHERE productName LIKE '%" + searchKeyword + "%'";
        }

        if (sortType != null && !sortType.trim().isEmpty()) {
            if (sortType.equals("asc")) {
                sql += " ORDER BY price ASC";
            } else if (sortType.equals("desc")) {
                sql += " ORDER BY price DESC";
            }
        }

        ArrayList<Product> list = DP.getAll(sql);

        request.setAttribute("list", list);
        request.setAttribute("searchKeyword", searchKeyword);
        request.setAttribute("sortType", sortType);

        request.getRequestDispatcher("adminproduct.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}