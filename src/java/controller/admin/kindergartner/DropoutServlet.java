package controller.admin.kindergartner;

import dal.StudyRecordDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DropoutServlet", urlPatterns = {"/dropout"})
public class DropoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int kinderId = Integer.parseInt(request.getParameter("kinder_id"));
            int studyYear = Integer.parseInt(request.getParameter("study_year"));

            StudyRecordDAO dao = new StudyRecordDAO();
            dao.markDroppedOut(kinderId, studyYear);

            // giữ bộ lọc nếu có
            String classId = request.getParameter("classId");
            String keyword = request.getParameter("keyword");

            String redirectURL = request.getContextPath() + "/viewKinderList";
            if (classId != null || keyword != null) {
                redirectURL += "?";
                if (classId != null && !classId.isEmpty()) {
                    redirectURL += "classId=" + classId + "&";
                }
                if (keyword != null && !keyword.isEmpty()) {
                    redirectURL += "keyword=" + keyword;
                }
                // loại bỏ dấu & cuối nếu có
                if (redirectURL.endsWith("&")) {
                    redirectURL = redirectURL.substring(0, redirectURL.length() - 1);
                }
            }

            response.sendRedirect(redirectURL);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Lỗi khi xử lý thôi học.");
        }
    }
}
