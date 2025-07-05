package controller.teacher.parentmeeting;

import dal.ParentMeetingDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import model.Account;
import model.ParentMeeting;

@WebServlet("/teacher/parentmeetings")
public class ParentMeetingListServlet extends HttpServlet {

      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc == null || !"Teacher".equalsIgnoreCase(acc.getRole().getRoleName())) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String search = request.getParameter("search");
        String status = request.getParameter("status");

        int page = 1;
        int pageSize = 5;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception ignored) {}

        ParentMeetingDAO dao = new ParentMeetingDAO();
        int totalItems = dao.countMeetingsForTeacher(acc.getAccountID(), search, status);
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        if (page < 1) page = 1;
        if (page > totalPages && totalPages > 0) page = totalPages;

        int offset = (page - 1) * pageSize;

        List<ParentMeeting> meetingList = dao.searchMeetingsForTeacherPaging(
                acc.getAccountID(), search, status, offset, pageSize
        );

        request.setAttribute("meetingList", meetingList);
        request.setAttribute("search", search);
        request.setAttribute("status", status);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/teacher/ParentMeeting/parentMeetings.jsp").forward(request, response);
    }
}
