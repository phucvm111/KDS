package controller.teacher.parentmeeting;

import dal.ClassDAO;
import dal.ParentMeetingDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import model.Account;
import model.Class;
import model.ParentMeeting;

@WebServlet("/teacher/editparentmeeting")
public class EditParentMeetingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        if (acc == null || !"Teacher".equalsIgnoreCase(acc.getRole().getRoleName())) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        ParentMeeting pm = new ParentMeetingDAO().getMeetingById(id);

        ClassDAO classDao = new ClassDAO();
        List<Class> classes = classDao.getClassesByTeacher(acc.getAccountID());

        request.setAttribute("classes", classes);
        request.setAttribute("meeting", pm);
        request.getRequestDispatcher("/teacher/ParentMeeting/editParentMeeting.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        if (acc == null || !"Teacher".equalsIgnoreCase(acc.getRole().getRoleName())) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String topic = request.getParameter("topic").trim();
            String notes = request.getParameter("notes").trim();
            String meetingDateStr = request.getParameter("meeting_date").trim();
            String status = request.getParameter("status").trim();
            int classId = Integer.parseInt(request.getParameter("class_id"));

            Timestamp meetingDate = Timestamp.valueOf(meetingDateStr.replace("T", " ") + ":00");

            ParentMeeting pm = new ParentMeeting();
            pm.setMeetingId(id);
            pm.setTopic(topic);
            pm.setNotes(notes);
            pm.setClassId(classId);
            pm.setStatus(status);
            pm.setMeetingDate(meetingDate);

            new ParentMeetingDAO().updateMeeting(pm);

            // redirect quay về list thay vì forward
            response.sendRedirect(request.getContextPath() + "/teacher/parentmeetings");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Dữ liệu không hợp lệ");
            request.getRequestDispatcher("/teacher/ParentMeeting/editParentMeeting.jsp").forward(request, response);
        }
    }

}
