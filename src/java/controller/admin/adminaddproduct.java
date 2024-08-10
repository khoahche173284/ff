package controller.admin;

import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOPremium_Package;

public class adminaddproduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String priceStr = request.getParameter("price");
        String description = request.getParameter("description");
        String duration = request.getParameter("duration");

        if (!productName.matches("[a-zA-Z\\s]+")) {
            request.setAttribute("message", "Product name must contain only letters.");
            request.setAttribute("messageType", "error");
            request.setAttribute("productName", productName);
            request.setAttribute("price", priceStr);
            request.setAttribute("description", description);
            request.setAttribute("duration", duration);
            request.getRequestDispatcher("adminaddproduct.jsp").forward(request, response);
            return;
        }

        try {
            Double.parseDouble(priceStr); // Kiểm tra định dạng của giá
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid price format.");
            request.setAttribute("messageType", "error");
            request.setAttribute("productName", productName);
            request.setAttribute("price", priceStr);
            request.setAttribute("description", description);
            request.setAttribute("duration", duration);
            request.getRequestDispatcher("adminaddproduct.jsp").forward(request, response);
            return;
        }

        DAOPremium_Package daoProduct = new DAOPremium_Package();

        int productCount = daoProduct.getProductCount();
        if (productCount > 4) {
            request.setAttribute("message", "Cannot add more products. The limit is 5 products.");
            request.setAttribute("messageType", "error");
            request.setAttribute("productName", productName);
            request.setAttribute("price", priceStr);
            request.setAttribute("description", description);
            request.setAttribute("duration", duration);
            request.getRequestDispatcher("adminaddproduct.jsp").forward(request, response);
            return;
        }

        if (daoProduct.isProductNameExist(productName)) {
            request.setAttribute("message", "Product name already exists.");
            request.setAttribute("messageType", "error");
            request.setAttribute("productName", productName);
            request.setAttribute("price", priceStr);
            request.setAttribute("description", description);
            request.setAttribute("duration", duration);
            request.getRequestDispatcher("adminaddproduct.jsp").forward(request, response);
            return;
        }

        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(priceStr); // Đặt giá trị price dưới dạng String
        product.setDescription(description);
        product.setDuration(duration);

        int result = daoProduct.addProduct(product);

        if (result > 0) {
            request.setAttribute("message", "Product added successfully!");
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("message", "There was an error adding the product.");
            request.setAttribute("messageType", "error");
        }

        request.setAttribute("price", priceStr); // Đặt lại thuộc tính priceStr

        request.getRequestDispatcher("adminaddproduct.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
