package controller.admin;

import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOPremium_Package;

public class adminupdateproduct extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productIdStr = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String priceStr = request.getParameter("price");
        String description = request.getParameter("description");
        String duration = request.getParameter("duration");

        // Lấy thông tin sản phẩm ban đầu từ cơ sở dữ liệu
        DAOPremium_Package daoProduct = new DAOPremium_Package();
        int productId = Integer.parseInt(productIdStr);
        Product originalProduct = daoProduct.getProductById(productId);

        if (originalProduct == null) {
            request.setAttribute("message", "Product not found.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("adminproduct.jsp").forward(request, response);
            return;
        }

        boolean hasError = false;
        String errorMessage = "";

        if (productName == null || productName.trim().isEmpty()) {
            errorMessage += "Product name is required. ";
            hasError = true;
        } else if (!productName.matches(".*[a-zA-Z]+.*")) {
            errorMessage += "Product name must contain only letters. ";
            hasError = true;
        } else if (!productName.equals(originalProduct.getProductName()) && daoProduct.isProductNameExist(productName)) {
            errorMessage += "Product name already exists. ";
            hasError = true;
        }

        if (priceStr == null || priceStr.trim().isEmpty()) {
            errorMessage += "Price is required. ";
            hasError = true;
        } else {
            try {
                Double.parseDouble(priceStr); // Kiểm tra định dạng của giá
            } catch (NumberFormatException e) {
                errorMessage += "Invalid price format. ";
                hasError = true;
            }
        }

        if (description == null || description.trim().isEmpty()) {
            errorMessage += "Description is required. ";
            hasError = true;
        }

        if (hasError) {
            request.setAttribute("message", errorMessage);
            request.setAttribute("messageType", "error");
            request.setAttribute("product", originalProduct);
            request.getRequestDispatcher("adminupdateproduct.jsp").forward(request, response);
            return;
        }

        // Tạo đối tượng Product với thông tin mới
        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setProductName(productName);
        updatedProduct.setPrice(priceStr); // Đặt giá trị price dưới dạng String
        updatedProduct.setDescription(description);
        updatedProduct.setDuration(duration);

        // Cập nhật sản phẩm trong cơ sở dữ liệu
        int result = daoProduct.updateProduct(updatedProduct);

        if (result > 0) {
            request.setAttribute("message", "Product updated successfully!");
            request.setAttribute("messageType", "success");
            request.setAttribute("product", updatedProduct);
        } else {
            request.setAttribute("message", "There was an error updating the product.");
            request.setAttribute("messageType", "error");
            request.setAttribute("product", originalProduct);
        }

        // Chuyển tiếp đến trang JSP
        request.getRequestDispatcher("adminupdateproduct.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("pid"));

        DAOPremium_Package daoProduct = new DAOPremium_Package();
        Product product = daoProduct.getProductById(productId);

        if (product != null) {
            request.setAttribute("product", product);
            request.getRequestDispatcher("adminupdateproduct.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Product not found.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("adminproduct.jsp").forward(request, response);
        }
    }
}