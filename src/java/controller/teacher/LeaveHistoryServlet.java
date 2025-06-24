package controller.teacher;

import java.io.IOException;
import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LeaveRequest;

@WebServlet(name = "LeaveHistoryServlet", urlPatterns = {"/leavehistory"})
public class LeaveHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Trong thực tế, bạn sẽ lấy dữ liệu lịch sử yêu cầu nghỉ phép
        // từ cơ sở dữ liệu, có thể dựa trên ID giáo viên đang đăng nhập.
        List<LeaveRequest> leaveRequests = new ArrayList<>();

        // Ví dụ dữ liệu giả lập cho lịch sử
        leaveRequests.add(new LeaveRequest(1, "Nguyễn Văn A", LocalDate.of(2025, 7, 1), LocalDate.of(2025, 7, 5),
                                           "Nghỉ ốm nặng", "sick", "Pending", LocalDate.of(2025, 6, 20), 5));
        leaveRequests.add(new LeaveRequest(2, "Nguyễn Văn A", LocalDate.of(2025, 7, 10), LocalDate.of(2025, 7, 10),
                                           "Đi công tác gấp", "personal", "Approved", LocalDate.of(2025, 6, 22), 1));
        leaveRequests.add(new LeaveRequest(3, "Nguyễn Văn A", LocalDate.of(2025, 8, 1), LocalDate.of(2025, 8, 3),
                                           "Nghỉ phép năm còn lại", "annual", "Rejected", LocalDate.of(2025, 6, 15), 3));


        request.setAttribute("leaveRequests", leaveRequests);
        request.getRequestDispatcher("teacher/leaveHistory.jsp").forward(request, response);
    }
}