package controller.teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;

@WebServlet("/teacher/viewImages")
public class ImageManagerServlet extends HttpServlet {

    private static final String UPLOAD_FOLDER = "uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String selectedDate = request.getParameter("date");
        String pageParam = request.getParameter("page");
        int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
        int itemsPerPage = 8;

        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_FOLDER;
        File baseDir = new File(uploadPath);
        List<String> allImages = new ArrayList<>();

        if (baseDir.exists() && baseDir.isDirectory()) {
            File[] dateFolders = baseDir.listFiles(File::isDirectory);

            if (dateFolders != null) {
                for (File dateDir : dateFolders) {
                    String folderName = dateDir.getName();
                    if (selectedDate == null || selectedDate.equals(folderName)) {
                        File[] images = dateDir.listFiles(file -> {
                            String name = file.getName().toLowerCase();
                            return name.endsWith(".jpg") || name.endsWith(".png")
                                    || name.endsWith(".jpeg") || name.endsWith(".gif");
                        });

                        if (images != null) {
                            for (File img : images) {
                                String relativePath = UPLOAD_FOLDER + "/" + folderName + "/" + img.getName();
                                allImages.add(relativePath);
                            }
                        }
                    }
                }
            }
        }

        // Phân trang
        int totalItems = allImages.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        currentPage = Math.max(1, Math.min(currentPage, totalPages)); // Giữ trong khoảng

        int fromIndex = (currentPage - 1) * itemsPerPage;
        int toIndex = Math.min(fromIndex + itemsPerPage, totalItems);
        List<String> imagesOnPage = allImages.subList(fromIndex, toIndex);

        Map<String, List<String>> imageMap = new LinkedHashMap<>();
        imageMap.put(selectedDate != null ? selectedDate : "Tất cả", imagesOnPage);

        request.setAttribute("imageMap", imageMap);
        request.setAttribute("selectedDate", selectedDate);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/teacher/viewImages.jsp").forward(request, response);

        request.setAttribute("imageMap", imageMap);
        request.setAttribute("selectedDate", selectedDate); // truyền lại để giữ nguyên input
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathToDelete = request.getParameter("path");

        if (pathToDelete != null && !pathToDelete.isEmpty()) {
            String fullPath = getServletContext().getRealPath("/") + File.separator + pathToDelete;
            File file = new File(fullPath);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        }

        String redirectDate = request.getParameter("date");
        String redirectPage = request.getParameter("page");
        response.sendRedirect(request.getContextPath() + "/teacher/viewImages"
                + (redirectDate != null ? "?date=" + redirectDate : "")
                + (redirectPage != null ? "&page=" + redirectPage : ""));
    }
}
