/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validations;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * user will upload file and this stories at UPLOAD_DIRECTORY
 *
 * @author trantoan
 */

// để ở file muốn chooses file
/*
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
*/

public class UploadFile {

    // đường dẫn đến nơi lưu hình ảnh
    private static final String UPLOAD_DIRECTORY = "/Users/trantoan/tranining/shop/web/Images";

    /**
     * Uploads files from a HttpServletRequest and saves them to the server's
     * designated upload directory.
     *
     * @param request The HttpServletRequest containing the files to be
     * uploaded.
     * @param response The HttpServletResponse object.
     * @return A list of file names that have been successfully uploaded.
     * @throws ServletException If a servlet exception occurs.
     * @throws IOException If an I/O exception occurs.
     */
    public List<String> fileUpload(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> uploadedFileNames = new ArrayList<>();
        // Iterate through all parts sent from the form
        for (Part part : request.getParts()) {
            // Get the file name sent
            String fileName = extractFileName(part);
            // Save the file to the upload directory
            part.write(UPLOAD_DIRECTORY + File.separator + fileName);
            // Add the file name to the list
            uploadedFileNames.add(fileName);
        }
        return uploadedFileNames;
    }

    /**
     * Phương thức hỗ trợ để trích xuất tên tập tin từ phần được gửi trong yêu
     * cầu HTTP multipart.
     *
     * @param part Phần chứa thông tin về tập tin được gửi.
     * @return Tên của tập tin được trích xuất từ phần, hoặc chuỗi trống nếu
     * không tìm thấy.
     */
    private String extractFileName(Part part) {
        // Lấy tiêu đề "content-disposition" từ phần
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // Lấy tên tập tin ban đầu
                String originalFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                // Tạo một chuỗi duy nhất (UUID) để thêm vào tên tập tin
                String uniqueID = UUID.randomUUID().toString();
                // Tách phần mở rộng của tập tin (nếu có)
                String fileExtension = "";
                int dotIndex = originalFileName.lastIndexOf('.');
                if (dotIndex != -1) {
                    fileExtension = originalFileName.substring(dotIndex);
                    originalFileName = originalFileName.substring(0, dotIndex);
                }
                // Kết hợp tên tập tin ban đầu với UUID để tạo ra tên tập tin duy nhất
                return originalFileName + "_" + uniqueID + fileExtension;
            }
        }
        return "";
    }
}
