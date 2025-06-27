package controller.admin.leaverequests; // Đảm bảo đúng package của bạn

import dal.LeaveRequestDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LeaveRequest;

import java.io.IOException;

@WebServlet(name = "LeaveRequestDetailServlet", urlPatterns = {"/leaverequestdetail"})
public class LeaveRequestDetailServlet extends HttpServlet {

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
        String requestIdStr = request.getParameter("id"); // Lấy ID từ URL

        if (requestIdStr == null || requestIdStr.isEmpty()) {
            request.setAttribute("message", "Không tìm thấy ID yêu cầu.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("/admin/leaverequests/adminLeaveRequests.jsp").forward(request, response);
            return;
        }

        int requestId;
        try {
            requestId = Integer.parseInt(requestIdStr);
        } catch (NumberFormatException e) {
            // Nếu ID không phải số, hiển thị lỗi
            request.setAttribute("message", "ID yêu cầu không hợp lệ.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("/admin/leaverequests/adminLeaveRequests.jsp").forward(request, response);
            return;
        }

        LeaveRequest leaveRequest = leaveRequestDAO.getLeaveRequestById(requestId);

        if (leaveRequest == null) {
            // Nếu không tìm thấy đơn, hiển thị lỗi
            request.setAttribute("message", "Không tìm thấy đơn xin nghỉ phép với ID: " + requestId);
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("/admin/leaverequests/adminLeaveRequests.jsp").forward(request, response);
        } else {
            // Nếu tìm thấy, đặt đối tượng vào request và chuyển tiếp đến trang JSP chi tiết
            request.setAttribute("leaveRequest", leaveRequest);
            request.getRequestDispatcher("/admin/leaverequests/leaverequestdetail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Có thể thêm logic xử lý POST ở đây nếu bạn muốn Duyệt/Từ chối từ trang chi tiết
        // Hoặc có thể giữ nguyên logic duyệt/từ chối ở AdminLeaveRequestServlet và chuyển hướng về trang đó sau khi xem
        // Tùy thuộc vào luồng công việc bạn muốn
        
        // Ví dụ: Nếu bạn muốn xử lý Duyệt/Từ chối ở đây:
        String action = request.getParameter("action");
        String requestIdStr = request.getParameter("requestId");

        if (requestIdStr == null || requestIdStr.isEmpty()) {
            request.setAttribute("message", "Thiếu ID yêu cầu.");
            request.setAttribute("messageType", "error");
            doGet(request, response); // Quay lại trang chi tiết với thông báo lỗi
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
        } else if (!action.isEmpty()) {
            request.setAttribute("message", errorMessage);
            request.setAttribute("messageType", "error");
        }
        
        // Sau khi xử lý, load lại thông tin chi tiết của đơn để hiển thị trạng thái cập nhật
        doGet(request, response);
    }
}