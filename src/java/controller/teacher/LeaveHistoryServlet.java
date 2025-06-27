package controller.teacher;

import dal.LeaveRequestDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LeaveRequest;
import model.Account; 

@WebServlet(name = "LeaveHistoryServlet", urlPatterns = {"/leavehistory"})
public class LeaveHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LeaveRequestDAO leaveRequestDAO;

    public void init() {
        leaveRequestDAO = new LeaveRequestDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false); 

       
        if (session == null || session.getAttribute("account") == null) {
            
            response.sendRedirect(request.getContextPath() + "/login.jsp"); 
            return;
        }

        Account loggedInAccount = (Account) session.getAttribute("account");
        int loggedInUserId = loggedInAccount.getAccountID();

        List<LeaveRequest> leaveRequests = leaveRequestDAO.getLeaveRequestsByRequesterId(loggedInUserId);

        request.setAttribute("leaveRequests", leaveRequests);
        request.getRequestDispatcher("teacher/leaveHistory.jsp").forward(request, response);
    }
}