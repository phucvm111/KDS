package controller.admin.leaverequests; // Hoặc package servlet của bạn


import dal.LeaveRequestDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LeaveRequest;
import model.Account; // Import lớp Account để kiểm tra vai trò

import java.io.IOException;
import java.util.List;
import model.Role;

@WebServlet(name = "AdminLeaveRequestServlet", urlPatterns = {"/adminleaverequests"})
public class AdminLeaveRequestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private LeaveRequestDAO leaveRequestDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        leaveRequestDAO = new LeaveRequestDAO();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<LeaveRequest> leaveRequests = leaveRequestDAO.getAllLeaveRequests();
        request.setAttribute("leaveRequests", leaveRequests);
        request.getRequestDispatcher("admin/leaverequests/adminLeaveRequests.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String requestIdStr = request.getParameter("requestId");

        if (requestIdStr == null || requestIdStr.isEmpty()) {
            request.setAttribute("message", "Thiếu ID yêu cầu.");
            request.setAttribute("messageType", "error");
            doGet(request, response); // Quay lại hiển thị danh sách với thông báo lỗi
            return;
        }

        int requestId;
        try {
            requestId = Integer.parseInt(requestIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "ID yêu cầu không hợp lệ.");
            request.setAttribute("messageType", "error");
            doGet(request, response);
            return;
        }

        boolean success = false;
        String newStatus = "";
        String successMessage = "";
        String errorMessage = "";

        if ("approve".equalsIgnoreCase(action)) {
            newStatus = "Approved";
            success = leaveRequestDAO.updateLeaveRequestStatus(requestId, newStatus);
            successMessage = "Đơn đã được duyệt thành công!";
            errorMessage = "Duyệt đơn thất bại.";
        } else if ("reject".equalsIgnoreCase(action)) {
            newStatus = "Rejected";
            success = leaveRequestDAO.updateLeaveRequestStatus(requestId, newStatus);
            successMessage = "Đơn đã được từ chối thành công!";
            errorMessage = "Từ chối đơn thất bại.";
        } else {
            request.setAttribute("message", "Hành động không hợp lệ.");
            request.setAttribute("messageType", "error");
        }

        if (success) {
            request.setAttribute("message", successMessage);
            request.setAttribute("messageType", "success");
        } else if (!action.isEmpty()) { // Chỉ đặt lỗi nếu action không rỗng và không thành công
            request.setAttribute("message", errorMessage);
            request.setAttribute("messageType", "error");
        }

        // Sau khi xử lý, load lại danh sách để hiển thị trạng thái cập nhật
        doGet(request, response);
    }
}