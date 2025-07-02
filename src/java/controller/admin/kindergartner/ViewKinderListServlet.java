package controller.admin.kindergartner;

import dal.ClassDAO;
import dal.StudyRecordDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.StudyRecord;
import model.Class;

@WebServlet(name = "ViewKinderListServlet", urlPatterns = {"/viewKinderList"})
public class ViewKinderListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = Optional.ofNullable(request.getParameter("keyword")).orElse("").trim().toLowerCase();
        String status = Optional.ofNullable(request.getParameter("status")).orElse("").trim();

        int classId = -1;
        try {
            String classIdRaw = Optional.ofNullable(request.getParameter("classId")).orElse("-1").trim();
            classId = Integer.parseInt(classIdRaw);
        } catch (NumberFormatException e) {
            classId = -1;
        }

        int page = 1;
        int size = 10;
        try {
            String pageRaw = Optional.ofNullable(request.getParameter("page")).orElse("1").trim();
            page = Integer.parseInt(pageRaw);
            if (page < 1) {
                page = 1;
            }
        } catch (NumberFormatException e) {
            page = 1;
        }

        StudyRecordDAO studyDAO = new StudyRecordDAO();
        ClassDAO classDAO = new ClassDAO();

        List<Class> classList = classDAO.getAllClass();
        List<StudyRecord> allRecords = studyDAO.getAllStudyRecord();
        List<StudyRecord> filtered = new ArrayList<>();

        for (StudyRecord sr : allRecords) {
            boolean matchStatus = switch (status) {
                case "studying" ->
                    !sr.isGraduated() && !sr.isDroppedOut();
                case "graduated" ->
                    sr.isGraduated();
                case "dropped" ->
                    sr.isDroppedOut();
                default ->
                    true;
            };
            boolean matchClass = classId == -1 || sr.getClassID().getClass_id() == classId;
            boolean matchName = keyword.isEmpty() || sr.getKinder().getFirst_name().toLowerCase().contains(keyword);

            if (matchStatus && matchClass && matchName) {
                filtered.add(sr);
            }
        }

        int total = filtered.size();
        int totalPages = (int) Math.ceil((double) total / size);
        int start = (page - 1) * size;
        int end = Math.min(start + size, total);

        List<StudyRecord> pagedList = filtered.subList(start, end);

        request.setAttribute("classList", classList);
        request.setAttribute("studentList", pagedList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("classId", classId);
        request.setAttribute("keyword", keyword);
        request.setAttribute("status", status);

        request.getRequestDispatcher("/admin/kinder/kinder_view.jsp").forward(request, response);
    }

}
