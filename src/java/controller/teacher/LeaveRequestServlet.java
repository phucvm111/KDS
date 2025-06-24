/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.teacher;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LeaveRequestServlet", urlPatterns = {"/leaverequest"})
public class LeaveRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          request.getRequestDispatcher("teacher/leaveRequestForm.jsp").forward(request, response);
     }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Đặt encoding cho request
        response.setCharacterEncoding("UTF-8"); // Đặt encoding cho response

        String teacherName = request.getParameter("teacherName");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String leaveReason = request.getParameter("leaveReason");
        String leaveType = request.getParameter("leaveType");

       
        boolean success = true; // Giả sử xử lý thành công

        if (success) {
            request.setAttribute("message", "Đơn xin nghỉ phép của bạn đã được gửi thành công!");
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("message", "Đã xảy ra lỗi khi gửi đơn xin nghỉ phép. Vui lòng thử lại.");
            request.setAttribute("messageType", "error");
        }

        // Chuyển hướng lại trang JSP
        request.getRequestDispatcher("teacher/leaveRequestForm.jsp").forward(request, response);
    }
}