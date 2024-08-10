package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.charset.MalformedInputException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uploadedFilePath = null;
        String fileContent = null;
        String errorMessage = null;
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            fileName = new File(fileName).getName();
            File uploadFile = new File(this.getFolderUpload().getAbsolutePath() + File.separator + fileName);
            part.write(uploadFile.getAbsolutePath());
            uploadedFilePath = uploadFile.getAbsolutePath();

            if (isTextFile(uploadFile)) {
                try {
                    fileContent = readFileWithEncoding(uploadedFilePath, StandardCharsets.UTF_8);
                } catch (MalformedInputException e) {
                    fileContent = readFileWithEncoding(uploadedFilePath, StandardCharsets.ISO_8859_1);
                }
            } else {
                errorMessage = "The uploaded file is not a text file.";
                break;
            }
        }
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Upload File Success!");
            request.setAttribute("filePath", uploadedFilePath);
            request.setAttribute("fileContent", fileContent);
            getServletContext().getRequestDispatcher("/text.jsp").forward(request, response);
        }
    }

    

    private String readFileWithEncoding(String path, Charset charset) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path), charset)) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
    private boolean isTextFile(File file) throws IOException {
        String mimeType = Files.probeContentType(file.toPath());
        return mimeType != null && mimeType.startsWith("text");
    }
}
