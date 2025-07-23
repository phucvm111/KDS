/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.parent;

import dal.FeedbackTeacherDAO;
import dal.KindergartnerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Account;
import model.FeedbackTeacher;

/**
 *
 * @author Vu Tuan Hai <HE176383>
 */
@WebServlet(name = "FeedbackTeacherServlet", urlPatterns = {"/FeedbackTeacherServlet"})
public class FeedbackTeacherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private KindergartnerDAO kindergartnerDAO = new KindergartnerDAO();
    private FeedbackTeacherDAO feedbackTeacherDAO = new FeedbackTeacherDAO();

    // Hiển thị danh sách lớp của phụ huynh và thông tin giáo viên
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");  // Ensure login page exists
            return;
        }

        int parentId = account.getAccountID();

        // Lấy danh sách lớp học
        List<String> classList = kindergartnerDAO.getClassesByParentId(parentId);
        request.setAttribute("classList", classList);

        // Lấy thông tin giáo viên cho từng lớp
        List<Account> teachers = new ArrayList<>();
        for (String className : classList) {
            Account teacher = kindergartnerDAO.getTeacherByClassName(className);
            teachers.add(teacher);
        }
        request.setAttribute("teachers", teachers);

        // Lấy feedback của các giáo viên
        List<FeedbackTeacher> allFeedback = feedbackTeacherDAO.getFeedbackByParent(parentId);
        request.setAttribute("allFeedback", allFeedback);

        // Chuyển đến JSP để hiển thị
        request.getRequestDispatcher("/parent/feedback.jsp").forward(request, response);
    }

    // Xử lý thêm feedback cho giáo viên
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        int parentId = Integer.parseInt(request.getParameter("parentId"));
        String fbContent = request.getParameter("fbContent");
        float rating = Float.parseFloat(request.getParameter("rating"));
        Date fbDate = new Date();  // Ngày hiện tại

        FeedbackTeacher feedback = new FeedbackTeacher(0, parentId, teacherId, fbContent, rating, fbDate);
        boolean success = feedbackTeacherDAO.addFeedback(feedback);

        // Hiển thị kết quả
        if (success) {
            request.setAttribute("message", "Feedback submitted successfully!");
            response.sendRedirect("FeedbackTeacherServlet?parentId=" + parentId);
        } else {
            request.setAttribute("error", "Failed to add feedback.");
            request.getRequestDispatcher("/parent/feedback.jsp").forward(request, response);
        }
    }

    // Xử lý xóa feedback
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        boolean success = feedbackTeacherDAO.deleteFeedback(feedbackId);

        if (success) {
            request.setAttribute("message", "Feedback deleted successfully!");
        } else {
            request.setAttribute("error", "Failed to delete feedback.");
        }

        response.sendRedirect("FeedbackTeacherServlet");
    }

    // Xử lý sửa feedback
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        String newContent = request.getParameter("fbContent");
        float newRating = Float.parseFloat(request.getParameter("rating"));

        FeedbackTeacher feedback = feedbackTeacherDAO.getFeedbackById(feedbackId);
        feedback.setFbContent(newContent);
        feedback.setRating(newRating);

        boolean success = feedbackTeacherDAO.updateFeedback(feedback);

        if (success) {
            request.setAttribute("message", "Feedback updated successfully!");
        } else {
            request.setAttribute("error", "Failed to update feedback.");
        }

        response.sendRedirect("FeedbackTeacherServlet");
    }
}
