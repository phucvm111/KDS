/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.teacher;

import dal.FeedbackTeacherDAO;
import dal.AccountDAO;
import model.FeedbackTeacher;
import model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ViewTeacherFeedbackServlet", urlPatterns = {"/ViewTeacherFeedbackServlet"})
public class ViewTeacherFeedbackServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ViewTeacherFeedbackServlet.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy teacherId từ session
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            // Nếu chưa đăng nhập, chuyển hướng người dùng đến trang login
            response.sendRedirect("login.jsp"); // hoặc login servlet URL
            return;
        }

        int teacherId = acc.getAccountID();  // Lấy teacherId từ account trong session
        List<FeedbackTeacher> feedbackList = null;

        // Xử lý lấy feedback của giáo viên
        try {
            FeedbackTeacherDAO feedbackDAO = new FeedbackTeacherDAO();
            feedbackList = feedbackDAO.getFeedbackByTeacherId(teacherId);

            if (feedbackList.isEmpty()) {
                throw new Exception("No feedback found for the teacher.");
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error getting feedback for teacherId: " + teacherId, e);
            request.setAttribute("errorMessage", "An error occurred while retrieving the feedback. Please try again later.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;  // Dừng việc xử lý tiếp
        }

        // Lấy tên phụ huynh từ parentId
        AccountDAO accountDAO = new AccountDAO();
        try {
            for (FeedbackTeacher feedback : feedbackList) {
                Account parentAccount = accountDAO.getAccountByID(feedback.getParentId());

                if (parentAccount == null) {
                    throw new Exception("Parent account not found for parentId: " + feedback.getParentId());
                }

                feedback.setFbContent(parentAccount.getFirstName() + " " + parentAccount.getLastName() + ": " + feedback.getFbContent());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error getting parent information", e);
            request.setAttribute("errorMessage", "An error occurred while retrieving parent information. Please try again later.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // Chuyển feedbackList tới JSP để hiển thị
        request.setAttribute("feedbackList", feedbackList);
        request.getRequestDispatcher("/teacher/viewFeedback.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to view teacher feedback";
    }
}
