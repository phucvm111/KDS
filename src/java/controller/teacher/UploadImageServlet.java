package controller.teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/teacher/uploadImage")
@MultipartConfig
public class UploadImageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("imageFile");
        String fileName = new File(filePart.getSubmittedFileName()).getName();

        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String uploadPath = getServletContext().getRealPath("/uploads/") + File.separator + dateFolder;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        filePart.write(uploadPath + File.separator + fileName);
        response.sendRedirect("viewImages");
    }
}
