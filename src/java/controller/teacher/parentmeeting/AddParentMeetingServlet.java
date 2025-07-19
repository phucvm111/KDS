package controller.teacher.parentmeeting;

import dal.ParentMeetingDAO;
import dal.ClassDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import model.Account;
import model.Class;
import model.ParentMeeting;

@WebServlet("/teacher/addparentmeeting")
public class AddParentMeetingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc == null || !"Teacher".equalsIgnoreCase(acc.getRole().getRoleName())) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        ClassDAO classDAO = new ClassDAO();
        List<Class> classList = classDAO.getClassesByTeacher(acc.getAccountID());

        request.setAttribute("classList", classList);
        request.getRequestDispatcher("/teacher/ParentMeeting/addParentMeeting.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc == null || !"Teacher".equalsIgnoreCase(acc.getRole().getRoleName())) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            int classId = Integer.parseInt(request.getParameter("classId"));
            String topic = request.getParameter("topic") != null ? request.getParameter("topic").trim() : "";
            String notes = request.getParameter("notes") != null ? request.getParameter("notes").trim() : "";
            String meetingDateStr = request.getParameter("meetingDate");

            if (topic.isEmpty()) {
                throw new Exception("Chủ đề không được để trống");
            }

            // convert datetime-local format
            Timestamp meetingDate = Timestamp.valueOf(meetingDateStr.replace("T", " ") + ":00");

            // validate ngày họp phải ở tương lai
            LocalDateTime now = LocalDateTime.now();
            if (meetingDate.toLocalDateTime().isBefore(now)) {
                throw new Exception("Ngày giờ họp phải trong tương lai.");
            }

            ParentMeeting pm = new ParentMeeting();
            pm.setClassId(classId);
            pm.setTeacherId(acc.getAccountID());
            pm.setTopic(topic);
            pm.setNotes(notes);
            pm.setMeetingDate(meetingDate);
            pm.setStatus("Scheduled");

            new ParentMeetingDAO().addMeeting(pm);
            response.sendRedirect(request.getContextPath() + "/teacher/parentmeetings");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            ClassDAO classDAO = new ClassDAO();
            List<Class> classList = classDAO.getClassesByTeacher(acc.getAccountID());
            request.setAttribute("classList", classList);
            request.getRequestDispatcher("/teacher/ParentMeeting/addParentMeeting.jsp").forward(request, response);
        }
    }
}
