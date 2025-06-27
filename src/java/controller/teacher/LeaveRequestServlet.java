package controller.teacher;

import dal.LeaveRequestDAO;
import dal.LeaveTypeDAO; 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; 
import model.Account; 
import model.LeaveRequest;
import model.LeaveType; 
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List; 

@WebServlet(name = "LeaveRequestServlet", urlPatterns = {"/leaverequest"})
public class LeaveRequestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private LeaveRequestDAO leaveRequestDAO;
    private LeaveTypeDAO leaveTypeDAO; 

    public void init() {
        leaveRequestDAO = new LeaveRequestDAO();
        leaveTypeDAO = new LeaveTypeDAO(); 
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
       
        
        try {
            List<LeaveType> leaveTypes = leaveTypeDAO.getAllLeaveTypes(); 
            request.setAttribute("leaveTypes", leaveTypes); 
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách loại nghỉ phép: " + e.getMessage());
            request.setAttribute("message", "Không thể tải danh sách loại nghỉ phép.");
            request.setAttribute("messageType", "error");
        }

        request.getRequestDispatcher("teacher/leaveRequestForm.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
       
        Account loggedInAccount = (Account) session.getAttribute("account");
        int requesterId = loggedInAccount.getAccountID(); 

        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String reason = request.getParameter("reason"); 
        String leaveTypeIdStr = request.getParameter("leaveTypeId"); 

        LocalDate startDate = null;
        LocalDate endDate = null;
        String errorMessage = null;

        try {
            startDate = LocalDate.parse(startDateStr);
            endDate = LocalDate.parse(endDateStr);

            LocalDate today = LocalDate.now();

            if (startDate.isBefore(today)) {
                errorMessage = "Ngày bắt đầu không được trong quá khứ.";
            } else if (endDate.isBefore(startDate)) {
                errorMessage = "Ngày kết thúc không được trước ngày bắt đầu.";
            }

        } catch (DateTimeParseException e) {
            errorMessage = "Định dạng ngày không hợp lệ. Vui lòng nhập ngày theo định dạng YYYY-MM-DD.";
            System.err.println("Lỗi parse ngày: " + e.getMessage());
        } catch (NumberFormatException e) {
             errorMessage = "Loại nghỉ phép không hợp lệ.";
             System.err.println("Lỗi parse leaveTypeId: " + e.getMessage());
        }
     
        if (errorMessage != null) {
            request.setAttribute("message", errorMessage);
            request.setAttribute("messageType", "error");
            request.setAttribute("startDate", startDateStr);
            request.setAttribute("endDate", endDateStr);
            request.setAttribute("reason", reason);
            request.setAttribute("leaveTypeId", leaveTypeIdStr);
            try {
                List<LeaveType> leaveTypes = leaveTypeDAO.getAllLeaveTypes();
                request.setAttribute("leaveTypes", leaveTypes);
            } catch (Exception e) {
                System.err.println("Lỗi khi lấy danh sách loại nghỉ phép sau lỗi validation: " + e.getMessage());
            }

            request.getRequestDispatcher("teacher/leaveRequestForm.jsp").forward(request, response); 
            return; 
        }

        int leaveTypeId = Integer.parseInt(leaveTypeIdStr); 

        
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
        double totalDays = (double) daysBetween;

        LeaveRequest newRequest = new LeaveRequest(
                requesterId,
                leaveTypeId,
                startDate,
                endDate,
                totalDays,
                reason
        );

        boolean success = leaveRequestDAO.addLeaveRequest(newRequest);

        if (success) {
            
            response.sendRedirect("leavehistory?message=success"); 
        } else {
           
            request.setAttribute("message", "Đã xảy ra lỗi khi gửi đơn xin nghỉ phép. Vui lòng thử lại.");
            request.setAttribute("messageType", "error");
           
            request.setAttribute("startDate", startDateStr);
            request.setAttribute("endDate", endDateStr);
            request.setAttribute("reason", reason);
            request.setAttribute("leaveTypeId", leaveTypeIdStr);

            
            try {
                List<LeaveType> leaveTypes = leaveTypeDAO.getAllLeaveTypes();
                request.setAttribute("leaveTypes", leaveTypes);
            } catch (Exception e) {
                System.err.println("Lỗi khi lấy danh sách loại nghỉ phép sau lỗi DB: " + e.getMessage());
            }
            request.getRequestDispatcher("teacher/leaveRequestForm.jsp").forward(request, response);
        }
    }
}