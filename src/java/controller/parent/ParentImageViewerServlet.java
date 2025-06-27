package controller.parent;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;

@WebServlet("/parent/viewImages")
public class ParentImageViewerServlet extends HttpServlet {

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

        int totalItems = allImages.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        currentPage = Math.max(1, Math.min(currentPage, totalPages));

        int fromIndex = (currentPage - 1) * itemsPerPage;
        int toIndex = Math.min(fromIndex + itemsPerPage, totalItems);
        List<String> imagesOnPage = allImages.subList(fromIndex, toIndex);

        Map<String, List<String>> imageMap = new LinkedHashMap<>();
        imageMap.put(selectedDate != null ? selectedDate : "Tất cả", imagesOnPage);

        request.setAttribute("imageMap", imageMap);
        request.setAttribute("selectedDate", selectedDate);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/parent/parentViewImages.jsp").forward(request, response);

    }
}
